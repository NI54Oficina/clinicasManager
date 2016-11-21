package ar.com.clinicasmanager.script;
import ar.com.clinicasmanager.entity.NodoTratamiento;

import com.google.common.collect.Lists;


public class PopulateNodoTratamientoScript {
	
	public static NodoTratamiento doScript() {
		NodoTratamiento nodoRoot = new NodoTratamiento(null, "tratamiento");
		
		NodoTratamiento nodoInc = new NodoTratamiento(nodoRoot, "Incruento");
		NodoTratamiento nodoCru = new NodoTratamiento(nodoRoot, "Cruento"); 
		NodoTratamiento nodoOtros = new NodoTratamiento(nodoRoot, "Otros"); 
		nodoRoot.getChildren().addAll(Lists.newArrayList(nodoInc, nodoCru, nodoOtros));
		
		NodoTratamiento nodoInc1 = new NodoTratamiento(nodoInc, "nada");
		NodoTratamiento nodoInc2 = new NodoTratamiento(nodoInc, "toilette");
		NodoTratamiento nodoInc3 = new NodoTratamiento(nodoInc, "reduccion");
		NodoTratamiento nodoInc4 = new NodoTratamiento(nodoInc, "yeso");
		NodoTratamiento nodoInc5 = new NodoTratamiento(nodoInc, "valva");
		NodoTratamiento nodoInc6 = new NodoTratamiento(nodoInc, "ferula digital");
		NodoTratamiento nodoInc7 = new NodoTratamiento(nodoInc, "solidarizacion digital");
		NodoTratamiento nodoInc8 = new NodoTratamiento(nodoInc, "FKT");
		NodoTratamiento nodoInc9N = new NodoTratamiento(nodoInc, "infiltración"); 
		nodoInc.getChildren().addAll(Lists.newArrayList(nodoInc1, nodoInc2, nodoInc3, nodoInc4, nodoInc5, nodoInc6, nodoInc7, nodoInc8, nodoInc9N));
		
		NodoTratamiento nodoInc9 = new NodoTratamiento(nodoInc3, "abierta");
		NodoTratamiento nodoInc10 = new NodoTratamiento(nodoInc3, "cerrada");
		nodoInc3.getChildren().addAll(Lists.newArrayList(nodoInc9, nodoInc10));
		
		NodoTratamiento nodoInc11 = new NodoTratamiento(nodoInc4, "ABP");
		NodoTratamiento nodoInc12 = new NodoTratamiento(nodoInc4, "ABD");
		NodoTratamiento nodoInc13 = new NodoTratamiento(nodoInc4, "BP");
		NodoTratamiento nodoInc14 = new NodoTratamiento(nodoInc4, "BD");
		nodoInc4.getChildren().addAll(Lists.newArrayList(nodoInc11, nodoInc12, nodoInc13, nodoInc14));
		
		NodoTratamiento nodoInc15 = new NodoTratamiento(nodoInc5, "ABP");
		NodoTratamiento nodoInc16 = new NodoTratamiento(nodoInc5, "ABD");
		NodoTratamiento nodoInc17 = new NodoTratamiento(nodoInc5, "BP");
		NodoTratamiento nodoInc18 = new NodoTratamiento(nodoInc5, "BD");
		nodoInc5.getChildren().addAll(Lists.newArrayList(nodoInc15, nodoInc16, nodoInc17, nodoInc18));
		
		NodoTratamiento nodoInc19 = new NodoTratamiento(nodoInc6, "larga");
		NodoTratamiento nodoInc20 = new NodoTratamiento(nodoInc6, "corta");
		nodoInc6.getChildren().addAll(Lists.newArrayList(nodoInc19, nodoInc20));
		
		NodoTratamiento nodoCru1 = new NodoTratamiento(nodoCru, "osteosintesis");
		NodoTratamiento nodoCru2 = new NodoTratamiento(nodoCru, "osteodesis temporal");
		NodoTratamiento nodoCru3 = new NodoTratamiento(nodoCru, "reparacion ligamentaria");
		NodoTratamiento nodoCru4 = new NodoTratamiento(nodoCru, "reconstruccion ligamentaria");
		NodoTratamiento nodoCru5 = new NodoTratamiento(nodoCru, "artrodesis");
		NodoTratamiento nodoCru6 = new NodoTratamiento(nodoCru, "osteoplastia");
		NodoTratamiento nodoCru7 = new NodoTratamiento(nodoCru, "fresado y osteosintesis");
		NodoTratamiento nodoCru8 = new NodoTratamiento(nodoCru, "osteotomia correctiva");
		NodoTratamiento nodoCru9 = new NodoTratamiento(nodoCru, "reseccion osea");
		NodoTratamiento nodoCru10 = new NodoTratamiento(nodoCru, "artroplastia");
		NodoTratamiento nodoCru11 = new NodoTratamiento(nodoCru, "artrolisis");
		NodoTratamiento nodoCru12 = new NodoTratamiento(nodoCru, "colgajos");
		NodoTratamiento nodoCru13 = new NodoTratamiento(nodoCru, "fasciotomia");
		NodoTratamiento nodoCru14 = new NodoTratamiento(nodoCru, "cura plana");
		NodoTratamiento nodoCru15 = new NodoTratamiento(nodoCru, "sutura de piel");
		NodoTratamiento nodoCru16 = new NodoTratamiento(nodoCru, "neurorrafia");
		NodoTratamiento nodoCru17 = new NodoTratamiento(nodoCru, "neurolisis");
		NodoTratamiento nodoCru18 = new NodoTratamiento(nodoCru, "injerto de nervio");
		NodoTratamiento nodoCru19 = new NodoTratamiento(nodoCru, "neurotizacion");
		NodoTratamiento nodoCru20 = new NodoTratamiento(nodoCru, "tenorrafia");
		NodoTratamiento nodoCru21 = new NodoTratamiento(nodoCru, "tenoplastia");
		NodoTratamiento nodoCru22 = new NodoTratamiento(nodoCru, "tenolisis");
		NodoTratamiento nodoCru23 = new NodoTratamiento(nodoCru, "tenosuspenciones");
		NodoTratamiento nodoCru24 = new NodoTratamiento(nodoCru, "transferencia tendinosa");
		NodoTratamiento nodoCru25 = new NodoTratamiento(nodoCru, "reimplante");
		NodoTratamiento nodoCru26 = new NodoTratamiento(nodoCru, "tenosinovectomia");
		NodoTratamiento nodoCru27 = new NodoTratamiento(nodoCru, "sinovectomia");
		NodoTratamiento nodoCru28 = new NodoTratamiento(nodoCru, "bursectomia");
		NodoTratamiento nodoCru29 = new NodoTratamiento(nodoCru, "apertura de poleas");
		NodoTratamiento nodoCru30 = new NodoTratamiento(nodoCru, "drenaje");
		NodoTratamiento nodoCru31 = new NodoTratamiento(nodoCru, "puncion bipsia");
		NodoTratamiento nodoCru32 = new NodoTratamiento(nodoCru, "liberacion (descompresion)");
		NodoTratamiento nodoCru33 = new NodoTratamiento(nodoCru, "reseccion");
		NodoTratamiento nodoCru34 = new NodoTratamiento(nodoCru, "cureteado y relleno con injerto oseo");
		NodoTratamiento nodoCru35 = new NodoTratamiento(nodoCru, "amputacion");
		NodoTratamiento nodoCru36 = new NodoTratamiento(nodoCru, "aponeurectomia");
		NodoTratamiento nodoCru37 = new NodoTratamiento(nodoCru, "transposicion de un rayo");
		NodoTratamiento nodoCru38 = new NodoTratamiento(nodoCru, "transferencia dedo del pie a la mano");
		NodoTratamiento nodoCru39 = new NodoTratamiento(nodoCru, "pulgarizacion");
		NodoTratamiento nodoCru40 = new NodoTratamiento(nodoCru, "reconstruccion digital compleja");	
		NodoTratamiento nodoCru41N = new NodoTratamiento(nodoCru, "artroscopía");	
		NodoTratamiento nodoCru42N = new NodoTratamiento(nodoCru, "aponeurotomia");	
		NodoTratamiento nodoCru43N = new NodoTratamiento(nodoCru, "procedimientos percutáneos");	
		nodoCru.getChildren().addAll(Lists.newArrayList(nodoCru1, nodoCru2, nodoCru3, nodoCru4, nodoCru5, nodoCru6, nodoCru7, nodoCru8, nodoCru9, 
				nodoCru10, nodoCru11, nodoCru12, nodoCru13, nodoCru14, nodoCru15, nodoCru16, nodoCru17, nodoCru18, nodoCru19, 
				nodoCru20, nodoCru21, nodoCru22, nodoCru23, nodoCru24, nodoCru25, nodoCru26, nodoCru27, nodoCru28, nodoCru29, 
				nodoCru30, nodoCru31, nodoCru32, nodoCru33, nodoCru34, nodoCru35, nodoCru36, nodoCru37, nodoCru38, nodoCru39, nodoCru40,
				nodoCru41N, nodoCru42N, nodoCru43N));
		
		NodoTratamiento nodoCru41 = new NodoTratamiento(nodoCru1, "clavijas");
		NodoTratamiento nodoCru42 = new NodoTratamiento(nodoCru1, "detensores");
		NodoTratamiento nodoCru43 = new NodoTratamiento(nodoCru1, "tornillos");
		NodoTratamiento nodoCru44 = new NodoTratamiento(nodoCru1, "placa y tornillos");
		NodoTratamiento nodoCru45 = new NodoTratamiento(nodoCru1, "alambre");
		NodoTratamiento nodoCru46 = new NodoTratamiento(nodoCru1, "tutor externo");
		NodoTratamiento nodoCru47 = new NodoTratamiento(nodoCru1, "clavo endomedular");
		nodoCru1.getChildren().addAll(Lists.newArrayList(nodoCru41, nodoCru42, nodoCru43, nodoCru44, nodoCru45, nodoCru46, nodoCru47));
		
		NodoTratamiento nodoCru48 = new NodoTratamiento(nodoCru41, "transfragmentarias");
		NodoTratamiento nodoCru49 = new NodoTratamiento(nodoCru41, "ishiguro");
		NodoTratamiento nodoCru50 = new NodoTratamiento(nodoCru41, "trans MTC para F1");
		NodoTratamiento nodoCru51 = new NodoTratamiento(nodoCru41, "kapandji");
		nodoCru41.getChildren().addAll(Lists.newArrayList(nodoCru48, nodoCru49, nodoCru50, nodoCru51));		
		
		NodoTratamiento nodoCru52 = new NodoTratamiento(nodoCru43, "herbert");
		NodoTratamiento nodoCru53 = new NodoTratamiento(nodoCru43, "baruk");
		NodoTratamiento nodoCru54 = new NodoTratamiento(nodoCru43, "canulados");
		NodoTratamiento nodoCru55 = new NodoTratamiento(nodoCru43, "de compresion");
		nodoCru43.getChildren().addAll(Lists.newArrayList(nodoCru52, nodoCru53, nodoCru54, nodoCru55));	
		
		NodoTratamiento nodoCru56 = new NodoTratamiento(nodoCru44, "DCP");
		NodoTratamiento nodoCru57 = new NodoTratamiento(nodoCru44, "LCP");
		NodoTratamiento nodoCru58 = new NodoTratamiento(nodoCru44, "puente");
		NodoTratamiento nodoCru59 = new NodoTratamiento(nodoCru44, "plating");
		nodoCru44.getChildren().addAll(Lists.newArrayList(nodoCru56, nodoCru57, nodoCru58, nodoCru59));			
		
		NodoTratamiento nodoCru60 = new NodoTratamiento(nodoCru45, "cerclaje");
		NodoTratamiento nodoCru61 = new NodoTratamiento(nodoCru45, "bandas de tension");
		nodoCru45.getChildren().addAll(Lists.newArrayList(nodoCru60, nodoCru61));		
		
		NodoTratamiento nodoCru62 = new NodoTratamiento(nodoCru47, "elasticos");
		NodoTratamiento nodoCru63 = new NodoTratamiento(nodoCru47, "acerrojados");
		nodoCru47.getChildren().addAll(Lists.newArrayList(nodoCru62, nodoCru63));	
		
		NodoTratamiento nodoCru64 = new NodoTratamiento(nodoCru2, "IFD");
		NodoTratamiento nodoCru65 = new NodoTratamiento(nodoCru2, "IFP");
		NodoTratamiento nodoCru66 = new NodoTratamiento(nodoCru2, "MTC-F");
		NodoTratamiento nodoCru67 = new NodoTratamiento(nodoCru2, "C-MTC");
		NodoTratamiento nodoCru68 = new NodoTratamiento(nodoCru2, "MÑC");
		NodoTratamiento nodoCru69 = new NodoTratamiento(nodoCru2, "Radio-cubital");
		NodoTratamiento nodoCru70 = new NodoTratamiento(nodoCru2, "codo");
		nodoCru2.getChildren().addAll(Lists.newArrayList(nodoCru64, nodoCru65, nodoCru66, nodoCru67, nodoCru68, nodoCru69, nodoCru70));		
		
		NodoTratamiento nodoCru71 = new NodoTratamiento(nodoCru5, "IFD");
		NodoTratamiento nodoCru72 = new NodoTratamiento(nodoCru5, "IFP");
		NodoTratamiento nodoCru73 = new NodoTratamiento(nodoCru5, "MTC-F");
		NodoTratamiento nodoCru74 = new NodoTratamiento(nodoCru5, "C-MTC");
		NodoTratamiento nodoCru75 = new NodoTratamiento(nodoCru5, "Escafo-Tr-Trapezoides");
		NodoTratamiento nodoCru76 = new NodoTratamiento(nodoCru5, "4 esquinas");
		NodoTratamiento nodoCru77 = new NodoTratamiento(nodoCru5, "REL");		
		NodoTratamiento nodoCru78 = new NodoTratamiento(nodoCru5, "Total de MÑC");
		NodoTratamiento nodoCru79 = new NodoTratamiento(nodoCru5, "Sauve-Kapandji");
		NodoTratamiento nodoCru80 = new NodoTratamiento(nodoCru5, "codo");
		nodoCru5.getChildren().addAll(Lists.newArrayList(nodoCru71, nodoCru72, nodoCru73, nodoCru74, nodoCru75, nodoCru76, nodoCru77, nodoCru78, nodoCru79, nodoCru80));	
		
		NodoTratamiento nodoCru81 = new NodoTratamiento(nodoCru6, "con injerto de cresta");
		NodoTratamiento nodoCru82 = new NodoTratamiento(nodoCru6, "con injerto de olecranon");
		NodoTratamiento nodoCru83 = new NodoTratamiento(nodoCru6, "injerto oseo vascularizado");
		NodoTratamiento nodoCru83B = new NodoTratamiento(nodoCru6, "otros");
		nodoCru6.getChildren().addAll(Lists.newArrayList(nodoCru81, nodoCru82, nodoCru83,nodoCru83B));
		
		NodoTratamiento nodoCru84 = new NodoTratamiento(nodoCru9, "MTC");
		NodoTratamiento nodoCru85 = new NodoTratamiento(nodoCru9, "1ra fila del carpo");
		NodoTratamiento nodoCru86 = new NodoTratamiento(nodoCru9, "Darrach");
		NodoTratamiento nodoCru87 = new NodoTratamiento(nodoCru9, "Bowers");
		NodoTratamiento nodoCru88 = new NodoTratamiento(nodoCru9, "Wafer");
		NodoTratamiento nodoCru89 = new NodoTratamiento(nodoCru9, "cupulectomia");
		NodoTratamiento nodoCru90 = new NodoTratamiento(nodoCru9, "olecranon");		
		NodoTratamiento nodoCru91 = new NodoTratamiento(nodoCru9, "coronoides");
		NodoTratamiento nodoCru92 = new NodoTratamiento(nodoCru9, "epicondilo");
		NodoTratamiento nodoCru93 = new NodoTratamiento(nodoCru9, "epitroclea");
		NodoTratamiento nodoCru94 = new NodoTratamiento(nodoCru9, "gancho");
		NodoTratamiento nodoCru95 = new NodoTratamiento(nodoCru9, "escafoides");
		nodoCru9.getChildren().addAll(Lists.newArrayList(nodoCru84, nodoCru85, nodoCru86, nodoCru87, nodoCru88, nodoCru89, nodoCru90, nodoCru91, nodoCru92, nodoCru93, nodoCru94, nodoCru95));
		
		NodoTratamiento nodoCru96 = new NodoTratamiento(nodoCru10, "reseccion");
		NodoTratamiento nodoCru97 = new NodoTratamiento(nodoCru10, "con interposicion");
		NodoTratamiento nodoCru98 = new NodoTratamiento(nodoCru10, "con tenosuspencion");
		NodoTratamiento nodoCru99 = new NodoTratamiento(nodoCru10, "con protesis");
		nodoCru10.getChildren().addAll(Lists.newArrayList(nodoCru96, nodoCru97, nodoCru98, nodoCru99));		
		
		NodoTratamiento nodoCru100 = new NodoTratamiento(nodoCru12, "cierre de muñon");
		NodoTratamiento nodoCru101 = new NodoTratamiento(nodoCru12, "Attasoy");
		NodoTratamiento nodoCru102 = new NodoTratamiento(nodoCru12, "Kutler");
		NodoTratamiento nodoCru103 = new NodoTratamiento(nodoCru12, "Moberg");
		NodoTratamiento nodoCru104 = new NodoTratamiento(nodoCru12, "O Brien");
		NodoTratamiento nodoCru105 = new NodoTratamiento(nodoCru12, "en isla");
		NodoTratamiento nodoCru106 = new NodoTratamiento(nodoCru12, "cross finger");
		NodoTratamiento nodoCru107 = new NodoTratamiento(nodoCru12, "chino");
		NodoTratamiento nodoCru108 = new NodoTratamiento(nodoCru12, "interoseo posterior");
		NodoTratamiento nodoCru109 = new NodoTratamiento(nodoCru12, "lateral arm");
		NodoTratamiento nodoCru110 = new NodoTratamiento(nodoCru12, "abdominal");
		NodoTratamiento nodoCru111 = new NodoTratamiento(nodoCru12, "dorsal ancho");
		NodoTratamiento nodoCru112 = new NodoTratamiento(nodoCru12, "propeler");
		NodoTratamiento nodoCru113 = new NodoTratamiento(nodoCru12, "otros");
		nodoCru12.getChildren().addAll(Lists.newArrayList(nodoCru100, nodoCru101, nodoCru102, nodoCru103, nodoCru104, nodoCru105, nodoCru106, nodoCru107, nodoCru108, nodoCru109, nodoCru110, 
				nodoCru111, nodoCru112, nodoCru113));
		
		return nodoRoot;		
	}
}
