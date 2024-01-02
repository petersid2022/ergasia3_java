package graphSearch;

import java.util.*;

import graphClient.XGraphClient;

public class GraphSearch {

    public int afm = 58341;
    public String firstname = "Peter";
    public String lastname = "Sideris";

    XGraphClient xgraph;

    public GraphSearch(XGraphClient xgraph) {
        this.xgraph = xgraph;
    }

    public Result findResults() {
        Result res = new Result();
        SGraph bfsTree = new SGraph();

        long firstNode = xgraph.firstNode();

        HashMap<Long, Integer> degreeMap = new HashMap<>();
        ArrayList<Integer> degreeList = new ArrayList<>();

        Set<Long> visited = new LinkedHashSet<>();
        Queue<Long> queue = new LinkedList<>();

        queue.add(firstNode);

        while (!queue.isEmpty()) {
            long currentNode = queue.remove();

            // Check whether it has already been visited
            if (!visited.contains(currentNode)) {
                // Mark it as visited
                visited.add(currentNode);

                long[] top_neighbors = xgraph.getNeighborsOf(currentNode);

                // Node's degree is equal to the length of the getNeighborsOf slice.
                int degree = top_neighbors.length;

                // Using a hashmap to map each node with a degree Integer.
                degreeMap.put(currentNode, degree);

                List<Long> sortedNeighbors = new ArrayList<>();
                for (long top_neighbor : top_neighbors) {
                    if (!visited.contains(top_neighbor)) {
                        sortedNeighbors.add(top_neighbor);
                    }
                }
                sortedNeighbors.sort(Comparator.naturalOrder());

                queue.addAll(sortedNeighbors);

                // Update the Result
                res.m += sortedNeighbors.size();
                res.n++;
            }
        }

        for (Long foo : degreeMap.keySet()) {
            long temp = foo;
            bfsTree.addEdge(foo, temp);
        }

        // Map the values from the hashmap to the degreeList array and then sort it.
        for (Integer foo : degreeMap.values()) {
            degreeList.add(foo);
        }
        degreeList.sort(Comparator.reverseOrder());

        // Update the Result object
        res.degreeArrayList = degreeList;
        res.bfsNodeSequence = new ArrayList<>(visited);
        res.bfsTree = bfsTree;

        return res;
    }

}
