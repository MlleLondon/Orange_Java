package modele;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Client;

public class ModeleClient {
	private static Bdd uneBdd= new Bdd("localhost", "orange_250_2023", "root" ,"");
	
	
	//Méthodes
	public static void insertClient(Client unClient) {
		String requete="insert into client values(null, '"+unClient.getNom()+"', '"+unClient.getPrenom()+"', '"
				+unClient.getEmail()+"', '"+unClient.getAdresse()+"', '"
				+unClient.getTel()+"');";
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
	public static ArrayList<Client> selectAllClients(String mot){
		String requete="";
		
		if(mot.equals("")){
			requete="select * from client;";
		}
		else {
			requete="select * from client where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+
					"%' or adresse like '%"+mot+"%' or tel like '%"+mot+"%'"; 
		}
		ArrayList<Client> lesClients= new ArrayList<Client>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les résultats et construire des objets
			while(desResultats.next() ) {
				Client unClient=new Client(
						desResultats.getInt("idclient"), desResultats.getString("nom"),
						desResultats.getString("prenom"), desResultats.getString("email"),
						desResultats.getString("adresse"), desResultats.getString("tel"));
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesClients;
	}
	public static void deleteClient(int idclient) {
		String requete="delete from client where idclient= "+idclient+";";
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
	public static void updateClient(Client unClient) {
		String requete="update client set nom='"+unClient.getNom()+"', prenom='"+unClient.getPrenom()+"', email='"
				+unClient.getEmail()+"', adresse='"+unClient.getAdresse()+"', tel='"
				+unClient.getTel()+"' where idclient= "+unClient.getIdclient()+";";
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
	public static Client selectWhereClient(int idclient) {
		String requete="select * from client where idclient="+idclient+";";
		Client unClient=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				unClient=new Client(
					unResultat.getInt("idclient"), unResultat.getString("nom"),
					unResultat.getString("prenom"), unResultat.getString("email"),
					unResultat.getString("adresse"), unResultat.getString("tel"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return unClient;
	}
	public static Client selectWhereClient(String email, String nom, String prenom) {
		String requete="select * from client where email='"+email+"' and nom='"+nom+"' and prenom='"+prenom+"';";
		Client unClient=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			//Tester s'il y a un résultat
			if(unResultat.next()) {
				unClient=new Client(
					unResultat.getInt("idclient"), unResultat.getString("nom"),
					unResultat.getString("prenom"), unResultat.getString("email"),
					unResultat.getString("adresse"), unResultat.getString("tel"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return unClient;
	}
	
	
}
