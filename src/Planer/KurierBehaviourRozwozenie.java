package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

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
			//tutaj pisz
//			
//			shortest_path.Dijkstra dj = new Dijkstra();
//			dj.computePaths(dj.POZ);
//			for(int i=0; i<kurier.listPackage.size();i++)
//			{
//				Paczka packa = kurier.listPackage.get(i);
//				dj.getShortestPathTo(packa.ge)
//				
//				
//			}
//			
			Paczka packa = kurier.listPackage.get(0);
			System.out.println("kurier wysylanie tick onStart");
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Klient");
			template.addServices(sd);
			DFAgentDescription[] result = null;
			AID centralaId = null;
			
			try {
				result = DFService.search(myAgent, template);
				
				System.out.println("Znaleziono centrale, wysylam");
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			for(int i=0;i<result.length;i++){
				if(result[i].getName().equals(packa.getAIDKlienta()))
					centralaId = result[i].getName();
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
