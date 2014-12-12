package Planer;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DFServiceUtil {

	public static void registerAgent(Agent agent, String type) {
		System.out.println("Register agent: " + type + " : " + agent.getLocalName());
		DFAgentDescription template = new DFAgentDescription();
		template.setName(agent.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(type);
		sd.setName(agent.getLocalName());
		template.addServices(sd);

		try {
			DFService.register(agent, template);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
	
	public static void deRegister(Agent agent) {
		try {
			DFService.deregister(agent);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		System.out.println("Bye! " + agent.getAID().getName());

	}
}
