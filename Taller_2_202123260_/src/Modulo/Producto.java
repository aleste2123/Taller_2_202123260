package Modulo;

public class Producto {
    private String nombre;
    private int precio;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String generarTextoFactura() {
        return String.format("%s - $%d\n", nombre, precio);
    }
}