package com.mrjeff.cupondescuento.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mrjeff.cupondescuento.persistence.entities.CuponDescuentoEntity;
import com.mrjeff.cupondescuento.persistence.repositories.CuponDescuentoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
public class CuponDescuentoRepositoryTest {
 
    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;
	
	@Before
	public void setUp() throws Exception {
		CuponDescuentoEntity cuponDescuento = new CuponDescuentoEntity();
		cuponDescuento.setCodigo("TEST");
		cuponDescuento.setValor(10.0);
		cuponDescuentoRepository.save(cuponDescuento);
	}
	
	@Test
	public void whenFindByCodigoReturnValor() {
	 
	    //Act
	    Optional<Double> valorEncontrado = cuponDescuentoRepository.findValorByCodigo("TEST");
	 
	    //Assert
	    assertEquals(10, valorEncontrado.get(), 0.001);
	}
}
