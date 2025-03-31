package corso.java.controllers.web;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import corso.java.entities.ParolaIngleseEntity;
import corso.java.models.ParolaItaliana;
import corso.java.services.TraduttoreService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class TraduttoreWebController {

	private final TraduttoreService service;

	public TraduttoreWebController(TraduttoreService service) {
		this.service = service;
	}

	@GetMapping
	public String index(ModelMap map) {
		String[] dizionario = service.mostraDizionario();
		// Inizializza due oggetti ParolaItaliana e li aggiunge al modello
		map.addAttribute("parolaIng", new ParolaItaliana());
		map.addAttribute("parolaGia", new ParolaItaliana());
		map.addAttribute("dizionario", Arrays.asList(dizionario));
		// Passati alla vista index.html per essere usati nei form di input.
		// recuperati con ${}
		return "index";
	}

	@PostMapping("/inglese/parola")
	public String tradItaAIngParola(@ModelAttribute ParolaItaliana parola, ModelMap map) {
		String traduzione = service.getTraduzioneInglese(parola.getParola());
		map.addAttribute("parolaIng", parola);
		map.addAttribute("traParIng", traduzione);
	    map.addAttribute("parolaGia", new ParolaItaliana());
		return "index";
	}

	@PostMapping("/giapponese/parola")
	public String tradItaAGiaParola(@ModelAttribute ParolaItaliana parola, ModelMap map) {
		String traduzione = service.getTraduzioneGiapponese(parola.getParola());
		map.addAttribute("parolaGia", parola);
		map.addAttribute("traParGia", traduzione);
	    map.addAttribute("parolaIng", new ParolaItaliana());
		return "index";
	}
}
