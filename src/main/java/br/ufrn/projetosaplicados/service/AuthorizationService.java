package br.ufrn.projetosaplicados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(repository.findByEmail(email).isPresent())
            return repository.findByEmail(email).get();
        throw new EntityNotFoundException();
    }
}
