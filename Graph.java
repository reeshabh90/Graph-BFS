// Java code to demonstrate Graph representation 
// using ArrayList in Java 

import java.util.*;

class Graph {
    private int V; // No. of vertices
    private ArrayList<ArrayList<Integer>> adj; // Adjacency Lists
    List<Integer> resList = new ArrayList<>();

    // Constructor
    Graph(int v) {
        V = v;
        adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());
    }

    // A utility function to add an edge in an
    // undirected graph
    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    boolean hasEdge(int u, int v) {
        if (adj.get(u).contains(v)) {
            // System.out.println("Yes");
            return true;
        } else {
            // System.out.println("No");
            return false;
        }
    }

    void printGraph() {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    void BFS(int s) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj.get(s).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


    // Prints all paths from
    // 's' to 'd'
    public List<Integer> printAllPaths(int s, int d) {
        resList = new ArrayList<>();
        boolean[] isVisited = new boolean[V];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);

        // Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
        return resList;
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;

        if (u.equals(d)) {
            //System.out.println(localPathList);
            for (Integer r : localPathList) {
                resList.add(r);
            }

            // if match found then no need to traverse more till depth
            isVisited[u] = false;
            return;
        }
        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adj.get(u)) {
            if (!isVisited[i]) {
                // store current node
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }
        // Mark the current node
        isVisited[u] = false;

    }

    static void representInGraph(int[] T) {
        Graph g = new Graph(T.length);
        for (int i = 0; i < T.length; i++) {
            g.addEdge(i, T[i]);
        }

        List<Integer> res = g.printAllPaths(3, 4);
        System.out.println(res);
    }

    // Driver Code
    public static void main(String[] args) {
        // Creating a graph with 5 vertices
        int[] T = { 2, 0, 2, 2, 1, 0 };
        representInGraph(T);

    }
}
