package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class CentralaBehaviourOdbieranie extends TickerBehaviour {
	public CentralaBehaviourOdbieranie(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	public int state=0;
	
	public void onTick() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			String message=msg.getContent();				
			System.out.println(message);	
		}
	}

}
