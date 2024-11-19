package br.com.desafio.ntconsult.models.mapper;

import br.com.desafio.ntconsult.models.dto.form.VotoForm;
import br.com.desafio.ntconsult.models.dto.view.VotoView;
import br.com.desafio.ntconsult.models.entity.Voto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapStructMapper.class)
public interface VotoMapper extends MapStructMapper<Voto, VotoView, VotoForm>{
}
