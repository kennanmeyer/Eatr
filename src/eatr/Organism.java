package eatr;

import java.util.ArrayList;
import java.util.Random;

public class Organism implements java.io.Serializable {
	//private final int MAX_AGE=0;
	private int age;
	private double energy;
	private int generation;
	private Network brain;
	private double x;
	private double y;
	private final double MIN_MOVE = 0.0;
	private final double MAX_MOVE = 6.0;
	private final int SIZE=20;
	private int fitness;

	public Organism() {
		setFitness(0);
		setAge(0);
		setEnergy(50);
	}

	public Organism(int x, int y) {
		setFitness(0);
		setAge(0);
		setEnergy(50);
		setX(x);
		setY(y);
	}
	
	public void update(ArrayList<Organism> organism_list, ArrayList<Food> food_list, int x, int y) {
		//      moveCreatures(creaturelist, foodlist,x,y)
		//Vector v = this.think(organism_list,food_list,x,y);
		this.move(this.think(organism_list,food_list,x,y),(double)x,(double)y);
		//      reduce creatureEnergy
		
//		find closest creature
		Organism enemy = null;
		double e = Double.MAX_VALUE;
		for(Organism f : organism_list) {
			double fx = Math.abs(f.getX()-this.getX());
			double fy = Math.abs(f.getY()-this.getY());
			double length = Math.sqrt((fx*fx)+(fy*fy));
			if(length < e) {
	            e = length;
	            enemy = f;
            }
		}
		
//		if creatures exist
		if(enemy != null) {
//			make vector to enemy
			Vector e_vec = new Vector(enemy.getX()-this.getX(), enemy.getY()-this.getY());
			if(e_vec.length() < (getSIZE()/2)) {
				setEnergy(getEnergy()-3.0);
			}
		}
		setEnergy(getEnergy()-(getBrain().getSize()*0.00001));
		
		//      creatures eat
		eat(food_list);
	}

	public void eat(ArrayList<Food> food){
		Food c = null;
		double d = Double.MAX_VALUE;
		for(Food f : food) {
			double fx = Math.abs(f.getX()-this.getX());
			double fy = Math.abs(f.getY()-this.getY());
			double length = Math.sqrt((fx*fx)+(fy*fy));
			if(length < d) {
	            d = length;
	            c = f;
            }
		}
		
		if(c != null) {
			double fx = Math.abs(c.getX()-this.getX());
			double fy = Math.abs(c.getY()-this.getY());
			double length = Math.sqrt((fx*fx)+(fy*fy));
			if(length <= ((SIZE/2)+(c.getSIZE()))) {
	            setEnergy(getEnergy()+c.eat());
	            setFitness(getFitness()+1);
			}
		}
	}

	public void move(Vector v, double width, double height) {
		v.setX(v.getX()*MAX_MOVE);
		v.setY(v.getY()*MAX_MOVE);

		double l = v.length();
		
		if(l < MIN_MOVE) {	//check if smaller than min move
			v.normalize();
			v.setX(v.getX()*MIN_MOVE);
			v.setY(v.getY()*MIN_MOVE);
			l = MIN_MOVE;
		} else if(l > MAX_MOVE) {	//check if larger than max move
			v.normalize();
			v.setX(v.getX()*MAX_MOVE);
			v.setY(v.getY()*MAX_MOVE);
			l = MAX_MOVE;
		}
		
//		set move vector as result
//		set position = current pos + result vector
//		subtract energy * distance
		this.setEnergy(getEnergy()-(l*0.01));
		this.setX(this.getX()+v.getX());
		this.setY(this.getY()+v.getY());
		
//		check if passed boundaries
//			reset to inside x and y, and 0 and 0
		if(this.getX() < 0) {
			this.setX(0.0);
			this.setEnergy(getEnergy()-0.1);
		} else 
		if(this.getX() > width) {
			this.setX(width);
			this.setEnergy(getEnergy()-0.1);
		} else 
		if(this.getY() < 0) {
			this.setY(0.0);
			this.setEnergy(getEnergy()-0.1);
		} else 
		if(this.getY() > height) {
			this.setY(height);
			this.setEnergy(getEnergy()-0.1);
		}
	}

	public Vector think(ArrayList<Organism> organisms, ArrayList<Food> food, int x, int y) {
//		create self vector
//		find closest food
		Food c = null;
		double d = Double.MAX_VALUE;
		for(Food f : food) {
			double fx = Math.abs(f.getX()-this.getX());
			double fy = Math.abs(f.getY()-this.getY());
			double length = Math.sqrt((fx*fx)+(fy*fy));
			if(length < d) {
	            d = length;
	            c = f;
            }
		}
		
		double food_x = this.getX();
		double food_y = this.getY();
		
//		if food exists
		if(c != null) {
//			create vector
			food_x = c.getX();
			food_y = c.getY();
		}
		
//		find closest creature
		Organism enemy = null;
		double e = Double.MAX_VALUE;
		for(Organism f : organisms) {
			double fx = Math.abs(f.getX()-this.getX());
			double fy = Math.abs(f.getY()-this.getY());
			double length = Math.sqrt((fx*fx)+(fy*fy));
			if(length < e) {
	            e = length;
	            enemy = f;
            }
		}
		
		double enemy_x = 0;
		double enemy_y = 0;
		double enemy_energy = -1;
		
//		if creatures exist
		if(enemy != null) {
//			make vector to enemy
			enemy_x = enemy.getX()-this.getX();
			enemy_y = enemy.getY()-this.getY();
		}

		//brain.inputs.get(key);
		double[] in = {
					(double)random(2)-1,food_x,food_y,this.getX(),this.getY(),
					this.getEnergy(),enemy_x,enemy_y,enemy_energy
				};
		brain.setInputs(in);
		ArrayList<Double> out = new ArrayList<Double>();
		out = brain.run();
		
//		return outputs
		
		return new Vector(out.get(0),out.get(1));	
	}

	//Status Checkers
	public boolean isDead() {
		if(this.getEnergy() <= 0) {
			return true;
		}
		return false;
	}

	public boolean canReproduce() {
		if(this.getEnergy() > 100) {
			return true;
		}
		return false;
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
	
	//Getters and Setters
	public void setAge(int age) {
		this.age = age;
	}

	public void setBrain(Network brain) {
		this.brain = brain;
	}

	public void setEnergy(double d) {
		this.energy = d;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double d) {
		this.y = d;
	}
	
	public int getAge() {
		return age;
	}

	public Network getBrain() {
		return brain;
	}

	public double getEnergy() {
		return energy;
	}

	public int getGeneration() {
		return generation;
	}

	public int getSIZE() {
		return SIZE;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

}
