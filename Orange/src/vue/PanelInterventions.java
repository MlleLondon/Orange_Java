package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.C_Client;
import controleur.C_Intervention;
import controleur.C_Technicien;
import controleur.Client;
import controleur.Intervention;
import controleur.Tableau;
import controleur.Technicien;

public class PanelInterventions extends PanelPrincipal implements ActionListener{
	private JPanel panelForm= new JPanel();
	
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JTextField txtMateriel= new JTextField();
	private JTextField txtPanne= new JTextField();
	private JTextField txtDate= new JTextField();
	private JTextField txtPrix= new JTextField();
	private JComboBox<String> cbxIdClient= new JComboBox<String>();
	private JComboBox<String> cbxIdTechnicien= new JComboBox<String>();
	private Tableau unTableau;
	
	public PanelInterventions() {
		super(Color.red);
		
		//Construction du PanelForm
		this.panelForm.setBounds(40, 40, 300, 300);
		this.panelForm.setLayout(new GridLayout(7,2));
		//1 ligne
		this.panelForm.add(new JLabel("Matériel: "));
		this.panelForm.add(this.txtMateriel);
		//2 ligne
		this.panelForm.add(new JLabel("Panne: "));
		this.panelForm.add(this.txtPanne);
		//3 ligne
		this.panelForm.add(new JLabel("Date: "));
		this.panelForm.add(this.txtDate);
		//4 ligne
		this.panelForm.add(new JLabel("Prix: "));
		this.panelForm.add(this.txtPrix);
		//5 ligne
		this.panelForm.add(new JLabel("Le Client: "));
		this.panelForm.add(this.cbxIdClient);
		//6 ligne
		this.panelForm.add(new JLabel("Le Technicien: "));
		this.panelForm.add(this.cbxIdTechnicien);
		//7 ligne
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		
		//Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//Remplir les comboBox
		this.remplirCBX();
		
		
	}
	
	public void remplirCBX() {
		this.cbxIdClient.removeAllItems();
		this.cbxIdTechnicien.removeAllItems();
		
		ArrayList<Client> lesClients=C_Client.selectAllClients("");
		for(Client unClient: lesClients) {
			this.cbxIdClient.addItem(unClient.getIdclient()+"- "+unClient.getNom()+""+unClient.getPrenom());
		}
		
		ArrayList<Technicien> lesTechniciens=C_Technicien.selectAllTechniciens("");
		for(Technicien unTechnicien: lesTechniciens) {
			this.cbxIdTechnicien.addItem(unTechnicien.getIdtechnicien()+"- "+unTechnicien.getNom()+""+unTechnicien.getPrenom());
		}
		
	}

	public void viderChamps() {
	this.txtMateriel.setText("");
	this.txtDate.setText("");
	this.txtPanne.setText("");
	this.txtPrix.setText("");
	this.btEnregistrer.setText("Enregistrer");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		}
		else if(e.getSource()==this.btEnregistrer &&this.btEnregistrer.getText().equals("Enregistrer") ) {
			String materiel= this.txtMateriel.getText();
			String panne= this.txtPanne.getText();
			String date= this.txtDate.getText();
			float prix=0; 
			try{
				prix=Float.parseFloat(this.txtPrix.getText());
			}
			catch(NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "Erreur de saisir du prix");
			}
			//Récupération des idClient et idTechnicien
			String chaine=this.cbxIdClient.getSelectedItem().toString();
			String tab[]= chaine.split("-");
			int idClient=Integer.parseInt(tab[0]);
			
			chaine=this.cbxIdTechnicien.getSelectedItem().toString();
			tab= chaine.split("-");
			int idTechnicien=Integer.parseInt(tab[0]);
			
			Intervention uneIntervention=  new Intervention(materiel, panne, date, prix, idClient, idTechnicien);
			C_Intervention.insertIntervention(uneIntervention);

			uneIntervention=C_Intervention.selectWhereIntervention(materiel, date, panne);
			int idIntervention= uneIntervention.getIdintervention();
			Object[] ligne= {"IdTechnicien", "Matériel", "Panne", "Date Interv", "Prix", "IdClient", "IdTechnicien"};
			unTableau.insererLigne(ligne);
		}
		
	}
}
