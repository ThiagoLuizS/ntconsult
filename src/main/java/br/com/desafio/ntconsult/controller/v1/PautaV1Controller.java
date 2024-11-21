package br.com.desafio.ntconsult.controller.v1;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.resource.PautaResource;
import br.com.desafio.ntconsult.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaV1Controller implements PautaResource {

    private final PautaService service;

    @Autowired
    public PautaV1Controller(@Qualifier("v1") PautaService service) {
        this.service = service;
    }

    @Override
    public PautaView save(PautaForm pautaForm) {
        return service.save(pautaForm);
    }

    @Override
    public Page<PautaView> findAll(Pageable pageable, String nome) {
        return service.findByFilter(pageable, nome);
    }
}
