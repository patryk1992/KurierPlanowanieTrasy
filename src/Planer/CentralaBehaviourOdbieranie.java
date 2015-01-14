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

public class CentralaBehaviourOdbieranie extends TickerBehaviour {
	
	private static final long serialVersionUID = 5338053626092303683L;
	
	Centrala centrala;
	
	public CentralaBehaviourOdbieranie(Agent centralaAgent, long period) {
		super(centralaAgent, period);
		this.centrala = (Centrala) centralaAgent;
	}

	// TODO is it necessary?
	public int state=0;
	
	public void onTick() {
		
		MessageTemplate  mt2= MessageTemplate.MatchOntology(Dictionary.PACKAGES_REQUEST);
		ACLMessage msg2 = centrala.receive(mt2);
		if(msg2 != null) 
		{
		try {
			System.out.println(msg2.getContentObject().toString());
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		mt2= MessageTemplate.MatchOntology(Dictionary.PACKAGES_KLIENT_REGISTER);
		msg2 = centrala.receive(mt2);
		if(msg2 != null) 
		{
		try {
			System.out.println(msg2.getContentObject().toString());
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		
		mt2= MessageTemplate.MatchOntology(Dictionary.RECV_PACK);
		msg2 = centrala.receive(mt2);
		if(msg2 != null) 
		{	
		try {
			System.out.println(msg2.getContentObject().toString());
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		mt2= MessageTemplate.MatchOntology(Dictionary.PACKAGES_NEW);
		msg2 = centrala.receive(mt2);
		if(msg2 != null) 
		{
		try {
			centrala.listPackage.add((Paczka) msg2.getContentObject());
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//		if(Dictionary.PACKAGES_REQUEST.equals(msg.getContent())){
//			System.out.println("*Packages Request. Packages size: " + centrala.listPackage.size());
//			centrala.kurierzy.add(msg.getSender());
//		} else if(Dictionary.PACKAGES_NEW.equals(msg.getContent())) {
//			System.out.println("Klient przyniosl paczke do centrali");
//			centrala.listPackage.add(new Integer(5));
//		}else if(msg.getContent().contains(Dictionary.PACKAGES_KLIENT_REGISTER)) {
//			System.out.println("Klient sie rejestruje");
//			
//		}
	}

	// TODO is it still necessary?
	@SuppressWarnings("unused")
	private AID getKurier() {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Kurier");
		template.addServices(sd);
		DFAgentDescription[] result;
		AID kurierIdId = null;
		
		try {
			result = DFService.search(myAgent, template);
			kurierIdId = result[0].getName();
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return kurierIdId;
	}

}
