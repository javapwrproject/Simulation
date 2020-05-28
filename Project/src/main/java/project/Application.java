package project;

public class Application {
	
	private static final int heigth = 49; //51 for linux terminal
	private static final int width = 75; //68

	public static void main(String[] args) {
		
		Simulation simulation = new Simulation(new Properties(), heigth, width);
		
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
