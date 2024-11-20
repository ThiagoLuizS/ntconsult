package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(VotoServiceTest.class);
    }

    @Test
    public void whenAssetSave_thenVotoFlowAsExpected() {
        VotoForm votoForm = VotoForm.builder().cpf("12178265644").build();

        IntegracaoRestTemplateService integracaoMock = Mockito.mock(IntegracaoRestTemplateService.class);

        Mockito.doThrow(new GlobalException("error")).when(integracaoMock).validarCpf(votoForm);

        assertThrows(GlobalException.class, () -> integracaoMock.validarCpf(votoForm));
    }
}
