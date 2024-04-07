package br.ufrn.projetosaplicados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int dia;
    private int mes;
    private int ano;
}
