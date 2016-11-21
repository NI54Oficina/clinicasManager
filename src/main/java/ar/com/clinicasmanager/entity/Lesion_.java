package ar.com.clinicasmanager.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import ar.com.clinicasmanager.entity.enums.Miembro;

@StaticMetamodel(Lesion.class)
public class Lesion_ {
	
	public static volatile SingularAttribute<Lesion, NodoDiagnostico> patologia;
	
	public static volatile SingularAttribute<Lesion, Miembro> miembro;

}
