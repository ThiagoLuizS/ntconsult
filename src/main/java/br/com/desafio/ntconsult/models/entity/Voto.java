package br.com.desafio.ntconsult.models.entity;

import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "VOTO")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPCAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PAUTA_ID")
    private Pauta pauta;

}
