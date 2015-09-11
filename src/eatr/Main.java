package eatr;

public class Main {
	final static int HEIGHT = 800;
	final static int WIDTH = 800;
	final static int MAX_EPOCHS =1000;
	
	
	public static void main(String args[]) {
		Environment env = new Environment(WIDTH, HEIGHT);
		
		Draw render = new Draw(WIDTH,HEIGHT);
		
//		for(int e=0; e<MAX_EPOCHS; e++) {
		while(true) {
			try {
			  Thread.sleep(80);
			} catch (InterruptedException ie) {
			    //Handle exception
			}
			//System.out.println("Update " + e);
			env.update();
			render.draw(env);

		}	
		//render.close();
	}
}
