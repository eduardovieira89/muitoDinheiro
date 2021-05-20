package com.dinheiro.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dinheiro.model.Moeda;
import com.dinheiro.model.MoedaOrigemDestino;
import com.dinheiro.model.TaxaCambio;
import com.dinheiro.repository.MoedaRepository;
import com.dinheiro.repository.TaxaCambioRepository;

@Controller
@RequestMapping(value="/moeda")
public class MoedaController {
	
	@Autowired
	private MoedaRepository moedaRepository;
	
	@Autowired
	private TaxaCambioRepository taxaCambioRepository;
	
	
	@GetMapping
	public ModelAndView listarMoedas() {
		ModelAndView mv = new ModelAndView("moeda/listarMoedas");
		Iterable<Moeda> moedas = moedaRepository.findAll();
		mv.addObject("moedas", moedas);
		
		return mv;
	}
	
	@GetMapping(value="/cadastro")
	public String form() {
		return "moeda/cadastro";
	}
	
	@GetMapping(value="/{id}")
	public ModelAndView taxaCambio(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("moeda/taxaDeCambio");
		Moeda moedaSelec = moedaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+id+" não encontrada" ));
		Iterable<Moeda> destinos = moedaRepository.findAll();
		long idMoedaOrigem = 0;
		long idMoedaDestino = 0;
		Double taxa = 0.0;
		
		
		mv.addObject("origem", moedaSelec);
		mv.addObject("destinos", destinos);
		mv.addObject("idMoedaDestino", idMoedaDestino);
		mv.addObject("idMoedaOrigem", idMoedaOrigem);
		mv.addObject("taxa", taxa);
		return mv;
	}
	
	@PostMapping(value="/cadastro")
	public String form(@Valid Moeda moeda, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/moeda/cadastro";
		}
		
		moedaRepository.save(moeda);
		attributes.addFlashAttribute("mensagem", "Moeda cadastrada com sucesso!");
		return "redirect:/moeda/cadastro";
	}
	
	@PostMapping(value="/{id}")
	public String cadastroTaxaDeCambio(@PathVariable("id") long idMoedaOrigem, long idMoedaDestino, double taxa) {
		
		Moeda moedaOrigem = moedaRepository.findById(idMoedaOrigem).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+idMoedaOrigem+" não encontrada" ));
		Moeda moedaDestino = moedaRepository.findById(idMoedaDestino).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+idMoedaDestino+" não encontrada" ));
		
		MoedaOrigemDestino origemDestino = new MoedaOrigemDestino(moedaOrigem, moedaDestino);
		
		TaxaCambio taxaDeCambio = new TaxaCambio(origemDestino, taxa);
		taxaCambioRepository.save(taxaDeCambio);
		
		System.out.println("Taxa de cambio: " + taxa);
		System.out.println("Destino: " + idMoedaDestino);
		System.out.println("Origem: " + idMoedaOrigem);
		return "redirect:/moeda/"+idMoedaOrigem;
	}
	
	@RequestMapping("/deletarMoeda")
	public String deletarMoeda(long id) {
		Moeda delMoeda = moedaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+id+" não encontrada" ));
		moedaRepository.delete(delMoeda);
		return "redirect:/moeda";
	}
	
}
