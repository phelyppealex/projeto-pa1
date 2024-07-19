package br.ufrn.projetosaplicados.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.service.AgendamentoService;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private AgendamentoService service;
    private ModelMapper mapper;

    public AgendamentoController(AgendamentoService service, ModelMapper mapper){
        this.mapper = mapper;
        this.service = service;
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
        Agendamento agendamento = Agendamento.DtoRequest.convertToEntity(a, mapper);
        this.service.save(agendamento);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping("/{id}")
    public void updateHorario(@RequestBody Agendamento a){
        this.service.update(a);
    }
    
}
