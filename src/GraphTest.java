public class GraphTest {

    public static void main(String[] args) {

        CampusGraph graph = new CampusGraph();

        // Add campus locations
        graph.addLocation("Main Gate");
        graph.addLocation("Library");
        graph.addLocation("Cafeteria");
        graph.addLocation("Computer Lab");
        graph.addLocation("Lecture Hall");

        // Add connections
        graph.addConnection(
                "Main Gate",
                "Library"
        );

        graph.addConnection(
                "Library",
                "Cafeteria"
        );

        graph.addConnection(
                "Cafeteria",
                "Computer Lab"
        );

        graph.addConnection(
                "Library",
                "Lecture Hall"
        );

        // Display connections
        graph.displayConnections();

        // BFS
        graph.bfs("Main Gate");

        // DFS
        graph.dfs("Main Gate");
    }
}