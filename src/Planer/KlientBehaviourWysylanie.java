package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class KlientBehaviourWysylanie extends TickerBehaviour {
	Package p;

	public KlientBehaviourWysylanie(Agent a, long period, Package p) {
		super(a, period);
		this.p = p;

	}

	public void onTick() {

		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Centrala");
		template.addServices(sd);
		DFAgentDescription[] result;
		AID Centrala = null;
		
		try {
			result = DFService.search(myAgent, template);
			Centrala = result[0].getName();

		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
		msg2.addReceiver(Centrala);
		msg2.setContent("klientmessage");
		myAgent.send(msg2);

	}

}
