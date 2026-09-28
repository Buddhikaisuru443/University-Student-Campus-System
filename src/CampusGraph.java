import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {

    private HashMap<String, ArrayList<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new HashMap<>();
    }

    // Add location
    public boolean addLocation(String location) {

        if (adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.put(location, new ArrayList<>());

        return true;
    }

    // Remove location
    public boolean removeLocation(String location) {

        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.remove(location);

        for (ArrayList<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        return true;
    }

    // Add connection
    public boolean addConnection(
            String location1,
            String location2) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {

            return false;
        }

        if (location1.equalsIgnoreCase(location2)) {
            return false;
        }

        if (adjacencyList.get(location1)
                .contains(location2)) {

            return false;
        }

        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);

        return true;
    }

    // Remove connection
    public boolean removeConnection(
            String location1,
            String location2) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {

            return false;
        }

        boolean removed1 =
                adjacencyList.get(location1)
                        .remove(location2);

        boolean removed2 =
                adjacencyList.get(location2)
                        .remove(location1);

        return removed1 || removed2;
    }

    // Display graph
    public void displayConnections() {

        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found.");
            return;
        }

        System.out.println(
                "\n========== CAMPUS CONNECTIONS =========="
        );

        for (String location : adjacencyList.keySet()) {

            System.out.print(location + " -> ");

            ArrayList<String> neighbours =
                    adjacencyList.get(location);

            if (neighbours.isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(
                        String.join(", ", neighbours)
                );
            }
        }
    }

    // BFS
    public void bfs(String startLocation) {

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(startLocation);
        queue.offer(startLocation);

        System.out.println(
                "\n========== BFS TRAVERSAL =========="
        );

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbour :
                    adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

        System.out.println();
    }

    // DFS
    public void dfs(String startLocation) {

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new HashSet<>();

        System.out.println(
                "\n========== DFS TRAVERSAL =========="
        );

        dfsRecursive(startLocation, visited);

        System.out.println();
    }

    private void dfsRecursive(
            String location,
            Set<String> visited) {

        visited.add(location);

        System.out.print(location + " ");

        for (String neighbour :
                adjacencyList.get(location)) {

            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour, visited);
            }
        }
    }
}