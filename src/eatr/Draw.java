package eatr;

import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout; 

public class Draw extends JPanel{
	private ArrayList<Organism> organism_list;
	private ArrayList<Food> food_list;
	
    public Draw(int width, int height)
    {        
    	super();
    	organism_list = new ArrayList<Organism>();
		food_list = new ArrayList<Food>();

		JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(this);
        application.setSize(width, height);
        application.setVisible(true);  

        setBackground(Color.WHITE);
    }
    
    public void paintComponent(Graphics g)
    {
//        int width = getWidth();
//        int height = getHeight();
    	super.paintComponent(g);

        for(Organism o: organism_list) {
        	
        	int x = (int) Math.round(o.getX()-(o.getSIZE()/2));
			int y = (int) Math.round(o.getY()-(o.getSIZE()/2));
			if(o.getEnergy() > 60) {
				g.setColor(Color.BLUE);
			} else if(o.getEnergy() > 40) {
				g.setColor(Color.BLACK);
			} else if(o.getEnergy() > 20) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.RED);
			}
        	g.fillOval(x, y, o.getSIZE(), o.getSIZE());
        }

		for(Food f : food_list) {
			g.setColor(Color.GREEN);
        	int x = (int) Math.round(f.getX()-(f.getSIZE()/2));
			int y = (int) Math.round(f.getY()-(f.getSIZE()/2));
        	g.drawRect(x, y, f.getSIZE(), f.getSIZE());
		}
    }

    public void draw(Environment env) {
    	organism_list = env.getOrganisms();
    	food_list = env.getFood();
    	this.repaint();
    }
}
