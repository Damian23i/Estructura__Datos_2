package Lab;
import java.util.Stack;


public class ConstructorArbol {
    public static NodoExpresion construirDesdePosfija(String expresion) {

        Stack<NodoExpresion> pila = new Stack<>();

        String[] tokens = expresion.trim().split("\\s+");

        for (String token : tokens) {

            NodoExpresion nodo = new NodoExpresion(token);

            if (esOperador(token)) {

                if (pila.size() < 2) {
                    throw new IllegalArgumentException(
                            "Expresion posfija invalida."
                    );
                }

                nodo.der = pila.pop();
                nodo.izq = pila.pop();
            }

            pila.push(nodo);
        }

        if (pila.size() != 1) {
            throw new IllegalArgumentException(
                    "Expresion posfija invalida."
            );
        }

        return pila.pop();
    }

    private static boolean esOperador(String token) {
        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/")
                || token.equals("^");
    }
}
