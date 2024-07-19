package br.ufrn.projetosaplicados.infra.security;

import br.ufrn.projetosaplicados.model.UserRole;

public record RegisterRequestDTO (String name, String email, String password, UserRole role) {
}