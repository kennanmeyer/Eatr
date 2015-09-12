package eatr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

public class Environment implements java.io.Serializable {
	final static int ELITISM = 5;
	final static int POPSIZE = 195 + ELITISM;
	static double MUTATION_RATE = 0.05;
	final static double CROSSOVER_RATE = 0.7;
	final static int FOOD_TOTAL = 110;
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
		o.getBrain().mutate();
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
            while (food_list.size() < FOOD_TOTAL) {
                food_list.add(new Food(random(x-1),random(y-1)));
            }
            if(organism_list.size() < 1) {
	            while (organism_list.size() < POPSIZE-ELITISM) {
	                organism_list.add(createRandOrganism());
	            }
	            while (organism_list.size() < POPSIZE) {
	                organism_list.add(createEliteOrganism());
	            }
	            //System.out.println(elitefitness);
	            elitefitness =0;
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                //checkIfElite(o.getFitness(),o.getBrain());
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
			child.setBrain((Network)deepCopy(o.getBrain()));
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

	private Food getNearestFood() {
		
		return null;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Network createRandomBrain() {
		//Neurons
		Neuron rand = new Neuron();
		Neuron food_x = new Neuron();
		Neuron food_y = new Neuron();
		Neuron self_x = new Neuron();
		Neuron self_y = new Neuron();
		Neuron self_energy = new Neuron();
		Neuron enemy_x = new Neuron();
		Neuron enemy_y = new Neuron();
		Neuron enemy_energy = new Neuron();
		//Neuron enemy_mouth = new Neuron();
		Neuron m1 = new Neuron(1);
		Neuron m2 = new Neuron(1);
		Neuron m3 = new Neuron(1);
		Neuron m4 = new Neuron(1);
		Neuron k1 = new Neuron(1);
		Neuron k2 = new Neuron(1);
		Neuron k3 = new Neuron(1);
		Neuron out_x = new Neuron();
		Neuron out_y = new Neuron();
//		Neuron out_mouth = new Neuron();

		//Synapses
		//inputs
//		Synapse in_rand = new Synapse(null,rand,1);
//		Synapse in_food_x = new Synapse(null,food_x,1);
//		Synapse in_food_y = new Synapse(null,food_y,1);
//		Synapse in_self_x = new Synapse(null,self_x,1);
//		Synapse in_self_y = new Synapse(null,self_y,1);
//		Synapse in_self_energy = new Synapse(null,self_energy,1);
//		Synapse in_enemy_x = new Synapse(null,enemy_x,1);
//		Synapse in_enemy_y = new Synapse(null,enemy_y,1);
//		Synapse in_enemy_energy = new Synapse(null,enemy_energy,1);
		//Synapse in_enemy_mouth = new Synapse(null,enemy_mouth,1);

		new Synapse(rand, m1);
		new Synapse(rand, m2);
		new Synapse(rand, m3);
		new Synapse(rand, m4);
		
		new Synapse(food_x, m1);
		new Synapse(food_x, m2);
		new Synapse(food_x, m3);
		new Synapse(food_x, m4);

		new Synapse(food_y, m1);
		new Synapse(food_y, m2);
		new Synapse(food_y, m3);
		new Synapse(food_y, m4);

		new Synapse(self_x, m1);
		new Synapse(self_x, m2);
		new Synapse(self_x, m3);
		new Synapse(self_x, m4);

		new Synapse(self_y, m1);
		new Synapse(self_y, m2);
		new Synapse(self_y, m3);
		new Synapse(self_y, m4);

		new Synapse(self_energy, m1);
		new Synapse(self_energy, m2);
		new Synapse(self_energy, m3);
		new Synapse(self_energy, m4);

		new Synapse(enemy_x, m1);
		new Synapse(enemy_x, m2);
		new Synapse(enemy_x, m3);
		new Synapse(enemy_x, m4);

		new Synapse(enemy_y, m1);
		new Synapse(enemy_y, m2);
		new Synapse(enemy_y, m3);
		new Synapse(enemy_y, m4);

		new Synapse(enemy_energy, m1);
		new Synapse(enemy_energy, m2);
		new Synapse(enemy_energy, m3);
		new Synapse(enemy_energy, m4);

//		new Synapse(enemy_mouth, m1);
//		new Synapse(enemy_mouth, m2);
//		new Synapse(enemy_mouth, m3);
//		new Synapse(enemy_mouth, m4);

		//Layer 3
		new Synapse(m1, k1);
		new Synapse(m2, k1);
		new Synapse(m3, k1);
		new Synapse(m4, k1);

		new Synapse(m1, k2);
		new Synapse(m2, k2);
		new Synapse(m3, k2);
		new Synapse(m4, k2);

		new Synapse(m1, k3);
		new Synapse(m2, k3);
		new Synapse(m3, k3);
		new Synapse(m4, k3);

		//Layer 4
		new Synapse(k1, out_x);
		new Synapse(k2, out_x);
		new Synapse(k3, out_x);

		new Synapse(k1, out_y);
		new Synapse(k2, out_y);
		new Synapse(k3, out_y);

//		new Synapse(k1, out_mouth);
//		new Synapse(k2, out_mouth);
//		new Synapse(k3, out_mouth);

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
//		layer1.add(enemy_mouth);

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
//		layer4.add(out_mouth);

		Network neural_net = new Network();
		neural_net.addLayer(layer1);
		neural_net.addLayer(layer2);
		neural_net.addLayer(layer3);
		neural_net.addLayer(layer4);

//		ArrayList<Synapse> in_map = new ArrayList<Synapse>();
//		in_map.add(in_rand);
//		in_map.add(in_food_x);
//		in_map.add(in_food_y);
//		in_map.add(in_self_x);
//		in_map.add(in_self_y);
//		in_map.add(in_self_energy);
//		in_map.add(in_enemy_x);
//		in_map.add(in_enemy_y);
//		in_map.add(in_enemy_energy);
		
//		neural_net.mapInputs(in_map);
		
//		in_food_x.setInput(1);
//		in_food_y.setInput(-2);
//		in_self_x.setInput(3);
//		in_self_y.setInput(4);
//		in_self_energy.setInput(5);
//		in_enemy_x.setInput(-1);
//		in_enemy_y.setInput(-2);
//		in_enemy_energy.setInput(1);
//		in_enemy_mouth.setInput(1);

		neural_net.randomize_weights();
		return neural_net;
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
	}
	
	public void loadOrganisms(){
	    try {
	        FileInputStream fileIn = new FileInputStream("orgs.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        setOrganism_list((ArrayList<Organism>)in.readObject());
	        in.close();
	      }
	      catch (Exception e) {
	          System.out.println(e);
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
