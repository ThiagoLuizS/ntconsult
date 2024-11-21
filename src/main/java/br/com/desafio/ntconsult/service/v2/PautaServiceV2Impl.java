package br.com.desafio.ntconsult.service.v2;

import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.mapper.PautaMapper;
import br.com.desafio.ntconsult.repository.PautaRepository;
import br.com.desafio.ntconsult.service.message.PautaProducer;
import br.com.desafio.ntconsult.service.v1.PautaServiceV1Impl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Qualifier("v2")
public class PautaServiceV2Impl extends PautaServiceV1Impl {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Autowired
    public PautaServiceV2Impl(PautaRepository pautaRepository, PautaMapper pautaMapper, PautaProducer pautaProducer) {
        super(pautaRepository, pautaMapper, pautaProducer);
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
    }

    @Override
    public Page<PautaView> findByFilter(Pageable pageable, String nome) {
        log.info(">> findByFilter V2 params: [nome={}] [pageable={}]", nome, pageable);
        Page<Pauta> page = pautaRepository.findByFilter(pageable, nome);

        List<PautaView> views = page.getContent().stream().map(pautaMapper::entityToView).toList();

        log.info("<< findByFilter V2 params: [nome={}] [pageable={}, content={}]", nome, pageable, views.size());

        return new PageImpl<>(views, pageable, page.getTotalElements());
    }
}
