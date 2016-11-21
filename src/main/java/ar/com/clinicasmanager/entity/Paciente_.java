package ar.com.clinicasmanager.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;

@StaticMetamodel(Paciente.class)
public class Paciente_ {
	public static volatile SingularAttribute<Paciente, String> nombre;
	
	public static volatile SingularAttribute<Paciente, Ocupacion> ocupacion;
	
	public static volatile SingularAttribute<Paciente, Sexo> sexo;

	public static volatile SingularAttribute<Paciente, Cobertura> cobertura;
	
	public static volatile SingularAttribute<Paciente, String> obraSocial;
	
	public static volatile SingularAttribute<Paciente, Integer> edad;
}
