package ar.com.clinicasmanager.entity;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Consulta.class)
public class Consulta_ {
	
	public static volatile SingularAttribute<Consulta, Diagnostico> diagnostico;
	
	public static volatile ListAttribute<Consulta, Tratamiento> tratamientos;
	
	public static volatile ListAttribute<Consulta, Cirugia> cirugias;
	
	public static volatile SingularAttribute<Consulta, EstadoConsulta> estado;
	
	public static volatile SingularAttribute<Consulta, Paciente> paciente;

	public static volatile SingularAttribute<Consulta, Date> fechaPrimerConsulta;

}
