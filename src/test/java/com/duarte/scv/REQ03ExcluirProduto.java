package com.duarte.scv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duarte.scv.model.Produto;
import com.duarte.scv.repository.ProdutoRepository;

@SpringBootTest
class REQ03ExcluirProduto {

	@Autowired
	private ProdutoRepository repository;

	@Test
	void excluirProdutoComSucesso() {
		repository.deleteAll();
		Produto produto = new Produto("1", "Galaxy note", "2", "15");
		repository.save(produto);
		Produto ro = repository.findByCodigo("1");
		repository.deleteById(ro.getId());
		assertThat(repository.findByCodigo("1")).isEqualTo(null);
	}
}
