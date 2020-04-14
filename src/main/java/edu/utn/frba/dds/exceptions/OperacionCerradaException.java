package edu.utn.frba.dds.exceptions;

public class OperacionCerradaException extends RuntimeException {
    public OperacionCerradaException() {
        super("No se puede realizar la acción requerida, la operación está cerrrada");
    }
}
