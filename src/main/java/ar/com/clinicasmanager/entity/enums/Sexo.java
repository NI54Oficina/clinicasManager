package ar.com.clinicasmanager.entity.enums;

public enum Sexo {

	FEM("Femenino"), MASC("Masculino");

	private String name;

    private Sexo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
