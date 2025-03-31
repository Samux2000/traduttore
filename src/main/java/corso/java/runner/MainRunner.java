package corso.java.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import corso.java.entities.ParolaGiapponeseEntity;
import corso.java.entities.ParolaIngleseEntity;
import corso.java.entities.ParolaItalianaEntity;
import corso.java.repositories.ParoleGiapponesiRepository;
import corso.java.repositories.ParoleInglesiRepository;
import corso.java.repositories.ParoleItalianeRepository;

@Component
public class MainRunner implements CommandLineRunner{

	@Autowired
	private ParoleItalianeRepository paroleItaliane;
	@Autowired
	private ParoleInglesiRepository paroleInglesi;
	@Autowired
	private ParoleGiapponesiRepository paroleGiapponesi;
	
	@Override
	public void run(String... args) throws Exception {
		var paIng1 = ParolaIngleseEntity.builder()
				.withParola("hello")
				.build();
		var paGia1 = ParolaGiapponeseEntity.builder()
				.withParola("kon'nichiwa")
				.build();
		var paIta1 = ParolaItalianaEntity.builder()
				.withParola("ciao")
				.withParolaInglese(paIng1)
				.withParolaGiapponese(paGia1)
				.build();
		var paIng2 = ParolaIngleseEntity.builder()
				.withParola("handsome")
				.build();
		var paIta2 = ParolaItalianaEntity.builder()
				.withParola("bello")
				.withParolaInglese(paIng2)
				.build();
		paroleInglesi.save(paIng1);
		paroleGiapponesi.save(paGia1);
		paroleItaliane.save(paIta1);
		paroleInglesi.save(paIng2);
		paroleItaliane.save(paIta2);
	}

}
