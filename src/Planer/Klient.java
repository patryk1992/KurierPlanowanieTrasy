package Planer;

import jade.core.Agent;
//TODO
//trzeba doda� zablokowanie wysy��nia, tak aby najpierw sie rejestrowa� a potem wysy�a�
public class Klient extends Agent {
	private static final long serialVersionUID = -4498367388709885619L;

	String argumenty;
	String adress;

	protected void setup() {
		readArguments();
//		registerAgent();
		DFServiceUtil.registerAgent(this, "Klient");
		addBehaviours();
	}

	private void readArguments() {
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumenty = ((String) args[0]);
		}
	}

	private void addBehaviours() {
		addBehaviour(new KlientBehaviourRegister(this));
		addBehaviour(new KlientBehaviourWysylanie(this, 1000));
		
	}


	protected void takeDown() {
		DFServiceUtil.deRegister(this);
	}
}
