package ar.com.clinicasmanager.entity.enums;

public enum LugarAccidente {

	TRABAJO_PAGO("Trabajo pago"), 
	TRABAJO_NO_PAGO("Trabajo no pago"),
	IN_ITINERE("In Itinere"),
	DEPORTES("Deportes"),
	RECREACION("Recreación"),
	ACCIDENTE_TRANSITO("Accidente de tránsito"),
	MOTO("Moto"),
	ASALTO("Asalto"),
	OTROS("Otros");

	private String name;

	private LugarAccidente(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
