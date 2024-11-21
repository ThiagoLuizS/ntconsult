package br.com.desafio.ntconsult.service.message;

import br.com.desafio.ntconsult.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PautaConsumer {

    private final VotoService votoService;

    @Autowired
    public PautaConsumer(@Qualifier("v1") VotoService votoService) {
        this.votoService = votoService;
    }

    @RabbitListener(queues = {"calcular-votos-pauta"})
    public void calcularVotos(@Payload Message message) {
        votoService.calcularVotos(message.getPayload().toString());
    }
}
