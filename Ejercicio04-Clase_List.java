public class List<T> {
    private Node<T> root;

    public void AgregarDatos(T dato) {
        Node<T> nuevoNode = new Node<>(dato);
        if (root == null) {
            root = nuevoNode;
        } else {
            Node<T> ActualNode = root;
            while (ActualNode.getNextNode() != null) {
                ActualNode = ActualNode.getNextNode();
            }
            ActualNode.setNextNode (nuevoNode);
        }
    }

    public void MostrarDatos() {
        Node<T> ActualNode = root;
        while (ActualNode != null) {
            System.out.println(ActualNode.getDato());
            ActualNode = ActualNode.getNextNode();
        }
    }
}
