// Juan Esteban Rosero Jimenez
// Laura Sofia Hernandez Chaparro

public class CineColombia {
    public static void main(String[] args) {
        listaBoletos boletos =new listaBoletos();
        boletos.insertarBoleto(6, "Blancanieves", "B45");
        boletos.insertarBoleto(5, "Minecraft: The Movie", "B78");
        boletos.insertarBoleto(5, "Minecraft: The Movie", "B74");
        boletos.buscarBoleto(3);
        boletos.mostrarBoleto();
        boletos.eliminarBoleto(1);
        boletos.mostrarBoleto();
    }
}

class Boleto {
    int numeroSala;
    String pelicula;
    String numeroSilla;
    Boleto siguiente;
    int ID;

    public Boleto(int numeroSala, String pelicula, String numeroSilla) {
        this.numeroSala = numeroSala;
        this.pelicula = pelicula;
        this.numeroSilla = numeroSilla;
        this.siguiente = null;
    }
}

class listaBoletos {
    private Boleto cabeza;

    public listaBoletos() {
        this.cabeza = null;
    }

    public void insertarBoleto (int numeroSala, String pelicula, String numeroSilla){
        Boleto nuevoBoleto = new Boleto(numeroSala, pelicula, numeroSilla);
        if (cabeza == null) {
            cabeza = nuevoBoleto;
            cabeza.ID = 1;
            return;
        }
        Boleto actual = cabeza;
        while (actual.siguiente != null){
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoBoleto;

        actual.siguiente.ID = actual.ID + 1;
    }

    public void eliminarBoleto (int ID){
        Boleto actual = cabeza;
        Boleto prev = null;
        if (actual != null && actual.ID == ID){
            cabeza = actual.siguiente;
            return;
        }
        while (actual != null && actual.ID != ID){
            prev = actual;
            actual = actual.siguiente;
        }
        if (actual == null) return;
        prev.siguiente = actual.siguiente;
    }

    public void buscarBoleto (int ID){
        Boleto actual =  cabeza;

        while (actual != null){
           if (actual.ID == ID){
               System.out.println("Boleto encontrado. \n Los datos del boleto son los siguientes: \n Pelicula: " + actual.pelicula + "\n Sala: "+actual.numeroSala + "\n numero de silla: " + actual.numeroSilla);
           }
           actual = actual.siguiente;
        }
    }

    public void mostrarBoleto (){
        Boleto actual = cabeza;
        System.out.println("Los datos del boleto son los siguientes: ");
        while (actual != null){
            System.out.println("\n Pelicula: " + actual.pelicula + "\n Sala: "+actual.numeroSala + "\n numero de silla: " + actual.numeroSilla+"\n ID de Boleto: "+actual.ID+"\n ----------------------------------");
            actual = actual.siguiente;
        }

    }

}

class Sillas {
    int silla;
    Sillas izquierdo;
    Sillas derecho;

    public Sillas(int silla) {
        this.silla = silla;
        this.izquierdo = null;
        this.derecho = null;
    }
}
class arbolSillas {
    Sillas raiz;

    public arbolSillas(Sillas raiz) {
        this.raiz = raiz;
    }
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new silla(valor);
        } else {
            insertarRecursivo(raiz, valor);
        }
    }

    private void insertarRecursivo(Nodo nodo, int silla) {
        if (silla < nodo.valor) {
            if (sillas.izquierdo == null) {
                nodo.izquierdo = new Nodo(silla);
            } else {
                insertarRecursivo(nodo.izquierdo, silla);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new Nodo(silla);
            } else {
                insertarRecursivo(nodo.derecho, silla);
            }
        }
    }


}
