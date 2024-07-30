package br.ufrn.projetosaplicados.controller;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.model.DiaSemana;
import br.ufrn.projetosaplicados.model.Horario;
import br.ufrn.projetosaplicados.model.Servico;
import br.ufrn.projetosaplicados.service.AgendamentoService;
import br.ufrn.projetosaplicados.service.DiaService;
import br.ufrn.projetosaplicados.service.HorarioService;
import br.ufrn.projetosaplicados.service.ServicoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private AgendamentoService service;
    private DiaService diaService;
    private HorarioService horarioService;
    private ServicoService servicoService;
    private ModelMapper mapper;

    public AgendamentoController(AgendamentoService service, DiaService diaService, HorarioService horarioService, ServicoService servicoService, ModelMapper mapper){
        this.mapper = mapper;
        this.service = service;
        this.diaService = diaService;
        this.horarioService = horarioService;
        this.servicoService = servicoService;
    }

    @GetMapping()
    public List<Agendamento.DtoResponse> findAllAgendamentos() {
        List<Agendamento.DtoResponse> a = this.service.findAll().stream().map(
            agendamento -> Agendamento.DtoResponse.convertToDto(agendamento, mapper)
        ).toList();
        return a;
    }

    @PostMapping
    public void saveAgendamento(@RequestBody Agendamento.DtoRequest a) {
        DiaSemana d = this.diaService.findById(a.getDia());
        Horario h = this.horarioService.findById(a.getHorario());
        Servico s = this.servicoService.findById(a.getServico());

        System.out.println();
        
        Agendamento agendamento = new Agendamento();
        agendamento.setDia(d);
        agendamento.setHorario(h);
        agendamento.setServico(s);

        this.service.save(agendamento);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping
    public void updateHorario(@RequestBody Agendamento a){
        this.service.update(a);
    }
    
}
