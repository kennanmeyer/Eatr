package eatr;

public class NNTest {
	public static void main(String args[]) {
		//Neurons
		Neuron food_x = new Neuron();
		Neuron food_y = new Neuron();
		Neuron self_x = new Neuron();
		Neuron self_y = new Neuron();
		Neuron self_energy = new Neuron();
		Neuron enemy_x = new Neuron();
		Neuron enemy_y = new Neuron();
		Neuron enemy_energy = new Neuron();
		Neuron enemy_mouth = new Neuron();
		Neuron m1 = new Neuron();
		Neuron m2 = new Neuron();
		Neuron m3 = new Neuron();
		Neuron m4 = new Neuron();
		Neuron k1 = new Neuron();
		Neuron k2 = new Neuron();
		Neuron k3 = new Neuron();
		Neuron out_x = new Neuron();
		Neuron out_y = new Neuron();
		Neuron out_mouth = new Neuron();
		
		//Synapses
		//inputs
		Synapse in_food_x = new Synapse(food_x,food_x);
		Synapse in_food_y = new Synapse(food_y,food_y);
		Synapse in_self_x = new Synapse(self_x,self_x);
		Synapse in_self_y = new Synapse(self_y,self_y);
		Synapse in_self_energy = new Synapse(self_energy,self_energy);
		Synapse in_enemy_x = new Synapse(enemy_x,enemy_x);
		Synapse in_enemy_y = new Synapse(enemy_y,enemy_y);
		Synapse in_enemy_energy = new Synapse(enemy_energy,enemy_energy);
		Synapse in_enemy_mouth = new Synapse(enemy_mouth,enemy_mouth);
		
		new Synapse(food_x, m1);
		new Synapse(food_x, m2);
		new Synapse(food_x, m3);
		new Synapse(food_x, m4);
		
		new Synapse(food_y, m1);
		new Synapse(food_y, m2);
		new Synapse(food_y, m3);
		new Synapse(food_y, m4);
		
		new Synapse(self_x, m1);
		new Synapse(self_x, m2);
		new Synapse(self_x, m3);
		new Synapse(self_x, m4);
		
		new Synapse(self_y, m1);
		new Synapse(self_y, m2);
		new Synapse(self_y, m3);
		new Synapse(self_y, m4);
		
		new Synapse(self_energy, m1);
		new Synapse(self_energy, m2);
		new Synapse(self_energy, m3);
		new Synapse(self_energy, m4);
		
		new Synapse(enemy_x, m1);
		new Synapse(enemy_x, m2);
		new Synapse(enemy_x, m3);
		new Synapse(enemy_x, m4);
		
		new Synapse(enemy_y, m1);
		new Synapse(enemy_y, m2);
		new Synapse(enemy_y, m3);
		new Synapse(enemy_y, m4);
		
		new Synapse(enemy_energy, m1);
		new Synapse(enemy_energy, m2);
		new Synapse(enemy_energy, m3);
		new Synapse(enemy_energy, m4);

		new Synapse(enemy_mouth, m1);
		new Synapse(enemy_mouth, m2);
		new Synapse(enemy_mouth, m3);
		new Synapse(enemy_mouth, m4);
		
		//Layer 3
		new Synapse(m1, k1);
		new Synapse(m2, k1);
		new Synapse(m3, k1);
		new Synapse(m4, k1);

		new Synapse(m1, k2);
		new Synapse(m2, k2);
		new Synapse(m3, k2);
		new Synapse(m4, k2);
		
		new Synapse(m1, k3);
		new Synapse(m2, k3);
		new Synapse(m3, k3);
		new Synapse(m4, k3);
		
		//Layer 4
		new Synapse(k1, out_x);
		new Synapse(k2, out_x);
		new Synapse(k3, out_x);

		new Synapse(k1, out_y);
		new Synapse(k2, out_y);
		new Synapse(k3, out_y);
		
		new Synapse(k1, out_mouth);
		new Synapse(k2, out_mouth);
		new Synapse(k3, out_mouth);
		
		//Make Layers
		Layer layer1 = new Layer();
		layer1.add(food_x);
		layer1.add(food_y);
		layer1.add(self_x);
		layer1.add(self_y);
		layer1.add(self_energy);
		layer1.add(enemy_x);
		layer1.add(enemy_y);
		layer1.add(enemy_energy);
		layer1.add(enemy_mouth);

		Layer layer2 = new Layer();
		layer2.add(m1);
		layer2.add(m2);
		layer2.add(m3);
		layer2.add(m4);

		Layer layer3 = new Layer();
		layer3.add(k1);
		layer3.add(k2);
		layer3.add(k3);
		
		Layer layer4 = new Layer();
		layer4.add(out_x);
		layer4.add(out_y);
		layer4.add(out_mouth);
		
		Network neural_net = new Network();
		neural_net.addLayer(layer1);
		neural_net.addLayer(layer2);
		neural_net.addLayer(layer3);
		neural_net.addLayer(layer4);

		in_food_x.setInput(1);
		in_food_y.setInput(9);
		in_self_x.setInput(3);
		in_self_y.setInput(4);
		in_self_energy.setInput(5);
		in_enemy_x.setInput(1);
		in_enemy_y.setInput(2);
		in_enemy_energy.setInput(1);
		in_enemy_mouth.setInput(1);
		
		System.out.println(neural_net.run());
		
	}
}
