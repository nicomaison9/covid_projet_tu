package com.controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.AdminDAO;
import com.DAO.CasDAO;
import com.covid.Admin;
import com.covid.Cas;

/**
 * Servlet implementation class controleur
 */
@WebServlet("/controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {
//		utilisateurs
		case "authentification":
			System.out.println("MonControleur: connexion en cours");
			HttpSession session = request.getSession(true);
			String login = request.getParameter("login");
			String pwd = request.getParameter("pwd");
			Admin admin = new Admin(login, pwd);
			String erreur = "";
			if (Admin.isAdmin4real(admin)) {
				response.sendRedirect("Gestion.jsp");
			} else {
				session.setAttribute("erreur", erreur);
			}
			break;
		case "deconnexion":
			System.out.println("MonControleur: deconnexion en cours");
			
			HttpSession session1 = request.getSession(true);
			session1.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);

			break;

//Cas de covid		
		case "ajouterCas":
			// afficher le formulaire d'ajout de cas
			System.out.println("MonControleur: formulaire d'ajout de Cas en cours");
			request.getRequestDispatcher("ajouterCas.jsp").forward(request, response);
			break;

		case "CasToAdd":
			// afficher le formulaire d'ajout de cas
			
			System.out.println("MonControleur: formulaire d'ajout de Admin en cours");
			ArrayList<String> erreur1 = new ArrayList<String>();

			
			String nom_complet = request.getParameter("nom_complet");
			String telephone = request.getParameter("telephone");
			String adresse = request.getParameter("adresse");
			String code_postal = request.getParameter("code_postal");
			String etat = request.getParameter("etat");

			
		if (nom_complet.equals("")||telephone.equals("")||adresse.equals("")||code_postal.equals("")||etat.equals(""))
			erreur1.add("tous les champs doivent être remplis \n");
		
		if(!nom_complet.contains(" "))
			erreur1.add("le champ nom_complet doit contenir un \" \" entre le nom et le prenom \n");
		
		if(!(code_postal.length()==5))
			erreur1.add("le code postal doit faire 5 caractères \n");
		
		if(!(adresse.length()>=8))
			erreur1.add("l adresse doit faire 8 caractères minimum \n");
		
		if(!(etat.equals("1")||etat.equals("-1")))
			erreur1.add("le statut covid doit être 1 ou -1 \n");
		
		if(!(telephone.length()>=8 || telephone.contains("+")||telephone.contains("00")))
			erreur1.add("le téléphone doit faire 8 caractères minimum et contenir + ou 00 \n");
		
			if (erreur1.size()!=0) {
				request.setAttribute("erreur", erreur1);
				System.out.println(erreur1);
				request.getRequestDispatcher("ajouterCas.jsp").forward(request, response);
			}else {
			//on ajoute le cas
				Cas casToAdd= new Cas();
				
				try {
					casToAdd.setNom_complet(nom_complet);
					casToAdd.setTelephone(telephone);
					casToAdd.setAdresse(adresse);
					casToAdd.setCode_postal(code_postal);
					casToAdd.setEtat(Integer.parseInt(etat));
					CasDAO.ajouterCas(casToAdd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("Gestion.jsp").forward(request, response);
			break;
		}
	}

}
