package ar.com.clinicasmanager.entity.enums;

public enum Cobertura {
	ART("ART"), OS("Obra social"), PAMI("PAMI"), SOCIO("Socio"), OSDE("OSDE"), OTROS("Otros pre pagos");
	
	private String name;

    private Cobertura(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
