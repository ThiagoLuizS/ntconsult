package br.com.desafio.ntconsult.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAUTA")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @JsonManagedReference
    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ABERTO_ATE")
    private Date abertoAte;

}
