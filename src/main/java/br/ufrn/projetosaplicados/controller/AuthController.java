package br.ufrn.projetosaplicados.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.projetosaplicados.infra.security.LoginRequestDTO;
import br.ufrn.projetosaplicados.infra.security.RegisterRequestDTO;
import br.ufrn.projetosaplicados.infra.security.ResponseDTO;
import br.ufrn.projetosaplicados.infra.security.TokenService;
import br.ufrn.projetosaplicados.model.Usuario;
import br.ufrn.projetosaplicados.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario user = this.repository.findByEmail(
            body.email()
        ).orElseThrow(
            () -> new RuntimeException("User not found")
        );

        if( passwordEncoder.matches( body.password(), user.getPassword() )) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        if(this.repository.findByEmail(body.email()).isPresent())
            return ResponseEntity.badRequest().build();
        
        Usuario newUser = new Usuario(
            body.name(),
            body.email(),
            body.password(),
            body.role()
        );
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}