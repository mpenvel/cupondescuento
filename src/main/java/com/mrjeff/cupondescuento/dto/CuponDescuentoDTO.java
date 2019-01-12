package com.mrjeff.cupondescuento.dto;

import java.util.List;
import java.util.Optional;

public class CuponDescuentoDTO {
	private String codigoCupon;
	private List<Optional<ProductoDTO>> productosSeleccionados;

	public String getCodigoCupon() {
		return codigoCupon;
	}

	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	public List<Optional<ProductoDTO>> getProductosSeleccionados() {
		return productosSeleccionados;
	}

	public void setProductosSeleccionados(List<Optional<ProductoDTO>> productosSeleccionados) {
		this.productosSeleccionados = productosSeleccionados;
	}

}
