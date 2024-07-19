package br.ufrn.projetosaplicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.model.Servico;

@Service
public interface ServicoRepository extends JpaRepository<Servico, String> {
    
}