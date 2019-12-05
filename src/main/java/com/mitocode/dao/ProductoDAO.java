package com.mitocode.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mitocode.model.Producto;

public class ProductoDAO extends DAO{
    
    public void registrar(Producto pro) throws Exception{
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO mitocode.producto (nombre, precio) VALUES(?,?)");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    public List<Producto> listar() throws Exception{
        List<Producto> lista;
        ResultSet rs;
        
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, precio, nombre FROM mitocode.producto");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while(rs.next()){
                Producto pro = new Producto();
                pro.setCodigo(rs.getInt("codigo"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setNombre(rs.getString("nombre"));
                lista.add(pro);
            }
        } catch (Exception e) {
            throw e;
        } finally{
            this.cerrar();
        }
        return lista;
    }
    
    public Producto leerID(Producto pro) throws Exception{
        Producto prod = null;
        ResultSet rs;
        
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio FROM mitocode.producto WHERE codigo = ?");
            st.setInt(1, pro.getCodigo());
            rs = st.executeQuery();
            while(rs.next()){
                prod = new Producto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally{
            this.cerrar();
        }
        return prod;
    }
    
    public void modificar(Producto pro) throws Exception{
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE mitocode.producto SET nombre = ?, precio = ? WHERE codigo = ?");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.setInt(3, pro.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    public void eliminar(Producto pro) throws Exception{
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM mitocode.producto WHERE codigo = ?");
            st.setInt(1, pro.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
}	