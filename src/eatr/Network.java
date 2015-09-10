package eatr;

import java.util.ArrayList;
import java.util.Random;

public class Network {
	final double MUTATION_RATE = 0.05;
	ArrayList<Layer> layers;
	Random rand = new Random();
	
	public Network() {
		layers = new ArrayList<Layer>();
	}
		
	public void addLayer(Layer l){
		layers.add(l);
	}

	public void addLayer(Layer l, int i){
		layers.add(i,l);
	}

	public void addRandomNeuronSynapse() {
    	if(layers.size() < 3) {
    		addLayer(new Layer(), 1);
    	}
    	Layer l = layers.get(rand.nextInt(layers.size()-3)+1);
    	l.add(new Neuron());

    	addRandomSynapse();
    }
	
	public void addRandomSynapse() {
    	Neuron n1 = getRandomNeuron();
        Neuron n2 = getRandomNeuron();

        //set weight to 0.0 so that it does nothing initially.
        //future generations may utilize it.
        Synapse s = new Synapse(n1, n2);
        s.setWeight(0);
    }
	
	public Layer copyLayer(int old, int created){
		return null;
	}

    public Neuron getRandomNeuron() {
    	int count = 0;
    	for(Layer l : layers) {
    		count += l.neurons.size();
    	}
        int index = rand.nextInt(count-1);
    	int incr = 0;
    	for(Layer l : layers) {
			for(Neuron n : l.neurons) {
				if(incr == index) {
					return n;
				}
				incr++;
			}
    	}
		return null;
    }

    public int mutate() {
    	return mutateStructure() + mutateWeights() + mutateThresholds();
    }

    public int mutateStructure() {
		if(rand.nextDouble() < MUTATION_RATE) {
			switch(rand.nextInt(3)+1){
				case 0:
					addRandomSynapse();
					break;
				case 1:
					removeRandomSynapse();
					break;
				case 2:
					addRandomNeuronSynapse();
					break;
				case 3:
					removeRandomNeuron();
					break;
			} 
			return 1;
		}
        return 0;
    }
    
    public int mutateThresholds() {
		int mutated = 0;
		for(Layer l : layers) {
			for(Neuron n : l.neurons) {
		        if(rand.nextDouble() < MUTATION_RATE) {
		            double delta = (2.0 * rand.nextDouble()) - 1.0;
		            n.setThreshold( n.getThreshold() + delta);
		            mutated++;
		        }
			}
		}
		return mutated;
    }
    
    public int mutateWeights() {
		int mutated = 0;
		for(Layer l : layers) {
			for(Neuron n : l.neurons) {
				for(Synapse s : n.outLinks) {
			        if(rand.nextDouble() < MUTATION_RATE) {
			            double delta = (2.0 * rand.nextDouble()) - 1.0;
						s.setWeight(s.getWeight()+delta);
			            mutated++;
			        }
				}
			}
		}
		return mutated;
    }

    public void randomize_weights() {
		for(Layer l : layers) {
			for(Neuron n : l.neurons) {
				for(Synapse s : n.outLinks) {
					s.setWeight((rand.nextDouble()*2.0)-1.0);
				}
			}
		}
    }

    public void removeRandomNeuron() {
    	if(layers.size() < 3) return;
    	int count = 0;
    	for(Layer l : layers) {
    		count += l.neurons.size();
    	}
        int index = rand.nextInt(count-1);
    	int incr = 0;
    	for(int i=1; i<layers.size()-2;i++) {
			for(Neuron n : layers.get(i).neurons) {
				if(incr == index) {
					layers.get(i).deleteNeuron(n);
					return;
				}
				incr++;
			}
    	}
    }

    public void removeRandomSynapse() {
        Neuron n1 = getRandomNeuron();

        if(n1.outLinks.size() > 0){
        	Synapse s = n1.outLinks.get(rand.nextInt(n1.outLinks.size()-1));
        	s.remove();
        }
    }

    public ArrayList<Double> run() {
		ArrayList<Double> out = null;
		for (int i = 0; i < layers.size(); i++) {
			out = layers.get(i).run();
		}
		return out;
	}
}
