package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.entity.Pauta;
import br.com.desafio.ntconsult.models.mapper.PautaMapper;
import br.com.desafio.ntconsult.repository.PautaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private PautaMapper pautaMapper;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(PautaServiceTest.class);
    }

    @Test
    public void whenAssetSave_thenPautaFlowAsExpected() {
        PautaForm pautaForm = PautaForm.builder().nome("teste").descricao("teste").duracaoSessaoMinuto(1).build();

        Pauta pauta = Pauta.builder().nome("teste").descricao("teste").expiracaoSessao(new Date()).build();

        Mockito.when(pautaRepository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
        Mockito.when(pautaMapper.formToEntity(Mockito.any(PautaForm.class))).thenReturn(pauta);

        pautaService.save(pautaForm);

        Mockito.verify(this.pautaRepository, Mockito.times(1)).save(Mockito.any(Pauta.class));
    }

}
