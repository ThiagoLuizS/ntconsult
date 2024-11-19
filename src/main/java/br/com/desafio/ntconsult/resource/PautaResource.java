package br.com.desafio.ntconsult.resource;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface PautaResource {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    PautaView save(@RequestBody @Valid PautaForm pautaForm);
}
