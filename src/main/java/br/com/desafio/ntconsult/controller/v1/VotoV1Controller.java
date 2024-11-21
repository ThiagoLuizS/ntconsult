package br.com.desafio.ntconsult.controller.v1;

import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import br.com.desafio.ntconsult.resource.VotoResource;
import br.com.desafio.ntconsult.service.VotoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/votos")
public class VotoV1Controller implements VotoResource {

    private final VotoService votoService;

    public VotoV1Controller(@Qualifier("v1") VotoService votoService) {
        this.votoService = votoService;
    }

    @Override
    public CompletableFuture<VotoView> save(VotoForm votoForm) {
        return votoService.salvar(votoForm);
    }
}
