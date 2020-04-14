package edu.utn.frba.dds.exceptions;

public class PrecioNegativoException extends RuntimeException{
    public PrecioNegativoException() {
        super("El precio de un producto/servicio deben ser mayor que 0");
    }
}
