package testsJunit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Exceptions.badformatException;
import Exceptions.longueurinsuffisanteException;
import Exceptions.nonconvertibleException;
import covid.Admin;
import covid.Cas;
import covid.ListCas;

public class testsUnitairesCasEtAdmin {
	private Cas cas1;
	private Cas cas2;
	private Cas cas3;
	private Cas cas4;
	private Cas cas5;
	private Cas cas6;
	private ListCas listecas;
	private Admin a;

	@Before
	public void setup() {

		// valide
		 cas1 = new Cas("v a", "63500", "rue du champ", -1, "+0123456789");
		 cas2 = new Cas("v b", "63500", "rue du champ", 1, "+0123456789");
		// pas valide
		 cas3 = new Cas("v c", "63500", "rue du champ", -1, "+0123456789");
		 cas4 = new Cas("v c", "63500", "rue du champ", -1, "+0123456789");
		 cas5 = new Cas("v c", "63500", "rue du champ", -1, "+0123456789");
		 cas6 = new Cas("v c", "63500", "rue du champ", -1, "+0123456789");
		 listecas= new ListCas();
		 listecas.ajouter(cas1);
		 listecas.ajouter(cas2);
		 listecas.ajouter(cas3);
		 listecas.ajouter(cas4);
		 a=new Admin("admin","orsys");
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testIncrementCas() {
		//System.out.println(cas1.getId_cas() + " " + cas2.getId_cas());
		assertEquals(cas1.getId_cas(), cas2.getId_cas() - 1);
	}

	@Test
	public void testNomSpacePrenom() throws badformatException {
		assertTrue(Cas.nomIsComplet("a b"));
		assertFalse(Cas.nomIsComplet("ab"));
//		exception.expect(Exception.class);
	}

	@Test
	public void codepostalconversible()throws nonconvertibleException {
			assertFalse(Cas.codePostalIsValid("6350a"));
//			exception.expect(Exception.class);
				
	}

	@Test
	public void codepostallongueur5() throws Exception{
		

			assertTrue(Cas.codePostalIsValid("63500"));
			//assertFalse(Cas.codePostalIsValid("6350a"));
			assertFalse(Cas.codePostalIsValid("6350"));
			
			assertFalse(Cas.codePostalIsValid("635000"));
//			exception.expect(Exception.class);
		
	}

	@Test
	public void testadresse() throws longueurinsuffisanteException {
		assertTrue(Cas.adresseIsValid("rue du champ"));
		assertFalse(Cas.adresseIsValid("rue"));
	}
	@Test
	public void teststatutCovid()  {
		assertTrue(Cas.etatIsValid(-1));
		assertTrue(Cas.etatIsValid(1));
		assertFalse(Cas.etatIsValid(20));
		
	}
	@Test
	public void testtelephone() throws badformatException  {
		assertTrue(Cas.telephoneIsValid("+01000000"));
		assertTrue(Cas.telephoneIsValid("000000000"));
		assertFalse(Cas.telephoneIsValid("0000"));
	}
	
	@Test
	public void testAdminIsAdmin() throws badformatException  {
		assertTrue(Admin.isAdmin4real(a));
		System.out.println(a.toString());;
		assertTrue(a.equals(a));
	}
	
	@Test
	public void testListCas_isExisting() {
		System.out.println(listecas.afficher());
		assertTrue(ListCas.isExisting(listecas.getListOfCas(), cas1.getId_cas()));
			
	}
//
//	@Test
//	public void testIncrementCas4() {
//	}
//
//	@Test
//	public void testIncrementCas5() {
//	}

}
