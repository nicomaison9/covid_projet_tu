package com.covid;

import java.util.ArrayList;

public class ListCas {
private static ArrayList<Cas> listOfCas;
public ListCas() {
	super();
	listOfCas=new ArrayList<Cas>();
}
public ListCas(ArrayList<Cas> list) {
	super();
	listOfCas=new ArrayList<Cas>();
	setListOfCas(list);
}
public ArrayList<Cas> getListOfCas() {
	return listOfCas;
}
public static void setListOfCas(ArrayList<Cas> listOfCas) {
	ListCas.listOfCas = listOfCas;
}
public void ajouter(Cas cas) {
	if(!ListCas.isExisting(listOfCas,cas.getId_cas()))
	listOfCas.add(cas);
}
public String afficher() {
	String str = "";
	str="=======liste des cas enregistrés=======\n";
	for(int i=0;i<listOfCas.size();i++) {
		str=str+ listOfCas.get(i).toString()+"\n";
	}
	return str;
}
public static boolean isExisting(ArrayList<Cas> liste,int id_cas) {
	//vérifie l'existence d'un cas par son Id dans une liste de cas
	boolean existing=false;
	for(int i=0;i<liste.size();i++) {
		if (liste.get(i).getId_cas()==id_cas)
			{existing= true;
			System.out.println(liste.get(i).getId_cas() +"-"+id_cas);
			}

		
	}
	return existing;
	
}

}
