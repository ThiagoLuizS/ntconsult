package br.com.desafio.ntconsult.controller;

import br.com.desafio.ntconsult.models.dto.view.StatusCpfView;
import br.com.desafio.ntconsult.resource.ValidarResource;
import br.com.desafio.ntconsult.service.ValidarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/validadores")
public class ValidarController implements ValidarResource {

    private final ValidarService validarService;

    @Override
    public StatusCpfView validarCpf(String cpf) {
        return validarService.validarCpf(cpf);
    }
}
