package Planer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class KurierBehaviourRozwozenie extends TickerBehaviour {
	
	Kurier kurier;
	
	public KurierBehaviourRozwozenie(Agent a, long period) {
		super(a, period);
		
		kurier = (Kurier) a;
	}

	public int state=0;
	
	public void onTick() {
		System.out.println("Dostarczono paczke do klienta. Ilosc paczek ktore pozostaly u kuriera: " + kurier.listPackage.size());
		if(kurier.listPackage.size() > 0) {
			Integer packa = kurier.listPackage.get(0);
		
			// TODO dostarcza 
			kurier.listPackage.remove(packa);
		}
	}

}
