package eatr;

import java.util.ArrayList;

public class Neuron {
	private int neuronId;
	private int layerId;
	private double threshold;
	private double output;
	private int type;
	private int functionType;
	public ArrayList<Synapse> inLinks;
	public ArrayList<Synapse> outLinks;
	
	public Neuron() {
		inLinks = new ArrayList<Synapse>();
		outLinks = new ArrayList<Synapse>();
	}
	
	public void addInLink(Synapse s) {
		inLinks.add(s);
	}
	
	public void addOutLink(Synapse s) {
		outLinks.add(s);
	}
	
	public double run(){
		float output = 0;
		for(Synapse s : inLinks) {
			output += s.getInput()*s.getWeight();
		}
		output = activate(output);
		for(Synapse s : outLinks) {
			s.setInput(output);
		}
		return output;
	}
	
	public float activate(float sum){
	     return (float) (1 / (1 + Math.exp(-sum)));
	}
}