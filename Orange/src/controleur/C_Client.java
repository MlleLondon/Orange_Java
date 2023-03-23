package controleur;

import java.util.ArrayList;

import modele.ModeleClient;

public class C_Client {
	
	public static void insertClient(Client unClient) {
		//On controle les données
		ModeleClient.insertClient(unClient);
	}
	public static ArrayList<Client> selectAllClients(String mot) {
		//On controle les données
		return ModeleClient.selectAllClients(mot);
	}
	public static void deleteClient(int idclient) {
		//On controle les données
		ModeleClient.deleteClient(idclient);
	}
	public static void updateClient(Client unClient) {
		//On controle les données
		ModeleClient.updateClient(unClient);
	}
	public static Client selectWhereClient(int idclient) {
		//On controle les données
		return ModeleClient.selectWhereClient(idclient);
	}
	public static Client selectWhereClient(String email, String nom, String prenom) {
		//On controle les données
		return ModeleClient.selectWhereClient(email, nom, prenom);
	}
}
