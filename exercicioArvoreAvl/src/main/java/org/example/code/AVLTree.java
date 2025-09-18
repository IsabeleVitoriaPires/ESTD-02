package org.example.code;

// AVL simples: insere como BST e gira se desbalancear.
public class AVLTree {

    static final class Node {
        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // folha
        }
    }

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    public void print() {
        printRec(root, 0);
    }
    // --- funções auxiliares ---

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        if (node == null) return;
        int hL = height(node.left);
        int hR = height(node.right);
        node.height = 1 + (hL > hR ? hL : hR);
    }

    private int balanceFactor(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // rotação p/ direita (caso LL)
    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        Node rightOfLeftChild = leftChild.right;

        leftChild.right = node;
        node.left = rightOfLeftChild;

        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    // rotação p/ esquerda (caso RR)
    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        Node leftOfRightChild = rightChild.left;

        rightChild.left = node;
        node.right = leftOfRightChild;

        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    // --- inserção AVL ---
    private Node insert(Node raiz, int chave) {
        if (raiz == null) return new Node(chave);

        if (chave < raiz.key) {
            raiz.left = insert(raiz.left, chave);
        } else if (chave > raiz.key) {
            raiz.right = insert(raiz.right, chave);
        } else {
            return raiz; // ignora duplicado
        }

        updateHeight(raiz);
        int bf = balanceFactor(raiz);

        // LL
        if (bf > 1 && chave < raiz.left.key) return rotateRight(raiz);
        // RR
        if (bf < -1 && chave > raiz.right.key) return rotateLeft(raiz);
        // LR
        if (bf > 1 && chave > raiz.left.key) {
            raiz.left = rotateLeft(raiz.left);
            return rotateRight(raiz);
        }
        // RL
        if (bf < -1 && chave < raiz.right.key) {
            raiz.right = rotateRight(raiz.right);
            return rotateLeft(raiz);
        }

        return raiz; // ok
    }

    // impressão deitada (direita em cima)
    private void printRec(Node node, int nivel) {
        if (node == null) return;
        printRec(node.right, nivel + 1);
        String indent = new String(new char[nivel * 4]).replace('\0', ' ');
        System.out.println(indent + node.key + " (h=" + node.height + ")");
        printRec(node.left, nivel + 1);
    }


}
