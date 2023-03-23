package controleur;
import vue.VueConnexion;
import vue.VueGenerale;

public class Orange {
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneVueConnexion= new VueConnexion();
		
	}
	public static void gererVueConnexion (boolean action) {
		Orange.uneVueConnexion.setVisible(action);
	}
	public static void gererVueGenerale (boolean action, Technicien unTechnicien) {
		uneVueGenerale= new VueGenerale(unTechnicien);
		Orange.uneVueGenerale.setVisible(action);
	}

}
