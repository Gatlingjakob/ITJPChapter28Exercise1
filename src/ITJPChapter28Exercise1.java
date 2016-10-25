/**
 * Created by Jakob on 22-10-2016.
 */

import java.util.*;
public class ITJPChapter28Exercise1 {


        public static void main(String[] args) throws Exception {

            System.out.print("Enter name of designated file ('GraphSample1' or 'GraphSample2'): ");
            Scanner fileName = new Scanner(System.in);
            java.io.File file = new java.io.File(fileName.nextLine());

            if (!file.exists()) {
                System.out.println("The file \"" + file + "\" does not exist.");
                System.exit(0);
            }

            Scanner input = new Scanner(file);
            int verticesAmount = input.nextInt();

            ArrayList<AbstractGraph.Edge> edgeList = new ArrayList<>();

            String[] vertices = new String[verticesAmount];

            input.nextLine();
            for (int j = 0; j < verticesAmount; j++) {
                String s = input.nextLine();
                String[] line = s.split("[\\s+]");
                String u = line[0];
                vertices[j] = u; // Add vertex

                // Add edges for vertex u
                for (int i = 1; i < line.length; i++) {
                    edgeList.add(new AbstractGraph.Edge(Integer.parseInt(u),
                            Integer.parseInt(line[i])));
                }
            }

            // Crate a graph
            Graph<String> graph = new UnweightedGraph<>(
                    Arrays.asList(vertices), edgeList);

            // Display the number of vertices
            System.out.println("The number of vertices is " + graph.getSize());

            // Display edges
            for (int u = 0; u < verticesAmount; u++) {
                System.out.print("Vertex " + graph.getVertex(u) + ":");
                for (Integer e : graph.getNeighbors(u))
                    System.out.print(" (" + u + ", " + e + ")");
                System.out.println();
            }

            // Obtain an instance tree of AbstractGraph.Tree
            AbstractGraph.Tree tree = graph.dfs(0);

            // Test if graph is connected and print results
            System.out.println("The graph is " +
                    (tree.getNumberOfVerticesFound() != graph.getSize() ?
                            "not " : "") + "connected");

            // Close the file
            input.close();
        }


}
