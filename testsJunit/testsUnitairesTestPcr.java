package testsJunit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import covid.Cas;
import covid.TestPcr;

public class testsUnitairesTestPcr {
	ArrayList<TestPcr> liste;
	Cas cas1;
	Cas cas2;
	TestPcr test1;
	TestPcr test2;
	@Before
	public void setUp() throws Exception {
		
		liste = new ArrayList<TestPcr>();
		cas1= new Cas("v a","63500","rue du champ",-1,"+0123456789");
		cas2= new Cas("v b","63500","rue du champ",1,"+0123456789");
		test1= new TestPcr(cas1.getId_cas(), cas1.getEtat());
		test2= new TestPcr(cas2.getId_cas(), cas2.getEtat());
		liste.add(test1);
		liste.add(test2);
		
	}
	
	@Test
	public void testafficher() {
		System.out.println(TestPcr.afficher(liste));
	}
	
	@Test
	public void testvaliddate() {
		//Mois : { 1 3 5 7 8 10 12 } ; jour : 1-31 ; 
		assertTrue(TestPcr.dateIsValid(05,03,2002));
		assertFalse(TestPcr.dateIsValid(32,12,3000));
		assertFalse(TestPcr.dateIsValid(31,13,2999));
		
		
		//Mois : { 4 6 9 11 } ;  jour : 1 – 30 ; 
		assertTrue(TestPcr.dateIsValid(05,06,2002));
		assertFalse(TestPcr.dateIsValid(31,04,2000));
		
		
		//Mois : 2 ; année  /4 ; jour : 1-29
		assertTrue(TestPcr.dateIsValid(12,02,2004));
		assertTrue(TestPcr.dateIsValid(29,02,2004));
		assertFalse(TestPcr.dateIsValid(30,02,2004));
		
		
		//Mois : 2 ; année  non divisible par 4 ; jour : 1-28
		assertTrue(TestPcr.dateIsValid(28,02,2001));
		assertTrue(TestPcr.dateIsValid(04,02,2021));
		assertTrue(TestPcr.dateIsValid(01,02,2020));
		assertFalse(TestPcr.dateIsValid(29,02,2021));
		assertTrue(TestPcr.dateIsValid(29,02,3000));
		
		
	}
	@Test
	public void testIdExist() {
		assertTrue(TestPcr.idIsExisting(liste,2));
		assertFalse(TestPcr.idIsExisting(liste,5));
		
	}

}
