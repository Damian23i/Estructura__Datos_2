/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author damia
 */
public class main {
    
    public static void main(String[] args) {
        
        Listaenlazadasimple lista = new Listaenlazadasimple();
        
        System.out.printf("Lista Enlazada Simple");
        
        
        //Insertar Nodos en la lista enlazada
        
        lista.insertar(10);
        lista.insertar(20);
        lista.insertar(30);
        lista.insertar(40);
        lista.insertar(50);
        lista.mostrar();
        
        //tamaño de la lista
        System.out.println("Tamaño de la lista "+ lista.tamanio());
        
        
        //buscar en la lista
        System.out.println("Buscar en la lista el numero 20 "+ lista.buscar(20));
        
        
        //Eliminar
        System.out.println("Eliminar de la lista enlazada el numero 40");
        lista.eliminar(40);
        lista.mostrar();
        
        
        
        
        
    }
    
}
