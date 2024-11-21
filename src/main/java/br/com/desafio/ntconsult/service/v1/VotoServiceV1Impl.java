package br.com.desafio.ntconsult.service.v1;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.entity.Voto;
import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import br.com.desafio.ntconsult.models.mapper.MapStructMapper;
import br.com.desafio.ntconsult.models.mapper.VotoMapper;
import br.com.desafio.ntconsult.repository.VotoRepository;
import br.com.desafio.ntconsult.service.AbstractService;
import br.com.desafio.ntconsult.service.IntegracaoRestTemplateService;
import br.com.desafio.ntconsult.service.PautaService;
import br.com.desafio.ntconsult.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Qualifier("v1")
public class VotoServiceV1Impl extends AbstractService<Voto, VotoView, VotoForm> implements VotoService {

    private final PautaService pautaService;

    private final IntegracaoRestTemplateService integracaoRestTemplateService;
    private final VotoRepository votoRepository;
    private final VotoMapper votoMapper;

    @Autowired
    public VotoServiceV1Impl(@Qualifier("v1") PautaService pautaService,
                             IntegracaoRestTemplateService integracaoRestTemplateService,
                             VotoRepository votoRepository,
                             VotoMapper votoMapper) {
        this.pautaService = pautaService;
        this.integracaoRestTemplateService = integracaoRestTemplateService;
        this.votoRepository = votoRepository;
        this.votoMapper = votoMapper;
    }

    /**
     * #ThiagoLuizS
     * Método responsavel por persistir o voto para cada pauta
     * O método está cacheado não permitindo o CPF votar na mesma pauta mais de uma vez.
     * Tendo essa validação o método irá retornar do caching a ultima votação cacheada feita pelo CPF.
     * Caso o cache expire o método irá validar se o cpf já votou.
     * */
    @Cacheable(value = "voto", key = "#votoForm.nomePauta.concat('-')" +
            ".concat(#votoForm.cpf).concat('-').concat(#votoForm.opcao)")
    @Override
    public CompletableFuture<VotoView> salvar(VotoForm votoForm) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info(">> save [votoForm={}]", votoForm);

                integracaoRestTemplateService.validarCpf(votoForm);

                Pauta pauta = pautaService.findPautaByNomeElseThrow(votoForm.getNomePauta());

                validarOpcao(votoForm);

                pautaService.validarSessaoPauta(pauta);

                validarVotoByCpfAndNomePauta(votoForm.getCpf(), votoForm.getNomePauta());

                VotoView view = converterESalvar(votoForm, pauta);

                log.info("<< save [view={}]", view);

                return view;

            } catch (Exception e) {
                log.error("<< save [error={}]", e.getMessage(), e);

                if(e instanceof GlobalException) {
                    throw (GlobalException) e;
                }

                throw new GlobalException("Não foi possivel registrar seu voto.");
            }
        });
    }

    private void validarVotoByCpfAndNomePauta(String cpf, String nomePauta) {
        int count = votoRepository.countAllByCpfAndPautaNome(cpf, nomePauta);
        if(count > 0) {
            throw new GlobalException("Este CPF já efetuou seu voto.");
        }
    }

    @Override
    public void calcularVotos(String pautaName) {
        int countVotosSim = votoRepository.countAllByOpcaoIsAndPautaNome(OpcaoVoto.SIM, pautaName);
        int countVotosNao = votoRepository.countAllByOpcaoIsAndPautaNome(OpcaoVoto.NAO, pautaName);
        pautaService.atualizarPautaCalculada(pautaName);
        log.info(">> SOMA DOS VOTOS [PAUTA={}, SIM={} N={}] <<", pautaName, countVotosSim, countVotosNao);
    }

    @Override
    public void validarOpcao(VotoForm votoForm) {
        if(Objects.isNull(votoForm.getOpcao())) {
            throw new GlobalException("A opção informada é inválida.");
        }
    }

    private VotoView converterESalvar(VotoForm votoForm, Pauta pauta) {
        Voto voto = getConverter().formToEntity(votoForm);

        voto.setOpcao(OpcaoVoto.valueOf(votoForm.getOpcao()));

        voto.setPauta(pauta);

        return saveToView(voto);
    }

    @Override
    protected JpaRepository<Voto, Long> getRepository() {
        return votoRepository;
    }

    @Override
    protected MapStructMapper<Voto, VotoView, VotoForm> getConverter() {
        return votoMapper;
    }
}
