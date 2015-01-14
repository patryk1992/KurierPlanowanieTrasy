package Planer;

import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Centrala extends Agent {
	private static final long serialVersionUID = 1525871364096848556L;

	String argumenty;
	ArrayList<Paczka> listPackage = new ArrayList<Paczka>();
	Queue<AID> kurierzy = new ConcurrentLinkedQueue<AID>();

	protected void setup() {
		System.out.println("Centrala: setup start");
//		initPackages();
		readArguments();
//		registerAgent();
		DFServiceUtil.registerAgent(this, "Centrala");
		addingBehaviours();
		System.out.println("Centrala: setup stop");
	}

	private void addingBehaviours() {
		System.out.println("Centrala: setup before adding behaviours");
		addBehaviour(new CentralaBehaviourOdbieranie(this, 1000));
		addBehaviour(new CentralaBehaviourWydawanie(this, 1000));
	}

//	private void initPackages() {
//		for (int i = 0; i < 36; ++i) {
//			listPackage.add(i);
//		}
//	}

	private void readArguments() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}
	}

	protected void takeDown() {
		DFServiceUtil.deRegister(this);
	}
}
