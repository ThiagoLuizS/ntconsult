package br.com.desafio.ntconsult.models.dto.view;

import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoView {
    private Long id;
    private OpcaoVoto opcao;
    private Long cpf;
    private PautaView pauta;
}
