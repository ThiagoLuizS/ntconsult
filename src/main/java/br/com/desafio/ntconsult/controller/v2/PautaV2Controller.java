package br.com.desafio.ntconsult.controller.v2;

import br.com.desafio.ntconsult.controller.v1.PautaV1Controller;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/pautas")
public class PautaV2Controller extends PautaV1Controller {

    private final PautaService service;

    @Autowired
    public PautaV2Controller(@Qualifier("v2") PautaService service) {
        super(service);
        this.service = service;
    }

    @Override
    public Page<PautaView> findAll(Pageable pageable, String nome) {
        return service.findByFilter(pageable, nome);
    }
}
