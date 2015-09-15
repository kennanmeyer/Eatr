package eatr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.w3c.dom.css.ElementCSSInlineStyle;

public class Environment implements java.io.Serializable {
	final static int ELITISM = 15;
	final static int POPSIZE =  ELITISM;
	static double MUTATION_RATE = 0.05;
	final static double CROSSOVER_RATE = 0.7;
	final static int FOOD_TOTAL = 100;
	final static double EXTINCTION_PERCENT = 0.05;
	final static int AGING_RATE = 10;
	final static int POP_MAX = POPSIZE*150;

	private Network eliteBrain;
	private int elitefitness =0;
	private int max_generation =0;

	private ArrayList<Organism> organism_list;
	private ArrayList<Food> food_list;
	private int x;
	
	private int y;
	
	private int energy_reduction_count = 0;

	public Environment(int x, int y) {
		setX(x);
		setY(y);
		organism_list = new ArrayList<Organism>();
		food_list = new ArrayList<Food>();
        while (food_list.size() < FOOD_TOTAL) {
            food_list.add(new Food(random(x-1),random(y-1)));
        }

        while (organism_list.size() < POPSIZE) {
            organism_list.add(createRandOrganism());
        }
	}

	private void ageOrganisms(int i) {
		for(Organism o : organism_list) {
			o.setAge(o.getAge()+i);
		}
	}
	
	public Organism createRandOrganism() {
		Organism o = new Organism();

		o.setEnergy(50);	
		o.setGeneration(1);
		o.setAge(0);
		o.setBrain(createRandomBrain());
		o.setPosition(random(getX()-1), random(getY()-1));
		
		return o;
	}
	
	private Organism createEliteOrganism() {
		Organism o = new Organism();

		o.setEnergy(50);	
		o.setGeneration(1);
		o.setAge(0);
		try {
			o.setBrain((Network)deepCopy(getEliteBrain()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setPosition(random(getX()-1), random(getY()-1));
		//o.getBrain().mutate();
		return o;
	}

	public Network getEliteBrain() {
		return eliteBrain;
	}
	
	public void moveOrganisms() {
		for(Organism o : organism_list) {
			Vector v = o.think(organism_list,food_list,getX(),getY());
			o.move(v,(double)getX(),(double)getY());
		}
	}
	
	public void update() {
        energy_reduction_count++;
        if (energy_reduction_count > AGING_RATE)
        {
            ageOrganisms(1);
            energy_reduction_count = 0;
            //Check if extinct
            if(organism_list.size() < 1) {
	            while (organism_list.size() < POPSIZE-ELITISM) {
	                organism_list.add(createRandOrganism());
	            }
	            while (organism_list.size() < POPSIZE) {
	                organism_list.add(createEliteOrganism());
	            }
//	            elitefitness =0;
	            food_list.clear();
            }
            
            while (food_list.size() < FOOD_TOTAL) {
                food_list.add(new Food(random(x-1),random(y-1)));
            }
        }
//        System.out.println(organism_list.size() +","+ food_list.size());
        for(int i=0;i<getOrganisms().size();i++) {
        	Organism o = getOrganisms().get(i);
        	o.update(organism_list, food_list, getX(), getY());
        	
			int max_mutations = 0;
			double max_mutation_rate = MUTATION_RATE;
			int mutations;

        	if(o.canReproduce() && organism_list.size() < POP_MAX) {
    			o.setFitness(o.getFitness()+1);

        		for(int j=0;j<2;j++) {
        			mutations = createChild(o);
        			if(mutations > max_mutations)
        				max_mutations = mutations;
//        			MUTATION_RATE *= 0.9;
        		}
        		
                if(o.getGeneration() > max_generation)
                {
                    max_generation = o.getGeneration();
                    System.out.println("new generation: "+ max_generation +  " max mutation rate: " + max_mutation_rate + " min mutation rate: " + MUTATION_RATE + " max mutations: " + max_mutations + " parent.nodes: " + o.getBrain().getSize());
                }
        	}
            if (o.isDead()) {
                organism_list.remove(i);
                if(o.getFitness() > elitefitness) {
                	try {
						eliteBrain = (Network)deepCopy(o.getBrain());
						elitefitness = o.getFitness();
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
                i--;
            }
            for(Iterator<Food> j = food_list.iterator(); j.hasNext();) {
            	Food f = j.next();
            	if(f.isEaten()) {
            		j.remove();
            	}
            }
        }
	}

	private int createChild(Organism o) {
		Organism child = new Organism();
		
		try {
//			Network b = new Network();
//			b=o.getBrain();
			child.setBrain((Network)deepCopy(o.getBrain()));
//			child.setBrain(b);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		child.setX(this.getX());
//		child.setY(this.getY());
		child.setEnergy(50);
		child.setAge(0);
		child.setGeneration(o.getGeneration() + 1);
		//child.setPosition(randomDouble(getX()-1), randomDouble(getY()-1));
		int n = child.getBrain().mutate();
		
		o.setEnergy(o.getEnergy() - 20);

		child.setPosition(randomDouble(getX()-1), randomDouble(getY()-1));
		organism_list.add(child);

		return n;
	}

	public int getElitefitness() {
		return elitefitness;
	}

	public void setElitefitness(int elitefitness) {
		this.elitefitness = elitefitness;
	}

	public static int random(int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max) + 1);
	    return randomNum;
	}
	
	public static int random(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

	private int randomDouble(double d) {
		int max = (int)Math.round(d);
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max) + 1);
	    return randomNum;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public ArrayList<Organism> getOrganisms() {
		return organism_list;
	}

	public ArrayList<Food> getFood() {
		return food_list;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getMax_generation() {
		return max_generation;
	}
	
	public Network createRandomBrain() {
		//Neurons
		Neuron food_x = new Neuron();
		Neuron food_y = new Neuron();
		Neuron self_x = new Neuron();
		Neuron self_y = new Neuron();
		Neuron self_energy = new Neuron();
		Neuron enemy_x = new Neuron();
		Neuron enemy_y = new Neuron();
		Neuron enemy_energy = new Neuron();
		Neuron m1 = new Neuron(1);
		Neuron m2 = new Neuron(1);
		Neuron m3 = new Neuron(1);
		Neuron m4 = new Neuron(1);
		Neuron k1 = new Neuron(1);
		Neuron k2 = new Neuron(1);
		Neuron k3 = new Neuron(1);
		Neuron out_x = new Neuron();
		Neuron out_y = new Neuron();

		//Synapses
		food_x.addEdge(m1);
		food_x.addEdge(m2);
		food_x.addEdge(m3);
		food_x.addEdge(m4);

		food_y.addEdge(m1);
		food_y.addEdge(m2);
		food_y.addEdge(m3);
		food_y.addEdge(m4);

		self_x.addEdge(m1);
		self_x.addEdge(m2);
		self_x.addEdge(m3);
		self_x.addEdge(m4);
		
		self_y.addEdge(m1);
		self_y.addEdge(m2);
		self_y.addEdge(m3);
		self_y.addEdge(m4);
		
		self_energy.addEdge(m1);
		self_energy.addEdge(m2);
		self_energy.addEdge(m3);
		self_energy.addEdge(m4);

		enemy_x.addEdge(m1);
		enemy_x.addEdge(m2);
		enemy_x.addEdge(m3);
		enemy_x.addEdge(m4);
		
		enemy_y.addEdge(m1);
		enemy_y.addEdge(m2);
		enemy_y.addEdge(m3);
		enemy_y.addEdge(m4);
		
		enemy_energy.addEdge(m1);
		enemy_energy.addEdge(m2);
		enemy_energy.addEdge(m3);
		enemy_energy.addEdge(m4);

		//Layer 3
		m1.addEdge(k1);
		m2.addEdge(k1);
		m3.addEdge(k1);
		m4.addEdge(k1);
		
		m1.addEdge(k2);
		m2.addEdge(k2);
		m3.addEdge(k2);
		m4.addEdge(k2);
		
		m1.addEdge(k3);
		m2.addEdge(k3);
		m3.addEdge(k3);
		m4.addEdge(k3);


		//Layer 4
		k1.addEdge(out_x);
		k2.addEdge(out_x);
		k3.addEdge(out_x);

		k1.addEdge(out_y);
		k2.addEdge(out_y);
		k3.addEdge(out_y);

		//Make Layers
		Layer layer1 = new Layer();
		layer1.add(food_x);
		layer1.add(food_y);
		layer1.add(self_x);
		layer1.add(self_y);
		layer1.add(self_energy);
		layer1.add(enemy_x);
		layer1.add(enemy_y);
		layer1.add(enemy_energy);

		Layer layer2 = new Layer();
		layer2.add(m1);
		layer2.add(m2);
		layer2.add(m3);
		layer2.add(m4);

		Layer layer3 = new Layer();
		layer3.add(k1);
		layer3.add(k2);
		layer3.add(k3);

		Layer layer4 = new Layer();
		layer4.add(out_x);
		layer4.add(out_y);

		Network neural_net = new Network();
		neural_net.addLayer(layer1);
		neural_net.addLayer(layer2);
		neural_net.addLayer(layer3);
		neural_net.addLayer(layer4);

		neural_net.randomize_weights();
		return neural_net;
	}
	
	public void save(){
		try {
			FileOutputStream fileOut = new FileOutputStream("env.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
		
	public void saveOrganisms(){
		try {
			FileOutputStream fileOut = new FileOutputStream("orgs.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(getOrganisms());
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
		if(eliteBrain != null) {
			try {
				
				FileOutputStream fileOut2 = new FileOutputStream("elite.ser");
				ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
				out2.writeObject(eliteBrain);
				out2.close();
				fileOut2.close();
			}catch(IOException i) {
				i.printStackTrace();
			}
		}
		if(elitefitness > 0) {
			try {
				
				FileOutputStream fileOut3 = new FileOutputStream("elitecount.ser");
				ObjectOutputStream out3 = new ObjectOutputStream(fileOut3);
				out3.writeObject(elitefitness);
				out3.close();
				fileOut3.close();
			}catch(IOException i) {
				i.printStackTrace();
			}
		}
	}
	
	public void loadOrganisms(){
		File f = new File("orgs.ser");
		if(f.exists() && !f.isDirectory()) {
			try {
				FileInputStream fileIn = new FileInputStream("orgs.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				setOrganism_list((ArrayList<Organism>)in.readObject());
				System.out.println("Loaded Organism!");

				in.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		File f2 = new File("elite.ser");
		if(f2.exists() && !f2.isDirectory()) {
			try {
				FileInputStream fileIn2 = new FileInputStream("elite.ser");
				ObjectInputStream in2 = new ObjectInputStream(fileIn2);
				eliteBrain = ((Network)in2.readObject());
				System.out.println("Loaded Elite!");

				in2.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		File f3 = new File("elitecount.ser");
		if(f3.exists() && !f3.isDirectory()) {
			try {
				FileInputStream fileIn3 = new FileInputStream("elitecount.ser");
				ObjectInputStream in3 = new ObjectInputStream(fileIn3);
				elitefitness = ((Integer)in3.readObject());
				System.out.println("Loaded Elite Count!");

				in3.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void setOrganism_list(ArrayList<Organism> organism_list) {
		this.organism_list = organism_list;
	}

	static public Object deepCopy(Object oldObj) throws Exception
	   {
	      ObjectOutputStream oos = null;
	      ObjectInputStream ois = null;
	      try
	      {
	         ByteArrayOutputStream bos = 
	               new ByteArrayOutputStream(); // A
	         oos = new ObjectOutputStream(bos); // B
	         // serialize and pass the object
	         oos.writeObject(oldObj);   // C
	         oos.flush();               // D
	         ByteArrayInputStream bin = 
	               new ByteArrayInputStream(bos.toByteArray()); // E
	         ois = new ObjectInputStream(bin);                  // F
	         // return the new object
	         return ois.readObject(); // G
	      }
	      catch(Exception e)
	      {
	         System.out.println("Exception in ObjectCloner = " + e);
	         throw(e);
	      }
	      finally
	      {
	         oos.close();
	         ois.close();
	      }
	   }
}
