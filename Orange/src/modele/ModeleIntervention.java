package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Intervention;


public class ModeleIntervention {
private static Bdd uneBdd= new Bdd("localhost", "orange_250_2023", "root" ,"");
	
	//Méthodes
		//Insertion d'une Intervention
	public static void insertIntervention(Intervention uneIntervention) {
		String requete="insert into intervention values(null, '"+uneIntervention.getMateriel()+"', '"+uneIntervention.getPanne()+"', '"
				+uneIntervention.getDateinter()+"', "+uneIntervention.getPrix()+", "
				+uneIntervention.getIdclient()+", "+uneIntervention.getIdtechnicien()+");";
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
	
		//Récupération de tous les interventions
	public static ArrayList<Intervention> selectAllInterventions(){
		String requete="select * from intervention;";
		ArrayList<Intervention> lesInterventions= new ArrayList<Intervention>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les résultats et construire des objets
			while(desResultats.next() ) {
				Intervention uneIntervention=new Intervention(
						desResultats.getInt("idintervention"), desResultats.getString("materiel"),
						desResultats.getString("panne"), desResultats.getString("dateinter"),
						desResultats.getFloat("prix"), desResultats.getInt("idclient"), desResultats.getInt("idtechnicien"));
				lesInterventions.add(uneIntervention);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesInterventions;
	}
	
		//Suppréssion d'une intervention
	public static void deleteIntervention(int idintervention) {
		String requete="delete from intervention where idintervention= "+idintervention+";";
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
	
		//Modification d'une intervention
	public static void updateIntervention(Intervention uneIntervention) {
		String requete="update intervention set materiel='"+uneIntervention.getMateriel()+"', panne='"+uneIntervention.getPanne()+"', dateinter='"
				+uneIntervention.getDateinter()+"', prix="+uneIntervention.getPrix()+", idclient="
				+uneIntervention.getIdclient()+", idtechnicien="+uneIntervention.getIdtechnicien()+" where idintervention= "+uneIntervention.getIdintervention()+";";
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
	
		//Récupération d'un seul intervention
	public static Intervention selectWhereIntervention(int idintervention) {
		String requete="select * from intervention where idintervention="+idintervention+";";
		Intervention uneIntervention=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				uneIntervention=new Intervention(
						unResultat.getInt("idintervention"), unResultat.getString("materiel"),
						unResultat.getString("panne"), unResultat.getString("dateinter"),
						unResultat.getFloat("prix"), unResultat.getInt("idclient"), unResultat.getInt("idtechnicien"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return uneIntervention;
	}
	public static Intervention selectWhereIntervention(String materiel, String panne, String date) {
		String requete="select * from intervention where materiel='"+materiel+"' and panne='"+panne+"' and dateinter='"+date+"';";
		Intervention uneIntervention=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				uneIntervention=new Intervention(
						unResultat.getInt("idintervention"), unResultat.getString("materiel"),
						unResultat.getString("panne"), unResultat.getString("dateinter"),
						unResultat.getFloat("prix"), unResultat.getInt("idclient"), unResultat.getInt("idtechnicien"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return uneIntervention;
	}
}
