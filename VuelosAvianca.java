import java.util.*;
import java.util.Scanner;

public class VuelosAvianca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolVuelos arbol = new ArbolVuelos();

        //aviones para cada vuelo
        Avion airbusA320 = new Avion("Airbus A320", 30, 6);
        Avion boeing737 = new Avion("Boeing 737", 32, 6);
        Avion airbusA380 = new Avion("Airbus A380", 50, 10);
        Avion boeing787 = new Avion("Boeing 787", 40, 8);

        // vuelos disponibles
        arbol.insertarVuelo("AV123", "Bogota", "Medellin", airbusA320);
        arbol.insertarVuelo("AV456", "Medellin", "Cali", boeing737);
        arbol.insertarVuelo("AV789", "Cali", "Cartagena", airbusA380);
        arbol.insertarVuelo("AV101", "Cartagena", "San Andres", boeing787);

        int opcion;
        do {
            System.out.println("\n--- Bienvenido al Menú de Gestión de Vuelos de Avianca ---");
            System.out.println("1. Mostrar lista de vuelos");
            System.out.println("2. Buscar un vuelo");
            System.out.println("3. Reservar un asiento");
            System.out.println("4. Mostrar boleto");
            System.out.println("5. Eliminar boleto");
            System.out.println("6. Modificar una reserva");
            System.out.println("7. Mostrar asientos disponibles");
            System.out.println("8. Mostrar pasajeros de un vuelo");
            System.out.println("9. Ver historial de modificaciones");
            System.out.println("10. Ver lista de espera de un vuelo");
            System.out.println("11. Agregar pasajero a la lista de espera");
            System.out.println("12. Asignar asiento desde la lista de espera");
            System.out.println("13. Salir");
            System.out.println("----------------------------- ✈ -------------------------");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número válido.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    arbol.mostrarVuelos();
                    break;

                case 2:
                    System.out.print("Ingrese el número de vuelo a buscar: ");
                    String numVueloBuscar = scanner.nextLine();
                    arbol.buscarVuelo(numVueloBuscar);
                    break;

                case 3:
                    System.out.print("Ingrese el número de vuelo en el que desea reservar: ");
                    String numVueloReservar = scanner.nextLine();
                    Vuelo vueloReservar = arbol.buscarVuelo(numVueloReservar);
                    if (vueloReservar != null) {
                        vueloReservar.reservarAsiento(scanner);
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el número de documento del pasajero: ");
                    int documentoBoleto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el número de vuelo: ");
                    String numVueloBoleto = scanner.nextLine();

                    Vuelo vueloBoleto = arbol.buscarVuelo(numVueloBoleto);
                    if (vueloBoleto != null) {
                        Pasajero pasajero = vueloBoleto.listaPasajeros.buscarPasajero(documentoBoleto);
                        if (pasajero != null) {
                            pasajero.mostrarBoleto();
                        } else {
                            System.out.println("No se encontró un boleto con ese documento en este vuelo.");
                        }
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el número de documento del pasajero: ");
                    int documentoEliminar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el número de vuelo: ");
                    String numVueloEliminar = scanner.nextLine();

                    Vuelo vueloEliminar = arbol.buscarVuelo(numVueloEliminar);
                    if (vueloEliminar != null) {
                        if (vueloEliminar.listaPasajeros.eliminarPasajero(documentoEliminar)) {
                            System.out.println("Boleto eliminado exitosamente.");
                            // Aquí deberías implementar la liberación del asiento
                        } else {
                            System.out.println("No se encontró un boleto con ese documento en este vuelo.");
                        }
                    }
                    break;

                case 6:
                    System.out.print("Ingrese el número de vuelo para modificar reserva: ");
                    String numVueloModificar = scanner.nextLine();
                    Vuelo vueloModificar = arbol.buscarVuelo(numVueloModificar);
                    if (vueloModificar != null) {
                        vueloModificar.modificarReserva(scanner);
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el número de vuelo para ver asientos: ");
                    String numVueloAsientos = scanner.nextLine();
                    Vuelo vueloAsientos = arbol.buscarVuelo(numVueloAsientos);
                    if (vueloAsientos != null) {
                        vueloAsientos.mostrarAsientos();
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 8:
                    System.out.print("Ingrese el número de vuelo para ver pasajeros: ");
                    String numVueloPasajeros = scanner.nextLine();
                    Vuelo vueloPasajeros = arbol.buscarVuelo(numVueloPasajeros);
                    if (vueloPasajeros != null) {
                        vueloPasajeros.listaPasajeros.mostrarPasajeros();
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 9:
                    System.out.print("Ingrese el número de vuelo para ver historial: ");
                    String numVueloHistorial = scanner.nextLine();
                    Vuelo vueloHistorial = arbol.buscarVuelo(numVueloHistorial);
                    if (vueloHistorial != null) {
                        vueloHistorial.historialReservas.mostrarHistorial();
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 10:
                    System.out.print("Ingrese el número de vuelo para ver lista de espera: ");
                    String numVueloEspera = scanner.nextLine();
                    Vuelo vueloEspera = arbol.buscarVuelo(numVueloEspera);
                    if (vueloEspera != null) {
                        vueloEspera.mostrarListaEspera();
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 11:
                    System.out.print("Ingrese el número de vuelo donde agregará a la lista de espera: ");
                    String numVueloAgregarEspera = scanner.nextLine();
                    Vuelo vueloAgregarEspera = arbol.buscarVuelo(numVueloAgregarEspera);
                    if (vueloAgregarEspera != null) {
                        System.out.print("Ingrese su nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese su documento: ");
                        int documento = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Seleccione método de pago (Tarjeta/En línea/Efectivo): ");
                        String metodoPago = scanner.nextLine();

                        vueloAgregarEspera.agregarAListaEspera(nombre, documento, metodoPago);
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 12:
                    System.out.print("Ingrese el número de vuelo para asignar un asiento desde la lista de espera: ");
                    String numVueloAsignar = scanner.nextLine();
                    Vuelo vueloAsignar = arbol.buscarVuelo(numVueloAsignar);
                    if (vueloAsignar != null) {
                        vueloAsignar.asignarDesdeListaEspera();
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 13:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 13);

        scanner.close();
    }
}

class PrecioVuelos {
    // Precios base por ruta
    private static final Map<String, Double> PRECIOS_BASE = Map.of(
            "Bogota-Medellin", 250000.00,
            "Bogota-Cali", 230000.00,
            "Medellin-Cartagena", 300000.00,
            "Cartagena-Bogota", 280000.00,
            "Cali-Barranquilla", 260000.00
    );

    private static final double ECONOMICA = 1.0;
    private static final double BUSINESS = 2.5;
    private static final double PRIMERA = 3.5;

    public static double obtenerPrecio(String origen, String destino, String clase) {
        String ruta = origen + "-" + destino;
        double precioBase = PRECIOS_BASE.getOrDefault(ruta, 200000.00);

        switch(clase.toUpperCase()) {
            case "BUSINESS":
                return precioBase * BUSINESS;
            case "PRIMERA":
                return precioBase * PRIMERA;
            default: // ECONÓMICA
                return precioBase * ECONOMICA;
        }
    }
}

class HistorialReservas {
    private Stack<String> historial;

    public HistorialReservas() {
        this.historial = new Stack<>();
    }

    public void agregarModificacion(String cambio) {
        historial.push(cambio);
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay modificaciones registradas.");
            return;
        }
        System.out.println("\n=== Historial de modificaciones (" + historial.size() + " registros) ===");
        for (String cambio : historial) {
            System.out.println("ᯓ ✈ " + cambio);
        }
    }
}

class ColaEspera {
    private Queue<Pasajero> colaEspera;

    public ColaEspera() {
        this.colaEspera = new LinkedList<>();
    }

    public boolean estaVacia() {
        return colaEspera.isEmpty();
    }

    public void agregarAEspera(Pasajero pasajero) {
        colaEspera.add(pasajero);
        System.out.println("Pasajero agregado a la lista de espera: " + pasajero.nombre);
    }

    public Pasajero removerDeEspera() {
        return colaEspera.poll();
    }

    public void mostrarListaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay pasajeros en la lista de espera.");
            return;
        }
        System.out.println("Lista de espera:");
        for (Pasajero pasajero : colaEspera) {
            System.out.println("Pasajero: " + pasajero.nombre + " | Documento: " + pasajero.documento + " | Pago: " + pasajero.metodoPago);
        }
    }
}

class Pasajero {
    String nombre;
    int documento;
    String metodoPago;
    String numVuelo;
    String asiento;
    String clase;  // Nueva propiedad para la clase
    Pasajero siguiente;

    public Pasajero(String nombre, int documento, String metodoPago,
                    String numVuelo, String asiento, String clase) {
        this.nombre = nombre;
        this.documento = documento;
        this.metodoPago = metodoPago;
        this.numVuelo = numVuelo;
        this.asiento = asiento;
        this.clase = clase;
        this.siguiente = null;
    }

    public void mostrarBoleto() {
        System.out.println("\n-------- ✈ BOLETO DE VUELO ✈ -----------");
        System.out.println("Vuelo: " + numVuelo);
        System.out.println("Pasajero: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Clase: " + clase);
        System.out.println("Asiento: " + asiento);
        System.out.println("Método de pago: " + metodoPago);
        System.out.println("-----------------------------------------");
    }
}

class ListaPasajeros {
    Pasajero cabeza;

    public void agregarPasajero(Pasajero pasajero) {
        if (cabeza == null) {
            cabeza = pasajero;
        } else {
            Pasajero temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = pasajero;
        }
    }

    public void mostrarPasajeros() {
        if (cabeza == null) {
            System.out.println("No hay pasajeros en este vuelo.");
            return;
        }
        Pasajero temp = cabeza;
        while (temp != null) {
            System.out.println("Pasajero: " + temp.nombre + " | Documento: " + temp.documento + " | Pago: " + temp.metodoPago);
            temp = temp.siguiente;
        }
    }
    public Pasajero buscarPasajero(int documento) {
        Pasajero temp = cabeza;
        while (temp != null) {
            if (temp.documento == documento) {
                return temp;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    public boolean eliminarPasajero(int documento) {
        if (cabeza == null) return false;

        if (cabeza.documento == documento) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Pasajero actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.documento == documento) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}
class Vuelo {
    // Atributos básicos
    String numVuelo;
    String origen;
    String destino;
    Avion avionAsignado;
    ListaPasajeros listaPasajeros;
    boolean[][] asientos;
    HistorialReservas historialReservas = new HistorialReservas();
    ColaEspera colaEspera;

    // Configuración de clases
    private static final int FILAS_ECONOMICA = 7;  // Filas 1-7
    private static final int FILAS_BUSINESS = 2;   // Filas 8-9
    private static final int FILAS_PRIMERA = 1;    // Fila 10

    // Constructor
    public Vuelo(String numVuelo, String origen, String destino, Avion avionAsignado) {
        this.numVuelo = numVuelo;
        this.origen = origen;
        this.destino = destino;
        this.avionAsignado = avionAsignado;
        this.listaPasajeros = new ListaPasajeros();
        this.asientos = new boolean[10][6]; // 10 filas x 6 columnas
        this.colaEspera = new ColaEspera();
        this.historialReservas = new HistorialReservas();
        this.historialReservas.agregarModificacion("Vuelo creado: " + numVuelo + " | Origen: " + origen + " | Destino: " + destino);
    }

    public void reservarAsiento(Scanner scanner) {
        // Selección de clase
        System.out.println("\nSeleccione la clase de viaje:");
        System.out.println("1. Económica ($" + PrecioVuelos.obtenerPrecio(origen, destino, "ECONOMICA") + ")");
        System.out.println("2. Business ($" + PrecioVuelos.obtenerPrecio(origen, destino, "BUSINESS") + ")");
        System.out.println("3. Primera Clase ($" + PrecioVuelos.obtenerPrecio(origen, destino, "PRIMERA") + ")");
        System.out.print("Opción: ");
        int opcionClase = scanner.nextInt();
        scanner.nextLine();

        String clase;
        switch(opcionClase) {
            case 2:
                clase = "BUSINESS";
                break;
            case 3:
                clase = "PRIMERA";
                break;
            default:
                clase = "ECONOMICA";
        }

        double precio = PrecioVuelos.obtenerPrecio(origen, destino, clase);

        System.out.println("\nAsientos disponibles en clase " + clase + ":");
        mostrarAsientosClase(clase);

        // Selección de asiento
        System.out.print("\nIngrese el número de fila: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese la letra de la columna (A-F): ");
        char columnaChar = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();

        int filaIndex = fila - 1;
        int columnaIndex = columnaChar - 'A';
        String asiento = fila + String.valueOf(columnaChar);

        if (!validarAsientoClase(fila, clase)) {
            System.out.println("Error: Este asiento no corresponde a la clase seleccionada.");
            return;
        }

        if (filaIndex < 0 || filaIndex >= 10 || columnaIndex < 0 || columnaIndex >= 6) {
            System.out.println("Asiento no válido.");
            return;
        }

        if (asientos[filaIndex][columnaIndex]) {
            System.out.println("Asiento ya ocupado.");
            return;
        }

        // pago
        System.out.println("Precio total: $" + precio);
        System.out.print("Ingrese el monto a pagar: ");
        double pago = scanner.nextDouble();
        scanner.nextLine();

        if (pago < precio) {
            System.out.println("Fondos insuficientes. Reserva cancelada.");
            return;
        }

        // Datos pasajero
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su documento: ");
        int documento = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Método de pago (Tarjeta/En Linea/Efectivo): ");
        String metodoPago = scanner.nextLine();

        // Crear y guardar reserva
        Pasajero pasajero = new Pasajero(nombre, documento, metodoPago, numVuelo, asiento, clase);
        listaPasajeros.agregarPasajero(pasajero);
        asientos[filaIndex][columnaIndex] = true;

        historialReservas.agregarModificacion("Nueva reserva: " + nombre + " (Doc: " + documento + ") en asiento " + asiento + " (Clase: " + clase + ")");

        System.out.println("\nReserva exitosa!");
        System.out.printf("Cambio: $%.2f%n", pago - precio); //formatea num con 2 decimales
        pasajero.mostrarBoleto();
    }

    private void mostrarAsientosClase(String clase) {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F'};
        int inicioFila = 0, finFila = 9;

        switch(clase) {
            case "BUSINESS":
                inicioFila = 7; // Filas 8-9
                finFila = 8;
                break;
            case "PRIMERA":
                inicioFila = 9; // Fila 10
                finFila = 9;
                break;
            default: // ECONOMICA
                inicioFila = 0; // Filas 1-7
                finFila = 6;
        }

        for (int i = inicioFila; i <= finFila; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((i + 1) + "" + letras[j] + (asientos[i][j] ? "[X] " : "[O] "));
            }
            System.out.println();
        }
    }

    private boolean validarAsientoClase(int fila, String clase) {
        switch(clase) {
            case "BUSINESS":
                return fila >= 8 && fila <= 9;
            case "PRIMERA":
                return fila == 10;
            default: // ECONOMICA
                return fila >= 1 && fila <= 7;
        }
    }

    public void modificarReserva(Scanner scanner) {
        System.out.print("Ingrese documento del pasajero: ");
        int documento = scanner.nextInt();
        scanner.nextLine();

        Pasajero pasajero = listaPasajeros.buscarPasajero(documento);
        if (pasajero == null) {
            System.out.println("No se encontró la reserva.");
            return;
        }

        System.out.println("\nDatos actuales:");
        pasajero.mostrarBoleto();

        System.out.println("\nOpciones de modificación:");
        System.out.println("1. Cambiar datos personales");
        System.out.println("2. Cambiar clase de viaje");
        System.out.println("3. Cambiar asiento");
        System.out.println("4. Cancelar modificación");
        System.out.print("Seleccione: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch(opcion) {
            case 1:
                modificarDatosPersonales(scanner, pasajero);
                break;
            case 2:
                cambiarClase(scanner, pasajero);
                break;
            case 3:
                cambiarAsiento(scanner, pasajero);
                break;
            case 4:
                System.out.println("Modificación cancelada.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void modificarDatosPersonales(Scanner scanner, Pasajero pasajero) {
        System.out.print("Nuevo nombre (" + pasajero.nombre + "): ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nuevo método de pago (" + pasajero.metodoPago + "): ");
        String nuevoMetodo = scanner.nextLine();

        historialReservas.agregarModificacion("Modificación datos: " + pasajero.nombre + " a " + nuevoNombre);
        pasajero.nombre = nuevoNombre;
        pasajero.metodoPago = nuevoMetodo;

        System.out.println("Datos actualizados correctamente.");
    }

    private void cambiarClase(Scanner scanner, Pasajero pasajero) {
        System.out.println("\nClases disponibles:");
        System.out.println("1. Económica ($" + PrecioVuelos.obtenerPrecio(origen, destino, "ECONOMICA") + ")");
        System.out.println("2. Business ($" + PrecioVuelos.obtenerPrecio(origen, destino, "BUSINESS") + ")");
        System.out.println("3. Primera Clase ($" + PrecioVuelos.obtenerPrecio(origen, destino, "PRIMERA") + ")");
        System.out.print("Seleccione nueva clase: ");
        int nuevaClaseOp = scanner.nextInt();
        scanner.nextLine();

        String nuevaClase;
        switch(nuevaClaseOp) {
            case 2:
                nuevaClase = "BUSINESS";
                break;
            case 3:
                nuevaClase = "PRIMERA";
                break;
            default:
                nuevaClase = "ECONOMICA";
        }

        if (pasajero.clase.equals(nuevaClase)) {
            System.out.println("El pasajero ya tiene esta clase asignada.");
            return;
        }

        double precioActual = PrecioVuelos.obtenerPrecio(origen, destino, pasajero.clase);
        double nuevoPrecio = PrecioVuelos.obtenerPrecio(origen, destino, nuevaClase);
        double diferencia = nuevoPrecio - precioActual;

        // Pago/reembolso
        if (diferencia > 0) {
            System.out.printf("Debe pagar adicional: $%.2f%n", diferencia);
            System.out.print("Ingrese el monto: ");
            double pago = scanner.nextDouble();
            scanner.nextLine();

            if (pago < diferencia) {
                System.out.println("Pago insuficiente. Cambio cancelado.");
                return;
            }
            System.out.printf("Cambio: $%.2f%n", pago - diferencia);
        } else if (diferencia < 0) {
            System.out.printf("Se le reembolsará: $%.2f%n", -diferencia);
        }

        // ✅ Registrar el cambio en el historial ANTES de modificar
        historialReservas.agregarModificacion(
                "Cambio de clase: " + pasajero.clase + " → " + nuevaClase +
                        " | Asiento anterior: " + pasajero.asiento +
                        " | Pasajero: " + pasajero.nombre
        );

        // Liberar asiento anterior si existe
        if (pasajero.asiento != null && !pasajero.asiento.equals("LISTA ESPERA")) {
            liberarAsiento(pasajero.asiento);
        }

        pasajero.clase = nuevaClase;
        pasajero.asiento = "POR ASIGNAR";

        System.out.println("Clase actualizada a " + nuevaClase + ". Ahora seleccione un nuevo asiento.");
        cambiarAsiento(scanner, pasajero);
    }

    private void cambiarAsiento(Scanner scanner, Pasajero pasajero) {
        System.out.println("\nAsientos disponibles en clase " + pasajero.clase + ":");
        mostrarAsientosClase(pasajero.clase);

        System.out.print("\nIngrese número de fila: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese letra de columna (A-F): ");
        char columnaChar = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();

        if (!validarAsientoClase(fila, pasajero.clase)) {
            System.out.println("Error: Asiento no corresponde a la clase del pasajero.");
            return;
        }

        int filaIndex = fila - 1;
        int columnaIndex = columnaChar - 'A';

        if (filaIndex < 0 || filaIndex >= 10 || columnaIndex < 0 || columnaIndex >= 6) {
            System.out.println("Asiento no válido.");
            return;
        }

        if (asientos[filaIndex][columnaIndex]) {
            System.out.println("Asiento ya ocupado.");
            return;
        }
        historialReservas.agregarModificacion( "Cambio de asiento: " + pasajero.asiento + " → " + (fila + String.valueOf(columnaChar)) +
                                                " | Pasajero: " + pasajero.nombre);

        // Liberar asiento anterior (si existe)
        if (pasajero.asiento != null && !pasajero.asiento.equals("POR ASIGNAR")) {
            liberarAsiento(pasajero.asiento);
        }

        // Liberar asiento anterior
        if (pasajero.asiento != null && !pasajero.asiento.equals("POR ASIGNAR") && !pasajero.asiento.equals("LISTA ESPERA")) {
            liberarAsiento(pasajero.asiento);
        }

        // Asignar nuevo asiento
        asientos[filaIndex][columnaIndex] = true;
        pasajero.asiento = fila + String.valueOf(columnaChar);

        System.out.println("Asiento actualizado correctamente.");
    }

    private void liberarAsiento(String asiento) {
        int fila = Integer.parseInt(asiento.substring(0, asiento.length() - 1)) - 1;
        char columnaChar = asiento.charAt(asiento.length() - 1);
        int columna = columnaChar - 'A';

        if (fila >= 0 && fila < 10 && columna >= 0 && columna < 6) {
            asientos[fila][columna] = false;
        }
    }

    public void mostrarAsientos() {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F'};

        System.out.println("\n--- PRIMERA CLASE (Fila 10) ---");
        for (int j = 0; j < 6; j++) {
            System.out.print("10" + letras[j] + (asientos[9][j] ? "[X] " : "[O] "));
        }

        System.out.println("\n\n--- BUSINESS (Filas 8-9) ---");
        for (int i = 7; i <= 8; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((i + 1) + "" + letras[j] + (asientos[i][j] ? "[X] " : "[O] "));
            }
            System.out.println();
        }

        System.out.println("\n--- ECONÓMICA (Filas 1-7) ---");
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((i + 1) + "" + letras[j] + (asientos[i][j] ? "[X] " : "[O] "));
            }
            System.out.println();
        }
    }

    public void mostrarListaEspera() {
        colaEspera.mostrarListaEspera();
    }

    public void agregarAListaEspera(String nombre, int documento, String metodoPago) {
        Pasajero pasajero = new Pasajero(nombre, documento, metodoPago, this.numVuelo, "LISTA ESPERA", "ECONOMICA");
        colaEspera.agregarAEspera(pasajero);
    }

    public void asignarDesdeListaEspera() {
        if (colaEspera.estaVacia()) {
            System.out.println("No hay pasajeros en la lista de espera para asignar un asiento.");
            return;
        }
        Pasajero pasajero = colaEspera.removerDeEspera();
        if (pasajero != null) {
            System.out.println("Pasajero " + pasajero.nombre + " ha sido asignado a un asiento.");
        }
    }
}


class Avion {
    String modelo;
    int filas;
    int columnas;

    public Avion(String modelo, int filas, int columnas) {
        this.modelo = modelo;
        this.filas = filas;
        this.columnas = columnas;
    }
}

class NodoVuelo {
    Vuelo vuelo;
    NodoVuelo izquierdo, derecho;

    public NodoVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolVuelos {
    NodoVuelo raiz;

    public void insertarVuelo(String numVuelo, String origen, String destino, Avion avionAsignado) {
        Vuelo nuevoVuelo = new Vuelo(numVuelo, origen, destino, avionAsignado);
        if (raiz == null) {
            raiz = new NodoVuelo(nuevoVuelo);
        } else {
            insertarRecursivo(raiz, nuevoVuelo);
        }
    }

    private void insertarRecursivo(NodoVuelo nodo, Vuelo vuelo) {
        if (vuelo.numVuelo.compareTo(nodo.vuelo.numVuelo) < 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoVuelo(vuelo);
            } else {
                insertarRecursivo(nodo.izquierdo, vuelo);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoVuelo(vuelo);
            } else {
                insertarRecursivo(nodo.derecho, vuelo);
            }
        }
    }

    public Vuelo buscarVuelo(String numVuelo) {
        Vuelo encontrado = buscarRecursivo(raiz, numVuelo);
        if (encontrado != null) {
            System.out.println("\nVuelo encontrado:");
            System.out.println("Número: " + encontrado.numVuelo);
            System.out.println("Origen: " + encontrado.origen);
            System.out.println("Destino: " + encontrado.destino);
            System.out.println("Avión: " + encontrado.avionAsignado.modelo);
            System.out.println("Precios:");
            System.out.println("- Económica: $" + PrecioVuelos.obtenerPrecio(encontrado.origen, encontrado.destino, "ECONOMICA"));
            System.out.println("- Business: $" + PrecioVuelos.obtenerPrecio(encontrado.origen, encontrado.destino, "BUSINESS"));
            System.out.println("- Primera Clase: $" + PrecioVuelos.obtenerPrecio(encontrado.origen, encontrado.destino, "PRIMERA") + "\n");
        } else {
            System.out.println("Vuelo no encontrado.");
        }
        return encontrado;
    }

    private Vuelo buscarRecursivo(NodoVuelo nodo, String numVuelo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.vuelo.numVuelo.equals(numVuelo)) {
            return nodo.vuelo;
        }
        return numVuelo.compareTo(nodo.vuelo.numVuelo) < 0
                ? buscarRecursivo(nodo.izquierdo, numVuelo)
                : buscarRecursivo(nodo.derecho, numVuelo);
    }

    private Vuelo minValue(NodoVuelo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo.vuelo;
    }

    public void mostrarVuelos() {
        inorden(raiz);
    }

    private void inorden(NodoVuelo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println("Vuelo: " + nodo.vuelo.numVuelo +
                    " | Origen: " + nodo.vuelo.origen +
                    " | Destino: " + nodo.vuelo.destino +
                    " | Avión: " + nodo.vuelo.avionAsignado.modelo);
            System.out.println("Precios: Económica $" + PrecioVuelos.obtenerPrecio(nodo.vuelo.origen, nodo.vuelo.destino, "ECONOMICA") +
                    " | Business $" + PrecioVuelos.obtenerPrecio(nodo.vuelo.origen, nodo.vuelo.destino, "BUSINESS") +
                    " | Primera $" + PrecioVuelos.obtenerPrecio(nodo.vuelo.origen, nodo.vuelo.destino, "PRIMERA"));
            System.out.println("------------------ ✈ -------------------");
            inorden(nodo.derecho);
        }
    }
}