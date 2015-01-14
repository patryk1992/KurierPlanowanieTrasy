package shortest_path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	
	// mark all the vertices
	public static Vertex SZC = new Vertex("SZC");
	public static Vertex WRO = new Vertex("WRO");
	public static Vertex POZ = new Vertex("POZ");
	public static Vertex GDA = new Vertex("GDA");
	public static Vertex WAW = new Vertex("WAW");
	public static Vertex LUB = new Vertex("LUB");
	public static Vertex KRK = new Vertex("KRK");

	static {
		// set the edges and weight
		SZC.adjacencies = new Edge[] { new Edge(WRO, 364), new Edge(POZ, 235),
				new Edge(GDA, 366), new Edge(WAW, 565), new Edge(LUB, 690),
				new Edge(KRK, 662) };

		WRO.adjacencies = new Edge[] { new Edge(POZ, 169), new Edge(GDA, 435),
				new Edge(POZ, 169), new Edge(WAW, 342), new Edge(LUB, 431),
				new Edge(KRK, 272), new Edge(SZC, 364) };

		POZ.adjacencies = new Edge[] { new Edge(GDA, 269), new Edge(WAW, 319),
				new Edge(LUB, 459), new Edge(KRK, 424), new Edge(WRO, 169),
				new Edge(GDA, 424), new Edge(SZC, 235) };

		GDA.adjacencies = new Edge[] { new Edge(WAW, 348), new Edge(LUB, 515),
				new Edge(KRK, 603), new Edge(SZC, 366), new Edge(POZ, 269),
				new Edge(WAW, 348), new Edge(WRO, 603) };

		WAW.adjacencies = new Edge[] { new Edge(LUB, 168), new Edge(KRK, 293),
				new Edge(GDA, 348), new Edge(POZ, 319), new Edge(SZC, 565),
				new Edge(LUB, 168), new Edge(WRO, 342) };

		LUB.adjacencies = new Edge[] { new Edge(KRK, 270), new Edge(WRO, 431),
				new Edge(POZ, 459), new Edge(KRK, 270), new Edge(SZC, 690),
				new Edge(WAW, 168), new Edge(GDA, 515) };

		KRK.adjacencies = new Edge[] { new Edge(KRK, 270), new Edge(WRO, 272),
				new Edge(POZ, 424), new Edge(LUB, 270), new Edge(SZC, 662),
				new Edge(WAW, 293), new Edge(GDA, 603) };
	}

	public void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			System.out.println(u.adjacencies);
			for (Edge e : u.adjacencies) {

				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

}