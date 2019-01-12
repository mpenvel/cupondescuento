package com.mrjeff.cupondescuento.controllers;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping("/cupon")
	public ResponseEntity<Double> aplicarCodigoDescuento(@RequestBody CuponDescuentoDTO cuponDescuentoDTO) throws CuponDescuentoException {
		String codigoCupon = cuponDescuentoDTO.getCodigoCupon();
		List<Optional<ProductoDTO>> productosSeleccionados = cuponDescuentoDTO.getProductosSeleccionados();
		
		double totalPedido = pedidoService.aplicarCuponDescuento(codigoCupon, productosSeleccionados);
		
		return new ResponseEntity<>(totalPedido, HttpStatus.OK);
	}
}
