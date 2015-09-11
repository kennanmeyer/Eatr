package eatr;

import java.util.ArrayList;

public class Neuron implements java.io.Serializable {
	private double threshold =0;
	private double output;
	public ArrayList<Synapse> inLinks = new ArrayList<Synapse>();
	public ArrayList<Synapse> outLinks = new ArrayList<Synapse>();
	
	public Neuron() {

	}
	
	public Neuron(double t) {
		threshold = t;
	}
	
	public double activate(double output2){
	     return 1 / (1 + Math.exp(-output2));
	}
	
	public void addInLink(Synapse s) {
		inLinks.add(s);
	}
	
	public void addOutLink(Synapse s) {
		outLinks.add(s);
	}
	
	public double getThreshold() {
		return threshold;
	}

	public void remove() {
		for(Synapse s : inLinks) {
			s.source.removeInLink(s);
		}
		inLinks.clear();
		for(Synapse s : outLinks) {
			s.target.removeOutLink(s);
		}
		outLinks.clear();
	}

	public void removeInLink(Synapse s) {
		inLinks.remove(s);
	}

	public void removeOutLink(Synapse s) {
		outLinks.remove(s);
	}

	public double run(){
		double output = 0;
		for(Synapse s : inLinks) {
			output += s.getInput()*s.getWeight();
		}
		output = 2*activate(output-getThreshold())-1;
		for(Synapse s : outLinks) {
			s.setInput(output);
		}
		return output;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	
	public void stimulate(double input) {
		for(Synapse s : outLinks) {
			s.setInput(output);
		}
	}
	
	public Neuron copy() {
		Neuron n = new Neuron(this.threshold);
		n.output = 0;
		for( Synapse s : inLinks ){
			n.inLinks.add(s.copy());
		}
		for( Synapse s : outLinks ){
			n.outLinks.add(s.copy());
		}
		return n;		
	}
}