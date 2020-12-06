package com.duarte.scv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String codigo;
	@NotBlank
	private String descricao;
	private String quantidade;
	private String custo;
	
	public Produto() {
	
	}
	
	public Produto(String codigo, String descricao, String quantidade, String custo) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.custo = custo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}
}
