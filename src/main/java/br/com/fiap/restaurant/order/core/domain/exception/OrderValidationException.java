package br.com.fiap.restaurant.order.core.domain.exception;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super(message);
    }
}
