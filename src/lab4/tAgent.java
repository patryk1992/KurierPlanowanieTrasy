package lab4;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class tAgent extends Agent {

	public int temperaturaTERM=0;
		
	 protected void setup() {
	// Printout a welcome message
		 AID id = new AID("tAgent", AID.ISLOCALNAME);
	 System.out.println("Serwus! " +getAID().getName()+"tAGENT is ready.");
	 
	 DFAgentDescription dfd = new DFAgentDescription();
	 dfd.setName(getAID());
	 ServiceDescription sd = new ServiceDescription();
	 sd.setType("tAgent");
	 dfd.addServices(sd);
	 
	 try {
		DFService.register(this, dfd);
	} catch (FIPAException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 addBehaviour(new termometr(this,1000));

	 

	 
	 }
	 protected void takeDown() {
		 
		 try {
				DFService.deregister(this);
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 System.out.println("¯egnam! "+getAID().getName());
	 }
	 
}
