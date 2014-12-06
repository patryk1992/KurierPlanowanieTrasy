package Planer;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;

import java.util.ArrayList;

public class Kurier extends Agent {
	
	private static final long serialVersionUID = -920146597594735871L;
	
	String argumenty;
	ArrayList<Integer> listPackage = new ArrayList<Integer>();
	boolean isWaitingForPackages = false;

	protected void setup() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}

		DFServiceUtil.registerAgent(this, "Kurier");
		addBehaviour(new KurierBehaviourPobraniePaczek(this, 1000));
		addBehaviour(new KurierBehaviourRozwozenie(this, 1000));
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
