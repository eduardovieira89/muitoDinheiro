package com.dinheiro.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dinheiro.model.Cliente;
import com.dinheiro.model.Moeda;
import com.dinheiro.model.MoedaOrigemDestino;
import com.dinheiro.model.Operacao;
import com.dinheiro.model.TaxaCambio;
import com.dinheiro.repository.ClienteRepository;
import com.dinheiro.repository.MoedaRepository;
import com.dinheiro.repository.OperacaoRepository;
import com.dinheiro.repository.TaxaCambioRepository;

@Controller
@RequestMapping(value="/operacao")
public class OperacaoController {

	@Autowired
	private OperacaoRepository operacaoRepository;
	@Autowired
	private TaxaCambioRepository taxaCambioRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ModelAndView novaOperacao(Operacao operacao) {
		ModelAndView mv = new ModelAndView("operacao/operacao");
		Iterable<Moeda> moedas = moedaRepository.findAll();
		Iterable<Moeda> moedaDestino = null;
		mv.addObject("moedas", moedas);
		mv.addObject("moedaDestino", moedaDestino);
		return mv;
	}
	
	
	@GetMapping(value="/relatorio")
	public ModelAndView relatorio(Operacao operacao) {
		ModelAndView mv = new ModelAndView("operacao/relatorio");
		
		Iterable<Operacao> operacoes = operacaoRepository.findByDataOperacao(LocalDate.now());
		Double somaValorConvertido = operacaoRepository.somaValorConvertidoByDataOperacao(LocalDate.now());
		Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradabyDataOperacao(LocalDate.now());
		
		mv.addObject("operacoes", operacoes);
		mv.addObject("somaValorConvertido", somaValorConvertido);
		mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
		return mv;
	}
	
	@ResponseBody
	@PostMapping(value="/relatorio")
	public ModelAndView relatorioComFiltro(Operacao operacao, @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) {
		ModelAndView mv = new ModelAndView("operacao/relatorio");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		//Se tem nome do cliente
		if(!operacao.getCliente().getNome().isBlank()) {
			if(!dataInicial.isBlank() && !dataFinal.isBlank()) {
				//Cliente, dataInicial e dataFinal estão preenchidos.
				LocalDate dtInicial = LocalDate.parse(dataInicial, formatter);
				LocalDate dtFinal = LocalDate.parse(dataFinal, formatter);
				Iterable<Operacao> operacoes = operacaoRepository.findByClienteAndDataOperacaoBetween(operacao.getCliente(), dtInicial, dtFinal);
				Double somaValorConvertido = operacaoRepository.somaValorConvertidoByClienteAndDataOperacaoBetween(operacao.getCliente(), dtInicial, dtFinal);
				Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradaByClienteAndDataOperacaoBetween(operacao.getCliente(), dtInicial, dtFinal);
				
				mv.addObject("somaValorConvertido", somaValorConvertido);
				mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
				mv.addObject("operacoes", operacoes);
				return mv;
				
			}else {
				if(!dataInicial.isBlank()) {
					//Cliente e data inicial estão preenchidos. Pesquisa somente para o dia do campo dataInicial 
					LocalDate dtInicial = LocalDate.parse(dataInicial, formatter);
					Iterable<Operacao> operacoes = operacaoRepository.findByClienteAndDataOperacao(operacao.getCliente(), dtInicial);
					Double somaValorConvertido = operacaoRepository.somaValoConvertidoByClienteAndDataOperacao(operacao.getCliente(), dtInicial);
					Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradaByClienteAndDataOperacao(operacao.getCliente(), dtInicial);
					
					mv.addObject("somaValorConvertido", somaValorConvertido);
					mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
					mv.addObject("operacoes", operacoes);
					return mv;
				}else {
					//Ignora se tem data final preenchida ou não, traz todas as operações do cliente
					Iterable<Operacao> operacoes = operacaoRepository.findByCliente(operacao.getCliente());
					Double somaValorConvertido = operacaoRepository.somaValorConvertidoByCliente(operacao.getCliente());
					Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradaByCliente(operacao.getCliente());
					
					mv.addObject("operacoes", operacoes);
					mv.addObject("somaValorConvertido", somaValorConvertido);
					mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
					
					return mv;
				}
			}
		//se não tem nome preenchido
		}else {
			//Se não tem nome do cliente preenchido e tem data de inicio e fim
			if(!dataInicial.isBlank() && !dataFinal.isBlank()) {
				// dataInicial e dataFinal estão preenchidos.
				LocalDate dtInicial = LocalDate.parse(dataInicial, formatter);
				LocalDate dtFinal = LocalDate.parse(dataFinal, formatter);
				Iterable<Operacao> operacoes = operacaoRepository.findByDataOperacaoBetween(dtInicial, dtFinal);
				Double somaValorConvertido = operacaoRepository.somaValorConvertidoByDataOperacaoBetween(dtInicial, dtFinal);
				Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradaByDataOperacaoBetween(dtInicial, dtFinal);
				
				mv.addObject("somaValorConvertido", somaValorConvertido);
				mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
				mv.addObject("operacoes", operacoes);
				return mv;
			}else {
				if(!dataInicial.isBlank()) {
					//Cliente e data final não estão preenchidos. Pesquisa todas as operações para o dia selecionado 
					LocalDate data = LocalDate.parse(dataInicial, formatter);
					Iterable<Operacao> operacoes = operacaoRepository.findByDataOperacao(data);
					Double somaValorConvertido = operacaoRepository.somaValorConvertidoByDataOperacao(data);
					Double somaTaxaCobrada = operacaoRepository.somaTaxaCobradabyDataOperacao(data);
					
					mv.addObject("somaValorConvertido", somaValorConvertido);
					mv.addObject("somaTaxaCobrada", somaTaxaCobrada);
					mv.addObject("operacoes", operacoes);
					return mv;
				}
			}
		}		
		
		
		return mv;
	}

	
	@PostMapping
	public String cadastraOperacao(@Valid Operacao operacao,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			
			attributes.addFlashAttribute("mensagemRetorno", "Verifique os campos");
			return "redirect:/operacao";
		}
		
		Cliente cliente = clienteRepository.findByNome(operacao.getCliente().getNome());
		if(cliente != null) {
			operacao.setCliente(cliente);
		}
		
		operacao.setTaxaCambio(taxaCambioRepository.findByorigemDestino(operacao.getTaxaCambio().getOrigemDestino()));
		operacao.setDataOperacao(LocalDate.now());
		operacao.setValorConvertido(operacao.getValorOriginal() * operacao.getTaxaCambio().getTaxa());
		operacao.setTaxaCobrada(operacao.getValorConvertido() /100 * 10);
		
		operacaoRepository.save(operacao);
		attributes.addFlashAttribute("op", operacao);
		
		return "redirect:/operacao";
	}
	
	@ResponseBody
	@PostMapping(value="/taxacambio")
	public Double getTaxaCambio( long origem, long destino) {
		
		Moeda mOrigem = moedaRepository.findById(origem).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+origem+" não encontrada" ));
		Moeda mDestino = moedaRepository.findById(destino).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+destino+" não encontrada" ));
		MoedaOrigemDestino origemDestino = new MoedaOrigemDestino(mOrigem, mDestino);
		
		TaxaCambio taxaCambio = taxaCambioRepository.findByorigemDestino(origemDestino);
		
		return taxaCambio.getTaxa();
	}
	
	
	@ResponseBody
	@PostMapping(value="/moedasdestino")
	public Iterable<Moeda> getMoedasDestino(long origem) {
		
		Moeda mOrigem = moedaRepository.findById(origem).orElseThrow(() -> new ResourceNotFoundException("Moeda com id: "+origem+" não encontrada" ));
		Iterable<Moeda> moedaDestino = taxaCambioRepository.buscaMoedaDestinoByMoedaOrigem(mOrigem);
		
		
		
		return moedaDestino;
	}
	
}
