package ar.com.clinicasmanager.search;

public enum Comparacion {

	EQ("="),
	GE(">="),
	GT(">"),
	LE("<="),
	LT("<"),
	BETWEEN("Entre");
	
	private String value;

    private Comparacion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
