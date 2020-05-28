package project;
import java.util.Scanner;

public class Properties {
	int dermathoideses;
	int euroglyphuses;
	
	public Properties() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME IN MITES COLONIES SIMULATION \nPLEASE GIVE NUMBER OF DERMATHOIDESES:");
		dermathoideses = Integer.parseInt(scanner.nextLine());
		System.out.println("PLEASE GIVE NUMBER OF EUROGLYPHUSES:");
		euroglyphuses = Integer.parseInt(scanner.nextLine());
		System.out.println("-------------------------------------");
	}
}
