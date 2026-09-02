package Lab;


public class NodoExpresion {
    String valor;
    NodoExpresion izq;
    NodoExpresion der;

    public NodoExpresion(String valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }

    public boolean esOperador() {
        return valor.equals("+")
                || valor.equals("-")
                || valor.equals("*")
                || valor.equals("/")
                || valor.equals("^");
    }

}
