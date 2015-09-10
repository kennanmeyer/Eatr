package eatr;

public class Vector {
	private double x;
	private double y;
	
    public Vector normalize() {
		double size = 1.0f / Math.sqrt(x*x + y*y);
		x *= size;
		y *= size;
		return this;
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
	
	public double length() {
		return Math.sqrt((x*x)+(y*y));
	}
}
