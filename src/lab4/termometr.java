package lab4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class termometr extends TickerBehaviour  {
	int wlacz=0;
	public termometr(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	public void onTick() {		
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			String meessage=msg.getContent();
			int temp=Integer.parseInt(meessage);
			if(temp<0){
				wlacz=1;
			}
			else if(temp>50){
				wlacz=0;
			}
			System.out.println("termpmetr"+temp+"\twlacz"+wlacz);
			
			
			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			msg2.addReceiver(new AID("gAgent", AID.ISLOCALNAME));	
			msg2.setContent(String.valueOf(wlacz));
			myAgent.send(msg2);
		}
		
	}

}
