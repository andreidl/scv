package com.duarte.scv;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;

import com.duarte.scv.model.Produto;

@SpringBootTest
@AutoConfigureMockMvc
class REQ01CadastrarProdutoMAVTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void ct01_quando_seleciona_cadastrar_produto_retorna_http_200() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos/cadastrar"));
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void ct02_quando_seleciona_cadastrar_retorna_view() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos/cadastrar"));
		ViewResultMatchers view = MockMvcResultMatchers.view();

		resultActions.andExpect(view.name(Matchers.is("cadastrarProduto")));
	}

	@Test
	public void ct03_quando_descricao_branco_retorna_size() throws Exception {
		mockMvc.perform(post("/produtos/save")
				.param("codigo", "1").param("decricao", "").param("custo", "200").param("quantidade", "7"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.view().name("cadastrarProduto"))
				.andExpect(MockMvcResultMatchers.model().attribute("produto", Matchers.any(Produto.class)))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("produto", "descricao"))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("produto", "descricao", "NotNull"));
	}
}
