package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Technicien;

public class ModeleTechnicien {
	private static Bdd uneBdd= new Bdd("localhost", "orange_250_2023", "root" ,"");
	
	//Méthodes
		//Insertion d'un Technicien
	public static void insertTechnicien(Technicien unTechnicien) {
		String requete="insert into technicien values(null, '"+unTechnicien.getNom()+"', '"+unTechnicien.getPrenom()+"', '"
				+unTechnicien.getEmail()+"', '"+unTechnicien.getMdp()+"', '"
				+unTechnicien.getQualification()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Récupération de tous les techniciens
	public static ArrayList<Technicien> selectAllTechniciens(String mot){
		String requete="";
		if(mot.equals("")){
			requete="select * from technicien;";
		}
		else {
			requete="select * from technicien where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+
					"%' or mdp like '%"+mot+"%' or qualification like '%"+mot+"%';"; 
		}
		ArrayList<Technicien> lesTechniciens= new ArrayList<Technicien>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les résultats et construire des objets
			while(desResultats.next() ) {
				Technicien unTechnicien=new Technicien(
						desResultats.getInt("idtechnicien"), desResultats.getString("nom"),
						desResultats.getString("prenom"), desResultats.getString("email"),
						desResultats.getString("mdp"), desResultats.getString("qualification"));
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesTechniciens;
	}
	
		//Suppréssion d'un technicien
	public static void deleteTechnicien(int idtechnicien) {
		String requete="delete from technicien where idtechnicien= "+idtechnicien+";";
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Modification d'un technicien
	public static void updateTechnicien(Technicien unTechnicien) {
		String requete="update technicien set nom='"+unTechnicien.getNom()+"', prenom='"+unTechnicien.getPrenom()+"', email='"
				+unTechnicien.getEmail()+"', mdp='"+unTechnicien.getMdp()+"', qualification='"
				+unTechnicien.getQualification()+"' where idtechnicien= "+unTechnicien.getIdtechnicien()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Récupération d'un seul technicien
	public static Technicien selectWhereTechnicien(int idtechnicien) {
		String requete="select * from technicien where idtechnicien="+idtechnicien+";";
		Technicien unTechnicien=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				unTechnicien=new Technicien(
					unResultat.getInt("idtechnicien"), unResultat.getString("nom"),
					unResultat.getString("prenom"), unResultat.getString("email"),
					unResultat.getString("mdp"), unResultat.getString("qualification"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return unTechnicien;
	}
	
		//Vérifie la connexion pour un technicien
	public static Technicien selectWhereTechnicien(String email, String mdp) {
		String requete="select * from technicien where email='"+email+"' and mdp='"+mdp+"';";
		Technicien unTechnicien=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				unTechnicien=new Technicien(
					unResultat.getInt("idtechnicien"), unResultat.getString("nom"),
					unResultat.getString("prenom"), unResultat.getString("email"),
					unResultat.getString("mdp"), unResultat.getString("qualification"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return unTechnicien;
	}
}
