package eatr;

public class Food implements java.io.Serializable {
	private final int ENERGY_VALUE = 50;
	private final int SIZE=16;
	private double x;
	private double y;
	private boolean eaten = false;
	
	public Food(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getENERGY_VALUE() {
		return ENERGY_VALUE;
	}

	public int getSIZE() {
		return SIZE;
	}

	public double eat() {
		eaten = true;
		return ENERGY_VALUE;
	}

	public boolean isEaten() {
		return eaten;
	}
}