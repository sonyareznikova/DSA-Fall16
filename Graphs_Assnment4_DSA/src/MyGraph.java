import com.sun.javafx.geom.Edge;
import com.sun.tools.javac.code.Attribute;

import java.util.*;

/**
 * Created by Sonya on 17.11.16.
 */
public class MyGraph<T extends Comparable<T>> {

    //graph fields
    int size;
    ArrayList<VertexObject<T>> vertices;
    ArrayList<ArrayList<EdgeObject>> matrix;
    HashMap<VertexObject<T>, Integer> addr;

    //constructor
    public MyGraph(ArrayList<VertexObject<T>> v) {
        this.size = v.size();
        this.vertices = v;
        this.matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            matrix.add(new ArrayList<EdgeObject>());
            for (int j = 0; j < size; j++) {
                matrix.get(i).add(null);
            }
        }
        this.addr = new HashMap<>();
        for (int i = 0; i < size; i++) {
            this.addr.put(v.get(i), i);
        }
    }

    //methods
    public EdgeObject setEdge(VertexObject<T> from, VertexObject<T> to, EdgeObject weight) {
        EdgeObject ew = getEdge(from, to);
        matrix.get(addr.get(from)).set(addr.get(to), weight);
        return ew;
    }

    public void setEdgeBoth(VertexObject<T> from, VertexObject<T> to, EdgeObject weight) {
        setEdge(from, to, weight);
        setEdge(to, from, weight);
    }

    public EdgeObject getEdge(VertexObject<T> from, VertexObject<T> to) {
        int index = addr.get(from);
        if (matrix.get(index) == null) return null;
        else return matrix.get(addr.get(from)).get(addr.get(to));
    }

    public EdgeObject removeEdge(VertexObject<T> from, VertexObject<T> to) {
        EdgeObject ew = getEdge(from, to);
        setEdge(from, to, null);
        return ew;
    }

    public VertexObject<T> getVertex(T data) {
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).vertexData.compareTo(data) == 0) return this.vertices.get(i);
        }
        return null;
    }

    public void addVertex(T data) {
        VertexObject<T> v = new VertexObject<>(data);
        this.vertices.add(v);
        this.addr.put(v, this.size);
        size++;
    }
    //TODO: finish these two
    public VertexObject<T> removeVertex(VertexObject<T> v) {
        //removing vertex and its incident edges
        return null;
    }

    public ArrayList<EdgeObject> incidentEdges(VertexObject<T> v) {
        //returning incident edges of the given vertex
        return null;
    }

    public boolean areAdjacent(VertexObject<T> from, VertexObject<T> to) {
        return getEdge(from, to) != null;
    }

    public List<VertexObject<T>> neighbours(VertexObject<T> v) {
        ArrayList<VertexObject<T>> nbs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (areAdjacent(v, this.vertices.get(i)))
                nbs.add(this.vertices.get(i));
        }
        return nbs;
    }

    //TODO: finish these three
    public ArrayList<VertexObject<T>> endvertices(EdgeObject edge) {
        //an array of the two endvertices of edge
        return null;
    }

    public VertexObject<T> opposite(VertexObject<T> v, EdgeObject e) {
        //the vertex opposite of v on e
        return null;
    }

    public int degree(VertexObject<T> v) {
        //number of incident edges
        return 0;
    }

    public ArrayList<EdgeObject> shortestPath(T source, T destination) {
        VertexObject<T> finalPoint = this.getVertex(destination);
        //setting initial distances
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getVertexData().compareTo(source) == 0) this.vertices.get(i).setDistanceFromPrevious(0.0);
            else this.vertices.get(i).setDistanceFromPrevious(Double.POSITIVE_INFINITY);
            vertices.get(i).setPrevious(null);
        }
        //creating Priority Queue
        BinaryHeap<VertexObject<T>> priorityQ = new BinaryHeap<>();
        for (int i = 0; i < this.vertices.size(); i++) {
            priorityQ.add(this.vertices.get(i));
        }
        //changing distances of the vertices
        while(!priorityQ.isEmpty()) {
            VertexObject<T> currentVertex = priorityQ.delete();
            //if we reach our destination vertex
            if(currentVertex.compareTo(this.getVertex(destination)) == 0) break;
            //getting adjacent vertices
            List<VertexObject<T>> neighbourList = this.neighbours(currentVertex);
            //iterating over adjacent vertices
            for (VertexObject<T> vertex : neighbourList) {
                //getting the edge's weight between the current removed vertex and one of its neighbours
                Double time = getEdge(currentVertex, vertex).getTime();
                //if the distance is shorter from this vertex
                if (currentVertex.getDistanceFromPrevious() + time < vertex.getDistanceFromPrevious()) {
                    vertex.setDistanceFromPrevious(currentVertex.getDistanceFromPrevious() + time);
                    //changing the pointer to the previous vertex to return the path
                    vertex.setPrevious(currentVertex);
                    int indexOfChangedElement = priorityQ.getIndexOf(vertex);
                    priorityQ.heapUp(indexOfChangedElement);
                }
            }
        }
        ArrayList<EdgeObject> edgeList = new ArrayList<>();
        while (finalPoint.getPrevious() != null) {
            edgeList.add(getEdge(finalPoint, finalPoint.getPrevious()));
            finalPoint = finalPoint.getPrevious();
        }
        return edgeList;
    }

    public MyGraph<T> minimumSpanningTree() {
        this.cleanVerticesUp();
        vertices.get(0).setDistanceFromPrevious(0.0);

        //creating Priority Queue
        BinaryHeap<VertexObject<T>> priorityQ = new BinaryHeap<>();
        for (int i = 0; i < this.vertices.size(); i++) {
            priorityQ.add(this.vertices.get(i));
        }

        while(!priorityQ.isEmpty()) {
            VertexObject<T> currentVertex = priorityQ.delete();
            //getting adjacent vertices
            List<VertexObject<T>> neighbourList = this.neighbours(currentVertex);
            //iterating over adjacent vertices
            for (VertexObject<T> vertex : neighbourList) {
                if(priorityQ.getIndexOf(vertex) < 0) continue;
                //getting the edge's weight between the current removed vertex and one of its neighbours
                Double distance = getEdge(currentVertex, vertex).getDistance();
                //if the distance is shorter from this vertex
                if (distance < vertex.getDistanceFromPrevious()) {
                    vertex.setDistanceFromPrevious(distance);
                    //changing the pointer to the previous vertex to return the path
                    vertex.setPrevious(currentVertex);
                    int indexOfChangedElement = priorityQ.getIndexOf(vertex);
                    priorityQ.heapUp(indexOfChangedElement);
                }
            }
        }

        ArrayList<VertexObject<T>> newVertices = new ArrayList<>(this.vertices);
        List<EdgeObject> edgeObjects = new ArrayList<>();
        MyGraph<T> mstGraph = new MyGraph<>(newVertices);
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getPrevious() == null) continue;
            EdgeObject weight = getEdge(vertices.get(i), vertices.get(i).getPrevious());
            edgeObjects.add(getEdge(vertices.get(i),vertices.get(i).getPrevious()));
            mstGraph.setEdgeBoth(newVertices.get(i), newVertices.get(i).getPrevious(), weight);
        }

        this.cleanVerticesUp();
        mstGraph.cleanVerticesUp();
        return mstGraph;
    }

    //for shortest path and minimum spanning tree making vertices have distance of infinity
    public void cleanVerticesUp() {
        for (int i = 0; i < this.vertices.size(); i++) {
            this.vertices.get(i).setDistanceFromPrevious(Double.POSITIVE_INFINITY);
            vertices.get(i).setPrevious(null);
        }
    }

    public void depthFirstSearch() {
    }

}
