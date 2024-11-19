package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.entity.Voto;
import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import br.com.desafio.ntconsult.models.mapper.MapStructMapper;
import br.com.desafio.ntconsult.models.mapper.VotoMapper;
import br.com.desafio.ntconsult.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotoService extends AbstractService<Voto, VotoView, VotoForm>{

    private final PautaService pautaService;
    private final VotoRepository votoRepository;
    private final VotoMapper votoMapper;

    /**
     * #ThiagoLuizS
     * Método responsavel por persistir o voto para cada pauta
     * */
    public VotoView save(VotoForm votoForm) {

        try {
            log.info(">> save [votoForm={}]", votoForm);

            Optional<Pauta> pauta = pautaService.findPautaByNome(votoForm.getNomePauta());

            if(pauta.isEmpty()) {
                throw new GlobalException("A pauta informada não existe.");
            } else if(Objects.isNull(votoForm.getOpcao())) {
                throw new GlobalException("A opção informada é inválida.");
            }

            pautaService.validarSessaoPauta(pauta.get());

            Voto voto = getConverter().formToEntity(votoForm);

            voto.setOpcao(OpcaoVoto.valueOf(votoForm.getOpcao()));

            voto.setPauta(pauta.get());

            voto = getRepository().save(voto);

            VotoView view = getConverter().entityToView(voto);

            log.info("<< save [view={}]", view);

            return view;

        } catch (Exception e) {
            log.error("<< save [error={}]", e.getMessage(), e);

            if(e instanceof GlobalException) {
                throw (GlobalException) e;
            }

            throw new GlobalException("Não foi possivel registrar seu voto.");
        }
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
