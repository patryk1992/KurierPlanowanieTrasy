package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class CentralaBehaviourWydawanie extends TickerBehaviour {
	public CentralaBehaviourWydawanie(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	public int state=0;
	
	public void onTick() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			String meessage=msg.getContent();
			int wlacz=Integer.parseInt(meessage);
			state=wlacz;
			
			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			msg2.addReceiver(new AID("Woda", AID.ISLOCALNAME));	
			msg2.setContent(String.valueOf(state));
			myAgent.send(msg2);
		}
		

	}

}
