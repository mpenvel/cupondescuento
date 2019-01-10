package com.mrjeff.cupondescuento.dto;

public class ProductoDTO {
	private String codigo;
	private Double precio;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(String codigo, Double precio) {
		super();
		this.codigo = codigo;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
