package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.models.dto.view.StatusCpfView;

public interface ValidarService {
    StatusCpfView validarCpf(String cpf);
}
