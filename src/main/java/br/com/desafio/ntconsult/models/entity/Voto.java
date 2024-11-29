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
@Table(name = "tb_voto")
public class Voto {

    @Id
    @Column(name = "id_voto")
    @SequenceGenerator(name = "seq_id_voto", sequenceName = "seq_id_voto", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_voto")
    private Long id;

    @Column(name = "opcao", nullable = false)
    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

}
