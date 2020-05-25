package project;

public class Application {
	
	private static int dermathoideses = 20;
	private static int euroglyphuses = 20;
	private static int heigth =  49;//51; //49
	private static int width = 75;//68; //75

	public static void main(String[] args) {
		
		Simulation simulation = new Simulation(dermathoideses, euroglyphuses, heigth, width);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (simulation.notOver() ) {
			simulation.runSimulation();
			simulation.getPrinter().print();
		}	
		
		simulation.getPrinter().summary();
		
	}

}
