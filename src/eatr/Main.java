package eatr;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
	final static int HEIGHT = 800;
	final static int WIDTH = 800;
	final static int MAX_EPOCHS =1000;	
	
	public static void main(String args[]) {
		Environment env = new Environment(WIDTH, HEIGHT);
		
		Draw render = new Draw(WIDTH,HEIGHT);
			env.loadOrganisms();
		int epoch = 0;
		while(true) {
			try {
			  Thread.sleep(50);
			} catch (InterruptedException ie) {
			    //Handle exception
			}
			env.update();
			render.draw(env);
			epoch++;
			if(epoch % 10000 == 0) {
				System.out.printf("%d: MaxGen: %d\n",epoch,env.getMax_generation());
				env.saveOrganisms();
			}
		}	
	}

public static Environment load(){
	File f = new File("env.ser");
	if(f.exists() && !f.isDirectory()) {
		try {
			FileInputStream fileIn = new FileInputStream("env.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Environment e = ((Environment)in.readObject());
			System.out.println("Loaded!");
			in.close();
			return e;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	return null;
}
}
