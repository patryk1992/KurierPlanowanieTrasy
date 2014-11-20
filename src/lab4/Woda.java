package lab4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Woda extends Agent {

	public int temp = 20;
	public int cstala;

	private AID[] sellerAgents;

	protected void setup() {
		// Printout a welcome message
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			cstala = Integer.parseInt((String) args[0]);
		}
		AID id = new AID("Woda", AID.ISLOCALNAME);
		System.out.println("Serwus! " + getAID().getName() + "temp is ready.");

		DFAgentDescription dfad = new DFAgentDescription();
		dfad.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Woda");
		dfad.addServices(sd);

		try {
			DFService.register(this, dfad);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addBehaviour(new TickerBehaviour(this, 1000) {
			@Override
			public void onTick() {

				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription sd = new ServiceDescription();
				sd.setType("bookelling");
				template.addServices(sd);
				DFAgentDescription[] result;
				try {
					result = DFService.search(myAgent, template);
					sellerAgents = new AID[result.length];
					for (int i = 0; i < result.length; ++i) {
						sellerAgents[i] = result[i].getName();
					}

				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// TODO Auto-generated method stub
				ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
				msg2.addReceiver(new AID("tAgent", AID.ISLOCALNAME));
				msg2.setContent(String.valueOf(temp));
				myAgent.send(msg2);
				ACLMessage msg = myAgent.receive();
				if (msg != null) {
					String meessage = msg.getContent();
					int state = Integer.parseInt(meessage);
					if (state == 0) {
						temp -= 1 * cstala;
					} else if (state == 1) {
						temp += 10 * cstala;
					}
					// System.out.println("temp"+temp);

				}

			}

		});

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
