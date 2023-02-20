package Modulo;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Restaurante {
    public static  int IVA = (int) 0.19;
	private Pedido pedidoEnCurso;
    private ArrayList<Producto> menuBase;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Combo> combos;
    

    public Restaurante() {
        this.pedidoEnCurso = null;
        this.menuBase = new ArrayList<Producto>();
        this.ingredientes = new ArrayList<Ingrediente>();
    }

    public void iniciarPedido(String nombreCliente, String direccionCliente) {
        this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
    }

    public void cerrarYGuardarPedido() {
        if (this.pedidoEnCurso != null) {
            this.pedidoEnCurso.guardarFactura(new File("facturas/factura-" + this.pedidoEnCurso.getIdPedido() + ".txt"));
            this.pedidoEnCurso = null;
        }
    }

    public Pedido getPedidoEnCurso() {
        return this.pedidoEnCurso;
    }

    public ArrayList<Producto> getMenuBase() {
        return this.menuBase;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
        cargarIngredientes(archivoIngredientes);
        cargarMenu(archivoMenu);
        cargarCombos(archivoCombos);
    }
    
   

    private void cargarIngredientes(File archivoIngredientes) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoIngredientes));
            String line = reader.readLine(); // omitir la primera línea (encabezado)
        
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                String nombre = fields[0].trim();
                int stock = Integer.parseInt(fields[1].trim());
                Ingrediente ingrediente = new Ingrediente(nombre, stock);
                this.ingredientes.add(ingrediente);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cargar archivo de ingredientes: " + e.getMessage());
        }
    }

    

    private void cargarMenu(File archivoMenu) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoMenu));
            String line = reader.readLine(); // omitir la primera línea (encabezado)
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                String nombre = fields[0].trim();
                int precio = Integer.parseInt(fields[1].trim());
                ArrayList<String> ingredientes = new ArrayList<String>();
                for (int i = 2; i < fields.length; i++) {
                    ingredientes.add(fields[i].trim());
                }
                Producto producto = new Producto(nombre, precio);
                this.menuBase.add(producto);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cargar archivo de menú: " + e.getMessage());
        }
    }

    private Producto buscarProductoPorNombre(String nombreProducto) {
        for (Producto producto : this.menuBase) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null; // si no se encuentra el producto
    }

    private void cargarCombos(File archivoCombos) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoCombos));
            String line = reader.readLine(); // omitir la primera línea (encabezado)
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                String nombreCombo = fields[0].trim();
                
                double descuento = Double.parseDouble(fields[1].trim());
                ArrayList<Producto> itemsCombo = new ArrayList<Producto>();
                for (int i = 2; i < fields.length; i++) {
                    String nombreProducto = fields[i].trim();
                    Producto producto = buscarProductoPorNombre(nombreProducto);
                    
                    if (producto != null) {
                        itemsCombo.add(producto);
                      
                    }
                }
                Combo combo = new Combo(nombreCombo, descuento);
                this.combos.add(combo);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cargar archivo de combos: " + e.getMessage());
        }
    }

    } 
