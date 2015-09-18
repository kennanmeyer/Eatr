package eatr;

import java.util.ArrayList;

public class NNTest {
	public static void main(String args[]) {
		Neuron food_x = new Neuron();
		Neuron food_y = new Neuron();
		Neuron out_x = new Neuron();
		Neuron out_y = new Neuron();
		Neuron m1 = new Neuron(1);

		//Synapses
		food_x.addEdge(out_x,1);
		food_x.addEdge(out_y,0);

		food_y.addEdge(out_x,0);
		food_y.addEdge(out_y,1);
		
		food_x.addEdge(m1);
		food_y.addEdge(m1);

		m1.addEdge(out_x);
		m1.addEdge(out_y);

//		self_energy.addEdge(out_x);
//		self_energy.addEdge(out_y);
		
		
//		food_x.addEdge(m1);
//		food_x.addEdge(m2);
//		food_x.addEdge(m3);
//		food_x.addEdge(m4);
//
//		food_y.addEdge(m1);
//		food_y.addEdge(m2);
//		food_y.addEdge(m3);
//		food_y.addEdge(m4);
//
//		self_x.addEdge(m1);
//		self_x.addEdge(m2);
//		self_x.addEdge(m3);
//		self_x.addEdge(m4);
//		
//		self_y.addEdge(m1);
//		self_y.addEdge(m2);
//		self_y.addEdge(m3);
//		self_y.addEdge(m4);
//		
//		self_energy.addEdge(m1);
//		self_energy.addEdge(m2);
//		self_energy.addEdge(m3);
//		self_energy.addEdge(m4);
//
//		enemy_x.addEdge(m1);
//		enemy_x.addEdge(m2);
//		enemy_x.addEdge(m3);
//		enemy_x.addEdge(m4);
//		
//		enemy_y.addEdge(m1);
//		enemy_y.addEdge(m2);
//		enemy_y.addEdge(m3);
//		enemy_y.addEdge(m4);
//		
//		enemy_energy.addEdge(m1);
//		enemy_energy.addEdge(m2);
//		enemy_energy.addEdge(m3);
//		enemy_energy.addEdge(m4);
//
//		//Layer 3
//		m1.addEdge(k1);
//		m2.addEdge(k1);
//		m3.addEdge(k1);
//		m4.addEdge(k1);
//		
//		m1.addEdge(k2);
//		m2.addEdge(k2);
//		m3.addEdge(k2);
//		m4.addEdge(k2);
//		
//		m1.addEdge(k3);
//		m2.addEdge(k3);
//		m3.addEdge(k3);
//		m4.addEdge(k3);
//
//
//		//Layer 4
//		k1.addEdge(out_x);
//		k2.addEdge(out_x);
//		k3.addEdge(out_x);
//
//		k1.addEdge(out_y);
//		k2.addEdge(out_y);
//		k3.addEdge(out_y);

//		//Make Layers
//		Layer layer1 = new Layer();
//		layer1.add(food_x);
//		layer1.add(food_y);
//		layer1.add(self_x);
//		layer1.add(self_y);
//		layer1.add(self_energy);
//		layer1.add(enemy_x);
//		layer1.add(enemy_y);
//		layer1.add(enemy_energy);
//
//		Layer layer2 = new Layer();
//		layer2.add(m1);
//		layer2.add(m2);
//		layer2.add(m3);
//		layer2.add(m4);
//
//		Layer layer3 = new Layer();
//		layer3.add(k1);
//		layer3.add(k2);
//		layer3.add(k3);
//
//		Layer layer4 = new Layer();
//		layer4.add(out_x);
//		layer4.add(out_y);

//		Network neural_net = new Network();
//		neural_net.addLayer(layer1);
//		neural_net.addLayer(layer2);
//		neural_net.addLayer(layer3);
//		neural_net.addLayer(layer4);

		Layer layer1 = new Layer();
		layer1.add(food_x);
		layer1.add(food_y);
		
		Layer layer2 = new Layer();
		layer2.add(m1);
		
		Layer layer3 = new Layer();
		layer3.add(out_x);
		layer3.add(out_y);
		
		Network neural_net = new Network();
		neural_net.addLayer(layer1);
		neural_net.addLayer(layer2);
		neural_net.addLayer(layer3);


		food_x.setOutput(4.0);
		food_y.setOutput(-2.0);

//		neural_net.randomize_weights();
//			for(int i=0;i<100;i++){
//					neural_net.mutate();
//					//System.out.println(neural_net.run());
//			}
		ArrayList<Double> a = neural_net.run();
			System.out.println(a.get(0)*6);
			System.out.println(a.get(1)*6);
	}
}
