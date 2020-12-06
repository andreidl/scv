package com.duarte.scv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.duarte.scv.model.Produto;
import com.duarte.scv.service.ProdutoService;

@Controller
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("cadastrar")
	public String mostrarFormCadastrar(Produto produto, Model model) {
        model.addAttribute("produto'", new Produto());
        return "cadastrarProduto";
    }

    @PostMapping("save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("cadastrarProduto");

        try {
            service.save(produto);
            modelAndView.addObject("produtos", service.findAll());
            modelAndView.addObject("message", "Produto cadastrado!");
        } catch (Exception e) { 
            if (result.hasErrors()) {

                modelAndView.addObject("message","");
            } else {
                modelAndView.addObject("message", "Produto já cadastrado.");
            }
        }

        return modelAndView;
    }
    
    
}
