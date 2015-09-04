package eatr;

public class Main {
	final static int HEIGHT = 800;
	final static int WIDTH = 800;
	final static int MAX_EPOCHS = 10000;
	
	
	public static void main(String args[]) {
		Environment env = new Environment(WIDTH, HEIGHT);
		
		for(int e=0; e<MAX_EPOCHS; e++) {
			env.update();
		}	
	}
}
