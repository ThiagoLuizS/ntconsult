package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PautaService {
    PautaView save(PautaForm pautaForm);
    Page<PautaView> findByFilter(Pageable pageable, String nome);
    void atualizarPautaCalculada(String nome);
    void validarSessaoPauta(Pauta pauta);
    Optional<Pauta> findPautaByNome(String nome);
    Pauta findPautaByNomeElseThrow(String nome);
}
