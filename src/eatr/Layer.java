package eatr;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer {
	private int layerId;
	public ArrayList<Neuron> neurons;
//	boolean isSigmoid = true;
	
	public Layer() {
		neurons = new ArrayList<Neuron>();
	}
	
	public void InsertNeuron(Neuron N){
		
	}
	public void deleteNeuron(Neuron n){
		n.remove();
		neurons.remove(n);
	}
	public Neuron copyNeuron(int old){
		return null;
	}
	public void MoveNeuron (int old, int created){
		
	}
	public Neuron FindNeuron(int pos){
		return null;	
	}
	public ArrayList<Double> run() {
		ArrayList<Double> output = new ArrayList<Double>();;
		for(Neuron n : neurons) {
			output.add(n.run());
		}
		return output;
	}
	public void add(Neuron n) {
		neurons.add(n);
	}
	
//	public float[] run(float[] in) {
//		System.arraycopy(in, 0, input, 0, in.length);
//		input[input.length - 1] = 1;
//		int offs = 0;
//		Arrays.fill(output, 0);
//		for (int i = 0; i < output.length; i++) {
//			for (int j = 0; j < input.length; j++) {
//				output[i] += weights[offs + j] * input[j];
//			}
//			output[i] = (float) (1 / (1 + Math.exp(-output[i])));
//			offs += input.length;
//		}
//		return Arrays.copyOf(output, output.length);
//	}
}
