package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import java.util.HashSet;
import java.util.Set;

public class PrzepisNaMurzynka {

	public Set<SpecyfikacjaSkladnika> potrzebneSkladniki() {
		return new HashSet<SpecyfikacjaSkladnika>();
	}

	public Set<SpecyfikacjaSprzetuKuchennego> potrzebneSprzetyKuchenne() {
		return new HashSet<SpecyfikacjaSprzetuKuchennego>();
	}

	public SpecyfikacjaPrzygotowaniaSkladnikow specyfikacjaMieszaniaSkladnikow() {
		return new SpecyfikacjaPrzygotowaniaSkladnikow();
	}

	public SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki() {
		return new SpecyfikacjaPrzygotowaniaBlaszki();
	}

	public SpecyfikacjaUstawienPiekarnika specyfikacjaUstawienPiekarnika() {
		return new SpecyfikacjaUstawienPiekarnika();
	}
}
