package br.com.fiap.restaurant.order.core.gateway;

import java.util.UUID;

public interface AuthenticatedUserGateway {

    UUID getAuthenticatedUserId();
}
