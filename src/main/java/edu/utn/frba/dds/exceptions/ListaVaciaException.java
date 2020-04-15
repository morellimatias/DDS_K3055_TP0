package edu.utn.frba.dds.exceptions;

public class ListaVaciaException extends RuntimeException{
    public ListaVaciaException() {
        super("No es posible calcular el precio de una operación vacía");
    }
}
