package com.mrjeff.cupondescuento.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrjeff.cupondescuento.dto.CuponDescuentoDTO;
import com.mrjeff.cupondescuento.dto.ProductoDTO;
import com.mrjeff.cupondescuento.exceptions.CuponDescuentoException;
import com.mrjeff.cupondescuento.services.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/cupon")
	@ApiOperation(value = "Aplicar código descuento", notes = "Aplica un código de descuento a una lista de productos seleccionados.")
	public ResponseEntity<Double> aplicarCodigoDescuento(@RequestBody CuponDescuentoDTO cuponDescuentoDTO) throws CuponDescuentoException {
		LOGGER.info("Petición de aplicar código de descuento recibida");
		
		String codigoCupon = cuponDescuentoDTO.getCodigoCupon();
		List<Optional<ProductoDTO>> productosSeleccionados = cuponDescuentoDTO.getProductosSeleccionados();
		
		double totalPedido = pedidoService.aplicarCuponDescuento(codigoCupon, productosSeleccionados);
		
		LOGGER.info("Respuesta de aplicar código de descuento enviada");
		
		return new ResponseEntity<>(totalPedido, HttpStatus.OK);
	}
}
