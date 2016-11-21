package ar.com.clinicasmanager.entity.enums;

public enum Ocupacion {
	INDUSTRIA("Industria"), 
	CONSTRUCCION("Construcción"), 
	TRANSPORTE("Transporte"), 
	MAESTRANZA("Maestranza"), 
	PERS_DOMESTICO("Personal doméstico"), 
	SEGURIDAD("Seguridad"), 
	ALIMENTICIA("Alimenticia"), 
	EDUCACION("Educación"), 
	ADMINISTRATIVO("Administrativo"), 
	COMERCIO("Comercio"), 
	PROFESIONAL("Profesional"), 
	JUBILADO("Jubilado"), 
	AMA_DE_CASA("Ama de casa"), 
	OTROS("Otro");

	private String name;

    private Ocupacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
