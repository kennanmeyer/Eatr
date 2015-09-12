package eatr;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout; 

public class Draw extends JPanel {
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

        for(Iterator<Organism> i = getOrganism_list().iterator(); i.hasNext();) {

        	Organism o = i.next();
  
        	int x = (int) Math.round(o.getX()-(o.getSIZE()/2));
			int y = (int) Math.round(o.getY()-(o.getSIZE()/2));
			if(o.getEnergy() > 60) {
				g.setColor(Color.BLUE);
			} else if(o.getEnergy() > 20) {
				g.setColor(Color.BLACK);

			} else {
				g.setColor(Color.RED);
			}
        	g.fillOval(x, y, o.getSIZE(), o.getSIZE());

        }
        for(Iterator<Food> j = getFood_list().iterator(); j.hasNext();) {
        	Food f = j.next();
			g.setColor(Color.GREEN);
        	int x = (int) Math.round(f.getX()-(f.getSIZE()/2));
			int y = (int) Math.round(f.getY()-(f.getSIZE()/2));
        	g.drawRect(x, y, f.getSIZE(), f.getSIZE());
		}
    }

    public ArrayList<Organism> getOrganism_list() {
		return organism_list;
	}

	public void setOrganism_list(ArrayList<Organism> organism_list) {
		this.organism_list = organism_list;
	}

	public ArrayList<Food> getFood_list() {
		return food_list;
	}

	public void setFood_list(ArrayList<Food> food_list) {
		this.food_list = food_list;
	}

	public void draw(Environment env) {
    	organism_list = env.getOrganisms();
    	food_list = env.getFood();
    	this.repaint();
    }
}
