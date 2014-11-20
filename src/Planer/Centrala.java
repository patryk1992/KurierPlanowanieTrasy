package Planer;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Centrala extends Agent {
	String argumenty;
	private	AID Centrala;
	Package p;
	
	protected void setup() {
		p=new Package();
		
		Object[] args = getArguments();		
		if(	args != null &&	args.length >	0	) {
			argumenty=((String) args[0]) ;
		}
		
		 DFAgentDescription template = new DFAgentDescription();
		 template.setName(getAID());
		 ServiceDescription sd = new ServiceDescription();
		 sd.setType("Centrala");
		 sd.setName( getLocalName() );
		 template.addServices(sd);
		 
		 try {
			DFService.register(this, template);
		 } catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 addBehaviour( new CentralaBehaviourOdbieranie(this, 1000));
	}
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("¯egnam! " + getAID().getName());
	}
}
