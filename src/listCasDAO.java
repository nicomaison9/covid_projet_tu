import java.util.ArrayList;

import com.DAO.CasDAO;
import com.DAO.TestPcrDAO;
import com.covid.Cas;
import com.covid.TestPcr;

public class listCasDAO {
	listCasDAO() {

//	CasDAO lcas = new CasDAO();
		ArrayList<Cas> liste=new ArrayList<Cas>();
		try {
			liste = CasDAO.getListe();
			String str = "";
			str="=======liste des cas enregistrés=======\n";
			for(int i=0;i<liste.size();i++) {
				str=str+ liste.get(i).toString()+"\n";
			}
			System.out.println(str); 
			for (int i=0; i < liste.size(); i++) {
				liste.get(i).getId_cas();
				liste.get(i).getNom_complet();
				liste.get(i).getTelephone();
				liste.get(i).getAdresse();
				liste.get(i).getCode_postal();
				liste.get(i).getEtat();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> liste2=new ArrayList<String>();
		try {
			liste2 = CasDAO.getListeId_cas();
			String str = "";
			str="=======liste des Id de cas enregistrés=======\n";
			for(int i=0;i<liste2.size();i++) {
				str=str+ liste2.get(i).toString()+"\n";
			}
			System.out.println(str); 
			for (int i=0; i < liste2.size(); i++) {
				liste2.get(i);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<TestPcr> listep=new ArrayList<TestPcr>();
		try {
			listep = TestPcrDAO.getListe();
			String str1 = "";
			str1 = "=======liste des cas enregistrés=======\n";
			for (int i = 0; i < listep.size(); i++) {
				str1 = str1 + listep.get(i).toString() + "\n";
			}
			System.out.println(str1);
			for (int i = 0; i < listep.size(); i++) {
		listep.get(i).getId_test();
		
			
		listep.get(i).getJour();
		
			
		listep.get(i).getMois();
		
			
		listep.get(i).getAnnee()
		;
			
		listep.get(i).getId_cas();
		
			
		listep.get(i).getResultat();
	
	
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//		--  <jsp:useBean id="lcas" --
//	-- 		class="com.DAO.CasDAO" scope="page"></jsp:useBean> --
//	-- 	<jsp:setProperty property="*" name="lcas" />  --
//	<!--  <table> -->
//	-- <c:forEach items="${lcas.getListeId_cas()}" var="a"> --
//	<!-- <tr> -->
//	-- ${a.id_cas} --
//	-- ${a.nom_complet} --
//	-- ${a.telephone} --
//	-- ${a.adresse} --
//	-- ${a.code_postal} --
//	-- ${a.etat} --
//
	}
}
