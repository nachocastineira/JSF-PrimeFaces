package com.mitocode.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mitocode.model.DetalleVenta;
import com.mitocode.model.Venta;

public class VentaDAO extends DAO {
	
	public void registrar(Venta venta, List<DetalleVenta> lista) throws Exception {

		try {
			this.conectar();
			this.getCn().setAutoCommit(false);
			
			PreparedStatement st = this.getCn().prepareStatement("INSERT INTO mitocode.venta (fecha, codPersona, monto) VALUES (?, ?, ?)");
			st.setDate(1, (Date) venta.getFecha());
			st.setInt(2, venta.getPersona().getCodigo());
			st.setDouble(3, venta.getMonto());
			st.executeUpdate();
			st.close();
			
			PreparedStatement st2 = this.getCn().prepareStatement("SELECT LAST_INSERT_ID() FROM venta LIMIT 1");
			ResultSet rs;
			
			int codVenta = 0;
			rs = st2.executeQuery();
			while (rs.next()) {
				codVenta = rs.getInt(1);
			}
			rs.close();
			
			for (DetalleVenta det : lista) {
				PreparedStatement st3 = this.getCn().prepareStatement("INSERT INTO detalleventa (codVenta, codProducto, cantidad) VALUES (?, ?, ?)");
				st3.setInt(1, codVenta);
				st3.setInt(2, det.getProducto().getCodigo());
				st3.setInt(3, det.getCantidad());
				st3.executeUpdate();
				st3.close();
			}
			this.getCn().commit();		
			
		} catch (Exception e) {
			this.getCn().rollback();
			throw e;
			
		} finally {
			this.cerrar();
		}

	}
}
