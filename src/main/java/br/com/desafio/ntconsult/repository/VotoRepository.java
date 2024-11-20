package br.com.desafio.ntconsult.repository;

import br.com.desafio.ntconsult.models.entity.Voto;
import br.com.desafio.ntconsult.models.enums.OpcaoVoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    int countAllByOpcaoIsAndPautaNome(OpcaoVoto opcao, String pautaName);
    int countAllByCpfAndPautaNome(String cpf, String pautaName);
}
