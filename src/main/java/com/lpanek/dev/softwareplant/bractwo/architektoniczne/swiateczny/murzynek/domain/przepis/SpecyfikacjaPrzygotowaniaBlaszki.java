package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TypSprzetuKuchennego;

public final class SpecyfikacjaPrzygotowaniaBlaszki {

	public SpecyfikacjaSprzetuKuchennego specyfikacjaBlaszki() {
		return new SpecyfikacjaSprzetuKuchennego(new IdSprzetuKuchennego("Blaszka do pieczenia murzynka"), TypSprzetuKuchennego.BLASZKA_DO_PIECZENIA);
	}
}
