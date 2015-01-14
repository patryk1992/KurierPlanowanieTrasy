package Planer;

import java.util.UUID;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class KlientBehaviourWysylanie extends TickerBehaviour {
	private static final long serialVersionUID = -5218160089894174649L;

	Klient klient;
	
	
	public KlientBehaviourWysylanie(Agent a, long period) {
		super(a, period);
		klient = (Klient) a;	
	}

	public void onTick() {
		System.out.println("Klient wysylanie tick onStart");
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Centrala");
		template.addServices(sd);
		DFAgentDescription[] result;
		AID centralaId = null;
		
		try {
			result = DFService.search(myAgent, template);
			centralaId = result[0].getName();
			System.out.println("Znaleziono centrale, wysylam");
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	Package packToSend =  new Package()

		ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
		msg2.addReceiver(centralaId);
		msg2.setContent(Dictionary.PACKAGES_NEW);
		// dac wysylanie paczki
		myAgent.send(msg2);
		System.out.println("Klient wysylanie tick onStop"); 
	}

}
