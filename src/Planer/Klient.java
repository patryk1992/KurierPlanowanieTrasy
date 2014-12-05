package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Klient extends Agent {
	String argumenty;
	private AID Centrala;
	Package p;

	protected void setup() {
		p = new Package();

		readArguments();

		registerAgent();
		addBehaviours();
	}

	private void readArguments() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}
	}

	private void addBehaviours() {
		addBehaviour(new KlientBehaviourWysylanie(this, 10000, p));
	}

	private void registerAgent() {
		DFAgentDescription template = new DFAgentDescription();
		template.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Klient");
		sd.setName(getLocalName());
		template.addServices(sd);

		try {
			DFService.register(this, template);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		System.out.println("Bye! " + getAID().getName());

	}
}
