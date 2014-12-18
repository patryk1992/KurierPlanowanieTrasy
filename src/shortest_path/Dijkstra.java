package src.shortest_path;


import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Dijkstra
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
        Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
        System.out.println(u.adjacencies);
            for (Edge e : u.adjacencies)
            {
            	
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
            vertexQueue.remove(v);

            v.minDistance = distanceThroughU ;
            v.previous = u;
            vertexQueue.add(v);
        }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
        // mark all the vertices 
        Vertex SZC = new Vertex("SZC");
        Vertex WRO = new Vertex("WRO");
        Vertex POZ = new Vertex("POZ");
        Vertex GDA = new Vertex("GDA");
        Vertex WAW = new Vertex("WAW");
        Vertex LUB = new Vertex("LUB");
        Vertex KRK = new Vertex("KRK");


        // set the edges and weight
        SZC.adjacencies = new Edge[]{ 
        		new Edge(WRO, 364),
		        new Edge(POZ, 235),
		        new Edge(GDA, 366),
		        new Edge(WAW, 565),
		        new Edge(LUB, 690),
		        new Edge(KRK, 662)};
                
        WRO.adjacencies = new Edge[]{ new Edge(POZ, 169),
		        new Edge(GDA, 435),
		        new Edge(POZ, 169),
		        new Edge(WAW, 342),
		        new Edge(LUB, 431),
		        new Edge(KRK, 272),
		        new Edge(SZC, 364) };
      
        
        POZ.adjacencies = new Edge[]{ new Edge(GDA, 269),
				new Edge(WAW, 319),
				new Edge(LUB, 459),
				new Edge(KRK, 424),
				new Edge(WRO, 169),
				new Edge(GDA, 424),
				new Edge(SZC, 235) };
        
        GDA.adjacencies = new Edge[]{ new Edge(WAW, 348),
			  new Edge(LUB, 515),
			  new Edge(KRK, 603),
			  new Edge(SZC, 366),
			  new Edge(POZ, 269),
			  new Edge(WAW, 348),
			  new Edge(WRO, 603) };
        
        WAW.adjacencies = new Edge[]{ new Edge(LUB, 168),
	         new Edge(KRK, 293),
	         new Edge(GDA, 348),
	         new Edge(POZ, 319),
	         new Edge(SZC, 565),
	         new Edge(LUB, 168),
	         new Edge(WRO, 342) };
        
        
        LUB.adjacencies = new Edge[]{ new Edge(KRK, 270),
	         new Edge(WRO, 431),
	         new Edge(POZ, 459),
	         new Edge(KRK, 270),
	         new Edge(SZC, 690),
	         new Edge(WAW, 168),
	         new Edge(GDA, 515) };
        
        KRK.adjacencies = new Edge[]{ new Edge(KRK, 270),
   	         new Edge(WRO, 272),
   	         new Edge(POZ, 424),
   	         new Edge(LUB, 270),
   	         new Edge(SZC, 662),
   	         new Edge(WAW, 293),
   	         new Edge(GDA, 603) };
        
        System.out.println();
        
        
        computePaths(SZC); // run Dijkstra
        System.out.println("Distance to " + WAW + ": " + WAW.minDistance);
        List<Vertex> path = getShortestPathTo(WAW);
        System.out.println("Path: " + path);
    }
}