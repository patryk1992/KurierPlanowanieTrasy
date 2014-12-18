package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.ArrayList;

public class KurierBehaviourPobraniePaczek extends TickerBehaviour {
	private static final long serialVersionUID = 3254059910956648855L;
	
	Kurier kurier;

	public KurierBehaviourPobraniePaczek(Agent a, long period) {
		super(a, period);
		kurier = (Kurier) a;
	}

	

	public void onTick() {
		if (kurier.listPackage.size() <= 0) {
			
			if(!kurier.isWaitingForPackages) {
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
						System.out.println("O kurde.... nie ma zarejestrowanej centrali :<");
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
				kurier.isWaitingForPackages = true;
			}
			
			ACLMessage msg = kurier.receive();
			if (msg != null) {
				try {
					ArrayList<Integer> meessage = ((ArrayList<Integer>) msg.getContentObject());
					kurier.listPackage.addAll(meessage);
					System.out.println("Dodalem paczki. Ilosc paczek to: " + kurier.listPackage.size());
					kurier.isWaitingForPackages = false;
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}

}
