package com.dinheiro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.dinheiro.model.Moeda;
import com.dinheiro.repository.MoedaRepository;

//@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = MuitoDinheiroApplication.class)
public class MoedaRepositoryTest {
	
	@Autowired
	private MoedaRepository moedaRepository;
	//@Rule
	//public ExpectedException trown = ExpectedException.none();
	
	@Test
	public void createShoulPersistData() {
		Moeda moeda = new Moeda("Real","Brasil");
		moedaRepository.save(moeda);
		Assertions.assertThat(moeda.getId_moeda()).isNotNull();
		Assertions.assertThat(moeda.getNome()).isEqualTo("Real");
		Assertions.assertThat(moeda.getPais()).isEqualTo("Brasil");
	}
	
	@Test
	public void deletShouldRemoveData() {
		Moeda moeda = new Moeda("Real","Brasil");
		moedaRepository.save(moeda);
		moedaRepository.delete(moeda);
		
		Assertions.assertThat(moedaRepository.findById(moeda.getId_moeda())).isEmpty();
	}

	@Test
	public void updateShouldChangeAndPersistData() {
		Moeda moeda = new Moeda("Real","Brasil");
		moedaRepository.save(moeda);
		moeda.setNome("Pila");
		moeda.setPais("Iguaçu");
		moedaRepository.save(moeda);
		
		Moeda moeda2 = new Moeda();
		moeda2 = moedaRepository.findById(moeda.getId_moeda()).get();
		Assertions.assertThat(moeda2.getNome()).isEqualTo("Pila");
		Assertions.assertThat(moeda2.getPais()).isEqualTo("Iguaçu");
	}

	
}

