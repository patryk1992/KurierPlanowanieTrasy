package Planer;

import java.security.spec.MGF1ParameterSpec;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class KlientBehaviourOdbieranie extends TickerBehaviour {
	private static final long serialVersionUID = -485427374982996959L;

	public KlientBehaviourOdbieranie(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	public int state=0;
	
	public void onTick() {
		MessageTemplate  mt2= MessageTemplate.MatchOntology(Dictionary.DELIVER_PACK);
		ACLMessage msg = myAgent.receive(mt2);
		if (msg != null) {
			try {
				Paczka pack=(Paczka) msg.getContentObject();
				System.out.println("Klient odebrano paczk�"+ pack.toString());
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
