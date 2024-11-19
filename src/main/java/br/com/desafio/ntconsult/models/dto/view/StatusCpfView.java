package br.com.desafio.ntconsult.models.dto.view;

import br.com.desafio.ntconsult.models.enums.StatusCpf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusCpfView {
    private StatusCpf status;
}
