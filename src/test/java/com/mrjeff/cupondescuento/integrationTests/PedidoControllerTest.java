package com.mrjeff.cupondescuento.integrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mrjeff.cupondescuento.CuponDescuentoApplication;
import com.mrjeff.cupondescuento.dto.CuponDescuentoDTO;
import com.mrjeff.cupondescuento.dto.ProductoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CuponDescuentoApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource(
//  locations = "classpath:application-tests.properties")
public class PedidoControllerTest {
	@Autowired
	private MockMvc mvc;

	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());
	}

	@Test
	public void whenPostCuponValidoGet200() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("TEST");

		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(Optional.of(productoDTO));
		productoDTO = new ProductoDTO("Traje", 10.0);
		productosSeleccionados.add(Optional.of(productoDTO));

		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		// @formatter:off
		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("3.0"));
		// @formatter:on
	}

	@Test
	public void whenPostCuponInvalidoGet200() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("TEST2");

		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(Optional.of(productoDTO));
		productoDTO = new ProductoDTO("Traje", 10.0);
		productosSeleccionados.add(Optional.of(productoDTO));

		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("13.0"));
	}

	@Test
	public void whenPostCuponNuloGet400() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();

		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(Optional.of(productoDTO));
		productoDTO = new ProductoDTO("Traje", 10.0);
		productosSeleccionados.add(Optional.of(productoDTO));

		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenPostCuponVacioGet400() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("");

		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", 3.0);
		productosSeleccionados.add(Optional.of(productoDTO));
		productoDTO = new ProductoDTO("Traje", 10.0);
		productosSeleccionados.add(Optional.of(productoDTO));

		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenPostListaProductoVacioGet400() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("TEST");
		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();

		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenPostListaProductoNuloGet400() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("");
		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = null;
		productosSeleccionados.add(Optional.ofNullable(productoDTO));
		productoDTO = new ProductoDTO("Traje", 12.0);
		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenPostPrecioNuloGet400() throws Exception {
		CuponDescuentoDTO cuponDescuento = new CuponDescuentoDTO();
		cuponDescuento.setCodigoCupon("");
		List<Optional<ProductoDTO>> productosSeleccionados = new ArrayList<>();
		ProductoDTO productoDTO = new ProductoDTO("Camisa", null);
		productosSeleccionados.add(Optional.of(productoDTO));
		productoDTO = new ProductoDTO("Traje", 10.0);
		productosSeleccionados.add(Optional.of(productoDTO));
		cuponDescuento.setProductosSeleccionados(productosSeleccionados);

		mvc.perform(post("/pedidos/cupon").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cuponDescuento))).andDo(print())
				.andExpect(status().isBadRequest());
	}
}
