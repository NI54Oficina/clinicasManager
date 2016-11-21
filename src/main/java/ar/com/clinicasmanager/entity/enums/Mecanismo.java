package ar.com.clinicasmanager.entity.enums;

public enum Mecanismo {

	CAIDA("Caída"),
	CAIDA_ALTURA("Caída de altura"),
	GOLPE_DIRECTO("Golpe directo"),
	APLASTAMIENTO("Aplastamiento"),
	CORTE("Corte"), 
	ESFUERZO("Esfuerzo"), 
	ACT_REPETITIVA("Actividad repetitiva"),
	QUEMADURA("Quemadura"), 
	HERIDA_PUNZANTE("Herida punzante"),
	HAF("HAF"), 
	DESGUANTAMIENTO("Desguantamiento"), 
	MORDEDURA("Mordedura"), 
	OTROS("Otros");	
	
	private String name;

    private Mecanismo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
