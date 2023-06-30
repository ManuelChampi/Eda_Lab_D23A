import java.util.Scanner;
class AVL {
    Node root;

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int calculateBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    Node leftRotate(Node node) {
        Node newRoot = node.right;
        Node leftSubtree = newRoot.left;

        newRoot.left = node;
        node.right = leftSubtree;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    Node rightRotate(Node node) {
        Node newRoot = node.left;
        Node rightSubtree = newRoot.right;

        newRoot.right = node;
        node.left = rightSubtree;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    Node balanceNode(Node node, int key) {
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balanceFactor = calculateBalanceFactor(node);

        // Left Left Case
        if (balanceFactor > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balanceFactor < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balanceFactor > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balanceFactor < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        return balanceNode(node, key);
    }

    Node getMin(Node node) {
        if (node == null || node.left == null)
            return node;
        return getMin(node.left);
    }

    Node getMax(Node node) {
        if (node == null || node.right == null)
            return node;
        return getMax(node.right);
    }

    boolean search(Node node, int key) {
        if (node == null)
            return false;

        if (key == node.key)
            return true;

        if (key < node.key)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    Node parent(Node node, int key) {
        if (node == null || (node.left == null && 
                node.right == null))
            return null;

        if ((node.left != null && node.left.key == key) || (node.right != null && node.right.key == key))
            return node;

        if (key < node.key)
            return parent(node.left, key);
        else
            return parent(node.right, key);
    }

    void son(Node node, int key) {
        if (node == null)
            return;

        if (node.key == key) {
            Node leftChild = node.left;
            Node rightChild = node.right;

            System.out.println("Hijo izquierdo de " + (char) node.key + ": " + (leftChild != null ? (char) leftChild.key : "null"));
            System.out.println("Hijo derecho de " + (char) node.key + ": " + (rightChild != null ? (char) rightChild.key : "null"));
        } else if (key < node.key) {
            son(node.left, key);
        } else {
            son(node.right, key);
        }
    }

    Node remove(Node node, int key) {
        if (node == null)
            return node;

        if (key < node.key)
            node.left = remove(node.left, key);
        else if (key > node.key)
            node.right = remove(node.right, key);
        else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = getMin(node.right);
                node.key = temp.key;
                node.right = remove(node.right, temp.key);
            }
        }

        if (node == null)
            return node;

        return balanceNode(node, key);
    }

    void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print((char) node.key + " ");
            printInOrder(node.right);
        }
    }


    public static void main(String[] args) {
        AVL avl = new AVL();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una palabra en mayúsculas: ");
        String word = scanner.nextLine();

        for (int i = 0; i < word.length(); i++) {
            int asciiValue = (int) word.charAt(i);
            avl.root = avl.insert(avl.root, asciiValue);
        }

        System.out.println("El árbol AVL ha sido construido.");

        for (int i = 0; i < word.length(); i++) {
            int asciiValue = (int) word.charAt(i);
            if (avl.search(avl.root, asciiValue))
                System.out.println("El valor " + (char) asciiValue + " está presente en el árbol AVL.");
            else
                System.out.println("El valor " + (char) asciiValue + " no está presente en el árbol AVL.");
        }

        Node minNode = avl.getMin(avl.root);
        System.out.println("El valor mínimo en el árbol AVL es: " + (char) minNode.key);

        Node maxNode = avl.getMax(avl.root);
        System.out.println("El valor máximo en el árbol AVL es: " + (char) maxNode.key);

        System.out.print("Ingrese un valor para obtener su nodo padre: ");
        int parentValue = scanner.nextInt();
        Node parentNode = avl.parent(avl.root, parentValue);
        if (parentNode != null)
            System.out.println("El nodo padre del valor " + (char) parentValue + " es: " + (char) parentNode.key);
        else
            System.out.println("El valor " + (char) parentValue + " no tiene nodo padre.");

        System.out.print("Ingrese un valor para obtener sus hijos: ");
        int sonValue = scanner.nextInt();
        avl.son(avl.root, sonValue);

        System.out.print("Ingrese un valor para eliminar del árbol AVL: ");
        int removeValue = scanner.nextInt();
        avl.root = avl.remove(avl.root, removeValue);
        System.out.println("El valor " + (char) removeValue + " ha sido eliminado del árbol AVL.");

        System.out.println("Recorrido InOrder del árbol AVL: ");
        avl.printInOrder(avl.root);
    }
}
