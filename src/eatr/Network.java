package eatr;

import java.util.ArrayList;

public class Network {
	int NetworkId;
	ArrayList<Layer> layers;
	
	public Network() {
		layers = new ArrayList<Layer>();
	}
	
	public void addLayer(Layer layer,int pos){
		
	}
	
	public void addLayer(Layer l){
		layers.add(l);
	}
	public void deleteLayer(int pos){
		
	}
	public Layer copyLayer(int old, int created){
		return null;
	}
	public void moveLayer(int old, int created){
		
	}
	public void addNeuron(Neuron N, int LPos, int NPos){
		
	}
	public void deleteNeuron(int LPos, int NPos){
		
	}
	public void addSynapse(Neuron source, Neuron target){
		
	}
	public void deleteSynapse(Neuron source,Neuron target){
		
	}
	
	public ArrayList<Double> run() {
		ArrayList<Double> out = null;
		for (int i = 0; i < layers.size(); i++) {
			out = layers.get(i).run();
		}
		return out;
	}
}
