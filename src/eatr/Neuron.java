package eatr;

import java.util.ArrayList;

public class Neuron {
	private int neuronId;
	private int layerId;
	private double threshold =0;
	private double output;
	private int type;
	private int functionType;
	public ArrayList<Synapse> inLinks = new ArrayList<Synapse>();
	public ArrayList<Synapse> outLinks = new ArrayList<Synapse>();
	
	public Neuron() {

	}
	
	public Neuron(double t) {
		threshold = t;
	}
	
	public void addInLink(Synapse s) {
		inLinks.add(s);
	}
	
	public void addOutLink(Synapse s) {
		outLinks.add(s);
	}
	
	public double run(){
		double output = 0;
		for(Synapse s : inLinks) {
			output += s.getInput()*s.getWeight();
			//sy
		}
		output = 2*activate(output-getThreshold())-1;
		for(Synapse s : outLinks) {
			s.setInput(output);
		}
		return output;
	}
	
	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double activate(double output2){
	     return (double) (1 / (1 + Math.exp(-output2)));
	}

	public void removeOutLink(Synapse s) {
		outLinks.remove(s);
	}

	public void removeInLink(Synapse s) {
		inLinks.remove(s);
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
}