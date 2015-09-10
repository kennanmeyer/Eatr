package eatr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Environment {
	final static int ELITISM = 5;
	final static int POPSIZE = 15 + ELITISM;
	final static double MUTATION_RATE = 0.05;
	final static double CROSSOVER_RATE = 0.7;
	final static int FOOD_TOTAL = 100;
	final static double EXTINCTION_PERCENT = 0.05;
	final static int AGING_RATE = 10;

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
		populate();
	}

	private void ageOrganisms(int i) {
		for(Organism o : organism_list) {
			o.setAge(o.getAge()+i);
		}
	}
	
	public Organism createChild(Organism parent) {
		Organism child = new Organism();
		
		child.setBrain(parent.getBrain());
		child.setX(parent.getX());
		child.setY(parent.getY());
		parent.setEnergy(parent.getEnergy() - 20);
		child.setEnergy(50);
		child.setAge(0);
		child.setGeneration(parent.getGeneration() + 1);
		//child.setPosition(random(getX()-1), random(getY()-1));
		child.getBrain().mutate();
		
		return child;
	}
	    
	public Network createRandomBrain() {
		Network n = new Network();
		return n;
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
	
	public void draw() {
		//draw background
		//draw food
		//draw organism
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveOrganisms() {
		for(Organism o : organism_list) {
			Vector v = o.think(organism_list,food_list,getX(),getY());
			o.move(v,(double)getX(),(double)getY());
		}
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
	
	public void update() {
        energy_reduction_count++;
        if (energy_reduction_count > AGING_RATE)
        {
            ageOrganisms(1);

            energy_reduction_count = 0;

            populate();
        }
//        System.out.println(organism_list.size() +","+ food_list.size());
        for(Organism o: organism_list) {		

        }
        
        for(Iterator<Organism> i = organism_list.iterator(); i.hasNext();) {
        	Organism o = i.next();
        	o.update(organism_list, food_list, getX(), getY());
            if (o.isDead()) {
                // Remove the current element from the iterator and the list.
                i.remove();
                System.out.println("A Creature has died");
            }
            for(Iterator<Food> j = food_list.iterator(); j.hasNext();) {
            	Food f = j.next();
            	if(f.isEaten()) {
            		j.remove();
                    System.out.println("Food has been eaten");

            	}
            }
        }


	}

	public void populate() {
        while (food_list.size() < FOOD_TOTAL) {
            food_list.add(new Food(random(x-1),random(y-1)));
        }

        while (organism_list.size() < POPSIZE) {
            organism_list.add(new Organism(random(x-1),random(y-1)));
        }
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
}
