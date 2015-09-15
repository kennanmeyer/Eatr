package eatr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Neuron implements java.io.Serializable {
	private double threshold =0;
	private double output;
	public Map<Neuron, Double> inLinks = new HashMap<Neuron, Double>();
	public Map<Neuron, Double> outLinks = new HashMap<Neuron, Double>();
	
	public Neuron() {

	}
	
	public Neuron(double t) {
		threshold = t;
	}
	
	public double activate(double output2){
	     return 1 / (1 + Math.exp(-output2));
	}
	
	public void addInLink(Neuron n) {
		inLinks.put(n,0.0);
	}
	
	public void addOutLink(Neuron n) {
		outLinks.put(n,0.0);
	}
	
	public double getThreshold() {
		return threshold;
	}

	public void remove() {
	    Iterator<Entry<Neuron, Double>> it = inLinks.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Neuron, Double> s = it.next();
			s.getKey().removeInLink(this);
			it.remove();
		}
	    it = outLinks.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Neuron, Double> s = it.next();
			s.getKey().removeOutLink(this);
			it.remove();
		}
	}

	public Map<Neuron, Double> getInLinks() {
		return inLinks;
	}

	public void setInLinks(Map<Neuron, Double> inLinks) {
		this.inLinks = inLinks;
	}

	public Map<Neuron, Double> getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(Map<Neuron, Double> outLinks) {
		this.outLinks = outLinks;
	}

	public void removeInLink(Neuron neuron) {
		inLinks.remove(neuron);
	}

	public void removeOutLink(Neuron neuron) {
		outLinks.remove(neuron);
	}

	public double run(){
		double output = 0;
	    Iterator<Entry<Neuron, Double>> it = inLinks.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Neuron, Double> s = it.next();
	        output += s.getKey().getOutput()*s.getValue();
		}
		output = 2*activate(output-getThreshold())-1;
		this.setOutput(output);
		return output;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	
	public void stimulate(double input) {
		setOutput(input);
	}
	
	public Neuron copy() {
		Neuron n = new Neuron(this.threshold);
		n.output = 0;
	    Iterator<Entry<Neuron, Double>> it = inLinks.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Neuron, Double> s = it.next();
	        n.inLinks.put(s.getKey(),s.getValue());
		}
	    it = outLinks.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Neuron, Double> s = it.next();
	        n.outLinks.put(s.getKey(),s.getValue());
		}
		return n;		
	}

	public void addEdge(Neuron target) {
        this.addOutLink(target);
        target.addInLink(this);
	}
}