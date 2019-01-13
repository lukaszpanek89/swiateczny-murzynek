package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TypSprzetuKuchennego;

public final class SpecyfikacjaPrzygotowaniaCiasta {

	public SpecyfikacjaSprzetuKuchennego specyfikacjaLyzkiDoMieszania() {
		return new SpecyfikacjaSprzetuKuchennego(new IdSprzetuKuchennego("Lyzka do mieszania ciasta"), TypSprzetuKuchennego.LYZKA_STOLOWA);
	}
}
