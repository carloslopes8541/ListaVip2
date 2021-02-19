package br.com.carlos.listavip.repository;

import br.com.carlos.listavip.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Integer> {

}
