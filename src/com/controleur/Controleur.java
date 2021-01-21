package com.controleur;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.AdminDAO;
import com.DAO.CasDAO;
import com.DAO.TestPcrDAO;
import com.covid.Admin;
import com.covid.Cas;
import com.covid.TestPcr;

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
			ArrayList<String> erreur3 = new ArrayList<String>();
			if (AdminDAO.isAdmin4real(admin)) {
				response.sendRedirect("Gestion.jsp");
			} else {
				erreur3.add("login ou mot de passe inconnus");
				request.setAttribute("erreur", erreur3);
				request.getRequestDispatcher("index.jsp").forward(request, response);
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
			
			System.out.println("MonControleur: formulaire d'ajout de cas  en cours");
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
		case "testsDuCas":
			String id_cas2Search=request.getParameter("id_casToSearch");
			request.setAttribute("id_casToSearch",id_cas2Search );
			request.getRequestDispatcher("Gestion.jsp").forward(request, response);
			break;
		
//cas de testPCR			
			
		case "ajouterTestPcr":
			// afficher le formulaire d'ajout de cas
			System.out.println("MonControleur: formulaire d'ajout de TestPcr en cours");
			request.getRequestDispatcher("ajouterTestPcr.jsp").forward(request, response);
			break;

		case "TestPcrToAdd":
			// afficher le formulaire d'ajout de cas
			
			System.out.println("MonControleur: formulaire d'ajout de TestPcr en cours");
			ArrayList<String> erreur2 = new ArrayList<String>();
			LocalDate datetest = LocalDate.now();
			int annee = datetest.getYear();
			int mois = datetest.getMonthValue();
			int jour = datetest.getDayOfMonth();
			System.out.println("date du jour = " +jour+mois+annee);
			
			String id_teste = request.getParameter("id_teste");
			String resultat = request.getParameter("resultat");
System.out.println("resultat"+resultat);
			
		if (id_teste.equals("")||resultat.equals(""))
			erreur2.add("tous les champs doivent être remplis \n");
			
			
		if (!TestPcr.dateIsValid(jour,mois,annee))
		{
			erreur2.add("la date rentrée n'est pas correcte");
			if (mois < 1 || mois > 12) {
				erreur2.add("le mois doit être compris entre 1 et 12");
			}
			if (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12) {
				if (jour < 1 || jour > 31) {
					erreur2.add("1 à 31 jours pour les mois 1 3 5 7 8 10 12");
				}
			}
			if (mois == 4 || mois == 6 || mois == 8 || mois == 11) {
				if (jour < 1 || jour > 30) {
					erreur2.add("1  à 30 jours pour les mois 4 6 8 11");
				}
			}
			if (mois == 2) {
				if (annee % 4 == 0) {
					if (jour < 1 || jour > 29) {
						erreur2.add("si année bisextile 1 à 29 jours pour fevrier");
					}
				}else
					if (jour < 1 || jour > 28) {
						erreur2.add("si année pas bisextile 1 à 28 jours pour  fevrier");
					}
			}
		}
		;
		
		
		if(!(resultat.equals("1")||resultat.equals("-1")))
			erreur2.add("le statut covid doit être 1 ou -1 \n");
		
			try {
				if(request.getParameter("id_teste").equals("nouveau"))
						{	erreur2.add("ajouter le cas avant de saisir son test \n");
						request.getRequestDispatcher("ajouterCas.jsp").forward(request, response);}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			if (erreur2.size()!=0) {
				request.setAttribute("erreur", erreur2);
				request.setAttribute("jour", jour);
				request.setAttribute("mois", mois);
				request.setAttribute("annee", annee);
				System.out.println(erreur2);
				request.getRequestDispatcher("ajouterTestPcr.jsp").forward(request, response);
			}else {
			//on ajoute le TestPcr
				TestPcr testToAdd= new TestPcr();
				
				try {
					testToAdd.setJour(jour);
					testToAdd.setMois(mois);
					testToAdd.setAnnee(annee);
					testToAdd.setId_cas(Integer.parseInt(id_teste));
					
					testToAdd.setResultat(Integer.parseInt(resultat));
					System.out.println(testToAdd.toString());
					TestPcrDAO.ajouterTestPcr(testToAdd);
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
