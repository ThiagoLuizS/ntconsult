package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;

import java.util.concurrent.CompletableFuture;

public interface VotoService {
    CompletableFuture<VotoView> salvar(VotoForm votoForm);
    void calcularVotos(String pautaName);
    void validarOpcao(VotoForm votoForm);
}
