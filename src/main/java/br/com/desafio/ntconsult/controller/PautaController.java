package br.com.desafio.ntconsult.controller;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.resource.PautaResource;
import br.com.desafio.ntconsult.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pautas")
public class PautaController implements PautaResource {

    private final PautaService service;

    @Override
    public PautaView save(PautaForm pautaForm) {
        return service.save(pautaForm);
    }

    @Override
    public Page<PautaView> findAll(Pageable pageable, String nome) {
        return service.findByFilter(pageable, nome);
    }
}
