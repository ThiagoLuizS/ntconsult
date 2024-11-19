package br.com.desafio.ntconsult.domain.entity;

import br.com.desafio.ntconsult.domain.enums.OpcaoVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VOTOS")
public class Votos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPCAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "PAUTA_ID")
    private Pauta pauta;

}
