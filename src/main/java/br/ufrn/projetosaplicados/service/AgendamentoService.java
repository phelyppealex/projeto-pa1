package br.ufrn.projetosaplicados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.infra.security.TokenService;
import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.model.DiaSemana;
import br.ufrn.projetosaplicados.model.Horario;
import br.ufrn.projetosaplicados.model.Servico;
import br.ufrn.projetosaplicados.model.Usuario;
import br.ufrn.projetosaplicados.repository.AgendamentoRepository;
import br.ufrn.projetosaplicados.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgendamentoService {
    AgendamentoRepository repository;
    private DiaService diaService;
    private HorarioService horarioService;
    private ServicoService servicoService;
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public AgendamentoService(AgendamentoRepository aRepo, DiaService diaService, HorarioService horarioService, ServicoService servicoService, UsuarioRepository usuarioRepository, TokenService tokenService){
        this.repository = aRepo;
        this.diaService = diaService;
        this.horarioService = horarioService;
        this.servicoService = servicoService;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    public void save(Agendamento a){
        this.repository.save(a);
    }

    public List<Agendamento> findByUsuario(String token){
        String email = this.tokenService.validateToken(token);
        var u = this.usuarioRepository.findByEmail(email);

        if(u.isPresent()){
            return this.repository.findByUsuario(
                (Usuario) u.get()
            );
        }else{
            throw new UsernameNotFoundException("Usuário "+ email +" não encontrado!");
        }
    }

    public void save(Agendamento.DtoRequest a){
        DiaSemana d = this.diaService.findById(a.getDia());
        Horario h = this.horarioService.findById(a.getHorario());
        Servico s = this.servicoService.findById(a.getServico());
        Usuario u;

        String email = this.tokenService.validateToken(a.getUsuario());
        Optional usuarioOp = this.usuarioRepository.findByEmail(email);

        if(usuarioOp.isPresent()){
            u = (Usuario) usuarioOp.get();
        }else{
            throw new UsernameNotFoundException("Usuário "+ email +" não encontrado!");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setDia(d);
        agendamento.setHorario(h);
        agendamento.setServico(s);
        agendamento.setUsuario(u);

        this.repository.save(agendamento);
    }

    public Agendamento findById(String id){
        Optional<Agendamento> a = this.repository.findById(id);

        if(a.isPresent()) return a.get();

        throw new EntityNotFoundException();
    }

    public List<Agendamento> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String id){
        findById(id);
        this.repository.deleteById(id);
    }

    public void delete(Agendamento a){
        this.repository.delete(a);
    }

    public void update(Agendamento a){
        var temp = findById(a.getId());
        delete(temp);
        save(a);
    }

}
