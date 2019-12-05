package com.mitocode.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mitocode.model.DetalleVenta;
import com.mitocode.model.Producto;
import com.mitocode.model.Venta;

@ManagedBean
@ViewScoped
public class VentaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Venta venta;
	private Producto producto;
	private int cantidad;
	private List<DetalleVenta> lista = new ArrayList<>(); 
	
	public List<DetalleVenta> getLista() {
		return lista;
	}

	public void setLista(List<DetalleVenta> lista) {
		this.lista = lista;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public void agregar() {
		
		DetalleVenta det = new DetalleVenta();
		det.setCantidad(cantidad);
		det.setProducto(producto);
		this.lista.add(det);
	}

}
