package br.ufrn.projetosaplicados.controller;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import br.ufrn.projetosaplicados.model.Horario;
import br.ufrn.projetosaplicados.service.HorarioService;

@RestController
@RequestMapping("/horario/")
public class HorarioController {

    private ModelMapper mapper;
    private HorarioService service;

    public HorarioController(ModelMapper mapper, HorarioService service){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public List<Horario.DtoResponse> findAllHorarios() {
        List<Horario.DtoResponse> horario = this.service.findAll().stream().map(
            hour -> Horario.DtoResponse.convertToDto(hour, mapper)
        ).toList();
        return horario;
    }

    @PostMapping
    public void saveHorario(@RequestBody Horario.DtoRequest horarioDto) {
        Horario horario = Horario.DtoRequest.convertToEntity(horarioDto, mapper);
        this.service.save(horario);
    }
    
    @DeleteMapping("{id}")
    public void deleteHorario(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping("{id}")
    public void updateHorario(@RequestBody Horario horario){
        this.service.update(horario);
    }
}
