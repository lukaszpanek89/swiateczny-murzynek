package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import java.util.HashSet;
import java.util.Set;

public class PrzepisNaMurzynka {

	public Set<SpecyfikacjaSkladnika> potrzebneSkladniki() {
		HashSet<SpecyfikacjaSkladnika> potrzebneSkladniki = new HashSet<SpecyfikacjaSkladnika>();
		potrzebneSkladniki.addAll(skladnikiPotrzebneDoPrzygotowaniaCiasta());
		potrzebneSkladniki.addAll(skladnikiPotrzebneDoPrzygotowaniaBlaszki());
		return potrzebneSkladniki;
	}

	public Set<SpecyfikacjaSprzetuKuchennego> potrzebneSprzetyKuchenne() {
		return new HashSet<SpecyfikacjaSprzetuKuchennego>();
	}

	public SpecyfikacjaPrzygotowaniaCiasta specyfikacjaPrzygotowaniaCiasta() {
		return new SpecyfikacjaPrzygotowaniaCiasta();
	}

	public SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki() {
		return new SpecyfikacjaPrzygotowaniaBlaszki();
	}

	public SpecyfikacjaUstawienPiekarnika specyfikacjaUstawienPiekarnika() {
		return new SpecyfikacjaUstawienPiekarnika();
	}

	public Set<SpecyfikacjaSkladnika> skladnikiPotrzebneDoPrzygotowaniaCiasta() {
		return new HashSet<SpecyfikacjaSkladnika>();
	}

	private Set<SpecyfikacjaSkladnika> skladnikiPotrzebneDoPrzygotowaniaBlaszki() {
		return new HashSet<SpecyfikacjaSkladnika>();
	}
}
