package Planer;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.leap.List;

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
		ACLMessage msg = centrala.receive();
		if(msg == null) 
			return;
		
		if(Dictionary.PACKAGES_REQUEST.equals(msg.getContent())){
			System.out.println("*Packages Request. Packages size: " + centrala.listPackage.size());
			centrala.kurierzy.add(msg.getSender());
		} else if(Dictionary.PACKAGES_NEW.equals(msg.getContent())) {
			System.out.println("Klient przyniosl paczke do centrali");
			centrala.listPackage.add(new Integer(5));
		}
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kurierIdId;
	}

}
