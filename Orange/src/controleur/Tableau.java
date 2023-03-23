package controleur;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

//Classe abstraite de gestion dynamique de la table
public class Tableau extends AbstractTableModel{ 
	
	private Object[][] donnees;
	private String[] entetes;
	
	//Constructeur
	public Tableau(Object[][] donnees, String[] entetes) {
		this.donnees=donnees;
		this.entetes=entetes;
	}

	@Override
	public int getRowCount() {
		//Retourne le nombre de lignes
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		//Retourne le nombre de colonne
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		//Retourne la valeur se trouvent au croisement ligne - colonne
		return this.donnees[ligne][colonne];
	}

	@Override
	public String getColumnName(int colonne) {
		//Retourne le nom de la colonne
		return this.entetes[colonne];
	}

	//Méthode insertion d'une ligne dans la matrice donnees
	public void insererLigne(Object[] ligne) {
		Object[][] matrice= new Object[this.donnees.length+1][this.entetes.length];
		for(int i=0; i<this.donnees.length; i++) {
			matrice[i]=this.donnees[i];
		}
		matrice[this.donnees.length]=ligne;
		this.donnees=matrice;
		this.fireTableDataChanged(); //Actualiser la modification de donnees
	}
	
	
	//Méthode suppression d'une ligne dans la matrice donnees
	public void supprimerLigne(int numLigne) {
		Object[][] matrice= new Object[this.donnees.length-1][this.entetes.length];
		int j=0;
		for(int i=0; i<this.donnees.length; i++) {
			if(i!=numLigne) {
				matrice[j]=this.donnees[i];
				j++;
			}
		}
		this.donnees=matrice;
		this.fireTableDataChanged();
		
	}
	
	
	//Méthode modification d'une ligne dans la matrice donnees
	public void modifierLigne(int numLigne, Object[] ligne) {
		Object[][] matrice= new Object[this.donnees.length][this.entetes.length];
		for(int i=0; i<this.donnees.length; i++) {
			if(i==numLigne) {
				matrice[i]=ligne;
			}
			else {
				matrice[i]=this.donnees[i];
			}
		}
		this.donnees=matrice;
		this.fireTableDataChanged(); //Actualiser la modification de donnees
	}
	
	public void setDonnees(Object[][] matrice) {
		this.donnees=matrice;
		this.fireTableDataChanged();
	}
	
}
