package br.com.desafio.ntconsult.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pauta")
public class Pauta {

    @Id
    @Column(name = "id_pauta")
    @SequenceGenerator(name = "seq_id_pauta", sequenceName = "seq_id_pauta", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_pauta")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiracao_sessao")
    private Date expiracaoSessao;

    @Column(name = "calculado")
    private boolean calculado;
}
