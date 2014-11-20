package lab4;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class gAgent extends Agent {

	
	protected void setup() {
		AID id = new AID("grzalka", AID.ISLOCALNAME);
		
		DFAgentDescription dfd = new DFAgentDescription();
		 dfd.setName(getAID());
		 ServiceDescription sd = new ServiceDescription();
		 sd.setType("gAgent");
		 dfd.addServices(sd);
		 
		 try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		addBehaviour(new grzalka(this,1000));
	}
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("Dowidzenia! "+getAID().getName());
	}
}
