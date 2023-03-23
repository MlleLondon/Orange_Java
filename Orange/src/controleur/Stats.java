package controleur;

public class Stats {
	private String nom, prenom;
	private int nb;
	
	
	public Stats(String nom, String prenom, int nb) {
		this.nom = nom;
		this.prenom = prenom;
		this.nb = nb;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public int getNb() {
		return nb;
	}


	public void setNb(int nb) {
		this.nb = nb;
	}
	
	
	
}
