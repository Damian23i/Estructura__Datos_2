package Lab;
import java.util.Stack;


public class EvaluadorExpresiones {
    public static double evaluarPosfija(String expresion) {

        Stack<Double> pila = new Stack<>();

        String[] tokens = expresion.trim().split("\\s+");

        for (String token : tokens) {

            if (esNumero(token)) {

                pila.push(Double.parseDouble(token));

            } else {

                if (pila.size() < 2) {
                    throw new IllegalArgumentException(
                            "Expresion posfija invalida."
                    );
                }

                double b = pila.pop();
                double a = pila.pop();

                pila.push(
                        aplicarOperador(token, a, b)
                );
            }
        }

        if (pila.size() != 1) {
            throw new IllegalArgumentException(
                    "Expresion posfija invalida."
            );
        }

        return pila.pop();
    }

    public static double evaluarPrefija(String expresion) {

        Stack<Double> pila = new Stack<>();

        String[] tokens = expresion.trim().split("\\s+");

        for (int i = tokens.length - 1; i >= 0; i--) {

            String token = tokens[i];

            if (esNumero(token)) {

                pila.push(Double.parseDouble(token));

            } else {

                if (pila.size() < 2) {
                    throw new IllegalArgumentException(
                            "Expresion prefija invalida."
                    );
                }

                double a = pila.pop();
                double b = pila.pop();

                pila.push(
                        aplicarOperador(token, a, b)
                );
            }
        }

        if (pila.size() != 1) {
            throw new IllegalArgumentException(
                    "Expresion prefija invalida."
            );
        }

        return pila.pop();
    }

    private static double aplicarOperador(
            String op,
            double a,
            double b) {

        switch (op) {

            case "+":
                return a + b;

            case "-":
                return a - b;

            case "*":
                return a * b;

            case "/":

                if (b == 0) {
                    throw new ArithmeticException(
                            "Division por cero."
                    );
                }

                return a / b;

            case "^":
                return Math.pow(a, b);

            default:
                throw new IllegalArgumentException(
                        "Operador no soportado: " + op
                );
        }
    }

    private static boolean esNumero(String s) {

        try {

            Double.parseDouble(s);
            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }
}
