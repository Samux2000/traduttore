package corso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import corso.java.entities.ParolaIngleseEntity;

public interface ParoleInglesiRepository extends JpaRepository<ParolaIngleseEntity, Integer>{

	ParolaIngleseEntity findById(int id);
}
