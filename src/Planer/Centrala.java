package Planer;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Centrala extends Agent {
	String argumenty;
	private AID Centrala;
	Package p;
	ArrayList<Integer> listPackage = new ArrayList<Integer>();

	protected void setup() {
		System.out.println("Centrala: setup start");
		initPackages();
		readArguments();
		registerAgent();
		addingBehaviours();
		System.out.println("Centrala: setup stop");
	}

	private void registerAgent() {
		DFAgentDescription template = new DFAgentDescription();
		template.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Centrala");
		sd.setName(getLocalName());
		template.addServices(sd);

		try {
			DFService.register(this, template);
			System.out.println("Centrala: setup registered centrala");
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addingBehaviours() {
		System.out.println("Centrala: setup before adding behaviours");
		addBehaviour(new CentralaBehaviourOdbieranie(this, 1000));
		//addBehaviour(new CentralaBehaviourWydawanie(this, 1000));
	}

	private void initPackages() {
		p = new Package();

		for (int i = 0; i < 36; ++i) {
			listPackage.add(i);
		}
	}

	private void readArguments() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bye! " + getAID().getName());
	}
}
