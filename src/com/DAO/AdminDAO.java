package com.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import com.covid.Admin;

public class AdminDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ArrayList<Admin> liste = new ArrayList<>();
	public static Connection conn = Connecteur.getConnection();

	public static ArrayList<String> getListeId_Admin() {
		ArrayList<String> liste = new ArrayList<String>();
		try {
			PreparedStatement prst = conn.prepareStatement("select Id_Admin from Admin");
			for (String o : liste) {
				liste.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	public static boolean isAuth(String login, String pass) {
		Statement st;
		ResultSet rs;
		Admin p = AdminDAO.chercherUtilisateur(login, pass);
		if (p != null)
			return true;
		else
			return false;

	}

	public static Admin chercherUtilisateur(String login, String pass) {
		Statement st;
		ResultSet rs;
		Admin p = null;
		try {
			st = conn.createStatement();
//			
			PreparedStatement prst = conn.prepareStatement("SELECT * FROM admin where login = ? and pass= ?");
			prst.setString(1, login);
			prst.setString(2, pass);
			rs = prst.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					p = new Admin();
					p.setId_admin((int) rs.getLong("id_admin"));
					p.setLogin(rs.getString("login"));
					p.setPassword(rs.getString("pass"));

					System.out.println("utilisateur trouvé " + p.toString() + "");
				}

			} else {
				System.out.println("utilisateur non trouvé");
				// utilisateur=null

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return p;
	}

	public static Admin getAdminParLogin(String login) {
		for (Admin e : liste) {
			if (e.getLogin().equals(login))
				return e;
		}
		return null;
	}

	public static Admin getAdminParId(int Id) throws Exception {
		for (Admin e : getListe()) {
			if (e.getId_admin() == Id)
				return e;
		}
		return null;
	}

	public static void ajouterAdmin(Admin e) throws Exception {
		try {
			// Statement st = conn.createStatement();

			PreparedStatement prst = conn.prepareStatement("INSERT INTO Admin (id_Admin, login, pass) VALUES (?,?,?)");

			prst.setLong(1, e.getId_admin());
			prst.setString(2, e.getLogin());
			prst.setString(3, e.getPassword());

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

	public static void supprimerAdmin(int id_Admin) throws Exception {
		try {
			// Statement st = conn.createStatement();

			PreparedStatement prst = conn.prepareStatement("DELETE FROM Admin WHERE id_Admin=?");
			prst.setInt(1, id_Admin);

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

	public static ArrayList<Admin> versCollection() throws Exception {
		Statement st;
		ResultSet rs;
		ArrayList<Admin> liste = new ArrayList<Admin>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from Admin");
			if (rs != null) {
				while (rs.next()) {
					Admin p = new Admin();
					p.setId_admin((int) rs.getLong("id_Admin"));
					p.setLogin(rs.getString("nom_complet"));
					p.setPassword(rs.getString("telephone"));

					liste.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<Admin> getListe() throws Exception {
		liste = versCollection();
		//liste.sort(new ComparerAdminById());
		return liste;
	}

	public static void setListe(ArrayList<Admin> liste) {
		AdminDAO.liste = liste;
	}

	public static boolean isAdmin4real(Admin admin) {
		Admin adminDB = new Admin();
		adminDB = AdminDAO.chercherUtilisateur(admin.getLogin(), admin.getPassword());
		//System.out.println(admin.getLogin() + adminDB.getLogin()+admin.getPassword() + adminDB.getLogin());
		if (adminDB==null) return false;
		else return (admin.getLogin().equals(adminDB.getLogin()) && (admin.getPassword().equals(adminDB.getPassword())));
	}

	class ComparerAdminById implements Comparator<Admin> {

		@Override
		public int compare(Admin o1, Admin o2) {
			return Integer.compare(o1.getId_admin(), o2.getId_admin());
		}

	}
}