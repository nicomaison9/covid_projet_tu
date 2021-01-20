
package com.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import com.covid.TestPcr;

public class TestPcrDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<TestPcr> liste = new ArrayList<>();
	public static Connection conn = Connecteur.getConnection();





	public static ArrayList<String> getListeId_TestPcr() {
		ArrayList<String> liste = new ArrayList<String>();
		try {
			PreparedStatement prst = conn.prepareStatement("select Id_TestPcr from TestPcr");
			for (String o : liste) {
				liste.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}
	
	

	
	

	
	public static TestPcr getTestPcrParId_Cas(int id_cas) {
		for (TestPcr e : liste) {
			if (e.getId_cas()==id_cas)
				return e;
		}
		return null;
	}
	
	public static TestPcr getTestPcrParId_test(int Id) throws Exception {
		for (TestPcr e : getListe()) {
			if (e.getId_test() == Id)
				return e;
		}
		return null;
	}

	public static void ajouterTestPcr(TestPcr e) throws Exception {
		try {
			//Statement st = conn.createStatement();
			
			PreparedStatement prst = conn.prepareStatement("INSERT INTO TestPcr (id, jour, mois,annee,id_teste,resultat) VALUES (?,?,?,?,?,?)");
			
			prst.setLong(1, e.getId_test());
			prst.setLong(2, e.getJour());
			prst.setLong(3, e.getMois());
			prst.setLong(1, e.getAnnee());
			prst.setLong(2, e.getId_cas());
			prst.setLong(3, e.getResultat());
			
			
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
	public static void supprimerTestPcr(int id_TestPcr) throws Exception {
		try {
			//Statement st = conn.createStatement();
			
			PreparedStatement prst = conn.prepareStatement("DELETE FROM TestPcr WHERE id=?");
			prst.setInt(1, id_TestPcr);
			
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
	
	
	
	public static ArrayList<TestPcr> versCollection() throws Exception {
		Statement st;
		ResultSet rs;
		ArrayList<TestPcr> liste = new ArrayList<TestPcr>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from TestPcr");						
			if (rs != null) {
				while (rs.next()) {
					TestPcr p =new TestPcr();
					p.setId_test((int) rs.getLong("id_TestPcr"));
					p.setJour((int) rs.getLong("nom_complet"));
					p.setMois((int) rs.getLong("telephone"));
					p.setAnnee((int) rs.getLong("nom_complet"));
					p.setId_cas((int) rs.getLong("telephone"));
					p.setResult((int) rs.getLong("id_TestPcr"));
					
					
					
					liste.add(p);					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return liste;		
	}


	public static ArrayList<TestPcr> getListe() throws Exception {
		liste=versCollection();
		liste.sort(new ComparerTestPcrById());
		return liste;
	}

	public static void setListe(ArrayList<TestPcr> liste) {
		TestPcrDAO.liste=liste;
	}

}


class ComparerTestPcrById implements Comparator<TestPcr>{

	@Override
	public int compare(TestPcr o1, TestPcr o2) {
		return Integer.compare(o1.getId_test(), o2.getId_test());
	}
	
}