package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaSprzetuKuchennego;

public class SprzetyKuchenne {

	public static SprzetyKuchenne pustyZbior() {
		return new SprzetyKuchenne();
	}

	public void dodaj(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu, SprzetKuchenny sprzet) {

	}

	// TODO: Pozbyć się tych metod?
	public SprzetKuchenny garnek() {
		return new SprzetKuchenny();
	}

	public SprzetKuchenny blaszka() {
		return new SprzetKuchenny();
	}

	public SprzetKuchenny piekarnik() {
		return new SprzetKuchenny();
	}

	public SprzetKuchenny noz() {
		return new SprzetKuchenny();
	}

	public SprzetKuchenny lopatka() {
		return new SprzetKuchenny();
	}

	public SprzetKuchenny wez(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
		return new SprzetKuchenny();
	}
}
