package br.com.desafio.ntconsult.resource;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface PautaResource {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    PautaView save(@RequestBody @Valid PautaForm pautaForm);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<PautaView> findAll(Pageable pageable,
                            @RequestParam(required = false) String nome);
}
