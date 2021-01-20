package covid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TestPcr {
	private static int nb_id_test = 1;
	private int id_test;
	private int id_cas;
	private int jour;
	private int mois;
	private int annee;
	private int result;

	public TestPcr(int id_cas, int resultat) {
		id_test = nb_id_test;
		nb_id_test++;
		this.id_cas = id_cas;
		LocalDate datetest = LocalDate.now();
		annee = datetest.getYear();
		mois = datetest.getMonthValue();
		jour = datetest.getDayOfMonth();
		
		result=setResultat(resultat);

	}

	public TestPcr() {
	
	}

	@Override
	public String toString() {
		return "TestPcr n°"+id_test+" [id_cas=" + id_cas + ", jour=" + jour + ", mois=" + mois + ", annee=" + annee + ", result="
				+ result + "]";
	}

	public static boolean dateIsValid(int jour, int mois, int annee) {
		boolean valid = true;
		if (mois <= 1 || mois >= 12) {
			valid = false;
		}
		if (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12) {
			if (jour < 1 || jour > 31) {
				valid = false;
			}
		}
		if (mois == 4 || mois == 6 || mois == 8 || mois == 11) {
			if (jour < 1 || jour > 30) {
				valid = false;
			}
		}
		if (mois == 2) {
			if (annee % 4 == 0) {
				if (jour < 1 || jour > 29) {
					valid = false;
				}
			}else
				if (jour < 1 || jour > 28) {
					valid = false;
				}
		}
		return valid;
	}

	public static boolean idIsExisting(ArrayList<TestPcr> liste,int id) {
		boolean existing=false;
		for(int i=0;i<liste.size();i++) {
			if (liste.get(i).getId_test() ==id)
				{existing= true;
//				System.out.println(liste.get(i).getId_test() +"-"+id);
				}

			
		}
		return existing;
	}
	public  int getNb_id_test() {
		return nb_id_test;
	}

	public  int getId_test() {
		return id_test;
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	public int getResultat() {
		return result;
	}

	public static void setNb_id_test(int nb_id_test) {
		TestPcr.nb_id_test = nb_id_test;
	}

	public  void setId_test(int id_test) {
		this.id_test = id_test;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int setResultat(int resultat) {
		return  resultat;
	}

	public static String afficher(ArrayList<TestPcr> liste) {
		String str = "";
		str="=======liste des TestPcr enregistrés=======\n";
		for(int i=0;i<liste.size();i++) {
			str=str+ liste.get(i).toString()+"\n";
		}
		return str;
		
	}
	public static String historique(ArrayList<TestPcr> liste) {
		Scanner scan =new Scanner(System.in);
		System.out.println("De quel id souhaitez vous l'historique?\n");
		int id=scan.nextInt();
		
		String str = "";
		System.out.println(liste.size());
		str="=======historique du cas n° "+id+"=======\n";
		for(int i=0;i<liste.size();i++) {
			if (liste.get(i).getId_cas()==id)
				str=str+ liste.get(i).toString()+"\n";
//			else System.out.println("pas trouvé");
			
		}
		return str;
	}

	public int getId_cas() {
		return id_cas;
	}

	public int getResult() {
		return result;
	}

	public void setId_cas(int id_cas) {
		this.id_cas = id_cas;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
