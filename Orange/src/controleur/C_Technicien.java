package controleur;

import java.util.ArrayList;

import modele.ModeleTechnicien;

public class C_Technicien {
	public static void insertTechnicien(Technicien unTechnicien) {
		//On controle les données
		ModeleTechnicien.insertTechnicien(unTechnicien);
	}
	public static ArrayList<Technicien> selectAllTechniciens(String mot) {
		//On controle les données
		return ModeleTechnicien.selectAllTechniciens(mot);
	}
	public static void deleteTechnicien(int idtechnicien) {
		//On controle les données
		ModeleTechnicien.deleteTechnicien(idtechnicien);
	}
	public static void updateTechnicien(Technicien unTechnicien) {
		//On controle les données
		ModeleTechnicien.updateTechnicien(unTechnicien);
	}
	public static Technicien selectWhereTechnicien(int idtechnicien) {
		//On controle les données
		return ModeleTechnicien.selectWhereTechnicien(idtechnicien);
	}
	public static Technicien selectWhereTechnicien(String email, String mdp) {
		//On controle les données
		return ModeleTechnicien.selectWhereTechnicien(email, mdp);
	}
}
