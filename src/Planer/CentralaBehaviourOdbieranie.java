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
	
	Centrala centrala;
	
	public CentralaBehaviourOdbieranie(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
		centrala = (Centrala)a;
	}

	public int state=0;
	
	public void onTick() {
		System.out.println("Yep");
		ACLMessage msg = myAgent.receive();
		System.out.println(msg);
		if(Dictionary.PACKAGES_REQUEST.equals(msg.getContent())){
			System.out.println("Packages size: " + centrala.listPackage.size());
			if(centrala.listPackage.size() >= 10) {
				System.out.println("Mamy > 10 paczek");
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

				ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
				msg2.addReceiver(kurierIdId);
				
				try {
					msg2.setContentObject((ArrayList<Integer>)centrala.listPackage.subList(0, 10));
					myAgent.send(msg2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Po wyslaniu paczek");
			}

		}
	}

}
