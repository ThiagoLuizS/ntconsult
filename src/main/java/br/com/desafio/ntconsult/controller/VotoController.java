package br.com.desafio.ntconsult.controller;

import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import br.com.desafio.ntconsult.resource.VotoResource;
import br.com.desafio.ntconsult.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votos")
public class VotoController implements VotoResource {

    private final VotoService votoService;

    @Override
    public VotoView save(VotoForm votoForm) {
        return votoService.save(votoForm);
    }
}
