package Lab;
import java.util.Scanner;


public class main {
    
    private static Scanner scanner = new Scanner(System.in);

    private static String expresionInfija = "";

    public static void main(String[] args) {

        int opcion;

        do {

            mostrarMenu();

            try {

                opcion = Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    ingresarExpresion();
                    break;

                case 2:
                    convertirExpresion();
                    break;

                case 3:
                    construirEImprimirArbol();
                    break;

                case 4:
                    mostrarRecorridos();
                    break;

                case 5:
                    evaluarExpresion();
                    break;

                case 6:
                    mostrarEstadisticas();
                    break;

                case 0:
                    System.out.println(
                            "\nPrograma finalizado."
                    );
                    break;

                default:
                    System.out.println(
                            "\nOpcion no valida."
                    );
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {

        System.out.println("\n================================");
        System.out.println("       ARBOLES DE EXPRESION");
        System.out.println("==================================");
        System.out.println("1. Ingresar expresion infija");
        System.out.println("2. Convertir a posfija y prefija");
        System.out.println("3. Construir arbol de expresion");
        System.out.println("4. Mostrar recorridos");
        System.out.println("5. Evaluar expresion");
        System.out.println("6. Mostrar estadisticas del arbol");
        System.out.println("7. Ejecutar casos de prueba");
        System.out.println("0. Salir");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");
    }

    private static void ingresarExpresion() {

        System.out.println("\nIngrese la expresion infija.");
        System.out.println(
                "IMPORTANTE: separe cada elemento por espacios."
        );
        System.out.println(
                "Ejemplo: ( 5 + 3 ) * 2"
        );

        System.out.print("Expresion: ");

        expresionInfija = scanner.nextLine().trim();

        if (expresionInfija.isEmpty()) {

            System.out.println(
                    "No se ingreso ninguna expresion."
            );

            return;
        }

        System.out.println(
                "\nExpresion guardada correctamente."
        );
    }

    private static void convertirExpresion() {

        if (!hayExpresion()) {
            return;
        }

        try {

            String posfija =
                    ConversorNotacion.infijaAPosfija(
                            expresionInfija
                    );

            String prefija =
                    ConversorNotacion.infijaAPrefija(
                            expresionInfija
                    );

            System.out.println("\nRESULTADOS");
            System.out.println(
                    "Infija  : " + expresionInfija
            );
            System.out.println(
                    "Posfija : " + posfija
            );
            System.out.println(
                    "Prefija : " + prefija
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static NodoExpresion construirArbol() {

        String posfija =
                ConversorNotacion.infijaAPosfija(
                        expresionInfija
                );

        return ConstructorArbol.construirDesdePosfija(
                posfija
        );
    }

    private static void construirEImprimirArbol() {

        if (!hayExpresion()) {
            return;
        }

        try {

            NodoExpresion raiz = construirArbol();

            System.out.println(
                    "\nARBOL CONSTRUIDO CORRECTAMENTE"
            );

            System.out.println(
                    "Raiz: " + raiz.valor
            );

            System.out.println(
                    "Expresion reconstruida: "
                    + obtenerInorden(raiz)
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void mostrarRecorridos() {

        if (!hayExpresion()) {
            return;
        }

        try {

            NodoExpresion raiz = construirArbol();

            StringBuilder preorden =
                    new StringBuilder();

            StringBuilder inorden =
                    new StringBuilder();

            StringBuilder postorden =
                    new StringBuilder();

            Recorridos.preorden(
                    raiz,
                    preorden
            );

            Recorridos.inorden(
                    raiz,
                    inorden
            );

            Recorridos.postorden(
                    raiz,
                    postorden
            );

            System.out.println("\nRECORRIDOS DEL ARBOL");
            System.out.println("--------------------------------------");

            System.out.println(
                    "Preorden  (Prefija): "
                    + preorden.toString().trim()
            );

            System.out.println(
                    "Inorden   (Infija):  "
                    + inorden
            );

            System.out.println(
                    "Postorden (Posfija): "
                    + postorden.toString().trim()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void evaluarExpresion() {

        if (!hayExpresion()) {
            return;
        }

        try {

            String posfija =
                    ConversorNotacion.infijaAPosfija(
                            expresionInfija
                    );

            String prefija =
                    ConversorNotacion.infijaAPrefija(
                            expresionInfija
                    );

            double resultadoPosfija =
                    EvaluadorExpresiones.evaluarPosfija(
                            posfija
                    );

            double resultadoPrefija =
                    EvaluadorExpresiones.evaluarPrefija(
                            prefija
                    );

            System.out.println("\nEvaluacion");
            System.out.println("--------------------------------------");

            System.out.println(
                    "Resultado por posfija: "
                    + resultadoPosfija
            );

            System.out.println(
                    "Resultado por prefija: "
                    + resultadoPrefija
            );

            if (Math.abs(
                    resultadoPosfija
                    - resultadoPrefija
            ) < 0.0000001) {

                System.out.println(
                        "VERIFICACION: Ambos resultados coinciden."
                );

            } else {

                System.out.println(
                        "ADVERTENCIA: Los resultados no coinciden."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void mostrarEstadisticas() {

        if (!hayExpresion()) {
            return;
        }

        try {

            NodoExpresion raiz = construirArbol();

            int altura = calcularAltura(raiz);
            int operadores = contarOperadores(raiz);
            int operandos = contarOperandos(raiz);
            int total = operadores + operandos;

            System.out.println(
                    "\nEstadisticas del arbol"
            );

            System.out.println(
                    "--------------------------------------"
            );

            System.out.println(
                    "Altura del arbol: " + altura
            );

            System.out.println(
                    "Nodos operador: " + operadores
            );

            System.out.println(
                    "Nodos operando: " + operandos
            );

            System.out.println(
                    "Total de nodos: " + total
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static int calcularAltura(
            NodoExpresion nodo) {

        if (nodo == null) {
            return 0;
        }

        int alturaIzq =
                calcularAltura(nodo.izq);

        int alturaDer =
                calcularAltura(nodo.der);

        return 1 + Math.max(
                alturaIzq,
                alturaDer
        );
    }

    private static int contarOperadores(
            NodoExpresion nodo) {

        if (nodo == null) {
            return 0;
        }

        int cantidad = 0;

        if (nodo.esOperador()) {
            cantidad = 1;
        }

        return cantidad
                + contarOperadores(nodo.izq)
                + contarOperadores(nodo.der);
    }

    private static int contarOperandos(
            NodoExpresion nodo) {

        if (nodo == null) {
            return 0;
        }

        int cantidad = 0;

        if (!nodo.esOperador()) {
            cantidad = 1;
        }

        return cantidad
                + contarOperandos(nodo.izq)
                + contarOperandos(nodo.der);
    }

    private static String obtenerInorden(
            NodoExpresion raiz) {

        StringBuilder sb =
                new StringBuilder();

        Recorridos.inorden(raiz, sb);

        return sb.toString();
    }

    private static boolean hayExpresion() {

        if (expresionInfija == null
                || expresionInfija.trim().isEmpty()) {

            System.out.println(
                    "\nPrimero debe ingresar una expresion."
            );

            return false;
        }

        return true;
    }

      
    
}
