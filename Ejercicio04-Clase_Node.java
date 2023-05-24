public class Node<T> {
    private T dato;
    private Node<T> SiguienteNode;

    public Node(T data) {
        this.dato = data;
        this.SiguienteNode = null;
    }

    public T getDato() {
        return dato;
    }

    public Node<T> getSiguienteNode() {
        return SiguienteNode;
    }

    public void setSiguienteNode(Node<T> SiguienteNode) {
        this.SiguienteNode = SiguienteNode;
    }
}
