import java.util.ArrayList;

import com.DAO.CasDAO;
import com.covid.Cas;

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
//		<%--  <jsp:useBean id="lcas" --%>
//	<%-- 		class="com.DAO.CasDAO" scope="page"></jsp:useBean> --%>
//	<%-- 	<jsp:setProperty property="*" name="lcas" />  --%>
//	<!--  <table> -->
//	<%-- <c:forEach items="${lcas.getListeId_cas()}" var="a"> --%>
//	<!-- <tr> -->
//	<%-- <td>${a.id_cas}</td> --%>
//	<%-- <td>${a.nom_complet}</td> --%>
//	<%-- <td>${a.telephone}</td> --%>
//	<%-- <td>${a.adresse}</td> --%>
//	<%-- <td>${a.code_postal}</td> --%>
//	<%-- <td>${a.etat}</td> --%>
//
	}
}
