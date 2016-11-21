package ar.com.clinicasmanager.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ {
	
	public static volatile SetAttribute<Diagnostico, Lesion> lesiones;

}
