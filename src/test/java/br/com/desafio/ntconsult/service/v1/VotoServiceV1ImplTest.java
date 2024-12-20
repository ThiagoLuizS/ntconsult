package br.com.desafio.ntconsult.service.v1;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.service.IntegracaoRestTemplateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VotoServiceV1ImplTest {

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(VotoServiceV1ImplTest.class);
    }

    @Test
    public void whenAssetValidarCpf_thenVotoFlowAsExpected() {
        VotoForm votoForm = VotoForm.builder().cpf("12178265644").build();

        IntegracaoRestTemplateService integracaoMock = Mockito.mock(IntegracaoRestTemplateService.class);

        Mockito.doThrow(new GlobalException("error")).when(integracaoMock).validarCpf(votoForm);

        assertThrows(GlobalException.class, () -> integracaoMock.validarCpf(votoForm));
    }

    @Test
    public void whenAssetValidarOpcao_thenVotoFlowAsExpected() {
        VotoForm votoForm = VotoForm.builder().cpf("12178265644").build();

        VotoServiceV1Impl votoService = Mockito.mock(VotoServiceV1Impl.class);

        Mockito.doThrow(new GlobalException("error")).when(votoService).validarOpcao(votoForm);

        assertThrows(GlobalException.class, () -> votoService.validarOpcao(votoForm));
    }
}
