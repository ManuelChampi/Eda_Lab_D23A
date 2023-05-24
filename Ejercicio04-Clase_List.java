public class List<T> {
    private Node<T> inicio;

    public void AgregarDatos(T dato) {
        Node<T> nuevoNode = new Node<>(dato);
        if (inicio == null) {
            inicio = nuevoNode;
        } else {
            Node<T> ActualNode = inicio;
            while (ActualNode.getSiguienteNode() != null) {
                ActualNode = ActualNode.getSiguienteNode();
            }
            ActualNode.setSiguienteNode (nuevoNode);
        }
    }

    public void MostrarDatos() {
        Node<T> ActualNode = inicio;
        while (ActualNode != null) {
            System.out.println(ActualNode.getDato());
            ActualNode = ActualNode.getSiguienteNode();
        }
    }
}
