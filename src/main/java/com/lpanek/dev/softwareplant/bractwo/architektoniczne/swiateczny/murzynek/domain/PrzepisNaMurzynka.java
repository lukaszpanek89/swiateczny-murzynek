package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import java.util.HashSet;
import java.util.Set;

public class PrzepisNaMurzynka {

	Set<SpecyfikacjaSkladnika> potrzebneSkladniki() {
		return new HashSet<SpecyfikacjaSkladnika>();
	}

	Set<SpecyfikacjaSprzetuKuchennego> potrzebneSprzetyKuchenne() {
		return new HashSet<SpecyfikacjaSprzetuKuchennego>();
	}

	SpecyfikacjaMieszaniaSkladnikow specyfikacjaMieszaniaSkladnikow() {
		return new SpecyfikacjaMieszaniaSkladnikow();
	}

	SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki() {
		return new SpecyfikacjaPrzygotowaniaBlaszki();
	}

	SpecyfikacjaUstawienPiekarnika specyfikacjaUstawienPiekarnika() {
		return new SpecyfikacjaUstawienPiekarnika();
	}
}
