package eatr;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout; 
import java.util.ArrayList;

public class Graphic extends JPanel {
	private ArrayList<Organism> organism_list;
	private ArrayList<Food> food_list;	
	
	public Graphic(int x, int y) {
        setBackground(Color.WHITE);
        JFrame application = new JFrame();
        organism_list = new ArrayList<Organism>();
        food_list = new ArrayList<Food>();	
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(this);           
        application.setSize(x, y);
        application.setTitle("Eatr");
        application.setVisible(true);
        
	}
	
    public void paint(Graphics g)
    {
		int width = getWidth();
		int height = getHeight();
		
		super.paintComponent(g);
		int x;
		int y;
		int size;
    	g.setColor(Color.BLACK);
        for(Organism o:organism_list) {
        	x = (int) (o.getX()+.5);
        	y = (int) (o.getY()+.5);
        	size = o.getSIZE();
        	g.drawRect( x-(size/2), y-(size/2), size , size ); 
        }
		
    	g.setColor(Color.RED);
        for(Food f:food_list) {
        	x = (int) (f.getX()+.5);
        	y = (int) (f.getY()+.5);
        	size = f.getSIZE();
        	g.fillOval( x-(size/2), y-(size/2), size , size ); 
        }

    }
    
	
	public void draw(Environment env) {
		organism_list = env.getOrganisms();
		food_list = env.getFood();

		this.repaint();
	}

}
