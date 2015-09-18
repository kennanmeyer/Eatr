package eatr;

import java.util.ArrayList;

public class Layer implements java.io.Serializable {
	private int layerId;
	public ArrayList<Neuron> neurons;
//	boolean isSigmoid = true;
	
	public Layer() {
		neurons = new ArrayList<Neuron>();
	}
	
	public void add(Neuron n) {
		neurons.add(n);
	}
	
	public Neuron copyNeuron(int old){
		return null;
	}
	
	public void deleteNeuron(Neuron n){
		for (Neuron e : n.inLinks.keySet()) {
			e.removeOutLink(n);
		}
		for (Neuron e : n.outLinks.keySet()) {
			e.removeInLink(n);
		}
		neurons.remove(n);
	}
	
	public Neuron FindNeuron(int pos){
		return null;	
	}

	public ArrayList<Double> run() {
		ArrayList<Double> output = new ArrayList<Double>();
		for(Neuron n : neurons) {
			output.add(n.run());
		}
		return output;
	}
}