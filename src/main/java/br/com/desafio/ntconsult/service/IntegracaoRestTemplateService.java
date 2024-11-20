package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.RetornoValidadorCpfView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegracaoRestTemplateService {

    @Value("${url.cpf}")
    private String urlCpf;
    @Value("${url.token}")
    private String token;
    @Value("${url.type}")
    private String type;

    private final RestTemplate restTemplate;

    public RetornoValidadorCpfView validarCpf(String cpf) {
        try {
            RetornoValidadorCpfView response = restTemplate.getForEntity(urlCpf + "?token=" + token + "&value=" + cpf + "&type=" + type, RetornoValidadorCpfView.class).getBody();
            if(Objects.isNull(response)) {
                throw new GlobalException("O retorno do serviço de validação de CPF está nulo.");
            }
            log.info(">> validarCPF [cpf={}, valid={}]", cpf, response.getValid());
            return response;
        } catch (Exception e) {
            log.error("<< validarCPF [error={}, cpf={}]", e.getMessage(), cpf, e);
            if(e instanceof GlobalException) {
                throw (GlobalException) e;
            }
            throw new GlobalException("Erro ao validar CPF.");
        }
    }

    public void validarCpf(VotoForm votoForm) {
        RetornoValidadorCpfView validadorCpf = validarCpf(votoForm.getCpf());

        if(!validadorCpf.getValid()) {
            throw new GlobalException("CPF inválido.");
        }
    }

}
