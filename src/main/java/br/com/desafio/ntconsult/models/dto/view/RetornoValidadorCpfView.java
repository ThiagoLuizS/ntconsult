package br.com.desafio.ntconsult.models.dto.view;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetornoValidadorCpfView {
    private Boolean valid;
}
