package Modulo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



import java.util.List;

public class Pedido {
    private static int ID_PEDIDO = 1;

    private int numeroPedido;
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private List<ProductoAjustado> productos;

    public Pedido(String nombreCliente, String direccionCliente) {
        this.numeroPedido = ID_PEDIDO;
        this.idPedido = ID_PEDIDO;
        ID_PEDIDO++;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.productos = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void agregarProducto(ProductoAjustado nuevoProducto) {
        productos.add(nuevoProducto);
    }

    public int getPrecioNetoPedido() {
        int precioNeto = 0;
        for (ProductoAjustado producto : productos) {
            precioNeto += producto.getPrecio();
        }
        return precioNeto;
    }

    public int getPrecioTotalPedido() {
        int precioTotal = getPrecioNetoPedido();
        int precioIVA = (int) Math.round(precioTotal * Restaurante.IVA);
        precioTotal += precioIVA;
        return precioTotal;
    }

    public int getPrecioIvaPedido() {
        int precioNeto = getPrecioNetoPedido();
        int precioIVA = (int) Math.round(precioNeto * Restaurante.IVA);
        return precioIVA;
    }

    public void guardarFactura1(File archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir información del pedido
            writer.write("Pedido #" + this.numeroPedido + "\n");
            writer.write("Nombre del cliente: " + this.nombreCliente + "\n");
            writer.write("Dirección del cliente: " + this.direccionCliente + "\n\n");

            // Escribir información de los productos del pedido
            writer.write("Productos:\n");
            for (ProductoAjustado producto : productos) {
                writer.write(producto.generarTextoFactura() + "\n");
            }

            // Escribir información de los precios
            writer.write("\nPrecio neto: $" + getPrecioNetoPedido() + "\n");
            writer.write("IVA (" + (Restaurante.IVA * 100) + "%): $" + getPrecioIvaPedido() + "\n");
            writer.write("Precio total: $" + getPrecioTotalPedido() + "\n");
        } catch (IOException e) {
            System.err.println("Error al guardar la factura en el archivo: " + e.getMessage());
        }
    }

    public String generarTextoFactura() {
        StringBuilder sb = new StringBuilder();

        // Agregar información del pedido
        sb.append("Pedido #").append(this.numeroPedido).append("\n");
        sb.append("Nombre del cliente: ").append(this.nombreCliente).append("\n");
        sb.append("Dirección del cliente: ").append(this.direccionCliente).append("\n");

        // Agregar información de los productos del pedido
        sb.append("\nProductos:\n");
        for (ProductoAjustado producto : productos) {
            sb.append(producto.generarTextoFactura()).append("\n");
        }

        // Agregar información de los precios
        sb.append("\nPrecio neto: $").append(getPrecioNetoPedido()).append("\n");
        sb.append("IVA (").append((int)(Restaurante.IVA*100)).append("%): $").append(getPrecioIvaPedido()).append("\n");
        sb.append("Precio total: $").append(getPrecioTotalPedido()).append("\n");

        return sb.toString();
        }

    public void guardarFactura(File archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir información de la factura
            writer.write(generarTextoFactura());
        } catch (IOException e) {
            System.err.println("Error al guardar la factura en el archivo: " + e.getMessage());
        }
    }
}
 

