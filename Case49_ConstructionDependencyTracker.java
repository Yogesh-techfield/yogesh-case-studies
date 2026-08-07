import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Task {
    int id;
    String name;
    int days;

    public Task(int id, String name, int days) {
        this.id = id;
        this.name = name;
        this.days = days;
    }

    public void show() {
        System.out.println("ID: " + id + " | Task: " + name + " | Duration: " + days + " days");
    }
}

class Node {
    Task data;
    Node next;

    public Node(Task data) {
        this.data = data;
        this.next = null;
    }
}

class TaskLinkedList {
    private Node head;

    public void add(Task t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            curr.data.show();
            curr = curr.next;
        }
    }
}

public class Case49_ConstructionDependencyTracker {

    public static Task binarySearch(Task[] arr, int targetId) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].id == targetId) {
                return arr[mid];
            }
            if (arr[mid].id < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void bfs(ArrayList<ArrayList<Integer>> adj, int start, String[] names) {
        boolean[] vis = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();

        vis[start] = true;
        q.add(start);

        System.out.println("BFS Delay Ripple from " + names[start] + ":");
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(names[curr] + " -> ");

            for (int neighbor : adj.get(curr)) {
                if (!vis[neighbor]) {
                    vis[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        System.out.println("END");
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int curr, boolean[] vis, String[] names) {
        vis[curr] = true;
        System.out.print(names[curr] + " -> ");

        for (int neighbor : adj.get(curr)) {
            if (!vis[neighbor]) {
                dfs(adj, neighbor, vis, names);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 1. Linked List ---");
        TaskLinkedList list = new TaskLinkedList();
        list.add(new Task(10, "Excavation", 7));
        list.add(new Task(20, "Foundation", 10));
        list.add(new Task(30, "Framing", 14));
        list.display();

        System.out.println("\n--- 2. Binary Search ---");
        Task[] tasks = {
            new Task(10, "Excavation", 7),
            new Task(20, "Foundation", 10),
            new Task(30, "Framing", 14)
        };
        Task found = binarySearch(tasks, 20);
        if (found != null) {
            System.out.print("Found Task: ");
            found.show();
        }

        System.out.println("\n--- 3. Graph (BFS & DFS) ---");
        int n = 4;
        String[] names = {"Excavation", "Foundation", "Framing", "Finishing"};
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(3);

        bfs(graph, 1, names);

        System.out.print("DFS Path from start: ");
        boolean[] vis = new boolean[n];
        dfs(graph, 0, vis, names);
        System.out.println("END");
    }
}
