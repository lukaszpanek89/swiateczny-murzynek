package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia;

import java.util.HashMap;
import java.util.Map;

public class SprzetyKuchenne {

	private final Map<IdSprzetuKuchennego, SprzetKuchenny> sprzetyKuchenne = new HashMap<IdSprzetuKuchennego, SprzetKuchenny>();

	public static SprzetyKuchenne pustyZbior() {
		return new SprzetyKuchenne();
	}

	public void dodaj(SprzetKuchenny sprzet) {

	}

	public SprzetKuchenny wez(IdSprzetuKuchennego idSprzetu) {
		return new SprzetKuchenny(); // TODO: Ta metoda powinna usuwać sprzęt z kolekcji
	}

	public void zwroc(SprzetKuchenny sprzet) {

	}

	public SprzetKuchenny uzyj(IdSprzetuKuchennego idSprzetu) {
		return new SprzetKuchenny(); // TODO: Ta metoda nie powinna usuwać sprzętu z kolekcji
	}
}
