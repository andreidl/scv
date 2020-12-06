package com.duarte.scv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duarte.scv.model.Produto;
import com.duarte.scv.repository.ProdutoRepository;

@SpringBootTest
class REQ01CadastrarProduto {

	@Autowired
    private ProdutoRepository repository;

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void produtoCadastrado() {
        repository.deleteAll();

        Produto produto = new Produto("1", "Galaxy note", "2", "15");

        repository.save(produto);

        assertEquals(1, repository.count());
    }

    @Test
    void dadosEntradaValidos() {
    	Produto produto = new Produto("1", "Galaxy note", "2", "15");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void descricaoEmBranco() {
    	Produto produto = new Produto("1", "", "2", "15");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

        assertEquals(1, violations.size());
        assertEquals("Nome não pode ficar em branco", violations.iterator().next().getMessage());
    }

    @Test
    void produtoJaCadastrado() {
    	Produto produto = new Produto("1", "Galaxy note", "2", "15");

        repository.deleteAll();

        repository.save(produto);
        repository.save(produto);

        assertEquals(1, repository.count());
    }

}
