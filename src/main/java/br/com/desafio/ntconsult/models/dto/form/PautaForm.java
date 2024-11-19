package br.com.desafio.ntconsult.models.dto.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaForm {
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;
    private String descricao;
    private long tempoSessao;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date abertoAte;
}
