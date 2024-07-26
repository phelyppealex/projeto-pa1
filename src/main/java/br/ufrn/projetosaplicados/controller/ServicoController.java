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

import br.ufrn.projetosaplicados.model.Servico;
import br.ufrn.projetosaplicados.service.ServicoService;


@RestController
@RequestMapping("/servico")
public class ServicoController {
    
    private ModelMapper mapper;
    private ServicoService service;

    public ServicoController(ModelMapper mapper, ServicoService service){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public List<Servico.DtoResponse> findAllServicos(){
        List<Servico.DtoResponse> servicos = this.service.findAll().stream().map(
            service -> Servico.DtoResponse.convertToDto(service, mapper)
        ).toList();

        return servicos;
    }

    @PostMapping
    public void saveServico(@RequestBody Servico.DtoRequest servicoDto){
        Servico servico = Servico.DtoRequest.convertToEntity(servicoDto, mapper);
        this.service.save(servico);
    }

    @DeleteMapping("/{id}")
    public void deleteServico(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping
    public void updateDia(@RequestBody Servico servico){
        this.service.update(servico);
    }
}