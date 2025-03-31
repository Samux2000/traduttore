package corso.java.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corso.java.TraduttoreApplication;
import corso.java.entities.ParolaGiapponeseEntity;
import corso.java.entities.ParolaIngleseEntity;
import corso.java.entities.ParolaItalianaEntity;
import corso.java.repositories.ParoleGiapponesiRepository;
import corso.java.repositories.ParoleInglesiRepository;
import corso.java.repositories.ParoleItalianeRepository;

@Service
public class TraduttoreService {

	@Autowired
	private ParoleItalianeRepository paroleItaliane;
	@Autowired
	private ParoleInglesiRepository paroleInglesi;
	@Autowired
	private ParoleGiapponesiRepository paroleGiapponesi;

	public String[] mostraDizionario() {
		int i = 0;
		String[] dizionario = new String[paroleItaliane.findAll().size()];
		for (ParolaItalianaEntity parolaItEnt : paroleItaliane.findAll()) {
			String traduzioni = parolaItEnt.getParola() + " ";
			if (paroleInglesi.findById(parolaItEnt.getId()) == null) {
				traduzioni = traduzioni + "Inesistente ";
			} else {
				traduzioni = traduzioni + paroleInglesi.findById(parolaItEnt.getId()).getParola() + " ";
			}
			if (paroleGiapponesi.findById(parolaItEnt.getId()) == null) {
				traduzioni = traduzioni + "Inesistente ";
			} else {
				traduzioni = traduzioni + paroleGiapponesi.findById(parolaItEnt.getId()).getParola();
			}
			dizionario[i] = traduzioni;
			i++;
		}
		return dizionario;
	}

	public String getTraduzioneInglese(String parola) {
		ParolaItalianaEntity parolaItaEnt = paroleItaliane.findByParola(parola);
		if (parolaItaEnt == null) {
			return "Parola non presente nel dizionario";
		}
		ParolaIngleseEntity parolaIngEnt = paroleInglesi.findById(parolaItaEnt.getId());
		if(parolaIngEnt == null) {
			return "inesistente";
		}
		String parolaIng = parolaIngEnt.getParola();
		return parolaIng;
	}

	public String getTraduzioneGiapponese(String parola) {
		ParolaItalianaEntity parolaItaEnt = paroleItaliane.findByParola(parola);
		if (parolaItaEnt == null) {
			return "Parola non presente nel dizionario";
		}
		ParolaGiapponeseEntity parolaGiaEnt = paroleGiapponesi.findById(parolaItaEnt.getId());
		if(parolaGiaEnt == null) {
			return "inesistente";
		}
		String parolaGia = parolaGiaEnt.getParola();
		return parolaGia;
	}

	public String getTraduzione(String parola) {
		String traduzione = "";
		ParolaItalianaEntity parolaItaEnt = paroleItaliane.findByParola(parola);
		if (parolaItaEnt == null) {
			return "Parola non presente nel dizionario";
		}
		traduzione = traduzione + parola + " ";
		if (paroleInglesi.findById(parolaItaEnt.getId()) == null) {
			traduzione = traduzione + " Insistente ";
		} else {
			traduzione = traduzione + paroleInglesi.findById(parolaItaEnt.getId()).getParola() + " ";
		}
		if (paroleGiapponesi.findById(parolaItaEnt.getId()) == null) {
			traduzione = traduzione + "Insistente";
		} else {
			traduzione = traduzione + paroleGiapponesi.findById(parolaItaEnt.getId()).getParola();
		}
		return traduzione;
	}
}
