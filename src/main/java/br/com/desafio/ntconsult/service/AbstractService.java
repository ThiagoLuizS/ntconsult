package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.exception.GlobalException;
import br.com.desafio.ntconsult.models.mapper.MapStructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public abstract class AbstractService <T, View, Form> {

    protected abstract JpaRepository<T, Long> getRepository();
    protected abstract MapStructMapper<T, View, Form> getConverter();

    public View saveToView(T entity) {
        try {
            log.debug(">> save [entity={}] ", entity);
            T t = getRepository().save(entity);
            log.debug("<< save [t={}] ", t);
            View view =  getConverter().entityToView(t);
            log.debug("<< save [view={}] ", view);
            return view;
        } catch (Exception e) {
            log.error("<< save [error={}]", e.getMessage(), e);
            throw new GlobalException(e.getMessage());
        }
    }
}
