package br.com.desafio.ntconsult.models.dto.form;

import br.com.desafio.ntconsult.annotation.ValueOfEnum;
import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoForm {
    @ValueOfEnum(enumClass = OpcaoVoto.class, message = "Opção inválida, informe SIM/NAO")
    private String opcao;
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF precisa ter 11 caracteres")
    private String cpf;
    @NotBlank(message = "O nome da pauta deve ser válido e obrigatório")
    private String nomePauta;
}
