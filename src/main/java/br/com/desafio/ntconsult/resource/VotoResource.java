package br.com.desafio.ntconsult.resource;

import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

public interface VotoResource {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    CompletableFuture<VotoView> save(@RequestBody @Valid VotoForm votoForm);
}
