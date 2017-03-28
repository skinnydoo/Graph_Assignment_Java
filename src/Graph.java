import java.util.*;

/**
 * Created by Simel on 3/27/2017.
 */

public class Graph {

    /**
     * Inner class Vertex
     */
    private class Vertex implements Comparable<Vertex> {

        String              identifier_;  // station name
        String              type_;        // station type (fuel, electric, hybrid)
        Integer             distance_;    // distance to adj vertex
        Vertex              path_;        // previous vertex on shortest path
        LinkedList<Integer> adjWeight_;   // adjacent vertex weight
        LinkedList<Vertex> adjVertex_;    // adjacent vertex


        public Vertex(String identifier, String type) {

            identifier_ = identifier;
            type_ = type;
            adjWeight_ = new LinkedList<>();
            adjVertex_ = new LinkedList<>();

            reset();
        }


        /**
         * reset all vertices
         */
        void reset() {

            distance_ = Graph.INFINITY;
            path_ = null;
        }


        /**
         * @return a string representation of the vertex.
         */
        @Override
        public String toString() {
            return identifier_;
        }

        /**
         * @param vertex the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Vertex vertex) {

            return Integer.compare(distance_, vertex.distance_);
        }
    }

    private static final int INFINITY = Integer.MAX_VALUE;
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

    public void dijkstra ( String source ) throws NoSuchElementException {

        clear(); // first, clear all vertices

        Vertex start = vertexMap_.get(source);

        if ( start == null )
            throw new NoSuchElementException( source + " is not a vertex in this graph");

        start.distance_ = 0;
        PriorityQueue<Vertex> unVisitedVerticesQueue = new PriorityQueue<>();

        unVisitedVerticesQueue.add(start);

        while ( !unVisitedVerticesQueue.isEmpty() ) {

            Vertex u = unVisitedVerticesQueue.poll();

            Iterator it1 = start.adjVertex_.iterator();
            Iterator it2 = start.adjWeight_.iterator();

            while ( it1.hasNext() && it2.hasNext() ) {

                Vertex v = (Vertex) it1.next();
                Integer weight = (Integer) it2.next();

                if ( v.distance_ > u.distance_ + weight ) {

                    unVisitedVerticesQueue.remove(v);

                    v.distance_ = u.distance_ + weight;
                    v.path_ = u;
                    unVisitedVerticesQueue.add(v);

                }
            }
        }
    }


    private List<Vertex> getShortestPath( String target) {

        Vertex end = vertexMap_.get(target);

        if ( end == null )
            throw new NoSuchElementException( target + " is not a vertex in this graph");


        /* Computing the shortest path from source to target by reverse iteration*/
        List<Vertex> path = new ArrayList<>();
        for (Vertex v = end; v != null; v = v.path_)
            path.add(v);

        Collections.reverse(path);

        return path;
    }


    /**
     * @return a string representation of the a graph.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Iterator it = vertexMap_.values().iterator();
        while ( it.hasNext() ) {

            Vertex start = (Vertex) it.next();
            sb.append( "( " + start + ", " + start.type_ + " ( ");

            Iterator it2 = start.adjVertex_.iterator();
            while ( it2.hasNext() ) {

                Vertex adj = (Vertex) it2.next();
                sb.append(adj + ", ");

            } // end inner while

            sb.append(" ))");

        } // end outter while

        return new String(sb);
    }


    public void printShortestPath(String target) {

        List<Vertex> path = getShortestPath(target);

        for ( int i = 0; i < path.size(); ++i ) {

            if ( i > 0 )    // element separator
                System.out.print(" ---> ");

            System.out.print(path);
        }

        System.out.println();
    }


    /**
     * Reset all vertices
     */
    private void clear() {

        Iterator it = vertexMap_.values().iterator();

        while ( it.hasNext() ) {

            Vertex u = (Vertex) it.next();
            u.reset();;
        }
    }
}
