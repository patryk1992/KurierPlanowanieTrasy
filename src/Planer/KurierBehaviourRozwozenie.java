package Planer;

import java.io.IOException;
import java.util.UUID;

import shortest_path.Cities;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class KurierBehaviourRozwozenie extends TickerBehaviour {
	private static final long serialVersionUID = -7893170015543204040L;
	
	Kurier kurier;
	
	public KurierBehaviourRozwozenie(Agent a, long period) {
		super(a, period);
		
		kurier = (Kurier) a;
	}

	public int state=0;
	
	public void onTick() {
		System.out.println("Dostarczono paczke do klienta. Ilosc paczek ktore pozostaly u kuriera: " + kurier.listPackage.size());
		if(kurier.listPackage.size() > 0) {
			Paczka packa = kurier.listPackage.get(0);
			System.out.println("kurier wysylanie tick onStart");
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Klient");
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

			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			msg2.addReceiver(centralaId);
			try {
				msg2.setContentObject(packa);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg2.setOntology(Dictionary.DELIVER_PACK);
			myAgent.send(msg2);
			// TODO dostarcza 
			kurier.listPackage.remove(packa);
		}
	}

}
