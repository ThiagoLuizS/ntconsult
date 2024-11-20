package br.com.desafio.ntconsult.models.dto.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaForm {
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;
    private String descricao;
    private long duracaoSessaoMinuto;
}
