package com.mitocode.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mitocode.model.Persona;

public class PersonaDAO extends DAO{


	public void registrar(Persona persona) throws Exception {

		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("INSERT INTO mitocode.persona (nombre, sexo) VALUES (?, ?)");
			st.setString(1, persona.getNombre());
			st.setString(2, persona.getSexo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.cerrar();
		}

	}

	public List<Persona> listar() throws Exception {

		List<Persona> lista;
		ResultSet rs;

		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM mitocode.persona");
			rs = st.executeQuery();
			lista = new ArrayList<>();

			while (rs.next()) {
				Persona per = new Persona();
				per.setCodigo(rs.getInt("codigo"));
				per.setNombre(rs.getString("nombre"));
				per.setSexo(rs.getString("sexo"));

				lista.add(per);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.cerrar();
		}

		return lista;
	}
	
	public Persona leerID (Persona per) throws Exception {
		
		Persona persona = null;
		ResultSet rs;

		
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM mitocode.persona WHERE codigo = ?");
			st.setInt(1, per.getCodigo());
			rs = st.executeQuery();
			
			while (rs.next()) {
				persona = new Persona();
				persona.setCodigo(rs.getInt("codigo"));
				persona.setNombre(rs.getString("nombre"));
				persona.setSexo(rs.getString("sexo"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			this.cerrar();
		}
		return persona;
	}
	
    public void modificar(Persona per) throws Exception{
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE mitocode.persona SET nombre = ?, sexo = ? WHERE codigo = ?");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    public void eliminar(Persona per) throws Exception{
        try {
            this.conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM mitocode.persona WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

}
