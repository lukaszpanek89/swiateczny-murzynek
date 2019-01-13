package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TypSprzetuKuchennego;

public final class SpecyfikacjaSprzetuKuchennego {

	private final IdSprzetuKuchennego idSprzetu;

	private final TypSprzetuKuchennego typSprzetu;

	public SpecyfikacjaSprzetuKuchennego(IdSprzetuKuchennego idSprzetu, TypSprzetuKuchennego typSprzetu) {
		this.idSprzetu = idSprzetu;
		this.typSprzetu = typSprzetu;
	}

	public IdSprzetuKuchennego id() {
		return idSprzetu;
	}
}
