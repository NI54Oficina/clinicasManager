package ar.com.clinicasmanager.entity.enums;

public enum Miembro {

	MSD("Miembro superior derecho"), MSI("Miembro superior izquierdo"), BILATERAL("Bilateral"), AMBOS("Ambos miembros");
	
	private String name;

    private Miembro(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
