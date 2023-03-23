package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Stats;

public class ModeleStats {
	private static Bdd uneBdd= new Bdd("localhost", "orange_250_2023", "root" ,"");
	
	public static ArrayList<Stats> selectAllNbInterventionsTech(){
		String requete="select * from nbinterventionsTech";
		ArrayList<Stats> lesStats= new ArrayList<Stats>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les résultats et construire des objets
			while(desResultats.next() ) {
				Stats uneStat=new Stats(
						desResultats.getString("nom"), desResultats.getString("prenom"), desResultats.getInt("nb"));
				lesStats.add(uneStat);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesStats;
	}
}
