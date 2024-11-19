package br.com.desafio.ntconsult.repository;

import br.com.desafio.ntconsult.models.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
}
