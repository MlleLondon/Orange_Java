package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Orange;
import controleur.Technicien;

public class VueGenerale extends JFrame implements ActionListener{
	private JPanel panelMenu= new JPanel();
	private JButton btClients= new JButton("Gestion Clients");
	private JButton btTechniciens= new JButton("Gestion Techniciens");
	private JButton btInterventions= new JButton("Gestion Interventions");
	private JButton btStats= new JButton("Statistiques");
	private JButton btProfil= new JButton("Mon Profil");
	private JButton btQuitter= new JButton("Quitter");
	
	private static PanelProfil unPanelProfil;
	private static PanelClients unPanelClients= new PanelClients();
	private static PanelTechniciens unPanelTechniciens= new PanelTechniciens();
	private static PanelInterventions unPanelInterventions= new PanelInterventions();
	private static PanelStats unPanelStats= new PanelStats();
	
	public VueGenerale(Technicien unTechnicien) {
		//Instanciation du PanelProfil
		unPanelProfil= new PanelProfil(unTechnicien);
		
		this.setTitle("Gestion des interventions");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 184, 51) );
		this.setBounds(100, 100, 1200, 700);
		this.setLayout(null);
		
		//Construction du panel connexion
		this.panelMenu.setBounds(100, 20, 1000, 40);
		this.panelMenu.setBackground(new Color(255, 184, 51));
		//1 lignes sur 5 colonnes
		this.panelMenu.setLayout(new GridLayout(1, 6));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btClients);
		this.panelMenu.add(this.btTechniciens);
		this.panelMenu.add(this.btInterventions);
		this.panelMenu.add(this.btStats);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		//Rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btClients.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btTechniciens.addActionListener(this);
		this.btInterventions.addActionListener(this);
		this.btStats.addActionListener(this);
		
		//Insertion des paneaux dans la fenêtre
		this.add(unPanelProfil);
		this.add(unPanelClients);
		this.add(unPanelTechniciens);
		this.add(unPanelInterventions);
		this.add(unPanelStats);
		
		
		
		this.setVisible(false);
	}

	
	
	
	public static void activerPanel(int choix) {
		unPanelProfil.setVisible(false);
		unPanelClients.setVisible(false);
		unPanelTechniciens.setVisible(false);
		unPanelInterventions.setVisible(false);
		unPanelStats.setVisible(false);
		switch(choix) {
		case 1: unPanelProfil.setVisible(true); break;
		case 2: unPanelClients.setVisible(true); break;
		case 3: unPanelTechniciens.setVisible(true); break;
		case 4: unPanelInterventions.setVisible(true); break;
		case 5: unPanelStats.setVisible(true); break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btQuitter) {
			int retour= JOptionPane.showConfirmDialog(this, "Voulez vous quitter l'application ? ", 
					"Quitter l'application ? " ,JOptionPane.YES_NO_OPTION);
			if(retour==0) {
				this.dispose();
				Orange.gererVueConnexion(true);
			}
		}
		else if(e.getSource()== this.btProfil) {
			activerPanel(1);
		}
		else if(e.getSource()== this.btClients) {
			activerPanel(2);
		}
		else if(e.getSource()== this.btTechniciens) {
			activerPanel(3);
		}
		else if(e.getSource()== this.btInterventions) {
			activerPanel(4);
		}
		else if(e.getSource()== this.btStats) {
			activerPanel(5);
		}
	}
}
