package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class CentralaBehaviourWydawanie extends TickerBehaviour {
	Centrala centrala;
	public CentralaBehaviourWydawanie(Agent a, long period) {
		super(a, period);
		centrala=(Centrala) a;
	}

	public int state=0;
	
	public void onTick() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
		}
		

	}

}
