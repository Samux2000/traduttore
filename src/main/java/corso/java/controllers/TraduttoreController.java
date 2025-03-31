package corso.java.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corso.java.models.ParolaItaliana;
import corso.java.services.TraduttoreService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/traduttore")
public class TraduttoreController {

	private final TraduttoreService service;

	public TraduttoreController(TraduttoreService service) {
		this.service = service;
	}

	@GetMapping("/dizionario")
	public String[] mostraDizionario() {
		String[] dizionario = service.mostraDizionario();
		return dizionario; 
	}
	
	@PostMapping("/inglese/parola")
	public String tradItaAIng(@RequestBody ParolaItaliana parola) {
		String traduzione = service.getTraduzioneInglese(parola.getParola());
		return traduzione;
	}
	
	@PostMapping("/giapponese/parola")
	public String tradItaAGiap(@RequestBody ParolaItaliana parola) {
		String traduzione = service.getTraduzioneGiapponese(parola.getParola());
		return traduzione;
	}

	@PostMapping("/inglese/frase")
	public String tradItaAIngFrase(@RequestBody ParolaItaliana[] parole) {
		String fraseTrad = "";
		for (ParolaItaliana parolaItaliana : parole) {
			fraseTrad = fraseTrad + service.getTraduzioneInglese(parolaItaliana.getParola()) + " ";
		}
		return fraseTrad;
	}
	
	@PostMapping
	public String postMethodName(@RequestBody ParolaItaliana parola) {
		String traduzione = service.getTraduzione(parola.getParola());
		return traduzione;
	}
	

}
