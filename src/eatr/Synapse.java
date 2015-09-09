package eatr;

import java.util.Random;

public class Synapse {
	private double input;
	private double weight;
	public Neuron source;
	public Neuron target;

	public Synapse(Neuron source, Neuron target) {
		setSource(source);
		setTarget(target);
		source.addOutLink(this);
		target.addInLink(this);
	}
	
	public Synapse(Neuron source, Neuron target, int w) {
		if(source != null) {
			setSource(source);
			source.addOutLink(this);

		}
		setTarget(target);
		target.addInLink(this);
		setWeight(w);
	}
	
	public double getInput() {
		return input;
	}
	public void setInput(double input) {
		this.input = input;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Neuron getSource() {
		return source;
	}
	public void setSource(Neuron source) {
		this.source = source;
	}
	public Neuron getTarget() {
		return target;
	}
	public void setTarget(Neuron target) {
		this.target = target;
	}
	public void remove() {
		source.removeOutLink(this);
		target.removeInLink(this);
	}
}
