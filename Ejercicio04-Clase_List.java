public class List<T> {
    private Node<T> root;

    public void AgregarDatos(T dato) {
        Node<T> nuevoNode = new Node<>(dato);
        if (root == null) {
            root = nuevoNode;
        } else {
            Node<T> ActualNode = root;
            while (ActualNode.getSiguienteNode() != null) {
                ActualNode = ActualNode.getSiguienteNode();
            }
            ActualNode.setSiguienteNode (nuevoNode);
        }
    }

    public void MostrarDatos() {
        Node<T> ActualNode = root;
        while (ActualNode != null) {
            System.out.println(ActualNode.getDato());
            ActualNode = ActualNode.getSiguienteNode();
        }
    }
}
