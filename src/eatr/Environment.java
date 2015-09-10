package eatr;

import java.util.ArrayList;
import java.util.Random;

public class Environment {
	private ArrayList<Organism> organism_list;
	private ArrayList<Food> food_list;
	private int x;
	private int y;
	private int energy_reduction_count = 0;

	final static int ELITISM = 5;
	final static int POPSIZE = 200 + ELITISM;
	final static double MUTATION_RATE = 0.05;
	final static double CROSSOVER_RATE = 0.7;
	final static int FOOD_TOTAL = 100;
	final static double EXTINCTION_PERCENT = 0.05;
	final static int AGING_RATE = 10;
	
	public Environment(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void update() {
        energy_reduction_count++;
        if (energy_reduction_count > AGING_RATE)
        {
            ageOrganisms(1);

            energy_reduction_count = 0;

            if (food_list.size() < FOOD_TOTAL) {
                food_list.add(new Food());
            }

            if (organism_list.size() < POPSIZE*EXTINCTION_PERCENT) {
                organism_list.add(new Organism());
            }
        }
        for(Organism o: organism_list) {
//          moveCreatures(creaturelist, foodlist,x,y)
//          reduce creatureEnergy
//          creatures eat
//          procraete
//          killCreatures		
        	o.update();
        }
	}

	private void ageOrganisms(int i) {
		for(Organism o : organism_list) {
			o.setAge(i);
		}
	}

	public void draw() {
		//draw background
		//draw food
		//draw organism
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
	    
	public Organism createChild(Organism parent) {
		Organism child = new Organism();
		
		child.setBrain(parent.getBrain());
		child.setX(parent.getX());
		parent.setEnergy(parent.getEnergy() - 20);
		child.setEnergy(50);
		child.setAge(0);
		child.setGeneration(parent.getGeneration() + 1);
		//child.setPosition(random(getX()-1), random(getY()-1));
		child.getBrain().mutate();
		
		return child;
	}
	
	public void moveOrganisms() {
		for(Organism o : organism_list) {
			o.move();
		}
	}
	
	public void updateFoodOrganims() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Network createRandomBrain() {
		return null;
	}
	
	public static int random(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static int random(int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max) + 1);
	    return randomNum;
	}

}
