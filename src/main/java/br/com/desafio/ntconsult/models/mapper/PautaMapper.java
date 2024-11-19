package br.com.desafio.ntconsult.models.mapper;

import br.com.desafio.ntconsult.models.dto.form.PautaForm;
import br.com.desafio.ntconsult.models.dto.view.PautaView;
import br.com.desafio.ntconsult.models.entity.Pauta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapStructMapper.class)
public interface PautaMapper extends MapStructMapper<Pauta, PautaView, PautaForm>{
}
