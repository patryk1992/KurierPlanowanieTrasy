package shortest_path;

import java.io.Serializable;

class Edge implements Serializable {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}
