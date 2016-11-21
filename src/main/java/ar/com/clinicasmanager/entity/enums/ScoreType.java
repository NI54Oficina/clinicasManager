package ar.com.clinicasmanager.entity.enums;

public enum ScoreType {

	QUICK_DASH("Quick Dash"), ESCALA_DE_DOLOR("Escala de Dolor"), MICHIGAN("Michigan");
	
	private String name;

    private ScoreType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
