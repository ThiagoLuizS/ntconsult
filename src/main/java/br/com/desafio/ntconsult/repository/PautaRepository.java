package br.com.desafio.ntconsult.repository;

import br.com.desafio.ntconsult.models.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Optional<Pauta> findPautaByNome(String nome);
}
