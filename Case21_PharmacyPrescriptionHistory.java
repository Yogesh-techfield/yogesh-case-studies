class Prescription {
    int id;
    String name;
    String dose;
    String time;

    public Prescription(int id, String name, String dose, String time) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.time = time;
    }

    public void printInfo() {
        System.out.println("ID: " + id + " | Medicine: " + name + " | Dose: " + dose + " | Time: " + time);
    }
}

class MyStack {
    private Prescription[] arr;
    private int top;

    public MyStack(int size) {
        arr = new Prescription[size];
        top = -1;
    }

    public void push(Prescription item) {
        if (top == arr.length - 1) {
            System.out.println("Stack is full");
            return;
        }
        top++;
        arr[top] = item;
        System.out.print("Added: ");
        item.printInfo();
    }

    public Prescription pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }
        Prescription removed = arr[top];
        top--;
        System.out.print("Undone: ");
        removed.printInfo();
        return removed;
    }

    public Prescription peek() {
        if (top == -1) {
            return null;
        }
        return arr[top];
    }

    public void display() {
        if (top == -1) {
            System.out.println("History is empty");
            return;
        }
        System.out.println("\nPrescription History:");
        for (int i = top; i >= 0; i--) {
            arr[i].printInfo();
        }
        System.out.println();
    }
}

public class Case21_PharmacyPrescriptionHistory {
    public static void main(String[] args) {
        MyStack history = new MyStack(5);

        history.push(new Prescription(101, "Paracetamol", "500mg", "09:00 AM"));
        history.push(new Prescription(102, "Amoxicillin", "250mg", "09:15 AM"));
        history.push(new Prescription(103, "Ibuprofen", "400mg", "09:30 AM"));

        history.display();

        System.out.println("Doctor made a mistake on last entry, performing Undo...");
        history.pop();

        history.display();

        Prescription latest = history.peek();
        if (latest != null) {
            System.out.print("Current Active Prescription: ");
            latest.printInfo();
        }
    }
}
