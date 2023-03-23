package vue;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controleur.C_Stats;
import controleur.Stats;
import controleur.Tableau;

public class PanelStats extends PanelPrincipal{
	
	private JPanel panelTech= new JPanel();
	
	private JTable tableStats;
	private Tableau unTableau;
	
	public PanelStats() {
		super(Color.black);
		this.panelTech.setBounds(40, 80, 300, 300);
		this.panelTech.setLayout(null);
		String entetes[]= {"Nom", "Prenom", "Nb"};
		
		this.unTableau= new Tableau(this.obtenirStats(), entetes);
		this.tableStats=new JTable(this.unTableau);
		
		JScrollPane uneScroll= new JScrollPane(this.tableStats);
		uneScroll.setBounds(20, 0, 250, 300);
		this.panelTech.add(uneScroll);
		this.add(this.panelTech);
	}
	
	public Object[][] obtenirStats(){
		ArrayList<Stats> lesStats= C_Stats.selectAllNbInterventionsTech();
		Object[][] matrice= new Object[lesStats.size()][3];
		int i=0;
		for (Stats uneStats: lesStats) {
			matrice[i][0]=uneStats.getNom();
			matrice[i][1]=uneStats.getPrenom();
			matrice[i][2]=uneStats.getNb()+"";
			i++;
		}
		return matrice;
	}
}
