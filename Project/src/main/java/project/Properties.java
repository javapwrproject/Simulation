package project;
import java.util.Scanner;
/**
 * An object which gets the initial conditions from the user
 */
public class Properties {
	int dermathoideses;
	int euroglyphuses;
	
	public Properties() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("WELCOME IN MITES COLONIES SIMULATION \nPLEASE GIVE NUMBER OF DERMATHOIDESES:");
			dermathoideses = Integer.parseInt(scanner.nextLine());
			System.out.println("PLEASE GIVE NUMBER OF EUROGLYPHUSES:");
			euroglyphuses = Integer.parseInt(scanner.nextLine());
		}
		System.out.println("-------------------------------------");
	}
}
