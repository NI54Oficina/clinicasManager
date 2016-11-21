package ar.com.clinicasmanager.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EstadoConsulta.class)
public class EstadoConsulta_ {
	
	public static volatile SingularAttribute<EstadoConsulta, Boolean> conCirugiaPendiente;
	
	public static volatile SingularAttribute<EstadoConsulta, Boolean> conInternacion;
	
	public static volatile SingularAttribute<EstadoConsulta, Boolean> dadoDeAlta;

}
