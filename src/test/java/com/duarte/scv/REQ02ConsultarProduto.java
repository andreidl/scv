package com.duarte.scv;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duarte.scv.model.Produto;
import com.duarte.scv.repository.ProdutoRepository;

@SpringBootTest
class REQ02ConsultarProduto {

	@Autowired
	ProdutoRepository repository;

	@Test
	void consultaComSucesso() {
		repository.deleteAll();
		Produto produto = new Produto("1", "Galaxy note", "2", "200");
		repository.save(produto);
		Produto ro = repository.findByCodigo("1");
		assertThat(ro).isEqualTo(produto);
	}

	@Test
	void ct02_quando_consulta_titulo_parcial_retorna3() {
		repository.deleteAll();
		Produto produto = new Produto("1", "Galaxy note", "2", "100");
		repository.save(produto);
		Produto produto1 = new Produto("2", "Xiaomi note 2", "4", "200");
		repository.save(produto1);
		Produto produto2 = new Produto("3", "Iphone note 3", "6", "400");
		repository.save(produto2);
		Produto produto3 = new Produto("4", "Galaxy note 9", "8", "600");
		repository.save(produto3);
		List<Produto> ro = repository.findAllByDescricaoIgnoreCaseContaining("Galaxy");
		assertThat(ro.size()).isEqualTo(3);
	}
}
