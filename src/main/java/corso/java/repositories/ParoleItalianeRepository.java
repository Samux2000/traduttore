package corso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corso.java.entities.ParolaItalianaEntity;

@Repository
public interface ParoleItalianeRepository extends JpaRepository<ParolaItalianaEntity, Integer>{

	ParolaItalianaEntity findByParola(String parola);
}
