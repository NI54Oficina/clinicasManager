package ar.com.clinicasmanager.entity.enums;

public enum EstadoCirugia {
	REALIZADA("Realizada"), PENDIENTE("Pendiente"), CANCELADA("Cancelada");
	
	private String name;

    private EstadoCirugia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
