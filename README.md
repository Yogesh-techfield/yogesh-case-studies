This repository has my DSA case study solutions for questions 21, 48, and 49 implemented in Java.  

## Question 21)
Pharmacy Prescription History (Stack)  
The Problem 
Doctors sometimes need to reverse the last prescription change quickly, since medication errors can be dangerous.  

Logic used:
Models a hospital system where doctors add prescriptions and used pop() to undo accidental dosage mistakes in O(1) time.  

## Question 48)
National Blood Bank Coordination Network  
**The Problem**  
Blood has a short shelf life and hospitals need it urgently during emergencies. The network needs to find and deliver the right blood type fast, from wherever it's available.  

Logic used: 
Queue: Processes hospital blood requests in FIFO order.  
Merge Sort: Sorts blood banks by available inventory.  
Binary Search: Locates specific blood bank by ID.  
Dijkstra's Algorithm: Calculates shortest delivery time from blood bank to hospital instead of using weighted graph.  
BFS: Traverses network graph to confirm hospital reachability.  

## Question 49)
AI-Powered Construction Project Dependency Tracker  

Logic used:  
Custom Linked List (TaskLinkedList): Dynamic addition and iteration of construction tasks.  
Binary Search: Quick lookup of construction task by ID.  
Graph (Adjacency List): Represents task dependencies.  
BFS: Tracks delay ripple effects across downstream dependent tasks.  
DFS: Traces full task execution sequence from start to finish.  

## How to Run

1. Open terminal or command prompt in this directory.  
2. Compile the Java files and then  
3. Run the compiled classes or you can download the code yourself and see also the code  
