package com.mrjeff.cupondescuento.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mrjeff.cupondescuento.dto.ProductoDTO;
import com.mrjeff.cupondescuento.exceptions.CuponDescuentoException;
import com.mrjeff.cupondescuento.utils.Constantes;

@Service
public class PedidoService {

	public double aplicarCuponDescuento(String cuponDescuento, List<ProductoDTO> productosSeleccionados) throws CuponDescuentoException {
		
		if(cuponDescuento == null || cuponDescuento.isEmpty()) {
			throw new CuponDescuentoException(HttpStatus.BAD_REQUEST, Constantes.CODIGO_DESCUENTO_REQUEST_INVALIDA_MENSAJE);
		}
		
		if(productosSeleccionados == null || productosSeleccionados.isEmpty()) {
			throw new CuponDescuentoException(HttpStatus.BAD_REQUEST, Constantes.LISTA_PRODUCTOS_REQUEST_INVALIDA_MENSAJE);
		}
		
		double totalPedido = 0;
		for(ProductoDTO producto : productosSeleccionados) {
			if(producto != null) {
				totalPedido += producto.getPrecio();
			} else {
				throw new CuponDescuentoException(HttpStatus.BAD_REQUEST, Constantes.PRODUCTO_NULO_MENSAJE);
			}
			
		}
		
		double cantidadADescontar = 0;
		if(cuponDescuento.equals("TEST")) {
			cantidadADescontar = 10;
		}
		
		return totalPedido - cantidadADescontar;
	}
}
