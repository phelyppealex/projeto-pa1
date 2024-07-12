package br.ufrn.projetosaplicados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}