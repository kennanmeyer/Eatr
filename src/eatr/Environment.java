package eatr;

import java.util.ArrayList;

public class Environment {
	private ArrayList<Organism> organism_list;
	private ArrayList<Food> food_list;
	private int x;
	private int y;
	final static int ELITISM = 5;
	final static int POPSIZE = 200 + ELITISM;
	final static double MUTATION_RATE = 0.05;
	final static double CROSSOVER_RATE = 0.7;
	private int energy_reduction_count;
	
	public Environment(int x, int y) {
		
	}
	
	public void update() {
//        energy_reduction_count++;
//        if (energy_reduction_count > 10)
//        {
//            increase_creature_age(creatures, 1);
//
//            energy_reduction_count = 0;
//
//            if (food.size() < total_food)
//                add_random_food(food);
//
//            if (creatures.size() < 5)
//                add_random_creature(creatures);
//        }
//        moveCreatures(creaturelist, foodlist,x,y)
//        reduce creatureEnergy
//        creatures eat
//        procraete
//        killCreatures
	}

	public void draw() {
		
	}
	
	public Organism createRandOrganism() {
		return null;

//	        creature new_creature;
//	        new_creature.energy = 50;	
//	        new_creature.generation = 1;
//	        new_creature.age = 0;
//	        new_creature.brain = create_random_creature_brain();
//	        new_creature.position.set(get_rand(800), get_rand(600));
//
//	        creatures.push_back(new_creature);

	}
	
    public void createChildren(int child_count, double mutationrate,int x, int y) {
    	
    }
    
	public Organism createChild(Organism parent) {
		return null;
	}
	
	public void moveOrganisms() {
		
	}
	
	public void updateFoodOrganims() {
		
	}
}
