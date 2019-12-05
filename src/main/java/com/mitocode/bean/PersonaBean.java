package com.mitocode.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mitocode.dao.PersonaDAO;
import com.mitocode.model.Persona;

@ManagedBean
@ViewScoped
public class PersonaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Persona persona;
	private List<Persona> lstPersonas;
	private String action;
	
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.limpiar();
		this.action = action;
	}

	public PersonaBean() {
		persona = new Persona();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public List<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

    private boolean isPostBack(){
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }
	
	private void registrar() throws Exception {
		
		PersonaDAO personaDAO;
		
		try {
			personaDAO = new PersonaDAO();
			personaDAO.registrar(persona);
            this.listar("V");
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void listar(String valor) throws Exception {
		
		PersonaDAO personaDAO;
		
		try {
            if(valor.equals('F')){
                if(isPostBack() == false){
                personaDAO = new PersonaDAO();
                lstPersonas = personaDAO.listar();
            }
            }else{
            	personaDAO = new PersonaDAO();
                lstPersonas = personaDAO.listar();
            }
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void leerID(Persona per) throws Exception {
		
		PersonaDAO personaDAO;
		Persona temp;
		
		try {
			personaDAO = new PersonaDAO();
			temp = personaDAO.leerID(per);
			
			if(per != null) {
				this.persona = temp;
				this.action = "Modificar";
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void modificar() throws Exception{
        PersonaDAO dao;
        
        try {
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar("V");
            
        } catch (Exception e) {
            throw e;
        }
    }
	
	public void eliminar(Persona per) throws Exception{
        PersonaDAO dao;
        
        try {
            dao = new PersonaDAO();
            dao.eliminar(persona);
            this.listar("V");
            
        } catch (Exception e) {
            throw e;
        }
    }
	
	public void operar() throws Exception {
		switch (action) {
		case "Registrar":
			this.registrar();
			this.limpiar();
			break;

		case "Modificar":
			this.modificar();
			this.limpiar();
			break;
		}
	}
	
	public void limpiar() {
		this.persona.setCodigo(0);
		this.persona.setNombre("");
		this.persona.setSexo("");
	}

}
