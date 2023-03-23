package controleur;

public class Client {
	private int idclient;
	private String nom, prenom, email, adresse, tel;
	
	public Client(int idclient, String nom, String prenom, String email, String adresse, String tel) {
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
	}
	
	public Client(String nom, String prenom, String email, String adresse, String tel) {
		this.idclient = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
	}
	
	public Client() {
		this.idclient = 0;
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.adresse = "";
		this.tel = "";
	}
	
	//Getters & Setters
	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
