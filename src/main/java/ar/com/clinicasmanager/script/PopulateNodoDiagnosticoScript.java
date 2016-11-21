package ar.com.clinicasmanager.script;
import java.util.ArrayList;
import java.util.List;

import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.service.NodoDiagnosticoServiceImpl;

import com.google.common.collect.Lists;


public class PopulateNodoDiagnosticoScript {
	
	public static NodoDiagnostico doScript() {
		NodoDiagnostico root = new NodoDiagnostico(null, NodoDiagnosticoServiceImpl.ROOT_LABEL);
		
		NodoDiagnostico nodo2 = new NodoDiagnostico(root, "traumatica", false);
		NodoDiagnostico nodo3 = new NodoDiagnostico(root, "inflamatoria", false);
		NodoDiagnostico nodo4 = new NodoDiagnostico(root, "degenerativa", true);
		NodoDiagnostico nodo5 = new NodoDiagnostico(root, "infecciosa", false);
		NodoDiagnostico nodo6 = new NodoDiagnostico(root, "tumoral", false);
		NodoDiagnostico nodo7 = new NodoDiagnostico(root, "síndromes compresivos", true);
		NodoDiagnostico nodo8 = new NodoDiagnostico(root, "malformaciones", true);
		NodoDiagnostico nodo9 = new NodoDiagnostico(root, "misceláneas", false);
		NodoDiagnostico nodoN1 = new NodoDiagnostico(root, "deformidades digitales", false);
		NodoDiagnostico nodo10 = new NodoDiagnostico(root, NodoDiagnosticoServiceImpl.SIN_DIAGNOSTICO_NAME, true);
		
		root.getChildren().addAll(Lists.newArrayList(nodo2, nodo3, nodo4, nodo5, nodo6, nodo7, nodo8, nodo9, nodoN1, nodo10));
		
		//Deformidades digitales
		NodoDiagnostico nodoDD1 = new NodoDiagnostico(nodoN1, "boutonniere", true);
		NodoDiagnostico nodoDD2 = new NodoDiagnostico(nodoN1, "cuello de cisne", true);
		NodoDiagnostico nodoDD3 = new NodoDiagnostico(nodoN1, "mallet inveterado", true);
		nodoN1.getChildren().addAll(Lists.newArrayList(nodoDD1,nodoDD2,nodoDD3));
		
		completarDeformidadesDigitales(nodoDD1, false);
		completarDeformidadesDigitales(nodoDD2, false);
		completarDeformidadesDigitales(nodoDD3, true);
		
		//Traumática
		NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodo2, "osteoarticular");
		NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodo2, "partes blandas", true);
		NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodo2, "secuela traumáticas");
		NodoDiagnostico nodoTr14 = new NodoDiagnostico(nodo2, "reimplante", true);
		NodoDiagnostico nodoTr15 = new NodoDiagnostico(nodo2, "traumatismo leve", true);
		NodoDiagnostico nodoTr16 = new NodoDiagnostico(nodo2, "traumatismo grave complejo", true);
		NodoDiagnostico nodoTr17 = new NodoDiagnostico(nodo2, "otros", true);
		nodo2.getChildren().addAll(Lists.newArrayList(nodoTr11, nodoTr12, nodoTr13, nodoTr14, nodoTr15, nodoTr16, nodoTr17));
		
		NodoDiagnostico nodoTr18 = new NodoDiagnostico(nodoTr11, "fractura", true);
		NodoDiagnostico nodoTr19 = new NodoDiagnostico(nodoTr11, "luxación", true);
		NodoDiagnostico nodoTr20 = new NodoDiagnostico(nodoTr11, "lesión ligamentaria", true);
		nodoTr11.getChildren().addAll(Lists.newArrayList(nodoTr18, nodoTr19, nodoTr20));
		
		NodoDiagnostico nodoTr21 = new NodoDiagnostico(nodoTr18, "abierta", true);
		NodoDiagnostico nodoTr22 = new NodoDiagnostico(nodoTr18, "cerrada", true);
		nodoTr18.getChildren().addAll(Lists.newArrayList(nodoTr21,nodoTr22));

		NodoDiagnostico nodoTr23 = new NodoDiagnostico(nodoTr21, "intra art");
		NodoDiagnostico nodoTr24 = new NodoDiagnostico(nodoTr21, "extra art");
		nodoTr21.getChildren().addAll(Lists.newArrayList(nodoTr23,nodoTr24));
		
		NodoDiagnostico nodoTr25 = new NodoDiagnostico(nodoTr22, "intra art");
		NodoDiagnostico nodoTr26 = new NodoDiagnostico(nodoTr22, "extra art");
		nodoTr22.getChildren().addAll(Lists.newArrayList(nodoTr25,nodoTr26));

		addFracturas(nodoTr23);
		addFracturas(nodoTr24);
		addFracturas(nodoTr25);
		addFracturas(nodoTr26);
		
		NodoDiagnostico nodoTr27 = new NodoDiagnostico(nodoTr20, "abierta", false);
		NodoDiagnostico nodoTr28 = new NodoDiagnostico(nodoTr20, "cerrada", false);
		nodoTr20.getChildren().addAll(Lists.newArrayList(nodoTr27,nodoTr28));
		
		addAgudaCronicaToAllLeaves(nodoTr20, false);
		addDistensionRupturaCompletaToAllLeaves(nodoTr20);
		completeLesionLigamentariaToAllLeaves(nodoTr20);
		
		NodoDiagnostico nodoTr29 = new NodoDiagnostico(nodoTr19, "abierta", true);
		NodoDiagnostico nodoTr30 = new NodoDiagnostico(nodoTr19, "cerrada", true);
		nodoTr19.getChildren().addAll(Lists.newArrayList(nodoTr29,nodoTr30));
		
		addAgudaCronicaToAllLeaves(nodoTr19, true);
		completeLuxacionesToAllLeaves(nodoTr19);
		
		NodoDiagnostico nodoTr31 = new NodoDiagnostico(nodoTr13, "consolidación viciosa", true);
		NodoDiagnostico nodoTr32 = new NodoDiagnostico(nodoTr13, "rigidez", true);
		NodoDiagnostico nodoTr33 = new NodoDiagnostico(nodoTr13, "seudoartrosis", true);
		NodoDiagnostico nodoTr34 = new NodoDiagnostico(nodoTr13, "síndrome compartimental", true);
		NodoDiagnostico nodoTr35 = new NodoDiagnostico(nodoTr13, "artrosis post traumática", true);
		NodoDiagnostico nodoTr36 = new NodoDiagnostico(nodoTr13, "amputación", true);
		NodoDiagnostico nodoTr37 = new NodoDiagnostico(nodoTr13, "distrofia simpática", true);
		NodoDiagnostico nodoTr38 = new NodoDiagnostico(nodoTr13, "mano secuelar para reconstrucción", true);
		nodoTr13.getChildren().addAll(Lists.newArrayList(nodoTr31,nodoTr32,nodoTr33,nodoTr34,nodoTr35,nodoTr36,nodoTr37,nodoTr38));
		
		NodoDiagnostico nodoTr39 = new NodoDiagnostico(nodoTr31, "temprana (<2meses)");
		NodoDiagnostico nodoTr40 = new NodoDiagnostico(nodoTr31, "tardía (>2meses)");
		nodoTr31.getChildren().addAll(Lists.newArrayList(nodoTr39,nodoTr40));
		
		NodoDiagnostico nodoTr41 = new NodoDiagnostico(nodoTr39, "intra art");
		NodoDiagnostico nodoTr42 = new NodoDiagnostico(nodoTr39, "extra art");
		nodoTr39.getChildren().addAll(Lists.newArrayList(nodoTr41,nodoTr42));
		
		NodoDiagnostico nodoTr43 = new NodoDiagnostico(nodoTr40, "intra art");
		NodoDiagnostico nodoTr44 = new NodoDiagnostico(nodoTr40, "extraart");
		nodoTr40.getChildren().addAll(Lists.newArrayList(nodoTr43,nodoTr44));

		completeConsVicSeudoartrosis(nodoTr41);
		completeConsVicSeudoartrosis(nodoTr42);
		completeConsVicSeudoartrosis(nodoTr43);
		completeConsVicSeudoartrosis(nodoTr44);
		
		NodoDiagnostico nodoTr45 = new NodoDiagnostico(nodoTr33, "séptica");
		NodoDiagnostico nodoTr46 = new NodoDiagnostico(nodoTr33, "aséptica");
		NodoDiagnostico nodoTr47 = new NodoDiagnostico(nodoTr33, "sin estado infectológico confirmado");
		nodoTr33.getChildren().addAll(Lists.newArrayList(nodoTr45,nodoTr46,nodoTr47));
		
		addTroficaToAllLeaves(nodoTr33);
		for (NodoDiagnostico leaf : getLeaves(nodoTr33.getChildren(), nodoTr33)) {
			completeConsVicSeudoartrosis(leaf);
		}	
		
		NodoDiagnostico nodoTr48 = new NodoDiagnostico(nodoTr32, "fx intr art");
		NodoDiagnostico nodoTr49 = new NodoDiagnostico(nodoTr32, "luxación");
		NodoDiagnostico nodoTr50 = new NodoDiagnostico(nodoTr32, "adherencias");
		NodoDiagnostico nodoTrN1 = new NodoDiagnostico(nodoTr32, "otras");
		nodoTr32.getChildren().addAll(Lists.newArrayList(nodoTr48,nodoTr49,nodoTr50, nodoTrN1));
		
		completeRigideces(nodoTr48);
		completeRigideces(nodoTr49);
		completeRigideces(nodoTr50);
		completeRigideces(nodoTrN1);
		
		completeAmputaciones(nodoTr36);
		
		NodoDiagnostico nodoTr51 = new NodoDiagnostico(nodoTr35, "Radio-Carpiana", true);
		NodoDiagnostico nodoTr52 = new NodoDiagnostico(nodoTr35, "RCI", true);
		NodoDiagnostico nodoTr53 = new NodoDiagnostico(nodoTr35, "Cubito-carpiana", true);
		NodoDiagnostico nodoTr54 = new NodoDiagnostico(nodoTr35, "síndrome de impactación Cubito-carpiano", true);
		NodoDiagnostico nodoTr55 = new NodoDiagnostico(nodoTr35, "intercarpiana");
		NodoDiagnostico nodoTr56 = new NodoDiagnostico(nodoTr35, "pulgar", true);
		NodoDiagnostico nodoTr57 = new NodoDiagnostico(nodoTr35, "II", true);
		NodoDiagnostico nodoTr58 = new NodoDiagnostico(nodoTr35, "III", true);
		NodoDiagnostico nodoTr59 = new NodoDiagnostico(nodoTr35, "IV", true);
		NodoDiagnostico nodoTr60 = new NodoDiagnostico(nodoTr35, "V", true);
		NodoDiagnostico nodoTr61 = new NodoDiagnostico(nodoTr35, "codo", true);
		nodoTr35.getChildren().addAll(Lists.newArrayList(nodoTr51,nodoTr52,nodoTr53,nodoTr54,nodoTr55,nodoTr56,nodoTr57,nodoTr58,
				nodoTr59,nodoTr60,nodoTr61));
		
		NodoDiagnostico nodoTr62 = new NodoDiagnostico(nodoTr61, "humero-cubital", true);
		NodoDiagnostico nodoTr63 = new NodoDiagnostico(nodoTr61, "humero-radial", true);
		NodoDiagnostico nodoTr64 = new NodoDiagnostico(nodoTr61, "RCS", true);
		NodoDiagnostico nodoTrN3 = new NodoDiagnostico(nodoTr61, "combinada", true);
		nodoTr61.getChildren().addAll(Lists.newArrayList(nodoTr62,nodoTr63,nodoTr64, nodoTrN3));
		
		NodoDiagnostico nodoTr65 = new NodoDiagnostico(nodoTr55, "periescafoidea", true);
		NodoDiagnostico nodoTr66 = new NodoDiagnostico(nodoTr55, "SNAC", true);
		NodoDiagnostico nodoTr67 = new NodoDiagnostico(nodoTr55, "SLAC", true);
		NodoDiagnostico nodoTrN2 = new NodoDiagnostico(nodoTr55, "trapecio escafoidea", true);
		nodoTr55.getChildren().addAll(Lists.newArrayList(nodoTr65,nodoTr66,nodoTr67, nodoTrN2));
		
		addTmtcMtcfIf(nodoTr56);
		addMtcfIfdIfp(nodoTr57);
		addMtcfIfdIfp(nodoTr58);
		addMtcfIfdIfp(nodoTr59);
		addMtcfIfdIfp(nodoTr60);
		
		NodoDiagnostico nodoTr68 = new NodoDiagnostico(nodoTr34, "antebrazo", true);
		NodoDiagnostico nodoTr69 = new NodoDiagnostico(nodoTr34, "mano", true);
		NodoDiagnostico nodoTr70 = new NodoDiagnostico(nodoTr34, "ambos", true);
		nodoTr34.getChildren().addAll(Lists.newArrayList(nodoTr68,nodoTr69,nodoTr70));
		
		NodoDiagnostico nodoTr71 = new NodoDiagnostico(nodoTr12, "piel", true);
		NodoDiagnostico nodoTr72 = new NodoDiagnostico(nodoTr12, "flexores", true);
		NodoDiagnostico nodoTr73 = new NodoDiagnostico(nodoTr12, "extensores", true);
		NodoDiagnostico nodoTr74 = new NodoDiagnostico(nodoTr12, "nervios");
		NodoDiagnostico nodoTr75 = new NodoDiagnostico(nodoTr12, "vasos");
		NodoDiagnostico nodoTr76 = new NodoDiagnostico(nodoTr12, "otros tendones");
		NodoDiagnostico nodoTrN75 = new NodoDiagnostico(nodoTr12, "lesión manguito rotador", true);
		NodoDiagnostico nodoTrN76 = new NodoDiagnostico(nodoTr12, "lesión biceps", true);
		nodoTr12.getChildren().addAll(Lists.newArrayList(nodoTr71,nodoTr72,nodoTr73,nodoTr74,nodoTr75,nodoTr76,nodoTrN75,nodoTrN76));
		
		NodoDiagnostico nodoTr77 = new NodoDiagnostico(nodoTr71, "escoriación", true);
		NodoDiagnostico nodoTr78 = new NodoDiagnostico(nodoTr71, "herida cortante", true);
		NodoDiagnostico nodoTr79 = new NodoDiagnostico(nodoTr71, "defecto tegumentario", true);
		NodoDiagnostico nodoTr80 = new NodoDiagnostico(nodoTr71, "quemadura", true);
		NodoDiagnostico nodoTr81 = new NodoDiagnostico(nodoTr71, "desguantamiento", true);
		nodoTr71.getChildren().addAll(Lists.newArrayList(nodoTr77,nodoTr78,nodoTr79,nodoTr80,nodoTr81));

		completePartesBlandas(nodoTr77, false, false, true);
		completePartesBlandas(nodoTr78, false, false, false);
		completePartesBlandas(nodoTr79, true, false, false);
		completeDesguantamiento(nodoTr81);
		
		NodoDiagnostico nodoTr82 = new NodoDiagnostico(nodoTr80, "A (epidermis)");
		NodoDiagnostico nodoTr83 = new NodoDiagnostico(nodoTr80, "B (dermis)");
		NodoDiagnostico nodoTr84 = new NodoDiagnostico(nodoTr80, "AB (hipodermis)");
		nodoTr80.getChildren().addAll(Lists.newArrayList(nodoTr82,nodoTr83,nodoTr84));
		
		completePartesBlandas(nodoTr82, false, true, false);
		completePartesBlandas(nodoTr83, false, true, false);
		completePartesBlandas(nodoTr84, false, true, false);
		
		NodoDiagnostico nodoTr85 = new NodoDiagnostico(nodoTr74, "neuropraxia", true);
		NodoDiagnostico nodoTr86 = new NodoDiagnostico(nodoTr74, "lesion nerviosa", true);
		nodoTr74.getChildren().addAll(Lists.newArrayList(nodoTr85,nodoTr86));
		
		completeNervios(nodoTr85);
		completeNervios(nodoTr86);
		
		NodoDiagnostico nodoTrN5 = new NodoDiagnostico(nodoTr72, "corte", true);
		NodoDiagnostico nodoTrN6 = new NodoDiagnostico(nodoTr72, "ruptura", true);
		NodoDiagnostico nodoTrN7 = new NodoDiagnostico(nodoTr72, "avulsion", true);
		nodoTr72.getChildren().addAll(Lists.newArrayList(nodoTrN5,nodoTrN6,nodoTrN7));
		
		completeFlexores(nodoTrN5);
		completeFlexores(nodoTrN6);
		completeFlexores(nodoTrN7);
		
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodoTr73, "corte", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodoTr73, "ruptura", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodoTr73, "avulsion", true);
		nodoTr73.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
		completeExtensoresToAllLeaves(nodoTr1);
		completeExtensoresToAllLeaves(nodoTr2);
		completeExtensoresToAllLeaves(nodoTr3);
		
		NodoDiagnostico nodoTr90 = new NodoDiagnostico(nodoTr75, "arteria radial", true);
		NodoDiagnostico nodoTr91 = new NodoDiagnostico(nodoTr75, "arteria cubital", true);
		NodoDiagnostico nodoTr92 = new NodoDiagnostico(nodoTr75, "arteria humeral", true);
		nodoTr75.getChildren().addAll(Lists.newArrayList(nodoTr90,nodoTr91,nodoTr92));
		
		NodoDiagnostico nodoTr93 = new NodoDiagnostico(nodoTr76, "palmar mayor", true);
		NodoDiagnostico nodoTr94 = new NodoDiagnostico(nodoTr76, "cubital ant", true);
		NodoDiagnostico nodoTr95 = new NodoDiagnostico(nodoTr76, "1er radial", true);
		NodoDiagnostico nodoTr96 = new NodoDiagnostico(nodoTr76, "2do radial", true);
		NodoDiagnostico nodoTrN8 = new NodoDiagnostico(nodoTr76, "abeductor largo pulgar", true);
		NodoDiagnostico nodoTrN9 = new NodoDiagnostico(nodoTr76, "extensor corto pulgar", true);
		NodoDiagnostico nodoTrN10 = new NodoDiagnostico(nodoTr76, "cubital posterior", true);
		NodoDiagnostico nodoTr97 = new NodoDiagnostico(nodoTr76, "biceps", true);
		NodoDiagnostico nodoTrN11 = new NodoDiagnostico(nodoTr76, "manguito rotador", true);
		NodoDiagnostico nodoTr98 = new NodoDiagnostico(nodoTr76, "otros", true);
		nodoTr76.getChildren().addAll(Lists.newArrayList(nodoTr93,nodoTr94,nodoTr95,nodoTr96,nodoTrN8,nodoTrN9,nodoTrN10,nodoTr97,nodoTrN11,nodoTr98));

		NodoDiagnostico nodoTr99 = new NodoDiagnostico(nodoTr14, "dedos");
		NodoDiagnostico nodoTr100 = new NodoDiagnostico(nodoTr14, "mano", true);
		nodoTr14.getChildren().addAll(Lists.newArrayList(nodoTr99,nodoTr100));
		
		addUbicaciones(nodoTr99, true, false, false, false, false, false, false, false);
		
		NodoDiagnostico nodoTr101 = new NodoDiagnostico(nodoTr100, "nivel metacarpiano");
		NodoDiagnostico nodoTr102 = new NodoDiagnostico(nodoTr100, "nivel muñeva o proximal");
		nodoTr100.getChildren().addAll(Lists.newArrayList(nodoTr101,nodoTr102));
		
		//Inflamatoria
		NodoDiagnostico nodoInfl1 = new NodoDiagnostico(nodo3, "tenosinovitis", true);
		NodoDiagnostico nodoInfl2 = new NodoDiagnostico(nodo3, "epicondilitis", true);
		NodoDiagnostico nodoInfl3 = new NodoDiagnostico(nodo3, "epitrocleitis", true);
		NodoDiagnostico nodoInfl4 = new NodoDiagnostico(nodo3, "bursitis codo", true);
		NodoDiagnostico nodoInfl5 = new NodoDiagnostico(nodo3, "sinovitis articular inespecífica", true);
		NodoDiagnostico nodoInfl6 = new NodoDiagnostico(nodo3, "AR", true);
		//NodoDiagnostico nodoInfl7 = new NodoDiagnostico(nodo3, "otras");
		nodo3.getChildren().addAll(Lists.newArrayList(nodoInfl1,nodoInfl2,nodoInfl3,nodoInfl4,nodoInfl5,nodoInfl6/*,nodoInfl7*/));
		
		NodoDiagnostico nodoInfl8 = new NodoDiagnostico(nodoInfl1, "digital", true);
		NodoDiagnostico nodoInfl9 = new NodoDiagnostico(nodoInfl1, "De Quervain", true);
		NodoDiagnostico nodoInfl10 = new NodoDiagnostico(nodoInfl1, "volar de muñeca sin STC", true);
		NodoDiagnostico nodoInfl11 = new NodoDiagnostico(nodoInfl1, "dorsal de munñeca", true);
		NodoDiagnostico nodoInfl12 = new NodoDiagnostico(nodoInfl1, "cubital ant", true);
		NodoDiagnostico nodoInfl13 = new NodoDiagnostico(nodoInfl1, "palmar >", true);
		NodoDiagnostico nodoInfl14 = new NodoDiagnostico(nodoInfl1, "cubital post");
		nodoInfl1.getChildren().addAll(Lists.newArrayList(nodoInfl8,nodoInfl9,nodoInfl10,nodoInfl11,nodoInfl12,nodoInfl13,nodoInfl14));
		
		NodoDiagnostico nodoInfl15 = new NodoDiagnostico(nodoInfl8, "con gatillo", true);
		NodoDiagnostico nodoInfl16 = new NodoDiagnostico(nodoInfl8, "sin gatillo", true);
		NodoDiagnostico nodoInfl17 = new NodoDiagnostico(nodoInfl8, "digital completa", true);
		nodoInfl8.getChildren().addAll(Lists.newArrayList(nodoInfl15,nodoInfl16,nodoInfl17));
		
		addUbicaciones(nodoInfl15, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInfl16, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInfl17, true, false, false, false, false, false, false, false);
		
		addUbicacionesRaro(nodoInfl5);

		//Infecciosa
		NodoDiagnostico nodoInf1= new NodoDiagnostico(nodo5, "aguda");
		NodoDiagnostico nodoInf2 = new NodoDiagnostico(nodo5, "crónica");
		nodo5.getChildren().addAll(Lists.newArrayList(nodoInf1,nodoInf2));
		
		addInfecciosas(nodoInf1);
		addInfecciosas(nodoInf2);
		
		
		//Tumoral
		NodoDiagnostico nodoTum1 = new NodoDiagnostico(nodo6, "lesion seudotumoral");
		NodoDiagnostico nodoTum2 = new NodoDiagnostico(nodo6, "tumor benigno");
		NodoDiagnostico nodoTum3 = new NodoDiagnostico(nodo6, "tumor  maligno");
		nodo6.getChildren().addAll(Lists.newArrayList(nodoTum1,nodoTum2,nodoTum3));
		
		NodoDiagnostico nodoTum4 = new NodoDiagnostico(nodoTum1, "partes blandas");
		NodoDiagnostico nodoTum5 = new NodoDiagnostico(nodoTum1, "oseos");
		nodoTum1.getChildren().addAll(Lists.newArrayList(nodoTum4,nodoTum5));
		
		NodoDiagnostico nodoTum6 = new NodoDiagnostico(nodoTum4, "ganglion", true);
		NodoDiagnostico nodoTum7 = new NodoDiagnostico(nodoTum4, "carpo giboso", true);
		NodoDiagnostico nodoTum8 = new NodoDiagnostico(nodoTum4, "quiste mucoide", true);
		NodoDiagnostico nodoTum9 = new NodoDiagnostico(nodoTum4, "quiste de vaina", true);
		NodoDiagnostico nodoTum10 = new NodoDiagnostico(nodoTum4, "quiste epidermoide", true);
		NodoDiagnostico nodoTum11 = new NodoDiagnostico(nodoTum4, "granuloma por cuerpo extraño", true);
		NodoDiagnostico nodoTum12 = new NodoDiagnostico(nodoTum4, "botriomicoma", true);
		NodoDiagnostico nodoTum13 = new NodoDiagnostico(nodoTum4, "tofos", true);
		nodoTum4.getChildren().addAll(Lists.newArrayList(nodoTum6,nodoTum7,nodoTum8,nodoTum9,nodoTum10,nodoTum11,nodoTum12,nodoTum13));
		
		NodoDiagnostico nodoTum14 = new NodoDiagnostico(nodoTum6, "dorsal de muñeca", true);
		NodoDiagnostico nodoTum15 = new NodoDiagnostico(nodoTum6, "volar de muñeca", true);
		NodoDiagnostico nodoTum16 = new NodoDiagnostico(nodoTum6, "en guyon", true);
		NodoDiagnostico nodoTum17 = new NodoDiagnostico(nodoTum6, "pulgar", true);
		NodoDiagnostico nodoTum18 = new NodoDiagnostico(nodoTum6, "II", true);
		NodoDiagnostico nodoTum19 = new NodoDiagnostico(nodoTum6, "III", true);
		NodoDiagnostico nodoTum20 = new NodoDiagnostico(nodoTum6, "IV", true);
		NodoDiagnostico nodoTum21 = new NodoDiagnostico(nodoTum6, "V", true);
		nodoTum6.getChildren().addAll(Lists.newArrayList(nodoTum14,nodoTum15,nodoTum16,nodoTum17,nodoTum18,nodoTum19,nodoTum20,nodoTum21));
		
		addTmtcMtcfIf(nodoTum17);
		addMtcfIfdIfp(nodoTum18);
		addMtcfIfdIfp(nodoTum19);
		addMtcfIfdIfp(nodoTum20);
		addMtcfIfdIfp(nodoTum21);
		
		addUbicaciones(nodoTum8, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoTum9, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoTum10, true, true, false, false, false, false, true, false);		
		addUbicaciones(nodoTum11, true, true, false, false, true, true, true, false);
		addUbicaciones(nodoTum12, true, true, false, false, false, false, true, false);
		addUbicaciones(nodoTum13, true, true, false, true, false, false, true, true);

		NodoDiagnostico nodoTum37 = new NodoDiagnostico(nodoTum5, "quiste oseo simple", true);
		NodoDiagnostico nodoTum38 = new NodoDiagnostico(nodoTum5, "quiste oseo aneurismatico", true);
		NodoDiagnostico nodoTum39 = new NodoDiagnostico(nodoTum5, "ganglion intra oseo", true);
		NodoDiagnostico nodoTum40 = new NodoDiagnostico(nodoTum5, "displasia fibrosa", true);
		NodoDiagnostico nodoTum41 = new NodoDiagnostico(nodoTum5, "granuloma eosinofilo", true);
		NodoDiagnostico nodoTum42 = new NodoDiagnostico(nodoTum5, "fibroma no osificante", true);
		NodoDiagnostico nodoTum43 = new NodoDiagnostico(nodoTum5, "miositis osificante", true);
		NodoDiagnostico nodoTum44 = new NodoDiagnostico(nodoTum5, "tumor pardo", true);
		nodoTum5.getChildren().addAll(Lists.newArrayList(nodoTum37,nodoTum38,nodoTum39,nodoTum40,nodoTum41,nodoTum42,nodoTum43,nodoTum44));
		
		addToOseo(nodoTum37, true, false);
		addToOseo(nodoTum38, true, false);
		addToOseo(nodoTum39, true, false);
		addToOseo(nodoTum40, true, false);
		addToOseo(nodoTum41, true, false);
		addToOseo(nodoTum42, true, false);
		addToOseo(nodoTum43, true, false);
		addToOseo(nodoTum44, true, false);
		
		NodoDiagnostico nodoTum45 = new NodoDiagnostico(nodoTum2, "partes blandas");
		NodoDiagnostico nodoTum46 = new NodoDiagnostico(nodoTum2, "óseo");
		nodoTum2.getChildren().addAll(Lists.newArrayList(nodoTum45,nodoTum46));
		
		NodoDiagnostico nodoTum47 = new NodoDiagnostico(nodoTum46, "encondroma", true);
		NodoDiagnostico nodoTum48 = new NodoDiagnostico(nodoTum46, "osteocondroma", true);
		NodoDiagnostico nodoTum49 = new NodoDiagnostico(nodoTum46, "osteoma osteoide", true);
		NodoDiagnostico nodoTum50 = new NodoDiagnostico(nodoTum46, "TCG oseo", true);
		NodoDiagnostico nodoTum51 = new NodoDiagnostico(nodoTum46, "otros", true);
		nodoTum46.getChildren().addAll(Lists.newArrayList(nodoTum47,nodoTum48,nodoTum49,nodoTum50,nodoTum51));
		
		addToOseo(nodoTum47, true, false);
		addToOseo(nodoTum48, true, false);
		addToOseo(nodoTum49, true, false);
		addToOseo(nodoTum50, true, false);
		addToOseo(nodoTum51, true, false);
		
		NodoDiagnostico nodoTum157 = new NodoDiagnostico(nodoTum45, "TCG", true);
		NodoDiagnostico nodoTum158 = new NodoDiagnostico(nodoTum45, "lipoma", true);
		NodoDiagnostico nodoTum159 = new NodoDiagnostico(nodoTum45, "hemangioma", true);
		NodoDiagnostico nodoTum160 = new NodoDiagnostico(nodoTum45, "fibroma", true);
		NodoDiagnostico nodoTum161 = new NodoDiagnostico(nodoTum45, "neurinoma", true);
		NodoDiagnostico nodoTum162 = new NodoDiagnostico(nodoTum45, "tumor glomico", true);
		NodoDiagnostico nodoTum163 = new NodoDiagnostico(nodoTum45, "otros", true);
		nodoTum45.getChildren().addAll(Lists.newArrayList(nodoTum157,nodoTum158,nodoTum159,nodoTum160,nodoTum161,nodoTum162,nodoTum163));
		
		addUbicaciones(nodoTum157, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum158, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum159, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum160, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum161, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum162, true, true, true, true, true, true, true, false);
		addUbicaciones(nodoTum163, true, true, true, true, true, true, true, false);
		
		NodoDiagnostico nodoTum164 = new NodoDiagnostico(nodoTum3, "partes blandas");
		NodoDiagnostico nodoTum165 = new NodoDiagnostico(nodoTum3, "óseo");
		nodoTum3.getChildren().addAll(Lists.newArrayList(nodoTum164,nodoTum165));
		
		NodoDiagnostico nodoTum166 = new NodoDiagnostico(nodoTum164, "CA epidermoide", true);
		NodoDiagnostico nodoTum167 = new NodoDiagnostico(nodoTum164, "melanoma", true);
		NodoDiagnostico nodoTum168 = new NodoDiagnostico(nodoTum164, "liposarcoma", true);
		NodoDiagnostico nodoTum169 = new NodoDiagnostico(nodoTum164, "fibrosarcoma", true);
		NodoDiagnostico nodoTum170 = new NodoDiagnostico(nodoTum164, "hemangiosarcoma", true);
		NodoDiagnostico nodoTum171 = new NodoDiagnostico(nodoTum164, "neurosarcoma", true);
		NodoDiagnostico nodoTum172 = new NodoDiagnostico(nodoTum164, "sarcoma sinovial", true);
		NodoDiagnostico nodoTum173 = new NodoDiagnostico(nodoTum164, "otros", true);
		nodoTum164.getChildren().addAll(Lists.newArrayList(nodoTum166,nodoTum167,nodoTum168,nodoTum169,nodoTum170,nodoTum171,nodoTum172,nodoTum173));
		
		addUbicaciones(nodoTum166, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum167, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum168, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum169, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum170, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum171, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum172, true, true, true, true, true, true, true, true);
		addUbicaciones(nodoTum173, true, true, true, true, true, true, true, true);	
		
		NodoDiagnostico nodoTum174 = new NodoDiagnostico(nodoTum165, "condrosarcoma", true);
		NodoDiagnostico nodoTum175 = new NodoDiagnostico(nodoTum165, "osteosarcoma", true);
		NodoDiagnostico nodoTum176 = new NodoDiagnostico(nodoTum165, "sarcoma de Ewing", true);
		NodoDiagnostico nodoTum177 = new NodoDiagnostico(nodoTum165, "otros", true);
		nodoTum165.getChildren().addAll(Lists.newArrayList(nodoTum174,nodoTum175,nodoTum176,nodoTum177));
		addToOseo(nodoTum174, false, false);
		addToOseo(nodoTum175, false, false);
		addToOseo(nodoTum176, false, false);
		addToOseo(nodoTum177, false, false);


		//Miscelaneas
		NodoDiagnostico nodoMisc1 = new NodoDiagnostico(nodo9, "Dupuytren", true);
		NodoDiagnostico nodoMisc2 = new NodoDiagnostico(nodo9, "Kienbock", true);
		NodoDiagnostico nodoMisc3 = new NodoDiagnostico(nodo9, "Preisser", true);
		NodoDiagnostico nodoMisc4 = new NodoDiagnostico(nodo9, "Polineuritis", true);
		NodoDiagnostico nodoMisc5 = new NodoDiagnostico(nodo9, "PC", true);
		NodoDiagnostico nodoMisc6 = new NodoDiagnostico(nodo9, "Parkinson", true);
		NodoDiagnostico nodoMisc7 = new NodoDiagnostico(nodo9, "Parálisis", true);
		NodoDiagnostico nodoMisc8 = new NodoDiagnostico(nodo9, "otros", true);
		nodo9.getChildren().addAll(Lists.newArrayList(nodoMisc1, nodoMisc2, nodoMisc3, nodoMisc4, nodoMisc5, nodoMisc6, nodoMisc7, nodoMisc8));
		
		NodoDiagnostico nodoMisc9 = new NodoDiagnostico(nodoMisc1, "Grado 0", true);
		NodoDiagnostico nodoMisc10 = new NodoDiagnostico(nodoMisc1, "Grado 1", true);
		NodoDiagnostico nodoMisc11 = new NodoDiagnostico(nodoMisc1, "Grado 2", true);
		NodoDiagnostico nodoMisc12 = new NodoDiagnostico(nodoMisc1, "Grado 3", true);
		NodoDiagnostico nodoMisc13 = new NodoDiagnostico(nodoMisc1, "Grado 4", true);
		nodoMisc1.getChildren().addAll(Lists.newArrayList(nodoMisc9, nodoMisc10, nodoMisc11, nodoMisc12, nodoMisc13));
		
		NodoDiagnostico nodoMisc14 = new NodoDiagnostico(nodoMisc10, "5to rayo", true);
		NodoDiagnostico nodoMisc15 = new NodoDiagnostico(nodoMisc10, "4to rayo", true);
		NodoDiagnostico nodoMisc16 = new NodoDiagnostico(nodoMisc10, "3er rayo", true);
		NodoDiagnostico nodoMisc17 = new NodoDiagnostico(nodoMisc10, "2do rayo", true);
		NodoDiagnostico nodoMisc18 = new NodoDiagnostico(nodoMisc10, "1er rayo", true);
		nodoMisc10.getChildren().addAll(Lists.newArrayList(nodoMisc14, nodoMisc15, nodoMisc16, nodoMisc17, nodoMisc18));
		
		NodoDiagnostico nodoMisc19 = new NodoDiagnostico(nodoMisc11, "5to rayo", true);
		NodoDiagnostico nodoMisc20 = new NodoDiagnostico(nodoMisc11, "4to rayo", true);
		NodoDiagnostico nodoMisc21 = new NodoDiagnostico(nodoMisc11, "3er rayo", true);
		NodoDiagnostico nodoMisc22 = new NodoDiagnostico(nodoMisc11, "2do rayo", true);
		NodoDiagnostico nodoMisc23 = new NodoDiagnostico(nodoMisc11, "1er rayo", true);
		nodoMisc11.getChildren().addAll(Lists.newArrayList(nodoMisc19, nodoMisc20, nodoMisc21, nodoMisc22, nodoMisc23));
		
		NodoDiagnostico nodoMisc24 = new NodoDiagnostico(nodoMisc12, "5to rayo", true);
		NodoDiagnostico nodoMisc25 = new NodoDiagnostico(nodoMisc12, "4to rayo", true);
		NodoDiagnostico nodoMisc26 = new NodoDiagnostico(nodoMisc12, "3er rayo", true);
		NodoDiagnostico nodoMisc27 = new NodoDiagnostico(nodoMisc12, "2do rayo", true);
		NodoDiagnostico nodoMisc28 = new NodoDiagnostico(nodoMisc12, "1er rayo", true);
		nodoMisc12.getChildren().addAll(Lists.newArrayList(nodoMisc24, nodoMisc25, nodoMisc26, nodoMisc27, nodoMisc28));
		
		NodoDiagnostico nodoMisc29 = new NodoDiagnostico(nodoMisc13, "5to rayo", true);
		NodoDiagnostico nodoMisc30 = new NodoDiagnostico(nodoMisc13, "4to rayo", true);
		NodoDiagnostico nodoMisc31 = new NodoDiagnostico(nodoMisc13, "3er rayo", true);
		NodoDiagnostico nodoMisc32 = new NodoDiagnostico(nodoMisc13, "2do rayo", true);
		NodoDiagnostico nodoMisc33 = new NodoDiagnostico(nodoMisc13, "1er rayo", true);
		nodoMisc13.getChildren().addAll(Lists.newArrayList(nodoMisc29, nodoMisc30, nodoMisc31, nodoMisc32, nodoMisc33));		
		
		//Degenerativa
		NodoDiagnostico nodoDeg1 = new NodoDiagnostico(nodo4, "artrosis", true);
		NodoDiagnostico nodoDeg2 = new NodoDiagnostico(nodo4, "otras", true);
		nodo4.getChildren().addAll(Lists.newArrayList(nodoDeg1,nodoDeg2));
		
		NodoDiagnostico nodoDeg3 = new NodoDiagnostico(nodoDeg1, "codo", true);
		NodoDiagnostico nodoDeg4 = new NodoDiagnostico(nodoDeg1, "R-Carpiana", true);
		NodoDiagnostico nodoDeg5 = new NodoDiagnostico(nodoDeg1, "RCI", true);
		NodoDiagnostico nodoDeg6 = new NodoDiagnostico(nodoDeg1, "Cubito-carpiana", true);
		NodoDiagnostico nodoDeg7 = new NodoDiagnostico(nodoDeg1, "intercarpiana", true);
		NodoDiagnostico nodoDeg8 = new NodoDiagnostico(nodoDeg1, "pulgar", true);
		NodoDiagnostico nodoDeg9 = new NodoDiagnostico(nodoDeg1, "II", true);
		NodoDiagnostico nodoDeg10 = new NodoDiagnostico(nodoDeg1, "III", true);
		NodoDiagnostico nodoDeg11 = new NodoDiagnostico(nodoDeg1, "IV", true);
		NodoDiagnostico nodoDeg12 = new NodoDiagnostico(nodoDeg1, "V", true);
		NodoDiagnostico nodoDeg13 = new NodoDiagnostico(nodoDeg1, "glenohumeral", true);
		NodoDiagnostico nodoDegN1 = new NodoDiagnostico(nodoDeg1, "acromioclavicular", true);
		nodoDeg1.getChildren().addAll(Lists.newArrayList(nodoDeg3,nodoDeg4,nodoDeg5,nodoDeg6,nodoDeg7,nodoDeg8,nodoDeg9,nodoDeg10,nodoDeg11,nodoDeg12,nodoDeg13,nodoDegN1));
		
		NodoDiagnostico nodoDeg14 = new NodoDiagnostico(nodoDeg3, "humero-cubital");
		NodoDiagnostico nodoDeg15 = new NodoDiagnostico(nodoDeg3, "humero-radial");
		NodoDiagnostico nodoDeg16 = new NodoDiagnostico(nodoDeg3, "RCS");
		NodoDiagnostico nodoDeg17 = new NodoDiagnostico(nodoDeg3, "combinada");
		nodoDeg3.getChildren().addAll(Lists.newArrayList(nodoDeg14,nodoDeg15,nodoDeg16,nodoDeg17));
		
		NodoDiagnostico nodoDeg18 = new NodoDiagnostico(nodoDeg7, "trapecio escafoidea");
		NodoDiagnostico nodoDeg19 = new NodoDiagnostico(nodoDeg7, "panartrosis del carpo");
		nodoDeg7.getChildren().addAll(Lists.newArrayList(nodoDeg18,nodoDeg19));
		
		addTmtcMtcfIf(nodoDeg8);
		addMtcfIfdIfp(nodoDeg9);
		addMtcfIfdIfp(nodoDeg10);
		addMtcfIfdIfp(nodoDeg11);
		addMtcfIfdIfp(nodoDeg12);
		
		//Síndromes compresivos
		NodoDiagnostico nodoSC1 = new NodoDiagnostico(nodo7, "STC", true);
		NodoDiagnostico nodoSC2 = new NodoDiagnostico(nodo7, "pronador redondo", true);
		NodoDiagnostico nodoSC3 = new NodoDiagnostico(nodo7, "interoseo ant", true);
		NodoDiagnostico nodoSC4 = new NodoDiagnostico(nodo7, "neurodocitis cubital", true);
		NodoDiagnostico nodoSC5 = new NodoDiagnostico(nodo7, "canal de guyon", true);
		NodoDiagnostico nodoSC6 = new NodoDiagnostico(nodo7, "tunel radial", true);
		NodoDiagnostico nodoSC7 = new NodoDiagnostico(nodo7, "interoseo posterior", true);
		NodoDiagnostico nodoSC8 = new NodoDiagnostico(nodo7, "sme de interseccion  (Wartenberg)", true);
		NodoDiagnostico nodoSC9 = new NodoDiagnostico(nodo7, "operculo toracico", true);
		nodo7.getChildren().addAll(Lists.newArrayList(nodoSC1,nodoSC2,nodoSC3,nodoSC4,nodoSC5,nodoSC6,nodoSC7,nodoSC8,nodoSC9));
		
		NodoDiagnostico nodoSC10 = new NodoDiagnostico(nodoSC9, "sme del escaleno");
		NodoDiagnostico nodoSC11 = new NodoDiagnostico(nodoSC9, "costilla cervical");
		NodoDiagnostico nodoSC12 = new NodoDiagnostico(nodoSC9, "otros");
		NodoDiagnostico nodoSC13 = new NodoDiagnostico(nodoSC9, "por radioterapia");
		nodoSC9.getChildren().addAll(Lists.newArrayList(nodoSC10,nodoSC11,nodoSC12,nodoSC13));
		
		return root;
	}
	
	private static void completeFlexores(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "superficial", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "profundo", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "ambos", true);
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "Flexor largo del pulgar (FPL)", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
		
		addUbicaciones(nodoTr1, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoTr2, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoTr3, true, false, false, false, false, false, false, false);
		
		addZonasToAllLeaves(nodo);
		
	}

	private static void completarDeformidadesDigitales(NodoDiagnostico nodoDD, boolean conPulgar) {
		NodoDiagnostico nodoDD1 = new NodoDiagnostico(nodoDD, "II", true);
		NodoDiagnostico nodoDD2 = new NodoDiagnostico(nodoDD, "III", true);
		NodoDiagnostico nodoDD3 = new NodoDiagnostico(nodoDD, "IV", true);
		NodoDiagnostico nodoDD4 = new NodoDiagnostico(nodoDD, "V", true);
		if(conPulgar){
			NodoDiagnostico nodoDD5 = new NodoDiagnostico(nodoDD, "pulgar", true);
			nodoDD.getChildren().addAll(Lists.newArrayList(nodoDD1, nodoDD2, nodoDD3, nodoDD4, nodoDD5));
		}
		else{
			nodoDD.getChildren().addAll(Lists.newArrayList(nodoDD1, nodoDD2, nodoDD3, nodoDD4));
		}
		
	}

	private static void completeExtensoresToAllLeaves(NodoDiagnostico nodo) {
		addUbicaciones(nodo, true, false, false, false, false, false, false, false);
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			if(leaf.getLabel().equals("pulgar")){
				NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "zona 1", true);
				NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "zona 2", true);
				NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "zona 3", true);
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "zona 4", true);
				NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "zona 5", true);
				leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5));
			}
			else if(leaf.getLabel().equals("II") || leaf.getLabel().equals("III") || leaf.getLabel().equals("IV") 
					|| leaf.getLabel().equals("V")){
				NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "zona 1 mallet", true);
				NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "zona 2", true);
				NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "zona 3 boutonniere", true);
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "zona 4", true);
				NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "zona 5 hood", true);
				NodoDiagnostico nodoTr6 = new NodoDiagnostico(leaf, "zona 6 dorso mano", true);
				NodoDiagnostico nodoTr7 = new NodoDiagnostico(leaf, "zona 7 mnc", true);
				NodoDiagnostico nodoTr8 = new NodoDiagnostico(leaf, "zona 8 antebrazo", true);
				leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7,nodoTr8));
			}
		}			
	}
		
	private static void addZonasToAllLeaves(NodoDiagnostico nodo) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "zona I", true);
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "zona II", true);
			NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "zona III", true);
			NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "zona IV", true);
			NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "zona V", true);
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5));
		}			
	}

	private static void completeNervios(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "colaterales", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "tronculares", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "radial", true);
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "mediano", true);
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodo, "cubital", true);
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodo, "plexo", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6));
		
		NodoDiagnostico nodoTr7 = new NodoDiagnostico(nodo, "pulgar", true);
		NodoDiagnostico nodoTr8 = new NodoDiagnostico(nodo, "II", true);
		NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodo, "III", true);
		NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodo, "IV", true);
		NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodo, "V", true);
		nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr7,nodoTr8,nodoTr9,nodoTr10,nodoTr11));
		
		addRadialCubitalAmbos(nodoTr7);
		addRadialCubitalAmbos(nodoTr8);
		addRadialCubitalAmbos(nodoTr9);
		addRadialCubitalAmbos(nodoTr10);
		addRadialCubitalAmbos(nodoTr11);
		
		NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodoTr2, "2do espacio", true);
		NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodoTr2, "3er espacio", true);
		NodoDiagnostico nodoTr14 = new NodoDiagnostico(nodoTr2, "4to espacio", true);
		nodoTr2.getChildren().addAll(Lists.newArrayList(nodoTr12,nodoTr13,nodoTr14));
		
		NodoDiagnostico nodoTr15 = new NodoDiagnostico(nodoTr3, "completo", true);
		NodoDiagnostico nodoTr16 = new NodoDiagnostico(nodoTr3, "interoseo posterior", true);
		NodoDiagnostico nodoTr17 = new NodoDiagnostico(nodoTr3, "rama sensitiva", true);
		nodoTr3.getChildren().addAll(Lists.newArrayList(nodoTr15,nodoTr16,nodoTr17));
		
		NodoDiagnostico nodoTr18 = new NodoDiagnostico(nodoTr4, "en brazo", true);
		NodoDiagnostico nodoTr19 = new NodoDiagnostico(nodoTr4, "en codo", true);
		NodoDiagnostico nodoTr20 = new NodoDiagnostico(nodoTr4, "en antebrazo", true);
		NodoDiagnostico nodoTr21 = new NodoDiagnostico(nodoTr4, "en mnc", true);
		NodoDiagnostico nodoTr22 = new NodoDiagnostico(nodoTr4, "rama tenar", true);
		NodoDiagnostico nodoTr23 = new NodoDiagnostico(nodoTr4, "rama palmar cutanea", true);
		NodoDiagnostico nodoTr24 = new NodoDiagnostico(nodoTr4, "interoseo anterior", true);
		nodoTr4.getChildren().addAll(Lists.newArrayList(nodoTr18,nodoTr19,nodoTr20,nodoTr21,nodoTr22,nodoTr23,nodoTr24));
		
		NodoDiagnostico nodoTr25 = new NodoDiagnostico(nodoTr5, "en brazo", true);
		NodoDiagnostico nodoTr26 = new NodoDiagnostico(nodoTr5, "en codo", true);
		NodoDiagnostico nodoTr27 = new NodoDiagnostico(nodoTr5, "en antebrazo", true);
		NodoDiagnostico nodoTr28 = new NodoDiagnostico(nodoTr5, "en mnc", true);
		NodoDiagnostico nodoTr29 = new NodoDiagnostico(nodoTr5, "en guyon", true);
		NodoDiagnostico nodoTr30 = new NodoDiagnostico(nodoTr5, "rama motora", true);
		NodoDiagnostico nodoTr31 = new NodoDiagnostico(nodoTr5, "rama sensitiva dorsal", true);
		nodoTr5.getChildren().addAll(Lists.newArrayList(nodoTr25,nodoTr26,nodoTr27,nodoTr28,nodoTr29,nodoTr30,nodoTr31));
	}
	
	private static void addRadialCubitalAmbos(NodoDiagnostico nodo){
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "radial", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "cubital", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "ambos", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void completeDesguantamiento(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "superficie < a 5 cm ");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "superficie > a 5 cm ");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "superficie > a 15 cm ");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
		
		addUbicaciones(nodoTr1, true, false, true, true, true, true, false, false);
		addUbicaciones(nodoTr2, true, false, true, true, true, true, false, false);
		addUbicaciones(nodoTr3, true, false, true, true, true, true, false, false);
	}

	private static void completePartesBlandas(NodoDiagnostico nodo, Boolean esDefectoTegumentario, Boolean esQuemaduras, Boolean conHombro) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "superficie < a 1 cm ");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "superficie > a 1 cm ");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "superficie > a 5 cm ");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
		
		addUbicaciones(nodoTr1, true, !esQuemaduras, true, true, true, true, false, conHombro);
		addUbicaciones(nodoTr2, true, !esQuemaduras, true, true, true, true, false, conHombro);
		addUbicaciones(nodoTr3, true, !esQuemaduras, true, true, true, true, false, conHombro);
		
		for (NodoDiagnostico children : getLeaves(nodo.getChildren(), nodo)) {
			if(children.getLabel().equals("pulgar") || children.getLabel().equals("II") || children.getLabel().equals("III") 
					|| children.getLabel().equals("IV")	|| children.getLabel().equals("V")){
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(children, "dedo");
				NodoDiagnostico nodoTr5 = new NodoDiagnostico(children, "pulpejo");
				if(esDefectoTegumentario || esQuemaduras){
					NodoDiagnostico nodoTr6 = new NodoDiagnostico(children, "unia");
					children.getChildren().addAll(Lists.newArrayList(nodoTr4,nodoTr5,nodoTr6));
					
					NodoDiagnostico nodoTr7 = new NodoDiagnostico(nodoTr5, "< 25%");
					NodoDiagnostico nodoTr8 = new NodoDiagnostico(nodoTr5, ">25%");
					nodoTr5.getChildren().addAll(Lists.newArrayList(nodoTr7,nodoTr8));
					
				}
				else{
					children.getChildren().addAll(Lists.newArrayList(nodoTr4,nodoTr5));
				}
			}
		}
		
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "volar");
			NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "dorsal");
			NodoDiagnostico nodoTr6 = new NodoDiagnostico(leaf, "lateral");
			NodoDiagnostico nodoTr7 = new NodoDiagnostico(leaf, "combinado");
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr4,nodoTr5,nodoTr6,nodoTr7));
		}
	}

	private static void completeAmputaciones(NodoDiagnostico nodo) {
		NodoDiagnostico nodoN1 = new NodoDiagnostico(nodo, "pulgar", true);
		NodoDiagnostico nodoN2 = new NodoDiagnostico(nodo, "II", true);
		NodoDiagnostico nodoN3 = new NodoDiagnostico(nodo, "III", true);
		NodoDiagnostico nodoN4 = new NodoDiagnostico(nodo, "IV", true);
		NodoDiagnostico nodoN5 = new NodoDiagnostico(nodo, "V", true);
		NodoDiagnostico nodoN6 = new NodoDiagnostico(nodo, "desarticulación muñeca", true);
		NodoDiagnostico nodoN7 = new NodoDiagnostico(nodo, "desarticulación codo", true);
		NodoDiagnostico nodoN8 = new NodoDiagnostico(nodo, "nivel brazo", true);
		NodoDiagnostico nodoN9 = new NodoDiagnostico(nodo, "nivel antebrazo", true);
		NodoDiagnostico nodoN10 = new NodoDiagnostico(nodo, "desarticulación muñeca", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoN1, nodoN2, nodoN3, nodoN4, nodoN5, nodoN6, nodoN7, nodoN8, nodoN9, nodoN10));
		
		for (NodoDiagnostico children : nodo.getChildren()) {
			if(children.getLabel().equals("pulgar")){
				NodoDiagnostico nodoTr1 = new NodoDiagnostico(children, "nivel T-MTC", true);
				NodoDiagnostico nodoTr2 = new NodoDiagnostico(children, "nivel MTC", true);
				NodoDiagnostico nodoTr3 = new NodoDiagnostico(children, "nivel MTC-F", true);
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(children, "nivel F1", true);
				NodoDiagnostico nodoTr5 = new NodoDiagnostico(children, "nivel IF", true);
				NodoDiagnostico nodoTr6 = new NodoDiagnostico(children, "nivel F2", true);
				NodoDiagnostico nodoTr7 = new NodoDiagnostico(children, "nivel pulpejo", true);
				children.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7));
			}
			else if(children.getLabel().equals("II") || children.getLabel().equals("III") || children.getLabel().equals("IV") 
					|| children.getLabel().equals("V")){
				NodoDiagnostico nodoTr1 = new NodoDiagnostico(children, "nivel C-MTC", true);
				NodoDiagnostico nodoTr2 = new NodoDiagnostico(children, "nivel MTC", true);
				NodoDiagnostico nodoTr3 = new NodoDiagnostico(children, "nivel MTC-F", true);
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(children, "nivel F1", true);
				NodoDiagnostico nodoTr5 = new NodoDiagnostico(children, "nivel IFP", true);
				NodoDiagnostico nodoTr6 = new NodoDiagnostico(children, "nivel F2", true);
				NodoDiagnostico nodoTr7 = new NodoDiagnostico(children, "nivel IFD", true);
				NodoDiagnostico nodoTr8 = new NodoDiagnostico(children, "nivel F3", true);
				NodoDiagnostico nodoTr9 = new NodoDiagnostico(children, "nivel pulpejo", true);
				children.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7,nodoTr8,nodoTr9));
			}
		}
	}

	private static void completeRigideces(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "codo", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "muñeca", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "medio-carpiana", true);
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "pulgar", true);
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodo, "II", true);
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodo, "III", true);
		NodoDiagnostico nodoTr7 = new NodoDiagnostico(nodo, "IV", true);
		NodoDiagnostico nodoTr8 = new NodoDiagnostico(nodo, "V", true);
		NodoDiagnostico nodoTrN1 = new NodoDiagnostico(nodo, "mano completa", true);
		NodoDiagnostico nodoTrN2 = new NodoDiagnostico(nodo, "hombro", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7,nodoTr8,nodoTrN1,nodoTrN2));
		
		NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodoTr1, "flexo-ext");
		NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodoTr1, "prono-sup");
		NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodoTr1, "ambas");
		nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr9,nodoTr10,nodoTr11));
		
		NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodoTr2, "flexo-ext");
		NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodoTr2, "prono-sup");
		NodoDiagnostico nodoTr14 = new NodoDiagnostico(nodoTr2, "ambas");
		nodoTr2.getChildren().addAll(Lists.newArrayList(nodoTr12,nodoTr13,nodoTr14));
		
		addTmtcMtcfIf(nodoTr4);
		addMtcfIfdIfp(nodoTr5);
		addMtcfIfdIfp(nodoTr6);
		addMtcfIfdIfp(nodoTr7);
		addMtcfIfdIfp(nodoTr8);
	}

	private static void addTroficaToAllLeaves(NodoDiagnostico nodo) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "atrofica");
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "eutrofica");
			NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "hipertrofica");
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
		}	
	}

	private static void completeConsVicSeudoartrosis(NodoDiagnostico nodo) {
		addToOseo(nodo, false, false);
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			if(leaf.getLabel().equals("MTC")){
				NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "base");
				NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "diafisis");
				NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "cuello");
				NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "cabeza");
				leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
			}
		}
	}

	private static void completeLuxacionesToAllLeaves(NodoDiagnostico nodo) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "codo", true);
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "muñeca", true);
			NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "carpo", true);
			NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "pulgar", true);
			NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "II", true);
			NodoDiagnostico nodoTr6 = new NodoDiagnostico(leaf, "III", true);
			NodoDiagnostico nodoTr7 = new NodoDiagnostico(leaf, "IV", true);
			NodoDiagnostico nodoTr8 = new NodoDiagnostico(leaf, "V", true);
			NodoDiagnostico nodoTrN1 = new NodoDiagnostico(leaf, "hombro", true);
			NodoDiagnostico nodoTrN2 = new NodoDiagnostico(leaf, "acromioclavicular", true);
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7,nodoTr8, nodoTrN1, nodoTrN2));
			
			NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodoTr1, "completa");
			NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodoTr1, "RCS");
			nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr9,nodoTr10));
			
			NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodoTr9, "anterior");
			NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodoTr9, "posterior");
			NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodoTr9, "divergente");
			nodoTr9.getChildren().addAll(Lists.newArrayList(nodoTr11,nodoTr12,nodoTr13));
			
			NodoDiagnostico nodoTr14 = new NodoDiagnostico(nodoTr10, "anterior");
			NodoDiagnostico nodoTr15 = new NodoDiagnostico(nodoTr10, "posterior");
			NodoDiagnostico nodoTr16 = new NodoDiagnostico(nodoTr10, "medial");
			NodoDiagnostico nodoTr17 = new NodoDiagnostico(nodoTr10, "lateral");
			nodoTr10.getChildren().addAll(Lists.newArrayList(nodoTr14,nodoTr15,nodoTr16,nodoTr17));
			
			NodoDiagnostico nodoTr18 = new NodoDiagnostico(nodoTr2, "R-Carpiana", true);
			NodoDiagnostico nodoTr19 = new NodoDiagnostico(nodoTr2, "RCI", true);
			nodoTr2.getChildren().addAll(Lists.newArrayList(nodoTr18,nodoTr19));
			
			NodoDiagnostico nodoTr20 = new NodoDiagnostico(nodoTr18, "volar");
			NodoDiagnostico nodoTr21 = new NodoDiagnostico(nodoTr18, "dorsal");
			nodoTr18.getChildren().addAll(Lists.newArrayList(nodoTr20,nodoTr21));
			
			NodoDiagnostico nodoTr22 = new NodoDiagnostico(nodoTr19, "volar");
			NodoDiagnostico nodoTr23 = new NodoDiagnostico(nodoTr19, "dorsal");
			nodoTr19.getChildren().addAll(Lists.newArrayList(nodoTr22,nodoTr23));
			
			NodoDiagnostico nodoTr24 = new NodoDiagnostico(nodoTr3, "perilunar", true);
			NodoDiagnostico nodoTr25 = new NodoDiagnostico(nodoTr3, "transecscafoperilunar", true);
			NodoDiagnostico nodoTr26 = new NodoDiagnostico(nodoTr3, "axial", true);
			nodoTr3.getChildren().addAll(Lists.newArrayList(nodoTr24,nodoTr25,nodoTr26));
			
			NodoDiagnostico nodoTr27 = new NodoDiagnostico(nodoTr4, "T-MTC", true);
			NodoDiagnostico nodoTr28 = new NodoDiagnostico(nodoTr4, "MTC-F", true);
			NodoDiagnostico nodoTr29 = new NodoDiagnostico(nodoTr4, "IF", true);
			nodoTr4.getChildren().addAll(Lists.newArrayList(nodoTr27,nodoTr28,nodoTr29));
			
			completeDedosLigamentaria(nodoTr5, true);
			completeDedosLigamentaria(nodoTr6, true);
			completeDedosLigamentaria(nodoTr7, true);
			completeDedosLigamentaria(nodoTr8, true);
			
			NodoDiagnostico nodoTrN3 = new NodoDiagnostico(nodoTrN1, "anterior");
			NodoDiagnostico nodoTrN4 = new NodoDiagnostico(nodoTrN1, "posterior");
			NodoDiagnostico nodoTrN5 = new NodoDiagnostico(nodoTrN1, "inferior");
			nodoTrN1.getChildren().addAll(Lists.newArrayList(nodoTrN3, nodoTrN4, nodoTrN5));
			
			NodoDiagnostico nodoTrN6 = new NodoDiagnostico(nodoTrN2, "1");
			NodoDiagnostico nodoTrN7 = new NodoDiagnostico(nodoTrN2, "2");
			NodoDiagnostico nodoTrN8 = new NodoDiagnostico(nodoTrN2, "3");
			NodoDiagnostico nodoTrN9 = new NodoDiagnostico(nodoTrN2, "4");
			NodoDiagnostico nodoTrN10 = new NodoDiagnostico(nodoTrN2, "5");
			NodoDiagnostico nodoTrN11 = new NodoDiagnostico(nodoTrN2, "6");
			nodoTrN2.getChildren().addAll(Lists.newArrayList(nodoTrN6, nodoTrN7, nodoTrN8, nodoTrN9, nodoTrN10, nodoTrN11));
		}
	}
	
	private static void completeLesionLigamentariaToAllLeaves(NodoDiagnostico nodo) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "codo", true);
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "muñeca", true);
			NodoDiagnostico nodoTr3 = new NodoDiagnostico(leaf, "carpo", true);
			NodoDiagnostico nodoTr4 = new NodoDiagnostico(leaf, "pulgar", true);
			NodoDiagnostico nodoTr5 = new NodoDiagnostico(leaf, "II", true);
			NodoDiagnostico nodoTr6 = new NodoDiagnostico(leaf, "III", true);
			NodoDiagnostico nodoTr7 = new NodoDiagnostico(leaf, "IV", true);
			NodoDiagnostico nodoTr8 = new NodoDiagnostico(leaf, "V", true);
			NodoDiagnostico nodoTrN1 = new NodoDiagnostico(leaf, "glenohumeral", true);
			NodoDiagnostico nodoTrN2 = new NodoDiagnostico(leaf, "acromioclavicular", true);
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6,nodoTr7,nodoTr8, nodoTrN1, nodoTrN2));
			
			NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodoTr1, "lig lat int");
			NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodoTr1, "lig lat ext");
			NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodoTr1, "lig anular");
			nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr9,nodoTr10,nodoTr11));
			
			NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodoTr2, "triangular");
			NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodoTr2, "volar");
			NodoDiagnostico nodoTr14 = new NodoDiagnostico(nodoTr2, "dorsal");
			nodoTr2.getChildren().addAll(Lists.newArrayList(nodoTr12,nodoTr13,nodoTr14));
			
			NodoDiagnostico nodoTr15 = new NodoDiagnostico(nodoTr3, "DISI");
			NodoDiagnostico nodoTr16 = new NodoDiagnostico(nodoTr3, "VISI");
			nodoTr3.getChildren().addAll(Lists.newArrayList(nodoTr15,nodoTr16));
			
			NodoDiagnostico nodoTr17 = new NodoDiagnostico(nodoTr15, "con colapso");
			NodoDiagnostico nodoTr18 = new NodoDiagnostico(nodoTr15, "sin colapso");
			nodoTr15.getChildren().addAll(Lists.newArrayList(nodoTr17,nodoTr18));
			
			NodoDiagnostico nodoTr19 = new NodoDiagnostico(nodoTr16, "con colapso");
			NodoDiagnostico nodoTr20 = new NodoDiagnostico(nodoTr16, "sin colapso");
			nodoTr16.getChildren().addAll(Lists.newArrayList(nodoTr19,nodoTr20));
			
			NodoDiagnostico nodoTr21 = new NodoDiagnostico(nodoTr4, "T-MTC", true);
			NodoDiagnostico nodoTr22 = new NodoDiagnostico(nodoTr4, "MTC-F", true);
			NodoDiagnostico nodoTr23 = new NodoDiagnostico(nodoTr4, "IF", true);
			nodoTr4.getChildren().addAll(Lists.newArrayList(nodoTr21,nodoTr22,nodoTr23));
			
			addLigColatPlaca(nodoTr22);
			addLigColatPlaca(nodoTr23);
			
			completeDedosLigamentaria(nodoTr5, false);
			completeDedosLigamentaria(nodoTr6, false);
			completeDedosLigamentaria(nodoTr7, false);
			completeDedosLigamentaria(nodoTr8, false);
		}
	}
	
	private static void completeDedosLigamentaria(NodoDiagnostico nodo, Boolean conDorsalVolarLateral) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "C-MTC", true);
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "MTC-F", true);
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "IFP", true);
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "IFD", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
		
		if(conDorsalVolarLateral){
			addDorsalVolarLateral(nodoTr2);
			addDorsalVolarLateral(nodoTr3);
			addDorsalVolarLateral(nodoTr4);
		}
		else{
			addLigColatPlaca(nodoTr2);
			addLigColatPlaca(nodoTr3);
			addLigColatPlaca(nodoTr4);
		}
	}
	
	private static void addDorsalVolarLateral(NodoDiagnostico nodo){
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "dorsal");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "volar");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "lateral");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void addLigColatPlaca(NodoDiagnostico nodo){
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "lig colat radial");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "lig colat cubital");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "placa palmar");
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "combinados");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
	}

	private static void addDistensionRupturaCompletaToAllLeaves(NodoDiagnostico nodo) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "distension");
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "ruptura completa");
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2));
		}
	}

	private static void addAgudaCronicaToAllLeaves(NodoDiagnostico nodo, Boolean displayInSummary) {
		for (NodoDiagnostico leaf : getLeaves(nodo.getChildren(), nodo)) {
			NodoDiagnostico nodoTr1 = new NodoDiagnostico(leaf, "aguda", displayInSummary);
			NodoDiagnostico nodoTr2 = new NodoDiagnostico(leaf, "crónica", displayInSummary);
			leaf.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2));
		}
	}

	private static void addFracturas(NodoDiagnostico nodo) {
		addToOseo(nodo, false, true);
		List<NodoDiagnostico> leaves = getLeaves(nodo.getChildren(), nodo);
		for (NodoDiagnostico leaf : leaves) {
			if(leaf.getLabel().equals("diafisario")){
				addDiafisis(leaf, false, false);
			}
			else if(leaf.getLabel().equals("paleta")){
				addPaleta(leaf);
			}
			else if(leaf.getParent().getLabel().equals("radio") && leaf.getLabel().equals("epifisis prox")){
				addEpifisisProx(leaf);
			}
			else if(leaf.getParent().getLabel().equals("radio") && leaf.getLabel().equals("diafisis")){
				addDiafisis(leaf, true, false);
			}
			else if(leaf.getParent().getLabel().equals("cubito") && leaf.getLabel().equals("epifisis prox")){
				addEpifisisProx2(leaf);
			}
			else if(leaf.getParent().getLabel().equals("cubito") && leaf.getLabel().equals("diafisis")){
				addDiafisis(leaf, false, false);
			}
			else if(leaf.getParent().getLabel().equals("cubito") && leaf.getLabel().equals("epifsis distal")){
				addEpifisisDistal(leaf);
			}
			else if(leaf.getParent().getLabel().equals("carpo") && leaf.getLabel().equals("escafoides")){
				addEscafoides(leaf);
			}
			else if(leaf.getParent().getLabel().equals("carpo") && leaf.getLabel().equals("ganchoso")){
				addGanchoso(leaf);
			}
			else if(leaf.getLabel().equals("MTC")){
				addMTC(leaf);
			}
			else if(leaf.getParent().getLabel().equals("pulgar") && leaf.getLabel().equals("F1")){
				addF1(leaf, true, false);
			}
			else if(leaf.getLabel().equals("F1")){
				addF1(leaf, false, true);
			}
			else if(leaf.getParent().getLabel().equals("pulgar") && leaf.getLabel().equals("F2")){
				addF2(leaf);
			}
			else if(leaf.getLabel().equals("F2")){
				addF1(leaf, false, true);
			}
			else if(leaf.getLabel().equals("F2")){
				addF2(leaf);
			}
		}
		
	}

	private static void addF2(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "base");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "diafisis");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "corona");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void addF1(NodoDiagnostico nodo, Boolean conCombinada, Boolean conLongitudinal) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "base");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "metafisis");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "diafisis");
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "cabeza");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
		
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodoTr1, "marginal volar");
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodoTr1, "marginal dorsal");
		NodoDiagnostico nodoTr7 = new NodoDiagnostico(nodoTr1, "avulsion cubital");
		NodoDiagnostico nodoTr8 = new NodoDiagnostico(nodoTr1, "avulsion radial");
		NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodoTr1, "conminuta");
		if(conCombinada){
			NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodoTr1, "combinada");
			nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr5,nodoTr6,nodoTr7,nodoTr8,nodoTr9,nodoTr10));
		}
		else{
			nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr5,nodoTr6,nodoTr7,nodoTr8,nodoTr9));
		}
		
		if(conLongitudinal){
			addDiafisis(nodoTr3, false, true);
		}
		else{
			addDiafisis(nodoTr3, true, false);
		}
		
		
		NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodoTr4, "transversa");
		NodoDiagnostico nodoTr12 = new NodoDiagnostico(nodoTr4, "unicondilea");
		NodoDiagnostico nodoTr13 = new NodoDiagnostico(nodoTr4, "bicondilea");
		nodoTr4.getChildren().addAll(Lists.newArrayList(nodoTr11,nodoTr12,nodoTr13));
	}

	private static void addMTC(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "base");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "diafisis");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "cuello");
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "cabeza");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4));
		
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodoTr1, "intra art simple");
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodoTr1, "intra art conminuta");
		NodoDiagnostico nodoTr7 = new NodoDiagnostico(nodoTr1, "extrarticular");
		NodoDiagnostico nodoTr8 = new NodoDiagnostico(nodoTr1, "avulsion");
		nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr5,nodoTr6,nodoTr7,nodoTr8));
		
		addDiafisis(nodoTr2, false, false);
		
		NodoDiagnostico nodoTr9 = new NodoDiagnostico(nodoTr4, "conminuta");
		NodoDiagnostico nodoTr10 = new NodoDiagnostico(nodoTr4, "simple");
		NodoDiagnostico nodoTr11 = new NodoDiagnostico(nodoTr4, "avulsion");
		nodoTr4.getChildren().addAll(Lists.newArrayList(nodoTr9,nodoTr10,nodoTr11));
	}

	private static void addGanchoso(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "gancho");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "cuerpo");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2));
	}

	private static void addEscafoides(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "polo proximal");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "cuerpo");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "polo distal");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void addEpifisisDistal(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "Q1");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "Q2");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "Q3");
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "Q4");
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodo, "Q5");
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodo, "Q6");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6));
	}

	private static void addEpifisisProx2(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "extrart");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "olecranon");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "coronoides");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void addDiafisis(NodoDiagnostico nodo, Boolean conEspiroidea, Boolean conLongitudinal) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "transversa");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "oblicua corta");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "oblicua larga");
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodo, "frag en mariposa");
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodo, "conminuta");
		if(conEspiroidea){
			NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodo, "espiroidea");
			nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6));
		}
		else if(conLongitudinal){
			NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodo, "longitudinal");
			nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5,nodoTr6));
		}
		else{
			nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3,nodoTr4,nodoTr5));
		}

	}

	private static void addEpifisisProx(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "cúpula");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "cuello");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "ambas");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
		
		NodoDiagnostico nodoTr4 = new NodoDiagnostico(nodoTr1, "Mason 1");
		NodoDiagnostico nodoTr5 = new NodoDiagnostico(nodoTr1, "Mason 2");
		NodoDiagnostico nodoTr6 = new NodoDiagnostico(nodoTr1, "Mason 3");
		nodoTr1.getChildren().addAll(Lists.newArrayList(nodoTr4,nodoTr5,nodoTr6));

	}

	private static void addPaleta(NodoDiagnostico nodo) {
		NodoDiagnostico nodoTr1 = new NodoDiagnostico(nodo, "supracondilea");
		NodoDiagnostico nodoTr2 = new NodoDiagnostico(nodo, "transcondilea");
		NodoDiagnostico nodoTr3 = new NodoDiagnostico(nodo, "infracondilea");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTr1,nodoTr2,nodoTr3));
	}

	private static void addInfecciosas(NodoDiagnostico nodoInf) {
		NodoDiagnostico nodoInf1 = new NodoDiagnostico(nodoInf, "panadizos", true);
		NodoDiagnostico nodoInf2 = new NodoDiagnostico(nodoInf, "tenosinovitis supuradas", true);
		NodoDiagnostico nodoInf3 = new NodoDiagnostico(nodoInf, "abscesos", true);
		NodoDiagnostico nodoInf4 = new NodoDiagnostico(nodoInf, "artritis septica", true);
		NodoDiagnostico nodoInf5 = new NodoDiagnostico(nodoInf, "osteomielitis", true);
		nodoInf.getChildren().addAll(Lists.newArrayList(nodoInf1,nodoInf2,nodoInf3,nodoInf4,nodoInf5));
		
		NodoDiagnostico nodoInf6 = new NodoDiagnostico(nodoInf1, "periungueal", true);
		NodoDiagnostico nodoInf7 = new NodoDiagnostico(nodoInf1, "subungueal", true);
		NodoDiagnostico nodoInf8 = new NodoDiagnostico(nodoInf1, "del pulpejo", true);
		NodoDiagnostico nodoInf9 = new NodoDiagnostico(nodoInf1, "subcutaneos volares", true);
		NodoDiagnostico nodoInf10 = new NodoDiagnostico(nodoInf1, "subcutaneos dorsales", true);
		NodoDiagnostico nodoInf11 = new NodoDiagnostico(nodoInf1, "combinados", true);
		nodoInf1.getChildren().addAll(Lists.newArrayList(nodoInf6,nodoInf7,nodoInf8,nodoInf9,nodoInf10,nodoInf11));
		addUbicaciones(nodoInf6, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInf7, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInf8, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInf9, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInf10, true, false, false, false, false, false, false, false);
		addUbicaciones(nodoInf11, true, false, false, false, false, false, false, false);

		NodoDiagnostico nodoInf12 = new NodoDiagnostico(nodoInf2, "vaina radial", true);
		NodoDiagnostico nodoInf13 = new NodoDiagnostico(nodoInf2, "vaina cubital", true);
		NodoDiagnostico nodoInf14 = new NodoDiagnostico(nodoInf2, "II", true);
		NodoDiagnostico nodoInf15 = new NodoDiagnostico(nodoInf2, "III", true);
		NodoDiagnostico nodoInf16 = new NodoDiagnostico(nodoInf2, "IV", true);
		nodoInf2.getChildren().addAll(Lists.newArrayList(nodoInf12,nodoInf13,nodoInf14,nodoInf15,nodoInf16));
		
		NodoDiagnostico nodoInf17 = new NodoDiagnostico(nodoInf3, "tenar", true);
		NodoDiagnostico nodoInf18 = new NodoDiagnostico(nodoInf3, "hipotenar", true);
		NodoDiagnostico nodoInf19 = new NodoDiagnostico(nodoInf3, "palmar medio", true);
		NodoDiagnostico nodoInf20 = new NodoDiagnostico(nodoInf3, "dorsal", true);
		NodoDiagnostico nodoInf21 = new NodoDiagnostico(nodoInf3, "comisural", true);
		NodoDiagnostico nodoInf22 = new NodoDiagnostico(nodoInf3, "otros", true);
		NodoDiagnostico nodoInf23 = new NodoDiagnostico(nodoInf3, "espacio de Parona", true);
		NodoDiagnostico nodoInf24 = new NodoDiagnostico(nodoInf3, "combinados", true);
		nodoInf3.getChildren().addAll(Lists.newArrayList(nodoInf17,nodoInf18,nodoInf19,nodoInf20,nodoInf21,nodoInf22,nodoInf23,nodoInf24));

		addUbicacionesRaro(nodoInf4);
		addToOseo(nodoInf5, false, false);

	}
	
	
	private static void addUbicacionesRaro(NodoDiagnostico nodo){
		NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "codo", true);
		NodoDiagnostico nodo2 = new NodoDiagnostico(nodo, "pulgar", true);
		NodoDiagnostico nodo3 = new NodoDiagnostico(nodo, "II", true);
		NodoDiagnostico nodo4 = new NodoDiagnostico(nodo, "III", true);
		NodoDiagnostico nodo5 = new NodoDiagnostico(nodo, "IV", true);
		NodoDiagnostico nodo6 = new NodoDiagnostico(nodo, "V", true);		
		NodoDiagnostico nodo7 = new NodoDiagnostico(nodo, "muñeca", true);
		NodoDiagnostico nodoN1 = new NodoDiagnostico(nodo, "glenohumeral", true);
		NodoDiagnostico nodoN2 = new NodoDiagnostico(nodo, "acromioclavicular", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodo1,nodo2,nodo3,nodo4,nodo5,nodo6,nodo7,nodoN1,nodoN2));
	
		addTmtcMtcfIf(nodo2);
		addMtcfIfdIfp(nodo3);
		addMtcfIfdIfp(nodo4);
		addMtcfIfdIfp(nodo5);
		addMtcfIfdIfp(nodo6);

	}	
	
	private static void addTmtcMtcfIf(NodoDiagnostico nodo){
		NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "T-MTC", true);
		NodoDiagnostico nodo2 = new NodoDiagnostico(nodo, "MTC-F", true);
		NodoDiagnostico nodo3 = new NodoDiagnostico(nodo, "IF", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodo1,nodo2,nodo3));
	}
	
	private static void addMtcfIfdIfp(NodoDiagnostico nodo){
		NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "MTC-F", true);
		NodoDiagnostico nodo2 = new NodoDiagnostico(nodo, "IFD", true);
		NodoDiagnostico nodo3 = new NodoDiagnostico(nodo, "IFP", true);
		nodo.getChildren().addAll(Lists.newArrayList(nodo1,nodo2,nodo3));
	}

	private static void addUbicaciones(NodoDiagnostico nodo, Boolean conDedos, Boolean conMano, Boolean conMuneca, Boolean conCodo, Boolean conAntebrazo, Boolean conBrazo, Boolean conCaras, Boolean conHombro){
		List<NodoDiagnostico> list = new ArrayList<NodoDiagnostico>();
		
		if(conDedos){
			NodoDiagnostico nodo2 = new NodoDiagnostico(nodo, "pulgar", true);
			NodoDiagnostico nodo3 = new NodoDiagnostico(nodo, "II", true);
			NodoDiagnostico nodo4 = new NodoDiagnostico(nodo, "III", true);
			NodoDiagnostico nodo5 = new NodoDiagnostico(nodo, "IV", true);
			NodoDiagnostico nodo6 = new NodoDiagnostico(nodo, "V", true);
			list.addAll(Lists.newArrayList(nodo2,nodo3,nodo4,nodo5,nodo6));
		}
		
		if(conMano){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "mano", true);
			list.add(nodo1);
		}
		
		if(conCodo){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "codo", true);
			list.add(nodo1);
		}
		
		if(conBrazo){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "brazo", true);
			list.add(nodo1);
		}
		
		if(conAntebrazo){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "antebrazo", true);
			list.add(nodo1);
		}
		
		if(conMuneca){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "muñeca", true);
			list.add(nodo1);
		}
		
		if(conHombro){
			NodoDiagnostico nodo1 = new NodoDiagnostico(nodo, "hombro", true);
			list.add(nodo1);
		}
		
		nodo.getChildren().addAll(list);
		
		if(conCaras){
			for (NodoDiagnostico nodoDiagnostico : list) {
				addCaraVolarYDorsal(nodoDiagnostico);
			}
		}
	}
	
	private static void addCaraVolarYDorsal(NodoDiagnostico nodo){
		NodoDiagnostico nodoTum1 = new NodoDiagnostico(nodo, "cara volar");
		NodoDiagnostico nodoTum2 = new NodoDiagnostico(nodo, "cara dosal");
		NodoDiagnostico nodoTum3 = new NodoDiagnostico(nodo, "ambas");
		
		//TODO: para tumoral, partes blandas, quiste epidermoide, granuloma por cuerpo extrano, botriomicoma, tofos no va

		NodoDiagnostico nodoTum4 = new NodoDiagnostico(nodo, "caras laterales");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTum1,nodoTum2,nodoTum3,nodoTum4));
	}
	
	private static void addToOseo(NodoDiagnostico nodo, Boolean conUnicoMultiple, Boolean conFullClaviculaEscapula) {
		NodoDiagnostico nodoTum155 = new NodoDiagnostico(nodo, "humero", true);
		NodoDiagnostico nodoTum156 = new NodoDiagnostico(nodo, "radio", true);
		NodoDiagnostico nodoTum157 = new NodoDiagnostico(nodo, "cubito", true);
		NodoDiagnostico nodoTum158 = new NodoDiagnostico(nodo, "pulgar", true);
		NodoDiagnostico nodoTum159 = new NodoDiagnostico(nodo, "II", true);
		NodoDiagnostico nodoTum160 = new NodoDiagnostico(nodo, "III", true);
		NodoDiagnostico nodoTum161 = new NodoDiagnostico(nodo, "IV", true);
		NodoDiagnostico nodoTum162 = new NodoDiagnostico(nodo, "V", true);
		NodoDiagnostico nodoTum163 = new NodoDiagnostico(nodo, "carpo");
		NodoDiagnostico nodoTumN1 = new NodoDiagnostico(nodo, "clavícula");
		NodoDiagnostico nodoTumN2 = new NodoDiagnostico(nodo, "escápula");
		nodo.getChildren().addAll(Lists.newArrayList(nodoTum155,nodoTum156,nodoTum157,nodoTum158,nodoTum159,nodoTum160,nodoTum161,nodoTum162,nodoTum163, nodoTumN1, nodoTumN2));

		NodoDiagnostico nodoTum164 = new NodoDiagnostico(nodoTum155, "proximal", true);
		NodoDiagnostico nodoTum165 = new NodoDiagnostico(nodoTum155, "diafisario", true);
		NodoDiagnostico nodoTum166 = new NodoDiagnostico(nodoTum155, "paleta", true);
		nodoTum155.getChildren().addAll(Lists.newArrayList(nodoTum164,nodoTum165,nodoTum166));

		NodoDiagnostico nodoTum167 = new NodoDiagnostico(nodoTum156, "epifisis prox", true);
		NodoDiagnostico nodoTum168 = new NodoDiagnostico(nodoTum156, "diafisis", true);
		NodoDiagnostico nodoTum169 = new NodoDiagnostico(nodoTum156, "epifisis distal", true);
		nodoTum156.getChildren().addAll(Lists.newArrayList(nodoTum167,nodoTum168,nodoTum169));
		
		NodoDiagnostico nodoTum170 = new NodoDiagnostico(nodoTum157, "epifisis prox", true);
		NodoDiagnostico nodoTum171 = new NodoDiagnostico(nodoTum157, "diafisis", true);
		NodoDiagnostico nodoTum172 = new NodoDiagnostico(nodoTum157, "epifisis distal", true);
		nodoTum157.getChildren().addAll(Lists.newArrayList(nodoTum170,nodoTum171,nodoTum172));

		NodoDiagnostico nodoTum173 = new NodoDiagnostico(nodoTum163, "escafoides", true);
		NodoDiagnostico nodoTum174 = new NodoDiagnostico(nodoTum163, "semilunar", true);
		NodoDiagnostico nodoTum175 = new NodoDiagnostico(nodoTum163, "piramidal", true);
		NodoDiagnostico nodoTum176 = new NodoDiagnostico(nodoTum163, "ganchoso", true);
		NodoDiagnostico nodoTum177 = new NodoDiagnostico(nodoTum163, "grande", true);
		NodoDiagnostico nodoTum179 = new NodoDiagnostico(nodoTum163, "pisiforme", true);
		NodoDiagnostico nodoTum180 = new NodoDiagnostico(nodoTum163, "trapecio", true);
		NodoDiagnostico nodoTum181 = new NodoDiagnostico(nodoTum163, "trapezoides", true);
		nodoTum163.getChildren().addAll(Lists.newArrayList(nodoTum173,nodoTum174,nodoTum175,nodoTum176,nodoTum177,nodoTum179,nodoTum180,nodoTum181));

		NodoDiagnostico nodoTum182 = new NodoDiagnostico(nodoTum158, "MTC", true);
		NodoDiagnostico nodoTum183 = new NodoDiagnostico(nodoTum158, "F1", true);
		NodoDiagnostico nodoTum184 = new NodoDiagnostico(nodoTum158, "F2", true);
		nodoTum158.getChildren().addAll(Lists.newArrayList(nodoTum182,nodoTum183,nodoTum184));

		NodoDiagnostico nodoTum185 = new NodoDiagnostico(nodoTum159, "MTC", true);
		NodoDiagnostico nodoTum186 = new NodoDiagnostico(nodoTum159, "F1", true);
		NodoDiagnostico nodoTum187 = new NodoDiagnostico(nodoTum159, "F2", true);
		NodoDiagnostico nodoTum188 = new NodoDiagnostico(nodoTum159, "F3", true);
		nodoTum159.getChildren().addAll(Lists.newArrayList(nodoTum185,nodoTum186,nodoTum187,nodoTum188));

		NodoDiagnostico nodoTum189 = new NodoDiagnostico(nodoTum160, "MTC", true);
		NodoDiagnostico nodoTum190 = new NodoDiagnostico(nodoTum160, "F1", true);
		NodoDiagnostico nodoTum191 = new NodoDiagnostico(nodoTum160, "F2", true);
		NodoDiagnostico nodoTum192 = new NodoDiagnostico(nodoTum160, "F3", true);
		nodoTum160.getChildren().addAll(Lists.newArrayList(nodoTum189,nodoTum190,nodoTum191,nodoTum192));

		NodoDiagnostico nodoTum193 = new NodoDiagnostico(nodoTum161, "MTC", true);
		NodoDiagnostico nodoTum194 = new NodoDiagnostico(nodoTum161, "F1", true);
		NodoDiagnostico nodoTum195 = new NodoDiagnostico(nodoTum161, "F2", true);
		NodoDiagnostico nodoTum196 = new NodoDiagnostico(nodoTum161, "F3", true);
		nodoTum161.getChildren().addAll(Lists.newArrayList(nodoTum193,nodoTum194,nodoTum195,nodoTum196));

		NodoDiagnostico nodoTum197 = new NodoDiagnostico(nodoTum162, "MTC", true);
		NodoDiagnostico nodoTum198 = new NodoDiagnostico(nodoTum162, "F1", true);
		NodoDiagnostico nodoTum199 = new NodoDiagnostico(nodoTum162, "F2", true);
		NodoDiagnostico nodoTum200 = new NodoDiagnostico(nodoTum162, "F3", true);
		nodoTum162.getChildren().addAll(Lists.newArrayList(nodoTum197,nodoTum198,nodoTum199,nodoTum200));
		
		if(conUnicoMultiple){
			addUnicoMultiple(nodo);
		}
		
		if(conFullClaviculaEscapula){
			NodoDiagnostico nodoTumN3 = new NodoDiagnostico(nodoTumN1, "tercio interno");
			NodoDiagnostico nodoTumN4 = new NodoDiagnostico(nodoTumN1, "tercio medio");
			NodoDiagnostico nodoTumN5 = new NodoDiagnostico(nodoTumN1, "tercio externo");
			nodoTumN1.getChildren().addAll(Lists.newArrayList(nodoTumN3, nodoTumN4, nodoTumN5));
			
			NodoDiagnostico nodoTumN6 = new NodoDiagnostico(nodoTumN2, "cuerpo");
			NodoDiagnostico nodoTumN7 = new NodoDiagnostico(nodoTumN2, "acromión");
			nodoTumN2.getChildren().addAll(Lists.newArrayList(nodoTumN6, nodoTumN7));
		}
	}

	private static void addUnicoMultiple(NodoDiagnostico nodoTum5) {
		List<NodoDiagnostico> leaves = getLeaves(nodoTum5.getChildren(), nodoTum5);
		for ( NodoDiagnostico leaf : leaves) {
			NodoDiagnostico nodoTum155 = new NodoDiagnostico(leaf, "único");
			NodoDiagnostico nodoTum156 = new NodoDiagnostico(leaf, "múltiple");
			leaf.getChildren().addAll(Lists.newArrayList(nodoTum155,nodoTum156));
		}
	}

	private static List<NodoDiagnostico> getLeaves(List<NodoDiagnostico> list, NodoDiagnostico nodoTum5) {
		ArrayList<NodoDiagnostico> leaves = new ArrayList<NodoDiagnostico>();
	    for (NodoDiagnostico nodo : list) {
	        if (nodo.getChildren() != null || nodo.getChildren().size() != 0) {
	        	List<NodoDiagnostico> leaves2 = getLeaves(nodo.getChildren(), nodo);
	            if (leaves2 == null) {
	                leaves.add(nodo);
	            } else {
	                leaves.addAll(leaves2);
	            }
	        }
	    }
	    if (leaves.isEmpty()) {
	        return null;
	    }
	    return leaves;
	}
}
