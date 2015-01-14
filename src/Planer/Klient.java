package Planer;

import shortest_path.Cities;
import jade.core.Agent;
//TODO
//trzeba dodaæ zablokowanie wysy³¹nia, tak aby najpierw sie rejestrowa³ a potem wysy³a³
public class Klient extends Agent {
	private static final long serialVersionUID = -4498367388709885619L;

	String argumenty;
	String adress;
	int nrAgenta;

	protected void setup() {

		
				
		
				
		readArguments();
//		registerAgent();
		DFServiceUtil.registerAgent(this, "Klient");
		
		String []tmp=getAID().getName().split("_");
		if(tmp.length>1){
		  this.nrAgenta=Integer.parseInt(tmp[1].split("@")[0]);
		}
		else{
		  this.nrAgenta=0;
		}
		this.adress=Cities.cities[nrAgenta];
		System.out.println("Numer klienta to: "+this.nrAgenta+", a jego miasto to: "+this.adress);
//		addBehaviours();
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
