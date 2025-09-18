package org.example.code;

public class Main {
    public static void main(String[] args) {
        // Caso 1
        AVLTree avlTreeCase1 = new AVLTree();
        int[] insertionOrderCase1 = {40, 20, 60, 10, 30, 25};
        for (int value : insertionOrderCase1) {
            avlTreeCase1.insert(value);
        }
        System.out.println("Caso 1:");
        avlTreeCase1.print();

        // Caso 2
        AVLTree avlTreeCase2 = new AVLTree();
        int[] insertionOrderCase2 = {60, 40, 80, 35, 50, 90, 20, 38, 37};
        for (int value : insertionOrderCase2) {
            avlTreeCase2.insert(value);
        }
        System.out.println("\nCaso 2:");
        avlTreeCase2.print();

        // Caso 3
        AVLTree avlTreeCase3 = new AVLTree();
        int[] insertionOrderCase3 = {30, 20, 10, 25, 40, 50, 5, 35, 45};
        for (int value : insertionOrderCase3) {
            avlTreeCase3.insert(value);
        }
        System.out.println("\nCaso 3:");
        avlTreeCase3.print();
    }
}
