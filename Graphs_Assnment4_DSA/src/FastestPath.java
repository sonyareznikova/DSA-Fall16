import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sonya on 20.11.16.
 */
public class FastestPath {

    public static void main(String[] args) throws IOException {

        //load graph
        File fileGraph = new File("russia.txt");
        Scanner scannerGraph = new Scanner(fileGraph);
        String[] citiesStr = scannerGraph.nextLine().split(" ");
        ArrayList<VertexObject<String>> vertices = new ArrayList<>();
        //converting an array of cities (string) into a VertexObject array to create a graph
        for (String city : citiesStr) {
            VertexObject<String> v = new VertexObject<>(city);
            vertices.add(v);
        }
        MyGraph<String> graph = new MyGraph<>(vertices);

        //adding edges to the graph
        while (scannerGraph.hasNext()) {
            String fromStr = scannerGraph.next().trim();
            String toStr = scannerGraph.next().trim();
            String[] data = scannerGraph.next().split(":");
            Double[] weights = new Double[3];
            for (int i = 0; i < data.length; i++) {
                Double d = Double.parseDouble(data[i]);
                weights[i] = d;
            }
            EdgeObject edge = new EdgeObject(weights[0], weights[1], weights[2]);
            graph.setEdgeBoth(graph.getVertex(fromStr), graph.getVertex(toStr), edge);
        }

        //making a minimum spanning tree out of an existing graph
        graph = graph.minimumSpanningTree();

        //load input
        File fileInp = new File("input.txt");
        FileWriter writer = new FileWriter("output.txt");

        Scanner scannerInp = new Scanner(fileInp);
        while (scannerInp.hasNextLine()) {
            //input into the shortestPathAlgorithm
            String from = scannerInp.next().trim();
            String to = scannerInp.next().trim();
            //amount of kilograms
            Double kilos = Double.parseDouble(scannerInp.next().trim());
            //finding the shortest path from starting point to destination
            ArrayList<EdgeObject> thePath = graph.shortestPath(from, to);

            Double finalTime = 0.0;
            Double finalCostForKG = 0.0;
            for (int i = 0; i < thePath.size(); i++) {
                finalTime += thePath.get(i).getTime();
                finalCostForKG += thePath.get(i).getCost();
            }

            String result = from + " " + to + " " + kilos + " " + String.format("%.1   f", finalTime) + " " + String.format("%.1f", finalCostForKG * kilos) + "\n";
            writer.write(result);
        }

        writer.close();
    }

}
