package com.covid;

public enum Resultat {POSITIF(1),NEGATIF(-1);
	private int result;
	Resultat(int resultat) {
		this.setResult(resultat);
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		if ((result==Resultat.NEGATIF.getResult())
			||(result==Resultat.POSITIF.getResult()))
		{this.result = result;}
		else System.out.println("le résultat n'est pas 1 ou -1");
	}
}
