package br.com.desafio.ntconsult.route;

import br.com.desafio.ntconsult.service.v1.PautaServiceV1Impl;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuartzRouter extends RouteBuilder {

    @Value("${camel.component.quartz2.cron.v1}")
    private String quartzV1;

    @Override
    public void configure() throws Exception {
        from(quartzV1).routeId("quartz-getdata-v1").autoStartup(true)
                .log(LoggingLevel.INFO, ">> calcular total de votos").bean(PautaServiceV1Impl.class, "buscarPautasEncerradas");
    }
}
