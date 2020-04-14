package edu.utn.frba.dds.exceptions;

public class CantidadNegativaException extends RuntimeException {
    public CantidadNegativaException() {
        super("Las cantidades de un producto/servicio deben ser mayor que 0");
    }
}
