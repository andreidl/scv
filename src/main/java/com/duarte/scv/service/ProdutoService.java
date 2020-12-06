package com.duarte.scv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duarte.scv.model.Produto;
import com.duarte.scv.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public void save(Produto p) {
		repository.save(p);
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
}
