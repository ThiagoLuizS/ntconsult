package br.com.desafio.ntconsult.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaProducer {

    @Value("${topic.calcular-votos-pauta}")
    private String topic;

    private final AmqpTemplate amqpTemplate;

    public void solicitarCalculoVotacaoPauta(String pautaName) {
        amqpTemplate.convertAndSend(topic, pautaName);
    }
}
