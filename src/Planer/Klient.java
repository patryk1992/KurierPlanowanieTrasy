package Planer;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;

public class Klient extends Agent {
	private static final long serialVersionUID = -4498367388709885619L;

	String argumenty;

	protected void setup() {
		readArguments();
//		registerAgent();
		DFServiceUtil.registerAgent(this, "Kurier");
		addBehaviours();
	}

	private void readArguments() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}
	}

	private void addBehaviours() {
		addBehaviour(new KlientBehaviourWysylanie(this, 10000));
	}

//	private void registerAgent() {
//		DFAgentDescription template = new DFAgentDescription();
//		template.setName(getAID());
//		ServiceDescription sd = new ServiceDescription();
//		sd.setType("Klient");
//		sd.setName(getLocalName());
//		template.addServices(sd);
//
//		try {
//			DFService.register(this, template);
//		} catch (FIPAException e) {
//			e.printStackTrace();
//		}
//	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		System.out.println("Bye! " + getAID().getName());

	}
}
