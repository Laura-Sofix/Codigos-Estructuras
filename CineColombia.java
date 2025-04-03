// Juan Esteban Rosero Jimenez
// Laura Sofia Hernandez Chaparro
import java.util.*;

public class CineColombia {
    private static Queue<String> colaCompras = new LinkedList<>();
    private static Stack<String> historialCompras = new Stack<>();

    public static ArbolSillas generarSillas() {
        ArbolSillas arbol = new ArbolSillas();

        for (char fila = 'A'; fila <= 'J'; fila++) {
            for (int num = 1; num <= 16; num++) {
                arbol.insertar(fila + String.valueOf(num));
            }
        }

        return arbol;
    }

    public static void main(String[] args) {
        listaBoletos boletos = new listaBoletos();
        Scanner sc = new Scanner(System.in);
        ArbolSillas sala1 = generarSillas();
        ArbolSillas sala2 = generarSillas();
        ArbolSillas sala3 = generarSillas();
        final int valorBoleta = 8000;
        int op;
        String silla;

        System.out.println("Bienvenido a Cine Colombia. Selecione una opción");
        do {
            System.out.println(" 1) Crear puesto en fila \n 2) Atender cliente \n 3) Consultar boleto \n 4) Ver Todos los Boletos \n 5) Ver Historial de Compras \n 6) Salir");
            System.out.print("Escriba su opción: ");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Ingrese su nombre para crear un puesto en la fila: ");
                    String nombre = sc.nextLine();
                    colaCompras.add(nombre);
                    System.out.println(nombre + ", ha sido añadido a la fila de compras. Por favor espere su turno.");
                    break;
                case 2:
                    if (!colaCompras.isEmpty()) {
                        String cliente = colaCompras.poll();
                        System.out.println("Atendiendo a " + cliente);
                        System.out.println("Escriba el numero de la pelicula que va a ver: \n" +
                                "1) Blanca nieves - Sala 1\n" +
                                "2) Capitan America - Sala 2\n" +
                                "3) Minecraft movie - Sala 3 \n");
                        int opcionPelicula = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Escriba el numero de silla (Recordar las filas van de la A a J y son 16 sillas por cada una): ");
                        silla = sc.nextLine().toUpperCase();

                        boolean sillaDisponible = false;
                        String pelicula = "";

                        if (opcionPelicula == 1) {
                            if (sala1.buscar(silla)) {
                                sala1.eliminar(silla);
                                sillaDisponible = true;
                                pelicula = "Blanca nieves";
                            }
                        } else if (opcionPelicula == 2) {
                            if (sala2.buscar(silla)) {
                                sala2.eliminar(silla);
                                sillaDisponible = true;
                                pelicula = "Capitan America";
                            }
                        } else if (opcionPelicula == 3) {
                            if (sala3.buscar(silla)) {
                                sala3.eliminar(silla);
                                sillaDisponible = true;
                                pelicula = "Minecraft movie";
                            }
                        }

                        if (sillaDisponible) {
                            System.out.println("El precio de la boleta es: " + valorBoleta);
                            System.out.println("Si va a pagar en efectivo escriba 1, si va a pagar con tarjeta escriba 2: ");
                            int opcionPago = sc.nextInt();
                            sc.nextLine();
                            if (opcionPago == 1) {
                                System.out.println("Escriba el dinero que va a pagar: ");
                                int dinero = sc.nextInt();
                                sc.nextLine();
                                if (dinero >= valorBoleta) {
                                    System.out.println("Pago aceptado. \n");
                                    boletos.insertarBoleto(opcionPelicula, pelicula, silla);
                                    historialCompras.push("Se compró boleto para " + pelicula + " en la silla " + silla);
                                } else {
                                    System.out.println("Dinero insuficiente. \n");
                                }
                            } else if (opcionPago == 2) {
                                System.out.println("Escriba el numero de la tarjeta: ");
                                String tarjeta = sc.nextLine();
                                System.out.println("Escriba el codigo de seguridad: ");
                                int codigo = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Pago aceptado. \n");
                                boletos.insertarBoleto(opcionPelicula, pelicula, silla);
                                historialCompras.push("Se compró boleto para " + pelicula + " en la silla " + silla);
                            } else {
                                System.out.println("Opción no válida. \n");
                            }
                        } else {
                            System.out.println("Silla no disponible. \n");
                        }
                    } else {
                        System.out.println("No hay clientes en la fila para atender.");
                    }
                    break;
                case 3:
                    System.out.println("Escriba el ID del boleto que desea consultar: ");
                    op = sc.nextInt();
                    sc.nextLine();
                    boletos.buscarBoleto(op);
                    break;
                case 4:
                    boletos.mostrarBoleto();
                    break;
                case 5:
                    mostrarHistorialCompras();
                    break;
                case 6:
                    System.out.println("Gracias por usar Cine Colombia. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor intente de nuevo.");
            }

        } while (op != 6);
    }

    private static void mostrarHistorialCompras() {
        if (historialCompras.isEmpty()) {
            System.out.println("No hay historial de compras.");
        } else {
            System.out.println("Historial de compras:");
            for (String compra : historialCompras) {
                System.out.println(compra);
            }
        }
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
            System.out.println("El ID de su boleto es: " + cabeza.ID);
            System.out.println("Los datos del boleto son los siguientes: \n Pelicula: " + cabeza.pelicula + "\n Sala: "+cabeza.numeroSala + "\n numero de silla: " + cabeza.numeroSilla);
            return;
        }
        Boleto actual = cabeza;
        while (actual.siguiente != null){
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoBoleto;

        actual.siguiente.ID = actual.ID + 1;
        System.out.println("El ID de su boleto es: " + actual.siguiente.ID);
        System.out.println("Los datos del boleto son los siguientes: \n Pelicula: " + actual.siguiente.pelicula + "\n Sala: "+actual.siguiente.numeroSala + "\n numero de silla: " + actual.siguiente.numeroSilla);
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

class nodoSillas {
    String numSillas;
    nodoSillas izquierdo;
    nodoSillas derecho;

    public nodoSillas(String numSillas) {
        this.numSillas = numSillas;
        this.izquierdo = null;
        this.derecho = null;
    }
}
class ArbolSillas {
    nodoSillas raiz;
    public ArbolSillas(){
        this.raiz=null;
    }
    public void insertar(String numsillas) {
        if (raiz == null) {
            raiz = new nodoSillas(numsillas);
        } else {
            insertarRecursivo(raiz, numsillas);
        }
    }
    private void insertarRecursivo(nodoSillas nodo, String numSillas) {
        if (numSillas.compareTo(nodo.numSillas) < 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new nodoSillas(numSillas);
            } else {
                insertarRecursivo(nodo.izquierdo, numSillas);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new nodoSillas(numSillas);
            } else {
                insertarRecursivo(nodo.derecho, numSillas);
            }
        }
    }
    public void inorden(nodoSillas nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.numSillas + " ");
            inorden(nodo.derecho);
        }
        System.out.println( );
    }
    public void eliminar(String numSillas) {
        raiz = eliminarRecursivo(raiz, numSillas);
    }

    private nodoSillas eliminarRecursivo(nodoSillas nodo, String numSillas) {
        numSillas=numSillas.toUpperCase();
        if (nodo == null) {
            return null; // No se encontró el nodo
        }
        if (numSillas.compareTo(nodo.numSillas)<0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, numSillas);
        } else if (numSillas.compareTo(nodo.numSillas)>0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, numSillas);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) return nodo.derecho;
            else if (nodo.derecho == null) return nodo.izquierdo;

            // Nodo con dos hijos: obtener el mínimo en el subárbol derecho
            nodo.numSillas = minValue(nodo.derecho);

            // Eliminar el nodo mínimo del subárbol derecho
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.numSillas);
        }
        return nodo;
    }

    private String minValue(nodoSillas nodo) {
        String min = nodo.numSillas;
        while (nodo.izquierdo != null) {
            min = nodo.izquierdo.numSillas;
            nodo = nodo.izquierdo;
        }
        return min;
    }
    public boolean buscar(String numSillas) {
        return buscarRecursivo(raiz, numSillas);
    }

    private boolean buscarRecursivo(nodoSillas nodo, String numSillas) {
        if (nodo == null) {
            return false;
        }
        if (numSillas.equals(nodo.numSillas)) {
            return true;
        }
        return numSillas.compareTo(nodo.numSillas) < 0
                ? buscarRecursivo(nodo.izquierdo, numSillas)
                : buscarRecursivo(nodo.derecho, numSillas);
    }
}