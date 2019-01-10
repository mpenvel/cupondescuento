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
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenCodigoCuponRecibidoCorrecto() throws CuponDescuentoException{
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
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenCodigoCuponRecibidoNoCorrecto() throws CuponDescuentoException{
		// Arrange
		cuponDescuento = "OTRO";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		productosSeleccionados.add(productoDTO);
		
		// Act
		double totalPedido = pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);

		// Assert
		assertEquals(15, totalPedido, 0.001);
	}
	
	/**
	 * Comprueba que el código del cupón nulo da error de petición incorrecta
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenCodigoCuponRecibidoNulo() throws CuponDescuentoException{
		// Arrange	
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.CODIGO_DESCUENTO_REQUEST_INVALIDA_MENSAJE);
		
		cuponDescuento = null;
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
	}
	
	/**
	 * Comprueba que el código del cupón vacío da error de petición incorrecta
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenCodigoCuponRecibidoVacio() throws CuponDescuentoException{
		// Arrange
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.CODIGO_DESCUENTO_REQUEST_INVALIDA_MENSAJE);
		
		cuponDescuento = "";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
	}
	
	/**
	* Comprueba que si la lista de producto seleccionados es nulo, da error de petición incorrecta
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenProductosSeleccionadosNulo() throws CuponDescuentoException{
		// Arrange
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.LISTA_PRODUCTOS_REQUEST_INVALIDA_MENSAJE);
		
		cuponDescuento = "TEST";
		productosSeleccionados = null;
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
	}
	
	/**
	*  Comprueba que si la lista de producto seleccionados es vacío, da error de petición incorrecta
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenProductosSeleccionadosVacio() throws CuponDescuentoException{
		// Arrange
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.LISTA_PRODUCTOS_REQUEST_INVALIDA_MENSAJE);
		
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
	}
	
	/**
	* Comprueba que si alguno de los productos seleccionados es nulo, de error de petición incorrecta
	 * @throws CuponDescuentoException 
	*/
	@Test
	public void whenProductoSeleccionadosNulo() throws CuponDescuentoException{
		// Arrange
		thrown.expect(CuponDescuentoException.class);
		thrown.expectMessage(Constantes.PRODUCTO_NULO_MENSAJE);
		
		cuponDescuento = "TEST";
		productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = null;
		productosSeleccionados.add(productoDTO);
		productoDTO = new ProductoDTO("Traje", 12.0);
		
		// Act
		pedidoService.aplicarCuponDescuento(cuponDescuento, productosSeleccionados);
	}
	

}

