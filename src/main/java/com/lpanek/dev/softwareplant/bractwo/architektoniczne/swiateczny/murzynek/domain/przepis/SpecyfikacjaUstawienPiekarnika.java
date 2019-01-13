package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.CzasPieczeniaPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TemperaturaPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TypSprzetuKuchennego;

public final class SpecyfikacjaUstawienPiekarnika {

	public SpecyfikacjaSprzetuKuchennego specyfikacjaPiekarnika() {
		return new SpecyfikacjaSprzetuKuchennego(new IdSprzetuKuchennego("Piekarnik do murzynka"), TypSprzetuKuchennego.PIEKARNIK_DO_CIASTA);
	}

	public TemperaturaPiekarnika temperatura() {
		return new TemperaturaPiekarnika();
	}

	public CzasPieczeniaPiekarnika minimalnyCzasPieczenia() {
		return new CzasPieczeniaPiekarnika();
	}

	public CzasPieczeniaPiekarnika maksymalnyCzasPieczenia() {
		return new CzasPieczeniaPiekarnika();
	}
}
