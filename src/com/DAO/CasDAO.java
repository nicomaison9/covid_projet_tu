package com.DAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import com.Exceptions.badformatException;
import com.covid.Cas;
import com.covid.ListCas;

public class CasDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Cas> liste = new ArrayList<>();
	public static Connection conn = Connecteur.getConnection();





	public static ArrayList<String> getListeId_cas() {
		ArrayList<String> liste = new ArrayList<String>();
		try {
			PreparedStatement prst = conn.prepareStatement("select Id_cas from cas");
			for (String o : liste) {
				liste.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}
	
	

	
	

	
	public static Cas getCasParNom(String nom_complet) {
		for (Cas e : liste) {
			if (e.getNom_complet().equals(nom_complet))
				return e;
		}
		return null;
	}
	
	public static Cas getCasParId(int Id) throws Exception {
		for (Cas e : getListe()) {
			if (e.getId_cas() == Id)
				return e;
		}
		return null;
	}

	public static void ajouterCas(Cas e) throws Exception {
		try {
			//Statement st = conn.createStatement();
			
			PreparedStatement prst = conn.prepareStatement("INSERT INTO cas ( nom_complet, telephone, adresse, code_postal, etat) VALUES (?,?,?,?,?)");
			
//			prst.setLong(1, e.getId_cas());
			prst.setString(1, e.getNom_complet());
			prst.setString(2, e.getTelephone());
			prst.setString(3, e.getAdresse());
			prst.setString(4, e.getCode_postal());
			prst.setLong(5, e.getEtat());
			
//			prst.execute();
			  int i = prst.executeUpdate();
              if (i > 0) {
                  System.out.println("record inserted...");
              } else {
            	  System.out.println("record not inserted...");
              }
			liste = versCollection();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}

	}
	public static void supprimerCas(int id_cas) throws Exception {
		try {
			//Statement st = conn.createStatement();
			
			PreparedStatement prst = conn.prepareStatement("DELETE FROM cas WHERE id_cas=?");
			prst.setInt(1, id_cas);
			
//			prst.execute();
			  int i = prst.executeUpdate();
              if (i > 0) {
                  System.out.println("record deleted...");
              } else {
            	  System.out.println("record not deleted...");
              }
			liste = versCollection();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}

	}
	
	
	
	public static ArrayList<Cas> versCollection() throws Exception {
		Statement st;
		ResultSet rs;
		ArrayList<Cas> liste = new ArrayList<Cas>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from cas");						
			if (rs != null) {
				while (rs.next()) {
					Cas p =new Cas();
					p.setId_cas((int) rs.getLong("id_cas"));
					p.setNom_complet(rs.getString("nom_complet"));
					p.setTelephone(rs.getString("telephone"));
					p.setAdresse(rs.getString("adresse"));
					p.setCode_postal(rs.getString("code_postal"));
					p.setEtat((int)rs.getLong("etat"));
					
					
					liste.add(p);					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return liste;		
	}


	public static ArrayList<Cas> getListe() throws Exception {
		liste=versCollection();
		liste.sort(new ComparerCasById());
		return liste;
	}

	public static void setListe(ArrayList<Cas> liste) {
		ListCas.setListOfCas(liste);
	}

}


class ComparerCasById implements Comparator<Cas>{

	@Override
	public int compare(Cas o1, Cas o2) {
		return Integer.compare(o1.getId_cas(), o2.getId_cas());
	}
	
}