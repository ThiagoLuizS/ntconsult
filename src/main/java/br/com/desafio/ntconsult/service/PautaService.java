package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.mapper.MapStructMapper;
import br.com.desafio.ntconsult.models.mapper.PautaMapper;
import br.com.desafio.ntconsult.repository.PautaRepository;
import br.com.desafio.ntconsult.service.message.PautaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaService extends AbstractService<Pauta, PautaView, PautaForm> {

    private static final int DEFAULT_MINUTES = 1;

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;
    private final PautaProducer pautaProducer;

    /**
     * #ThiagoLuizS
     * Método reposável por abrir uma nova pauta
     * */
    public PautaView save(PautaForm pautaForm) {

        try {
            log.info(">> save [pautaForm={}]", pautaForm);

            Optional<Pauta> pautaOptional = findPautaByNome(pautaForm.getNome());

            if(pautaOptional.isPresent()) {
                throw new GlobalException("Essa pauta já existe, cadastre com outro nome.");
            }

            Pauta pauta = converterESalvar(pautaForm);

            PautaView view = getConverter().entityToView(pauta);

            log.info("<< save [pautaView={}]", view);

            return view;
        } catch (Exception e) {
            log.error("<< save [error={}]", e.getMessage(), e);

            if(e instanceof GlobalException) {
                throw (GlobalException) e;
            }

            throw new GlobalException("Não foi possivel criar uma pauta.");
        }
    }

    private Pauta converterESalvar(PautaForm pautaForm) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(DEFAULT_MINUTES);
        ZonedDateTime newNow = now.atZone(ZoneId.systemDefault());

        if(pautaForm.getDuracaoSessaoMinuto() > 0) {
            now = LocalDateTime.now().plusMinutes(pautaForm.getDuracaoSessaoMinuto());
            newNow = now.atZone(ZoneId.systemDefault());
        }

        Pauta pauta = getConverter().formToEntity(pautaForm);
        pauta.setExpiracaoSessao(Date.from(newNow.toInstant()));
        pauta = getRepository().save(pauta);

        return pauta;
    }

    @Transactional
    public void atualizarPautaCalculada(String nome) {
        try {
            pautaRepository.atualizarPautaCalculada(nome);
        } catch (Exception e) {
            log.error("<< Erro ao atualizar a pauta calculada [nome={}]", nome, e);
        }
    }

    public void buscarPautasEncerradas() {
        List<Pauta> pautas = pautaRepository.findAllPautaEncerradas();

        if(CollectionUtils.isEmpty(pautas)) {
            log.info("<< Nenhuma pauta para ser calculada.");
        }

        pautas.parallelStream().forEach(pauta -> {
            pautaProducer.solicitarCalculoVotacaoPauta(pauta.getNome());
        });
    }

    public long tempoSessao(Pauta pauta) {
        ZonedDateTime abertoAte = pauta.getExpiracaoSessao().toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();

       return ChronoUnit.SECONDS.between(now, abertoAte);
    }

    public void validarSessaoPauta(Pauta pauta) {
        if(tempoSessao(pauta) <= 0) {
            throw new GlobalException("Pauta já foi encerrada.");
        }
    }

    public Optional<Pauta> findPautaByNome(String nome) {
        return pautaRepository.findPautaByNome(nome);
    }

    public Pauta findPautaByNomeElseThrow(String nome) {
        return pautaRepository.findPautaByNome(nome).orElseThrow(() -> new GlobalException("A pauta informada não existe."));
    }

    @Override
    protected JpaRepository<Pauta, Long> getRepository() {
        return pautaRepository;
    }

    @Override
    protected MapStructMapper<Pauta, PautaView, PautaForm> getConverter() {
        return pautaMapper;
    }
}
