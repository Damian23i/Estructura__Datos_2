package Lab;
import java.util.Stack;



public class ConversorNotacion {
    private static int precedencia(char op) {

        switch (op) {

            case '^':
                return 3;

            case '*':
            case '/':
                return 2;

            case '+':
            case '-':
                return 1;

            default:
                return -1;
        }
    }

    private static boolean asociaIzquierda(char op) {
        return op != '^';
    }

    public static String infijaAPosfija(String expresion) {

        StringBuilder salida = new StringBuilder();
        Stack<Character> pilaOp = new Stack<>();

        String[] tokens = expresion.trim().split("\\s+");

        for (String tok : tokens) {

            char c = tok.charAt(0);

            
            if (Character.isLetterOrDigit(c)) {

                salida.append(tok).append(" ");

           
            } else if (c == '(') {

                pilaOp.push(c);

            
            } else if (c == ')') {

                while (!pilaOp.isEmpty()
                        && pilaOp.peek() != '(') {

                    salida.append(pilaOp.pop()).append(" ");
                }

                if (pilaOp.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Parentesis desbalanceados."
                    );
                }

                pilaOp.pop();

            
            } else {

                while (!pilaOp.isEmpty()
                        && pilaOp.peek() != '('
                        && (
                            precedencia(pilaOp.peek()) >
                            precedencia(c)
                            ||
                            (
                                precedencia(pilaOp.peek()) ==
                                precedencia(c)
                                && asociaIzquierda(c)
                            )
                        )) {

                    salida.append(pilaOp.pop()).append(" ");
                }

                pilaOp.push(c);
            }
        }

        while (!pilaOp.isEmpty()) {

            if (pilaOp.peek() == '(') {
                throw new IllegalArgumentException(
                        "Parentesis desbalanceados."
                );
            }

            salida.append(pilaOp.pop()).append(" ");
        }

        return salida.toString().trim();
    }

    public static String infijaAPrefija(String expresion) {

        String[] tokens = expresion.trim().split("\\s+");

        StringBuilder invertida = new StringBuilder();

        
        for (int i = tokens.length - 1; i >= 0; i--) {

            String tok = tokens[i];

            if (tok.equals("(")) {
                tok = ")";
            } else if (tok.equals(")")) {
                tok = "(";
            }

            invertida.append(tok).append(" ");
        }

        
        String posfijaInvertida =
                infijaAPosfija(invertida.toString().trim());

        
        String[] resultTokens =
                posfijaInvertida.split("\\s+");

        StringBuilder prefija = new StringBuilder();

        for (int i = resultTokens.length - 1; i >= 0; i--) {
            prefija.append(resultTokens[i]).append(" ");
        }

        return prefija.toString().trim();
    }
}
