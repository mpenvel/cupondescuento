package com.mrjeff.cupondescuento.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mrjeff.cupondescuento.controllers.PedidoController;
import com.mrjeff.cupondescuento.dto.ProductoDTO;
import com.mrjeff.cupondescuento.exceptions.CuponDescuentoException;
import com.mrjeff.cupondescuento.persistence.repositories.CuponDescuentoRepository;
import com.mrjeff.cupondescuento.utils.Constantes;

@Service
public class PedidoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private CuponDescuentoRepository cuponDescuentoRepository;

	public double aplicarCuponDescuento(String codigoDescuento, List<Optional<ProductoDTO>> productosSeleccionados)
			throws CuponDescuentoException {
		LOGGER.info("Entrada al servicio de aplicar código del cupón");

		if (codigoDescuento == null || codigoDescuento.isEmpty()) {
			LOGGER.info(Constantes.CODIGO_DESCUENTO_REQUEST_INVALIDA_MENSAJE);
			throw new CuponDescuentoException(HttpStatus.BAD_REQUEST,
					Constantes.CODIGO_DESCUENTO_REQUEST_INVALIDA_MENSAJE);
		}

		if (productosSeleccionados == null || productosSeleccionados.isEmpty()) {
			LOGGER.info(Constantes.LISTA_PRODUCTOS_REQUEST_INVALIDA_MENSAJE);
			throw new CuponDescuentoException(HttpStatus.BAD_REQUEST,
					Constantes.LISTA_PRODUCTOS_REQUEST_INVALIDA_MENSAJE);
		}

		double totalPedido = 0;
		for (Optional<ProductoDTO> producto : productosSeleccionados) {
			Double precio = producto
					.orElseThrow(
							() -> new CuponDescuentoException(HttpStatus.BAD_REQUEST, Constantes.PRODUCTO_NULO_MENSAJE))
					.getPrecio();

			if (precio != null) {
				totalPedido += precio;
			} else {
				LOGGER.info(Constantes.PRECIO_NULO_MENSAJE);
				throw new CuponDescuentoException(HttpStatus.BAD_REQUEST,
						Constantes.PRECIO_NULO_MENSAJE);
			}
		}

		Double cantidadADescontar = cuponDescuentoRepository.findValorByCodigo(codigoDescuento).orElse(0.0);

		return totalPedido - cantidadADescontar;
	}
}
