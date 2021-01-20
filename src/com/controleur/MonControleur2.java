package com.controleur;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.AdminDAO;
import com.covid.Admin;


/**
 * Servlet implementation class monControleur
 */
@WebServlet(name = "MonControleur", urlPatterns = { "/monControleur", "/index.html" })
@MultipartConfig
public class MonControleur2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonControleur2() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("on est dans doget");
		String action = request.getParameter("action");
		if(action==null)
			{request.getRequestDispatcher("index.jsp").forward(request, response);}
		
		

		switch (action) {
//		
		case "afficherDetails":
			
			String id_testdetail=request.getParameter("id_Test");
			System.out.println("MonControleur: afficher détails de "+id_testdetail);
			Admin Admindetail=new Admin();
			try {
				Admindetail= AdminDAO.getAdminParId(Integer.parseInt(id_testdetail));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println("nom du Admin"+Admindetail.getNom_mod());
			request.setAttribute("p", Admindetail);
			request.getRequestDispatcher("/WEB-INF/vues/detailAdmin.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		String action = request.getParameter("action");

		switch (action) {
//		utilisateurs
		case "authentification":
			System.out.println("MonControleur: authentification en cours");
			String login = request.getParameter("login");
			String pwd = request.getParameter("pwd");
			System.out.println("login pass= " + login + pwd);
			Admin adminconnect= new Admin(login,pwd);
			if (!Admin.isAdmin4real(adminconnect)) {
//			if (!AdminDAO.isAuth(login, pwd)) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession(true);

				Admin p = AdminDAO.chercherUtilisateur(login, pwd);
				if (p != null) {
					System.out.println(p.toString());
					boolean admin = p.isAdmin();

					try {
						session.setAttribute("list", AdminDAO.getListe());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.setAttribute("admin", admin);
					System.out.println("role admin=" + admin);

					if (admin) {
						request.getRequestDispatcher("/WEB-INF/vues/Gestion.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("/WEB-INF/vues/index.jsp").forward(request, response);
					}
				}

			}
			break;
		case "uploadFichier":
			// à développer quand t"as 2 minutes
					
		case "deconnexion":
			System.out.println("MonControleur: deconnexion en cours");
			HttpSession session = request.getSession(true);
			session.invalidate();
			request.getRequestDispatcher("/WEB-INF/vues/index.jsp").forward(request, response);

			break;
		case "list4admin":
			System.out.println("MonControleur: affichage liste admin");
			request.getRequestDispatcher("/WEB-INF/vues/list4admin.jsp").forward(request, response);
			break;
		case "list4users":
			System.out.println("MonControleur: affichage liste users");
			request.getRequestDispatcher("/WEB-INF/vues/list4users.jsp").forward(request, response);
			break;
			
			
//			Admins
		case "ajouterAdmin":
			// afficher le formulaire
			System.out.println("MonControleur: formulaire d'ajout de Admin en cours");
			request.getRequestDispatcher("/WEB-INF/vues/ajouterAdmin.jsp").forward(request, response);
			break;
		//
		case "ajoutDuAdmin":
			// afficher le formulaire

			System.out.println("MonControleur: ajout de Admin en cours");
			// ajout du Admin si il n'existe pas déjà et si tous les champs sont remplis

			ArrayList<String> error = new ArrayList<String>();

			System.out.println("id_mod a ajouter"+request.getParameter("id_mod"));
			String id_mod = request.getParameter("id_mod");
			String nom_mod = request.getParameter("nom_mod");
			String image = request.getParameter("image");
			String description = request.getParameter("description");
			String qte_stock = request.getParameter("qte_stock");
			String prix = request.getParameter("prix");
			String id_capa = request.getParameter("id_capa");
			String id_os = request.getParameter("id_os");
			String id_mar = request.getParameter("id_mar");
			int qte_stockint = 0;
			double prixdble = 0;

			if (id_mod.isEmpty() || nom_mod.isEmpty() || image.isEmpty() || description.isEmpty() || qte_stock.isEmpty()
					|| prix.isEmpty() || id_capa.isEmpty() || id_os.isEmpty() || id_mar.isEmpty() || id_mod.equals("")
					|| nom_mod.equals("") || image.equals("") || description.equals("") || qte_stock.equals(null)
					|| prix.equals(null) || id_capa.equals("") || id_os.equals("") || id_mar.equals("")) {
				error.add(" tous les champs doivent être remplis ...<br/>");
			} else {
				try {
					if (AdminDAO.getAdminParId(Integer.parseInt(id_mod)) != null) {
						error.add(" l'identifiant " + id_mod + " est déjà pris ...<br/>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				if (AdminDAO.getAdminParNom(nom_mod)!=null) {
//					error.add(" le modèle " + nom_mod + "est déjà présent dans la liste...<br/>");
//				}
			}

			
			try {
				qte_stockint = Integer.parseInt(qte_stock);
			} catch (NumberFormatException nfe) {

				error.add("la quantité de stock doit être un numérique ...<br/>");
			}
			try {
				prixdble = Double.parseDouble(prix);
			} catch (NumberFormatException nfe) {
				error.add("le prix doit être un réel ...<br/>");
			}

			if (qte_stockint < 0 || prixdble < 0) {
				error.add(" la quantité de stock et le prix doivent être positifs ...<br />");
			}
			if (error.size() != 0) {
				// il y a des erreurs, on redemande le remplissage du formulaire d'ajout
				request.setAttribute("error", error);
				System.out.println(error);
				request.getRequestDispatcher("/WEB-INF/vues/ajouterAdmin.jsp").forward(request, response);
			} else {
				// on demande l'ajout

				Admin newAdmin = new Admin();
//				newAdmin.setId_mod(Integer.parseInt(id_mod));
//				newAdmin.setNom_mod(nom_mod);
//				newAdmin.setImage(image);
//				newAdmin.setDescription(description);
//				newAdmin.setQte_stock(qte_stockint);
//				newAdmin.setPrix((long) prixdble);
//				newAdmin.setId_capa(id_capa);
//				newAdmin.setId_os(id_os);
//				newAdmin.setId_mar(id_mar);

				try {
					AdminDAO.ajouterAdmin(newAdmin);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// si l'ajout s'est correctement fait on renvoie list4admin
				request.getRequestDispatcher("/WEB-INF/vues/list4admin.jsp").forward(request, response);

			}

			break;

			
			
		case "supprimerAdmin":
			System.out.println("MonControleur: suppression de Admin en cours");
			ArrayList<String> error2 = new ArrayList<String>();
			
			System.out.println("id_mod a ajouter"+request.getParameter("id_mod"));
			request.getRequestDispatcher("/WEB-INF/vues/supprimerAdmin.jsp").forward(request, response);
			break;
		case "suppressionDuAdmin":
			String id_modsuppr=request.getParameter("id_mod");
			System.out.println("MonControleur: suppression de Admin"+id_modsuppr+" en cours");
			

			try {
				AdminDAO.supprimerAdmin(Integer.parseInt(id_modsuppr));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/vues/list4admin.jsp").forward(request, response);
			break;
			
			
			
			
		
			
			
		case "modifierAdmin":
			System.out.println("MonControleur: detail de Admin en cours");
			request.getRequestDispatcher("/WEB-INF/vues/detailAdmin.jsp").forward(request, response);
			break;
		case "filtrerListeAdmin":
			System.out.println("MonControleur: filtrage de liste de Admin en cours");
			request.getRequestDispatcher("/WEB-INF/vues/filtrerListeAdmin.jsp").forward(request, response);
			break;

//			capacité

		case "ajouterCapa":
			request.getRequestDispatcher("/WEB-INF/vues/ajouterCapa.jsp").forward(request, response);
			break;
		case "supprimerCapa":
			request.getRequestDispatcher("/WEB-INF/vues/supprimerCapa.jsp").forward(request, response);
			break;
		case "updateCapa":
			request.getRequestDispatcher("/WEB-INF/vues/modifierCapa.jsp").forward(request, response);
			break;

//			marques
		case "ajouterMarque":
			request.getRequestDispatcher("/WEB-INF/vues/ajouterMarque.jsp").forward(request, response);
			break;
		case "supprimerMarque":
			request.getRequestDispatcher("/WEB-INF/vues/supprimerMarque.jsp").forward(request, response);
			break;
		case "updateMarque":
			request.getRequestDispatcher("/WEB-INF/vues/modifierMarque.jsp").forward(request, response);
			break;

//			os
		case "ajouterOs":
			request.getRequestDispatcher("/WEB-INF/vues/ajouterOs.jsp").forward(request, response);
			break;

		}

//		if(action.equals("supprimerAdmin"))
//				this.getServletContext().getRequestDispatcher("supprimerAdmin.jsp").forward(request, response);
//		if(action.equals("ajouterAdmin"))
//			this.getServletContext().getRequestDispatcher("ajouterAdmin.jsp").forward(request, response);
//		if(action.equals("détailAdmin"))
//			this.getServletContext().getRequestDispatcher("detailAdmin.jsp").forward(request, response);
//		if(action.equals("deconnecter"))
//			this.getServletContext().getRequestDispatcher("Deconnexion.jsp").forward(request, response);
	}

}
