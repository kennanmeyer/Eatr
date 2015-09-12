package eatr;

import java.io.File;

public class Main {
	final static int HEIGHT = 800;
	final static int WIDTH = 800;
	final static int MAX_EPOCHS =1000;
	
	
	public static void main(String args[]) {
		Environment env = new Environment(WIDTH, HEIGHT);
		
		Draw render = new Draw(WIDTH,HEIGHT);
		File f = new File("orgs.ser");
		if(f.exists() && !f.isDirectory()) {
			env.loadOrganisms();
			System.out.println("Loaded File!");
		}
//		for(int e=0; e<MAX_EPOCHS; e++) {
		int epoch = 0;
		while(true) {
			try {
			  Thread.sleep(10);
			} catch (InterruptedException ie) {
			    //Handle exception
			}
			//System.out.println("Update " + e);
			env.update();
			render.draw(env);
			epoch++;
			if(epoch % 1000 == 0) {
				System.out.println(epoch);
				env.saveOrganisms();
			}

		}	
		//render.close();
	}
}
