package Consola;





import Modulo.Restaurante;
import Modulo.Combo;
import Modulo.Pedido;
import Modulo.Producto;
import Modulo.ProductoMenu;
import Modulo.Ingrediente;
import Modulo.ProductoAjustado; 


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {

    private static Restaurante restaurante;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Creamos el objeto Restaurante y cargamos la información desde los archivos
        restaurante = new Restaurante();
        cargarInformacionRestaurante();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        System.out.println("Bienvenido al Restaurante");
        System.out.println("1. Iniciar pedido");
        System.out.println("2. Cerrar pedido y guardar factura");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        int opcionSeleccionada = scanner.nextInt();
        scanner.nextLine(); // limpiamos el buffer del scanner
        
        ejecutarOpcion(opcionSeleccionada);
    }

    private static void ejecutarOpcion(int opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case 1:
                System.out.print("Ingrese el nombre del cliente: ");
                String nombreCliente = scanner.nextLine();
                String direccionCliente = scanner.nextLine();
                restaurante.iniciarPedido(nombreCliente, direccionCliente);
                System.out.println("Pedido iniciado para " + nombreCliente + " en " + direccionCliente);
                mostrarMenu();
                break;
            case 2:
                Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
                if (pedidoEnCurso == null) {
                    System.out.println("No hay pedido en curso");
                } else {
                    System.out.print("Ingrese el nombre del archivo para guardar la factura: ");
                    String nombreArchivo = scanner.nextLine();
                    File archivo = new File(nombreArchivo);
                    pedidoEnCurso.guardarFactura(archivo);
                    System.out.println("Factura guardada en " + nombreArchivo);
                }
                mostrarMenu();
                break;
            case 3:
                System.out.println("Gracias por visitar nuestro restaurante!");
                break;
            default:
                System.out.println("Opción no válida. Seleccione otra opción.");
                mostrarMenu();
                break;
        }
    }
    
    private static void cargarInformacionRestaurante() {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");
        
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
    }
}
