package br.com.desafio.ntconsult.service;

import br.com.desafio.ntconsult.models.mapper.MapStructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractService <T, View, Form> {

    protected abstract JpaRepository<T, Long> getRepository();
    protected abstract MapStructMapper<T, View, Form> getConverter();

    public Page<View> findByPage(Pageable pageable) {
        log.info(">>[pageable={}]", pageable);
        Page<T> t = getRepository().findAll(pageable);
        List<View> view = t.getContent().stream().map(getConverter()::entityToView).collect(Collectors.toList());
        log.info("<<[List View Size={}]", view.size()   );
        return new PageImpl<>(view);
    }
}
