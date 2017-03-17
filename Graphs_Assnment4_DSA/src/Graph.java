
import java.util.*;
import java.io.*;
import java.util.function.BiPredicate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s.protasov
 * @param <VertexType> type of object stored in vertex
 * @param <EdgeWeight> weight type of edge
 */
public class Graph<VertexType, EdgeWeight> {
    
    int size;
    EdgeWeight[][] adj;
    VertexType[] vertices;
    HashMap<VertexType, Integer> addr;
    
    public Graph(VertexType[] vertices) {
        this.size = vertices.length;
        this.vertices = vertices;
        this.adj = (EdgeWeight[][])(new Object[size][size]);
        this.addr = new HashMap<>();
        for (int i = 0; i < size; i++) {
            this.addr.put(vertices[i], i);
        }       
    }

    public EdgeWeight setEdge(VertexType from, VertexType to, EdgeWeight weight) {
        EdgeWeight ew = getEdge(from, to);
        adj[addr.get(from)][addr.get(to)] = weight;
        return ew;
    }

    public void setEdgeBoth(VertexType from, VertexType to, EdgeWeight weight) {
        setEdge(from, to, weight);
        setEdge(to, from, weight);
    }
  
    public EdgeWeight getEdge(VertexType from, VertexType to) {
        return adj[addr.get(from)][addr.get(to)];
    }
    
    public EdgeWeight removeEdge(VertexType from, VertexType to) {
        EdgeWeight ew = getEdge(from, to);
        setEdge(from, to, null);
        return ew;
    }
    
    public boolean areAdjacent(VertexType from, VertexType to) {
        return getEdge(from, to) != null;
    }
    
    public List<VertexType> neighbours(VertexType v) {
        ArrayList<VertexType> nbs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (areAdjacent(v, this.vertices[i]))
                nbs.add(this.vertices[i]);
        }
        return nbs;
    }
    
    public static Graph<String, Integer> loadDirected(String filename) {
        File file = new File(filename);
        Graph<String, Integer> graph = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] names = scanner.nextLine().split(" ");
            for (int i = 0; i < names.length; i++) names[i] = names[i].trim();
            graph = new Graph<>(names);
            while (scanner.hasNext()) {
                String from = scanner.next().trim();
                String to = scanner.next().trim();
                int dist = Integer.parseInt(scanner.next().trim());
                graph.setEdge(from, to, dist);
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return graph;
    }
    
    public static Graph<String, Integer> loadUnDirected(String filename) {
        File file = new File(filename);
        Graph<String, Integer> graph = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] names = scanner.nextLine().split(" ");
            for (int i = 0; i < names.length; i++) names[i] = names[i].trim();
            graph = new Graph<>(names);
            while (scanner.hasNext()) {
                String from = scanner.next().trim();
                String to = scanner.next().trim();
                int dist = Integer.parseInt(scanner.next().trim());
                graph.setEdge(from, to, dist);
                graph.setEdge(to, from, dist);
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return graph;
    }

    public void save(String filename) {
        try {
            try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
                for (int i = 0; i < size - 1; i++) {
                    writer.print(this.vertices[i]); writer.print(" ");
                }
                if(size > 0) writer.print(this.vertices[size - 1]);
                writer.println();
                for (VertexType v1: vertices)
                    for (VertexType v2: neighbours(v1)) {
                        if (!v1.equals(v2)) {
                            writer.print(v1); writer.print(" ");
                            writer.print(v2); writer.print(" ");
                            writer.print(getEdge(v1, v2)); writer.print(" ");
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
}

