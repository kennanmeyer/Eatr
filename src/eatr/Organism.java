package eatr;

import java.util.ArrayList;

public class Organism {
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

	public Organism() {
		this.age = 0;
	}

	public Organism(int x, int y) {
		setX(x);
		setY(y);
		setEnergy(50);
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
			}
		}
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

	public void move(Vector v, double width, double height) {
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
		setEnergy(getEnergy()-(l*0.01));
		setX(this.getX()+v.getX());
		setY(this.getY()+v.getY());
		
//		check if passed boundaries
//			reset to inside x and y, and 0 and 0
		if(this.getX() < 0) {
			setX(0.0);
			setEnergy(getEnergy()-0.1);
		} else 
		if(this.getX() > width) {
			setX(width);
			setEnergy(getEnergy()-0.1);
		} else 
		if(this.getY() < 0) {
			setY(0.0);
			setEnergy(getEnergy()-0.1);
		} else 
		if(this.getY() > height) {
			setY(height);
			setEnergy(getEnergy()-0.1);
		}
	}

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

	public Vector think(ArrayList<Organism> o, ArrayList<Food> f, int x, int y) {
//		create self vector
//		find closest food
//		if food exists
//			create vector
//		
//		find closest creature
//		if creatures exist
//			make vector to enemy
//			stimulate input to enemy vectors and energy
//		 stimulate input for:
//			food vectors
//			self vectors
//			self energy
//		return outputs
		
		return null;	
	}

	public void update(ArrayList<Organism> organism_list, ArrayList<Food> food_list, int i, int j) {
		//      moveCreatures(creaturelist, foodlist,x,y)
		
		//      reduce creatureEnergy
		setEnergy(getEnergy()-3.0);
		//      creatures eat
		eat(food_list);
		//      procraete

	}

	public boolean isDead() {
		if(this.getEnergy() <= 0) {
			return true;
		}
		return false;
	}
}
