import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class BloodBank {
    int id;
    String name;
    int stock;

    public BloodBank(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public void show() {
        System.out.println("ID: " + id + " | Bank: " + name + " | Stock: " + stock + " units");
    }
}

class Request {
    int reqId;
    String hospital;
    String bloodGroup;

    public Request(int reqId, String hospital, String bloodGroup) {
        this.reqId = reqId;
        this.hospital = hospital;
        this.bloodGroup = bloodGroup;
    }

    public void show() {
        System.out.println("Request #" + reqId + " from " + hospital + " for " + bloodGroup);
    }
}

class Edge {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

public class Case48_NationalBloodBankNetwork {

    public static void mergeSort(BloodBank[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(BloodBank[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        BloodBank[] L = new BloodBank[n1];
        BloodBank[] R = new BloodBank[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].stock <= R[j].stock) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static BloodBank binarySearch(BloodBank[] arr, int targetId) {
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

    public static void dijkstra(ArrayList<ArrayList<Edge>> graph, int src, String[] names) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = -1;
            int min = Integer.MAX_VALUE;

            for (int v = 0; v < n; v++) {
                if (!vis[v] && dist[v] < min) {
                    min = dist[v];
                    u = v;
                }
            }

            if (u == -1) break;
            vis[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int w = edge.weight;
                if (!vis[v] && dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        System.out.println("Shortest travel time from " + names[src] + ":");
        for (int i = 0; i < n; i++) {
            if (i != src) {
                System.out.println("To " + names[i] + ": " + dist[i] + " mins");
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Edge>> graph, int start, String[] names) {
        boolean[] vis = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        vis[start] = true;
        q.add(start);

        System.out.print("BFS Network Nodes: ");
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(names[curr] + " ");

            for (Edge edge : graph.get(curr)) {
                if (!vis[edge.dest]) {
                    vis[edge.dest] = true;
                    q.add(edge.dest);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("1. Queue Hospital Requests");
        Queue<Request> q = new LinkedList<>();
        q.add(new Request(1, "City Hospital", "O+"));
        q.add(new Request(2, "St Jude Clinic", "AB-"));
        q.add(new Request(3, "Metro Care", "B+"));

        while (!q.isEmpty()) {
            q.poll().show();
        }

        System.out.println("\n2. Merge Sort Stock Sorting");
        BloodBank[] banks = {
            new BloodBank(103, "Central Bank", 400),
            new BloodBank(101, "Red Cross", 150),
            new BloodBank(102, "Apex Depot", 280)
        };

        mergeSort(banks, 0, banks.length - 1);
        for (BloodBank b : banks) {
            b.show();
        }

        System.out.println("\n3. Binary Search");
        Arrays.sort(banks, (a, b) -> a.id - b.id);
        BloodBank found = binarySearch(banks, 102);
        if (found != null) {
            System.out.print("Found: ");
            found.show();
        }

        System.out.println("\n4. Graph BFS and Dijkstra");
        int nodes = 4;
        String[] names = {"BloodBank", "Hospital A", "Hospital B", "Clinic C"};
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(2, 25));
        graph.get(1).add(new Edge(2, 10));
        graph.get(1).add(new Edge(3, 30));
        graph.get(2).add(new Edge(3, 10));

        bfs(graph, 0, names);
        dijkstra(graph, 0, names);
    }
}
