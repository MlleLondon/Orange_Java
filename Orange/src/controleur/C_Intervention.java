package controleur;

import java.util.ArrayList;

import modele.ModeleIntervention;

public class C_Intervention {
	public static void insertIntervention(Intervention uneIntervention) {
		//On controle les données
		ModeleIntervention.insertIntervention(uneIntervention);
	}
	public static ArrayList<Intervention> selectAllInterventions() {
		//On controle les données
		return ModeleIntervention.selectAllInterventions();
	}
	public static void deleteIntervention(int idintervention) {
		//On controle les données
		ModeleIntervention.deleteIntervention(idintervention);
	}
	public static void updateIntervention(Intervention uneIntervention) {
		//On controle les données
		ModeleIntervention.updateIntervention(uneIntervention);
	}
	public static Intervention selectWhereIntervention(int idintervention) {
		//On controle les données
		return ModeleIntervention.selectWhereIntervention(idintervention);
	}
	
	public static Intervention selectWhereIntervention(String materiel, String panne, String date) {
		//On controle les données
		return ModeleIntervention.selectWhereIntervention(materiel, panne, date);
	}
}
