package br.com.desafio.ntconsult.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper <T, View, Form>{
    View entityToView(T t);
    T formToEntity(Form form);
    Form viewToForm(View view);
    Form entityToForm(T t);
    T viewToEntity(View view);
}
