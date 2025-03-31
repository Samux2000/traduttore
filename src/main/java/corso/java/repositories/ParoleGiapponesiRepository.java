package corso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import corso.java.entities.ParolaGiapponeseEntity;

public interface ParoleGiapponesiRepository extends JpaRepository<ParolaGiapponeseEntity, Integer>{

	ParolaGiapponeseEntity findById(int id);
}
