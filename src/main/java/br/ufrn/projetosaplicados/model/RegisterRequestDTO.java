package br.ufrn.projetosaplicados.model;

public record RegisterRequestDTO (String name, String email, String password, UserRole role) {}