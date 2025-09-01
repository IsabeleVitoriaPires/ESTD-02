package org.example.code;

public class AVLTree {

    static final class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.height = 1; // folha começa com 1
        }
    }

    // Raiz da árvore
    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    public void print() {
        print(root, "", false);
    }

    private static int height(Node n) {
        if (n == null) {
            return 0;
        } else {
            return n.height;
        }
    }

    private static void updateHeight(Node n) {
        if (n == null) {
            return; // nada a atualizar
        }

        int alturaEsquerda = height(n.left);
        int alturaDireita  = height(n.right);

        int maiorAltura;
        if (alturaEsquerda > alturaDireita) {
            maiorAltura = alturaEsquerda;
        } else {
            maiorAltura = alturaDireita;
        }

        n.height = 1 + maiorAltura;
    }

    private static int balanceFactor(Node n) {
        int hEsq = height(n.left);
        int hDir = height(n.right);
        return hEsq - hDir;
    }

    private static Node rotateRight(Node y) {
        Node x = y.left;     // filho esquerdo de y
        Node T2 = x.right;   // pode ser null

        // Executa a rotação
        x.right = y;         // y desce para direita de x
        y.left = T2;         // T2 vira filho esquerdo de y

        // Atualiza alturas (primeiro quem desceu, depois quem subiu)
        updateHeight(y);
        updateHeight(x);

        // Nova raiz da subárvore
        return x;
    }

    private static Node rotateLeft(Node x) {
        Node y = x.right;    // filho direito de x
        Node T2 = y.left;    // pode ser null

        // Executa a rotação
        y.left = x;          // x desce para esquerda de y
        x.right = T2;        // T2 vira filho direito de x

        // Atualiza alturas (primeiro quem desceu, depois quem subiu)
        updateHeight(x);
        updateHeight(y);

        // Nova raiz da subárvore
        return y;
    }

    private static Node insert(Node raiz, int chave) {
        // 1) Inserção BST padrão
        if (raiz == null) {
            Node novo = new Node(chave);
            // novo.left = null; // já é null por padrão
            // novo.right = null; // já é null por padrão
            // novo.height = 1; // já setado no construtor
            return novo;
        }

        if (chave < raiz.key) {
            raiz.left = insert(raiz.left, chave);
        } else if (chave > raiz.key) {
            raiz.right = insert(raiz.right, chave);
        } else {
            // chaves iguais não são inseridas (política simples)
            return raiz;
        }

        // 2) Atualiza altura do nó atual
        updateHeight(raiz);

        // 3) Verifica balanceamento
        int bal = balanceFactor(raiz);

        // Casos de rotação

        // LL: desbalanceado à esquerda e chave < raiz.esq.key
        if (bal > 1 && chave < raiz.left.key) {
            return rotateRight(raiz);
        }

        // RR: desbalanceado à direita e chave > raiz.dir.key
        if (bal < -1 && chave > raiz.right.key) {
            return rotateLeft(raiz);
        }

        // LR: desbalanceado à esquerda e chave > raiz.esq.key
        if (bal > 1 && chave > raiz.left.key) {
            raiz.left = rotateLeft(raiz.left);
            return rotateRight(raiz);
        }

        // RL: desbalanceado à direita e chave < raiz.dir.key
        if (bal < -1 && chave < raiz.right.key) {
            raiz.right = rotateRight(raiz.right);
            return rotateLeft(raiz);
        }

        // 4) Já balanceada: retorna a própria raiz
        return raiz;
    }

    // Impressão simples da árvore "deitada" (direita em cima, esquerda em baixo)
    private void print(Node node, String prefix, boolean isRight) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            print(node.right, prefix + (isRight ? "        " : "│   "), true);
        }

        System.out.println(prefix + "└── " + node.key + " (h=" + node.height + ")");

        if (node.left != null) {
            print(node.left, prefix + (isRight ? "        " : "│   "), false);
        }
    }
}

