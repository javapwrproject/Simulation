package project;

public class Application {
	
	private static int dermathoideses = 1;
	private static int euroglyphuses = 1;
	private static int length =  5;//51; //49
	private static int width = 5;//68; //75

	public static void main(String[] args) {
		
		Simulation simulation = new Simulation(dermathoideses, euroglyphuses, length, width);
		
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
