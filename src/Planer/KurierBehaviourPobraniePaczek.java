package Planer;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.ams;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.tools.sniffer.Message;

public class KurierBehaviourPobraniePaczek extends TickerBehaviour {
	Kurier kurier;

	public KurierBehaviourPobraniePaczek(Agent a, long period) {
		super(a, period);
		kurier = (Kurier) a;
	}

	public int state = 0;

	public void onTick() {
		if (kurier.listPackage.size() <= 0) {

			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Centrala");
			template.addServices(sd);
			DFAgentDescription[] result;
			AID centralaId = null;

			try {
				result = DFService.search(myAgent, template);
				if (result.length > 0) {
					centralaId = result[0].getName();
				} else {
					System.out
							.println("O kurde.... nie ma zarejestrowanej centrali :<");
				}

			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			msg2.addReceiver(centralaId);
			msg2.setContent(Dictionary.PACKAGES_REQUEST);
			myAgent.send(msg2);
			System.out.println("czekam na paczki");

			ACLMessage msg = kurier.receive();
			System.out.println("Cos odebralem: " + msg);
			if (msg != null) {
				try {
					System.out.println("Jestem w try w kurierBehaviourPobraniePaczek");
					ArrayList<Integer> meessage = (ArrayList<Integer>) msg
							.getContentObject();
					kurier.listPackage.addAll(meessage);
					System.out.println("Dodalem paczki. Ilosc paczek to: " + kurier.listPackage.size());
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}

}
