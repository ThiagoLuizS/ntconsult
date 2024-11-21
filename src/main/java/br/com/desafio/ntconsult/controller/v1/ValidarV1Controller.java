package br.com.desafio.ntconsult.controller.v1;

import br.com.desafio.ntconsult.models.dto.view.StatusCpfView;
import br.com.desafio.ntconsult.resource.ValidarResource;
import br.com.desafio.ntconsult.service.v1.ValidarServiceV1Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/validadores")
public class ValidarV1Controller implements ValidarResource {

    private final ValidarServiceV1Impl validarService;

    @Autowired
    public ValidarV1Controller(@Qualifier("v1") ValidarServiceV1Impl validarService) {
        this.validarService = validarService;
    }

    @Override
    public StatusCpfView validarCpf(String cpf) {
        return validarService.validarCpf(cpf);
    }
}
