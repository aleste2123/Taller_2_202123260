package Modulo;

public class ProductoMenu {
    private String nombre;
    private int precioBase;

    public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public int getPrecio() {
		return precioBase;
	}
	
	public String getNombre() {
		return nombre;
	}
	
    public String generarTextoFactura() {
        return String.format("%s - $%d", nombre, precioBase);
    }
}
