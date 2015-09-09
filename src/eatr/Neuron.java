package eatr;

import java.util.ArrayList;

public class Neuron {
	private int neuronId;
	private int layerId;
	private double threshold;
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
		}
		output = activate(output);
		for(Synapse s : outLinks) {
			s.setInput(output);
		}
		return output;
	}
	
	public double activate(double output2){
	     return (double) 2.0*((1 / (1 + Math.exp(-output2))-.5));
	}
}