import java.util.*;

/**
 * Created by Simel on 3/27/2017.
 */

public class Graph {

    private class Vertex {

        String identifier_;      // station name
        String type_;           // station type (fuel, electric, hybrid)
        LinkedList<Integer> adjWeight_;  // adjacent vertex weight
        LinkedList<Vertex> adjVertex_;  // adjacent vertex

        public Vertex(String identifier, String type) {

            identifier_ = identifier;
            type_ = type;
            adjWeight_ = new LinkedList<>();
            adjVertex_ = new LinkedList<>();
        }

    }

    public static final int INFINITY = Integer.MAX_VALUE;
    private HashMap<String, Vertex> vertexMap_ = new HashMap<>();


    /**
     * adds an edge between to vertices in the graph
     * @param source source vertex
     * @param sourceType source vertex type (fuel, electric, hybrid)
     * @param target target vertex
     * @param targetType target vertex type (fuel, electric, hybrid)
     */
    void insertEdge(String source, String sourceType, String target, String targetType, int weight) {

        Vertex u = getVertex(source, sourceType);
        Vertex v = getVertex(target, targetType);

        u.adjWeight_.add(weight);
        u.adjVertex_.add(v);
    }

    /**
     * If vertex is not present, add it to vertexMap_
     * In either case, return the Vertex.
     * @param vertexName name of the vertex to be added
     * @param vertexType type of the vertex to be added
     * @return the vertex
     */
    private Vertex getVertex(String vertexName, String vertexType) {

        Vertex newVertex = vertexMap_.get(vertexName);
        if ( newVertex == null ) {

            newVertex = new Vertex(vertexName, vertexType);
            vertexMap_.put(vertexName, newVertex);
        }

        return  newVertex;
    }

    public boolean dijkstra ( String source, String target ) throws NoSuchElementException {

        Vertex u = vertexMap_.get(source);
        Vertex v = vertexMap_.get(target);

        if ( u == null || v == null )
            throw new NoSuchElementException( source + " or " + target + " is not a vertex in this graph");


        HashMap<Vertex, Vertex> previous = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();

        PriorityQueue<Vertex> unVisitedVertices = new PriorityQueue<>(vertexMap_.keySet().size(),
                (Vertex first, Vertex second) -> {

            int weightFirst = distance.get(first.identifier_);
            int weightSecond = distance.get(second.identifier_);

            return weightFirst - weightSecond;
        }); // all vertices are initially unvisited, source gets added first, used of Lambda Expression for the Comparator

        HashSet<Vertex> visitedVertices = new HashSet<>();    //  hold vertices that have been visited ( i.e., if a path from source to this node has been found)

        distance.put(source, 0);

        Iterator it = u.adjVertex_.iterator();
        Iterator it2 = u.adjWeight_.iterator();
        while ( it.hasNext() && it2.hasNext() ) {

            Vertex w = (Vertex) it.next();
            Integer w2 = (Integer) it2.next();

            previous.put(w, u);
            distance.put(w.identifier_, w2 );
            unVisitedVertices.add(w);
        }

        visitedVertices.add(u);

        /*for ( int i = 0; i < u.adjVertex_.size(); ++i ) {

            Vertex w = (Vertex) u.adjVertex_.get(i);
            Integer weight = (Integer) u.adjWeight_.get(i);

            previous.put(w, u);
            distance.put(w.identifier_, weight);
            unVisitedVertices.add(w);
        }

        visitedVertices.add(u);*/

        while ( unVisitedVertices.size() > 0 ) {

            Vertex next = unVisitedVertices.poll();
            int distanceToNext = distance.get(next.identifier_);

            // for each unvisited adjacent vertices of next
            it = next.adjVertex_.iterator();
            while ( it.hasNext() ) {

                Vertex w = (Vertex) it.next();
                if ( visitedVertices.contains(w))
                    continue;
            }


            // to be continued

        }





        return  false;
    }
}
