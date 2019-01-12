package com.mrjeff.cupondescuento.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrjeff.cupondescuento.persistence.repositories.CuponDescuentoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
public class CuponDescuentoRepositoryTest {
 
    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;
	
	@Test
	public void whenFindByName_thenReturnEmployee() {
	 
	    // when
	    Optional<Double> valorEncontrado = cuponDescuentoRepository.findValorByCodigo("TEST");
	 
	    // then
	    assertEquals(10, valorEncontrado.get(), 0.001);
	}
}
