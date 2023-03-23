package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Client;
import controleur.Client;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener{

	private JPanel panelForm= new JPanel();
	private JPanel panelTable= new JPanel();
	
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JTextField txtAdresse= new JTextField();
	private JTextField txtTel= new JTextField();
	
	//Déclaration de table des clients
	private JTable tableClients;
	private Tableau unTableau;
	
	//Implément d'un filtre
	private JTextField txtMot=new JTextField();
	private JButton btOk= new JButton("Ok");
	
	
	public PanelClients() {
		super(Color.cyan);
		
		//Construction du PanelForm
		this.panelForm.setBounds(40, 40, 300, 300);
		this.panelForm.setLayout(new GridLayout(6,2));
		//1er ligne
		this.panelForm.add(new JLabel("Nom: "));
		this.panelForm.add(this.txtNom);
		//2e ligne
		this.panelForm.add(new JLabel("Prenom: "));
		this.panelForm.add(this.txtPrenom);
		//3e ligne
		this.panelForm.add(new JLabel("Email: "));
		this.panelForm.add(this.txtEmail);
		//4e ligne
		this.panelForm.add(new JLabel("Adresse: "));
		this.panelForm.add(this.txtAdresse);
		//5e ligne
		this.panelForm.add(new JLabel("Téléphone: "));
		this.panelForm.add(this.txtTel);
		//6e ligne
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setBackground(Color.cyan);
		
		this.add(this.panelForm);
		this.panelForm.setVisible(true);
		
		//Construction du panelTable
		this.panelTable.setBounds(350,  30,  600,  450);
		this.panelTable.setBackground(new Color(255, 184, 51));
		this.panelTable.setLayout(null);
		String entetes[]= {"IdClient", "Nom", "Prenom", "Email", "Adresse", "Téléphone"};
		
		//Instanciation de la classe Tableau
		this.unTableau= new Tableau(this.obtenirClients(""), entetes);
		this.tableClients= new JTable(unTableau);
		JScrollPane uneScroll= new JScrollPane(this.tableClients);
		uneScroll.setBounds(20, 50, 560, 360);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
		//Rendre écoutable la JTable avec gestion de la suppression d'une ligne
		this.tableClients.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne=0;
				if(e.getClickCount()==2) {
					numLigne=tableClients.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez-vous supprimer ce client ?", "Suppression Client", JOptionPane.YES_NO_OPTION);
					if(retour==0) {
						int idClient= (int) unTableau.getValueAt(numLigne, 0);
						//Suppression du client dans la BDD
						C_Client.deleteClient(idClient);
						//Suppression du client dans la table
						unTableau.supprimerLigne(numLigne);
					}
				}
				else if(e.getClickCount()==1){
					numLigne=tableClients.getSelectedRow();
					txtNom.setText(unTableau.getValueAt(numLigne, 1)+"");
					txtPrenom.setText(unTableau.getValueAt(numLigne, 2)+"");
					txtEmail.setText(unTableau.getValueAt(numLigne, 3)+"");
					txtAdresse.setText(unTableau.getValueAt(numLigne, 4)+"");
					txtTel.setText(unTableau.getValueAt(numLigne, 5)+"");
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		//Rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btOk.addActionListener(this);
		
		//Placement du filtre
		this.txtMot.setBounds(20, 10,100,30);
		this.panelTable.add(this.txtMot);
		this.btOk.setBounds(120, 10, 100, 30);
		this.panelTable.add(this.btOk);
	}
	
	public Object[][] obtenirClients(String mot){
		ArrayList<Client> lesClients= C_Client.selectAllClients(mot);
		Object[][] matrice= new Object[lesClients.size()][6];
		int i=0;
		for (Client unClient: lesClients) {
			matrice[i][0]=unClient.getIdclient()+"";
			matrice[i][1]=unClient.getNom();
			matrice[i][2]=unClient.getPrenom();
			matrice[i][3]=unClient.getEmail();
			matrice[i][4]=unClient.getAdresse();
			matrice[i][5]=unClient.getTel();
			i++;
		}
		
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtAdresse.setText("");
		this.txtTel.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		}
		else if(e.getSource()==this.btEnregistrer &&this.btEnregistrer.getText().equals("Enregistrer") ) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String adresse= this.txtAdresse.getText();
			String tel= this.txtTel.getText();
			boolean ok=true;
			if(nom.equals("")) {
				this.txtNom.setBackground(Color.red);
				ok=false;
			}
			else {
				this.txtNom.setBackground(Color.white);
			}
			if(prenom.equals("")) {
				this.txtPrenom.setBackground(Color.red);
				ok=false;
			}
			else {
				this.txtPrenom.setBackground(Color.white);
			}
			if(email.equals("")) {
				this.txtEmail.setBackground(Color.red);
				ok=false;
			}
			else {
				this.txtEmail.setBackground(Color.white);
			}
			if(adresse.equals("")) {
				this.txtAdresse.setBackground(Color.red);
				ok=false;
			}
			else {
				this.txtAdresse.setBackground(Color.white);
			}
			if(tel.equals("")) {
				this.txtTel.setBackground(Color.red);
				ok=false;
			}
			else {
				this.txtTel.setBackground(Color.white);
			}
			
			if(ok) {
				Client unClient= new Client(nom, prenom, email, adresse, tel);
				//Insertion du new client dans la BDD
				C_Client.insertClient(unClient);
				//Actualisation de la table d'affichage des clients
				
				unClient=C_Client.selectWhereClient(email, nom, prenom);
				int idClient=unClient.getIdclient();
				Object[] ligne= {idClient, nom, prenom, email, adresse, tel};
				unTableau.insererLigne(ligne);
				JOptionPane.showMessageDialog(this, "Insertion réussie !");
				this.viderChamps();
			}
		}
		
		else if(e.getSource()==this.btEnregistrer &&this.btEnregistrer.getText().equals("Modifier") ) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String adresse= this.txtAdresse.getText();
			String tel= this.txtTel.getText();
			
			//On récupère l'Id
			int numLigne= this.tableClients.getSelectedRow();
			int idClient= (int) unTableau.getValueAt(numLigne, 0);
			//On update le client dans la Bdd
			Client updateClient= new Client(idClient, nom, prenom, email, adresse, tel);
			C_Client.updateClient(updateClient);
			
			Object[] ligne= {idClient, nom, prenom, email, adresse, tel};
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification réussie !");
			this.viderChamps();
		}
		else if(e.getSource()==this.btOk) {
			String mot=this.txtMot.getText();
			Object[][] matrice=this.obtenirClients(mot);
			unTableau.setDonnees(matrice);
		}
		
	}

}
