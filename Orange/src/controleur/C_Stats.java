package controleur;
import java.util.ArrayList;
import modele.ModeleStats;

public class C_Stats {
	public static ArrayList<Stats> selectAllNbInterventionsTech(){
		return ModeleStats.selectAllNbInterventionsTech();
	}
}
