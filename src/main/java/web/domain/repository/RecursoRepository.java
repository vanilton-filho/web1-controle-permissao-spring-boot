package web.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.domain.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

}
