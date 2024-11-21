package br.com.desafio.ntconsult.controller.v1;

import br.com.desafio.ntconsult.NtconsultApplication;
import br.com.desafio.ntconsult.controller.AbstractControllerTest;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NtconsultApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yml")
public class VotoControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenVoto_whenPostVoto_thenStatus201Or404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/votos")
                        .content(asJsonString(VotoForm.builder().opcao("SIM").nomePauta("teste").cpf("12178265642").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResponse().getStatus() == 201 || result.getResponse().getStatus() == 404));

    }

    @Test
    public void givenPauta_whenPostVoto_thenStatus400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/votos")
                        .content(asJsonString(VotoForm.builder().opcao("SIM").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
