package gama.lundegaard.simple_back.process.database.repository;

import gama.lundegaard.simple_back.process.database.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
}
