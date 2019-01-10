package com.mrjeff.cupondescuento.unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrjeff.cupondescuento.dto.ProductoDTO;
import com.mrjeff.cupondescuento.exceptions.CuponDescuentoException;
import com.mrjeff.cupondescuento.services.PedidoService;
import com.mrjeff.cupondescuento.utils.Constantes;

@RunWith(SpringRunner.class)
public class PedidoServiceTests {
	
	@InjectMocks
	private PedidoService pedidoService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private String cuponDescuento;
	private List<ProductoDTO> productosSeleccionados;
	@Test
	public void contextLoads() {
		
	}
	
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Comprueba que el código del cupón válido se ha aplicado correctamente
	*/
	@Test
	public void whenCodigoCuponRecibidoCorrecto(){
		// Arrange
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		productosSeleccionados.add(productoDTO);
		
		// Act
		double totalPedido = pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		assertEquals(5, totalPedido, 0.001);
	}
	
	/**
	 * Comprueba que el código del cupón no válido no se aplica
	*/
	@Test
	public void whenCodigoCuponRecibidoNoCorrecto(){
		// Arrange
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		double totalPedido = pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		assertEquals(15, totalPedido, 0.001);
	}
	
	/**
	 * Comprueba que el código del cupón nulo da error de petición incorrecta
	*/
	@Test
	public void whenCodigoCuponRecibidoNulo(){
		// Arrange	
		cuponDescuento = null;
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
		
		//Assert
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.CODIGO_DESCUENTO_NULO_MENSAJE);
	}
	
	/**
	 * Comprueba que el código del cupón vacío da error de petición incorrecta
	*/
	@Test
	public void whenCodigoCuponRecibidoVacio(){
		// Arrange
		cuponDescuento = "";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.CODIGO_DESCUENTO_VACIO_MENSAJE);
	}
	
	/**
	* Comprueba que si la lista de producto seleccionados es nulo, da error de petición incorrecta
	*/
	@Test
	public void whenProductosSeleccionadosNulo(){
		// Arrange
		cuponDescuento = "TEST";
		productosSeleccionados = null;
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.LISTA_PRODUCTOS_NULO_MENSAJE);
	}
	
	/**
	*  Comprueba que si la lista de producto seleccionados es vacío, da error de petición incorrecta
	*/
	@Test
	public void whenProductosSeleccionadosVacio(){
		// Arrange
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.LISTA_PRODUCTOS_NULO_MENSAJE);
	}
	
	/**
	* Comprueba que si alguno de los productos seleccionados es nulo, de error de petición incorrecta
	*/
	@Test
	public void whenProductoSeleccionadosNulo(){
		// Arrange
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = null;
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.PRODUCTO_NULO_MENSAJE);
	}
	

}

