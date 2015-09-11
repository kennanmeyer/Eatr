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
		n.remove();
		neurons.remove(n);
	}
	public Neuron FindNeuron(int pos){
		return null;	
	}
	public void InsertNeuron(Neuron N){
		
	}
	public void MoveNeuron (int old, int created){
		
	}
	public ArrayList<Double> run() {
		ArrayList<Double> output = new ArrayList<Double>();;
		for(Neuron n : neurons) {
			output.add(n.run());
		}
		return output;
	}
}