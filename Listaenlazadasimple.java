

package Principal;


public class Listaenlazadasimple {
    
    private Nodo cabeza;
    
    
    
    public Listaenlazadasimple (){
        cabeza = null; //Aqui la lista esta vacia
    }
    
    //insertar el dato
    
    public void insertar (int dato){
        Nodo nuevo = new Nodo(dato);
        
        
        
        if (cabeza == null){
            cabeza = nuevo;
        } else{
            Nodo actual = cabeza;
            
            while (actual.enlace != null){
                actual = actual.enlace;
            }
            
            actual.enlace = nuevo;
        }
            
    }
    
    //Eliminar
    public void eliminar (int dato){
        if (cabeza == null) return;
        
        if(cabeza.dato == dato){
            cabeza = cabeza.enlace;
            return;
        }
        Nodo anterior = cabeza;
        while(anterior.enlace != null && anterior.enlace.dato != dato){
            
            anterior = anterior.enlace;
        }
        
        if (anterior.enlace != null){
            anterior.enlace = anterior.enlace.enlace;
        }
    }
    
    //Eliminar
    public boolean buscar(int dato){
        Nodo actual = cabeza;
        
        while(actual != null){
            if(actual.dato == dato)return true;
            actual = actual.enlace;
        }
        return false;
    }
    
    
    //mostrar la lista
    public void mostrar(){
        if(cabeza == null){
            System.out.println("La lista esta vacia");
            return;
        }
        
        Nodo actual = cabeza;
        System.out.print(" Cabeza -> ");
        
        while(actual != null){
            System.out.print(actual + " -> ");
            
            actual = actual.enlace;
        }
        
        System.out.print(" Null ");
    }
    
    public int tamanio(){
        int cont = 0;
        
        Nodo actual = cabeza;
        
        while(actual != null ){
            cont++;
        }
        
        return cont;
    }
}
