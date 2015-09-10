package eatr;

public class Organism {
	//private final int MAX_AGE=0;
	private int age;
	private int energy;
	private int generation;
	private Network brain;
	private int x;
	private int y;
	
	private final int ORGANISM_SIZE=20;
	private final int FOOD_SIZE=20;
	
	public Organism() {
		this.age = 0;
	}

   public void move_vector() {
    	
    }
    
    public Vector think() {
		return null;	
    }
    
    public void move() {
    	
    }
    
    public void eat(){
    	
    }
    
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public Network getBrain() {
		return brain;
	}

	public void setBrain(Network brain) {
		this.brain = brain;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getORGANISM_SIZE() {
		return ORGANISM_SIZE;
	}

	public int getFOOD_SIZE() {
		return FOOD_SIZE;
	}

	public void update() {
//      moveCreatures(creaturelist, foodlist,x,y)
//      reduce creatureEnergy
//      creatures eat
//      procraete
//      killCreatures		
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
}
