package br.com.desafio.ntconsult.models.dto.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaView {
    private String nome;
    private String descricao;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expiracaoSessao;
}
