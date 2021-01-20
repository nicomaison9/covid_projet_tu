package Exceptions;

public class longueurinsuffisanteException extends Exception {
	longueurinsuffisanteException(){
		super();
		System.out.println("le code postal n'est pas convertible en int");
	}
}
