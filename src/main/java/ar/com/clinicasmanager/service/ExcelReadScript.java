package ar.com.clinicasmanager.service;

public class ExcelReadScript {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] nombres = { 
//				"polo proximal",
//				"intra art simple",
//				"intra art conminuta",
				"palmar mayor",
				"cubital ant",
				"1er radial",
				"2do radial",
				"biceps",
				"otros", 
//				"distrofia simpatica",
//				"manos secuelares para reconstruccion",
//				"combinados",
//				"V",
//				"grande",
//				"pulgar",
//				"pisiforme",
//				"trapecio",
//				"trapezoides",
		};
		int index = 93;
		String abbr = "Tr";
		String parent = "nodoTr76";

		String nodosSeparadosPorComa = "";
		for (String string : nombres) {
			string = string.trim();
			System.out.println("NodoDiagnostico nodo" + abbr + index
					+ " = new NodoDiagnostico(" + parent + ", \"" + string
					+ "\");");

			nodosSeparadosPorComa = nodosSeparadosPorComa + "nodo" + abbr
					+ index + ",";
			index++;
		}
		nodosSeparadosPorComa = nodosSeparadosPorComa.substring(0,
				nodosSeparadosPorComa.length() - 1);
		System.out.println(parent + ".getChildren().addAll(Lists.newArrayList("
				+ nodosSeparadosPorComa + "));");

	}
}
