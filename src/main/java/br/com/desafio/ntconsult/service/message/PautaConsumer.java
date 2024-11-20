package br.com.desafio.ntconsult.service.message;

import br.com.desafio.ntconsult.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PautaConsumer {

    @Autowired
    private VotoService votoService;

    @RabbitListener(queues = {"calcular-votos-pauta"})
    public void calcularVotos(@Payload Message message) {
        votoService.calcularVotos(message.getPayload().toString());
    }
}
