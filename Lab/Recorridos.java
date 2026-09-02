package Lab;


public class Recorridos {
    
    
    public static void preorden(
            NodoExpresion nodo,
            StringBuilder sb) {

        if (nodo == null) {
            return;
        }

        sb.append(nodo.valor).append(" ");

        preorden(nodo.izq, sb);
        preorden(nodo.der, sb);
    }

    
    public static void inorden(
            NodoExpresion nodo,
            StringBuilder sb) {

        if (nodo == null) {
            return;
        }

        boolean esOperador = nodo.esOperador();

        if (esOperador) {
            sb.append("(");
        }

        inorden(nodo.izq, sb);

        sb.append(nodo.valor);

        inorden(nodo.der, sb);

        if (esOperador) {
            sb.append(")");
        }
    }

    
    public static void postorden(
            NodoExpresion nodo,
            StringBuilder sb) {

        if (nodo == null) {
            return;
        }

        postorden(nodo.izq, sb);
        postorden(nodo.der, sb);

        sb.append(nodo.valor).append(" ");
    }
    
    
    
}
