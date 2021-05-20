package com.dinheiro;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dinheiro.model.Cliente;
import com.dinheiro.model.Moeda;
import com.dinheiro.model.MoedaOrigemDestino;
import com.dinheiro.model.Operacao;
import com.dinheiro.model.TaxaCambio;
import com.dinheiro.repository.MoedaRepository;
import com.dinheiro.repository.OperacaoRepository;
import com.dinheiro.repository.TaxaCambioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperacaoRepositoryTest {

	@Autowired
	private OperacaoRepository operacaoRepository;
	@Autowired
	private TaxaCambioRepository taxaCambioRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	
	@Test
	public void createShoulPersistData() {
		Moeda origem = new Moeda("Real");
		Moeda destino = new Moeda("Dolar");
		moedaRepository.save(origem);
		moedaRepository.save(destino);
		MoedaOrigemDestino origemDestino = new MoedaOrigemDestino(origem, destino);
		TaxaCambio taxaCambio = new TaxaCambio(origemDestino, 0.19);
		taxaCambioRepository.save(taxaCambio);
		Operacao operacao = new Operacao(new Cliente("Eduardo"), taxaCambio, LocalDate.now(), 100, 19, 1.9);
		operacaoRepository.save(operacao);
		Operacao operacao2 = new Operacao();
		operacao2 = operacaoRepository.findById(operacao.getId_operacao()).get();
		Assertions.assertThat(operacao2.getId_operacao()).isNotNull();
		Assertions.assertThat(operacao2.getCliente().getNome()).isEqualTo("Eduardo");
		Assertions.assertThat(operacao2.getTaxaCambio()).isEqualTo(taxaCambio);
		Assertions.assertThat(operacao2.getDataOperacao()).isEqualTo(LocalDate.now());
		Assertions.assertThat(operacao2.getValorOriginal()).isEqualTo(100);
		Assertions.assertThat(operacao2.getValorConvertido()).isEqualTo(19);
		Assertions.assertThat(operacao2.getTaxaCobrada()).isEqualTo(1.9);
	}
}
