package Planer;

import java.io.IOException;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class CentralaBehaviourWydawanie extends TickerBehaviour {
	private static final long serialVersionUID = 9109179521756423124L;
	
	Centrala centrala;
	private int amountOfPackages = 10;
	
	public CentralaBehaviourWydawanie(Agent a, long period) {
		super(a, period);
		centrala=(Centrala) a;
	}

	// TODO is it necessary?
	public int state=0;
	
	public void onTick() {
//		centrala.listPackage.add(new Integer(3));
		System.out.println("kurierzy.size: " + centrala.kurierzy.size() + ". \tlistPackage.size: " + centrala.listPackage.size());
		if(centrala.kurierzy.size() > 0 && centrala.listPackage.size() >= amountOfPackages ) {
			System.out.println("Jest oczekujacy kurier i mamy wymagana ilosc paczek");
			AID aktualnyKurier = centrala.kurierzy.poll();
			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			msg2.addReceiver(aktualnyKurier);

			// So ugly :< 
			ArrayList<Integer> packagesToSend = new ArrayList<>();
			for(int i = 0; i < 10; i++) {
				Integer p = centrala.listPackage.get(0);
				packagesToSend.add(p);
				centrala.listPackage.remove(p);
			}
			try {
				msg2.setContentObject(packagesToSend);
				myAgent.send(msg2);
			} catch (IOException e) {
				System.out.println("O kurcze, problem z wysylaniem");
			}
			System.out.println("Po wyslaniu paczek");			
		} else {
			System.out.println("Nie ma kurierow oczekujacych na paczki lub brak wystarczajacej ilosci paczek, wiec nie wysylam paczek");
		}
		

	}

}
