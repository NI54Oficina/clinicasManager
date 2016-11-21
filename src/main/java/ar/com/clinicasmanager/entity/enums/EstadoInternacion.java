package ar.com.clinicasmanager.entity.enums;

public enum EstadoInternacion {
	FINALIZADA("Finaliazada"), PROGRAMADA("Programada");
	
	private String name;

    private EstadoInternacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
