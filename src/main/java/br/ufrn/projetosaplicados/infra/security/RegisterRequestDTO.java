package br.ufrn.projetosaplicados.infra.security;

public record RegisterRequestDTO (String name, String email, String password) {
}