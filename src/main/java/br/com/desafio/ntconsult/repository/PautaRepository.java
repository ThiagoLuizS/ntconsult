package br.com.desafio.ntconsult.repository;

import br.com.desafio.ntconsult.models.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Optional<Pauta> findPautaByNome(String nome);

    @Query("select p From Pauta p where p.abertoAte < CURRENT_TIMESTAMP and p.calculado = false")
    List<Pauta> findAllPautaEncerradas();

    @Modifying
    @Query("update Pauta p set p.calculado = true where p.nome = :nome")
    void atualizarPautaCalculada(@Param("nome") String nome);
}
