package org.example.code;

public class Main {
    public static void main(String[] args) {
        // Caso 1
        AVLTree t1 = new AVLTree();
        int[] seq1 = {40, 20, 60, 10, 30, 25};
        for (int v : seq1) {
            t1.insert(v);
        }
        System.out.println("Caso 1:");
        t1.print();

        // Caso 2
        AVLTree t2 = new AVLTree();
        int[] seq2 = {60, 40, 80, 35, 50, 90, 20, 38, 37};
        for (int v : seq2) {
            t2.insert(v);
        }
        System.out.println("\nCaso 2:");
        t2.print();

        // Caso 3
        AVLTree t3 = new AVLTree();
        int[] seq3 = {30, 20, 10, 25, 40, 50, 5, 35, 45};
        for (int v : seq3) {
            t3.insert(v);
        }
        System.out.println("\nCaso 3:");
        t3.print();
    }
}
