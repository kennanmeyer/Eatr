package eatr;

public class Vector {
	private int x;
	private int y;
	
    public Vector normalize() {
		double size = 1.0f / Math.sqrt(x*x + y*y);
		x *= size;
		y *= size;
		return this;
    }
}
