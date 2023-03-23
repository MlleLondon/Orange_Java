package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.C_Technicien;
import controleur.Technicien;

public class PanelProfil extends PanelPrincipal implements ActionListener{
	private JTextArea txtProfil= new JTextArea();
	private JPanel panelUpdate= new JPanel();
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btModifier= new JButton("Modifier");
	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JTextField txtQualification= new JTextField();
	private JPasswordField txtMdp= new JPasswordField();
	
	private JButton btModifierProfil= new JButton("Modifier Profil");
	private boolean visible;
	private int idtechnicien;

	public PanelProfil(Technicien unTechnicien) {
		super(Color.blue);
		
		this.idtechnicien=unTechnicien.getIdtechnicien();
		//Positionnement visualisation profil
		String message="";
		message= "Nom du technicien: "+unTechnicien.getNom();
		message= message+"\nPrénom du technicien: "+unTechnicien.getPrenom();
		message= message+"\nE-mail du technicien: "+unTechnicien.getEmail();
		message=message+"\nQualification du technicien: "+unTechnicien.getQualification();
		
		this.txtProfil.setBounds(40, 40, 300, 300);
		this.txtProfil.setText(message);
		
		//Construction du panel de modification de profil
		this.panelUpdate.setBounds(400, 40, 300, 300);
		this.panelUpdate.setLayout(new GridLayout(6,2));
		//1er ligne
		this.panelUpdate.add(new JLabel("Nom: "));
		this.panelUpdate.add(this.txtNom);
		//2e ligne
		this.panelUpdate.add(new JLabel("Prenom: "));
		this.panelUpdate.add(this.txtPrenom);
		//3e ligne
		this.panelUpdate.add(new JLabel("Email: "));
		this.panelUpdate.add(this.txtEmail);
		//4e ligne
		this.panelUpdate.add(new JLabel("Qualification: "));
		this.panelUpdate.add(this.txtQualification);
		//5e ligne
		this.panelUpdate.add(new JLabel("Mdp: "));
		this.panelUpdate.add(this.txtMdp);
		//6e ligne
		this.panelUpdate.add(this.btAnnuler);
		this.panelUpdate.add(this.btModifier);
		this.panelUpdate.setBackground(Color.blue);
		
		this.add(this.panelUpdate);
		this.panelUpdate.setVisible(false);
		this.visible=false;
		
		//Ajout des boutons modifier et annuler
		this.btModifierProfil.setBounds(150, 340, 150, 30);
		this.add(this.btModifierProfil);
		
		this.add(this.txtProfil);
		
		
		//Rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btModifier.addActionListener(this);
		this.btModifierProfil.addActionListener(this);
		
		//Remplir les champs avec les infos
		this.txtNom.setText(unTechnicien.getNom());
		this.txtPrenom.setText(unTechnicien.getPrenom());
		this.txtEmail.setText(unTechnicien.getEmail());
		this.txtQualification.setText(unTechnicien.getQualification());
		this.txtMdp.setText(unTechnicien.getMdp());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btModifierProfil) {
			if(this.visible==false) {
				this.panelUpdate.setVisible(true);
				this.visible=true;
			}
			else {
				this.panelUpdate.setVisible(false);
				this.visible=false;
			}
		}
		else if(e.getSource()==this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			this.txtQualification.setText("");
		}
		else if(e.getSource()==this.btModifier) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String qualification= this.txtQualification.getText();
			String mdp= new String( this.txtMdp.getPassword());
			
			Technicien updateTechnicien= new Technicien(this.idtechnicien, nom, prenom, email, mdp, qualification);
			C_Technicien.updateTechnicien(updateTechnicien);
			JOptionPane.showMessageDialog(this, "Modification réussie !");
			this.panelUpdate.setVisible(false);
			this.visible=false;
			
			//Modification des infos dans la visualisation du profil
			String message="";
			message= "Nom du technicien: "+nom;
			message= message+"\nPrénom du technicien: "+prenom;
			message= message+"\nE-mail du technicien: "+email;
			message=message+"\nQualification du technicien: "+qualification;
			this.txtProfil.setText(message);
		}
		
	}
}
