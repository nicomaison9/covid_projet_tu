package covid;

import Exceptions.badformatException;
import Exceptions.longueurinsuffisanteException;
import Exceptions.nonconvertibleException;

public class Cas {
	private static int nb_id_cas = 0;
	private int id_cas;
	private String nom_complet = "";
	private String code_postal;
	private String adresse;
	private int Etat;
	private String telephone;

	public Cas() {
		super();
		
	}
	public Cas(int id_cas,String nom, String codepostal, String adresse, String telephone,int statutcovid) {
		super();
		setId_cas(id_cas);
		try {
			setNom_complet(nom);
		} catch (badformatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			setCode_postal(codepostal);
		} catch (nonconvertibleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			setAdresse(adresse);
		} catch (longueurinsuffisanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEtat(statutcovid);
		try {
			setTelephone(telephone);
		} catch (badformatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Cas(String nom, String codepostal, String adresse, int statutcovid, String telephone) {
		super();
		this.id_cas = nb_id_cas + 1;
		nb_id_cas++;
		try {
			setNom_complet(nom);
		} catch (badformatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			setCode_postal(codepostal);
		} catch (nonconvertibleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			setAdresse(adresse);
		} catch (longueurinsuffisanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEtat(statutcovid);
		try {
			setTelephone(telephone);
		} catch (badformatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public int getId_cas() {
		return id_cas;
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public String getAdresse() {
		return adresse;
	}

	public int getEtat() {
		return Etat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setId_cas(int id_cas) {
		id_cas = id_cas;
	}

	public void setNom_complet(String nom_complet) throws badformatException {
		if (nomIsComplet(nom_complet))
			this.nom_complet = nom_complet;
			else
				System.out.println("le nom et le prénom  doivent être séparés par un espace ");
		
	}

	public void setCode_postal(String code_postal) throws nonconvertibleException {
		if (codePostalIsValid(code_postal))
		this.code_postal = code_postal;
		else
			System.out.println("le code postal "+code_postal+" n'est pas correct ");
	}

	public void setAdresse(String adresse) throws longueurinsuffisanteException {
		if (adresseIsValid(adresse))
			this.adresse = adresse;
		else System.out.println("l'adresse n'est pas valide, elle doit faire + de 8 caractères");
	}

	public void setEtat(int etat) {
		if (etatIsValid(etat))
			this.Etat = etat;
		else
			System.out.println("le statut covid ne peut pas être " + etat + " - il doit être -1 ou 1 ");
	}

	public void setTelephone(String telephone) throws badformatException {
		if (telephoneIsValid(telephone))
		this.telephone = telephone;
		else System.out.println("le format du téléphone n'est pas valide");
		
	}
	public static boolean nomIsComplet(String Nomcomplet) throws badformatException{
		return Nomcomplet.contains(" ");
	}

	public static boolean telephoneIsValid(String telephone2) throws badformatException {
		return ((telephone2.contains("+") || telephone2.contains("00")) && telephone2.length() >= 8
				&& !telephone2.contains(" "));
		
	}

	public static boolean adresseIsValid(String adresse2) throws longueurinsuffisanteException{
		return (adresse2.length() >= 8);
		
	}
	public static boolean codePostalIsValid(String codepos) {
		int codepost=0;
		try {
			codepost=Integer.parseInt(codepos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}
		return ((codepos.length() == 5)&& (codepost!=0));
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Etat;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((code_postal == null) ? 0 : code_postal.hashCode());
		result = prime * result + ((nom_complet == null) ? 0 : nom_complet.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cas other = (Cas) obj;
		if (Etat != other.Etat)
			return false;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (code_postal == null) {
			if (other.code_postal != null)
				return false;
		} else if (!code_postal.equals(other.code_postal))
			return false;
		if (nom_complet == null) {
			if (other.nom_complet != null)
				return false;
		} else if (!nom_complet.equals(other.nom_complet))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cas [Id="+id_cas+", nom_complet=" + nom_complet + ", code_postal=" + code_postal + ", adresse=" + adresse + ", Etat="
				+ Etat + ", telephone=" + telephone + "]";
	}

	public static boolean etatIsValid(int statut) {
		return (statut == 1 || statut == (-1));
		
	}



}
