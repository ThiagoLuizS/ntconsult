package br.com.desafio.ntconsult.resource;

import br.com.desafio.ntconsult.models.dto.view.StatusCpfView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface ValidarResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{cpf}")
    StatusCpfView validarCpf(@PathVariable String cpf);
}
