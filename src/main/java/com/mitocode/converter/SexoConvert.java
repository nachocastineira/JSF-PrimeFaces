package com.mitocode.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sexoConverter")
public class SexoConvert implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		String sexo = "";
		
		if (value != null) {
			sexo = (String) value;
			switch (sexo) {
			case "M":
				sexo = "MASCULINO";
				break;
			case "F":
				sexo = "FEMENINO";
				break;
			}
		}		
		return sexo;
	}
	
	

}
