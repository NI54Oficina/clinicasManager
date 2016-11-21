package ar.com.clinicasmanager.entity;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class UltimoAcceso {

	private String username;
	
	private Date date;
	
	public UltimoAcceso() {
	}	
	
	public UltimoAcceso(String currentUsername) {
		this.username = currentUsername;
		date = new Date();
	}

	public void refresh(){
		date = new Date();
	}
}
