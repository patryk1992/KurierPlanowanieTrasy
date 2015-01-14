package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.ArrayList;

public class KurierBehaviourPobraniePaczek extends TickerBehaviour {
	private static final long serialVersionUID = 3254059910956648855L;
	
	Kurier kurier;
	public MessageTemplate mt;
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
				try {
					msg2.setContentObject(Dictionary.PACKAGES_REQUEST);
					msg2.setOntology(Dictionary.PACKAGES_REQUEST);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				myAgent.send(msg2);
				System.out.println("czekam na paczki");
				kurier.isWaitingForPackages = true;
			}
			
			
			mt = MessageTemplate.MatchOntology(Dictionary.RECV_PACK);
			ACLMessage msg = kurier.receive(mt);
			if (msg != null) {
				try {
					ArrayList<Paczka> message = ((ArrayList<Paczka>) msg.getContentObject());
					kurier.listPackage.addAll(message);
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
