package Exceptions;

public class badformatException extends Exception {
	badformatException(){
		super();
		System.out.println("le champ n'a pas le bon format d'entrée ");
	}
}
