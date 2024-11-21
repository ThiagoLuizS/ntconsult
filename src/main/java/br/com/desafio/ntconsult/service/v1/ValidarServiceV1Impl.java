package br.com.desafio.ntconsult.service.v1;

import br.com.desafio.ntconsult.models.dto.view.RetornoValidadorCpfView;
import br.com.desafio.ntconsult.models.dto.view.StatusCpfView;
import br.com.desafio.ntconsult.models.enums.StatusCpf;
import br.com.desafio.ntconsult.service.IntegracaoRestTemplateService;
import br.com.desafio.ntconsult.service.ValidarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("v1")
@RequiredArgsConstructor
public class ValidarServiceV1Impl implements ValidarService {

    private final IntegracaoRestTemplateService integracaoRestTemplateService;

    @Override
    public StatusCpfView validarCpf(String cpf) {
        RetornoValidadorCpfView retorno = integracaoRestTemplateService.validarCpf(cpf);

        StatusCpf status = switch (retorno.getValid()) {
            case true -> StatusCpf.ABLE_TO_VOTE;
            case false -> StatusCpf.UNABLE_TO_VOTE;
        };

        return StatusCpfView.builder().status(status).build();
    }
}
