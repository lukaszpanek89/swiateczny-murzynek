package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.CzasPracyPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TemperaturaPiekarnika;

public class SpecyfikacjaUstawienPiekarnika {

	public TemperaturaPiekarnika temperatura() {
		return new TemperaturaPiekarnika();
	}

	public CzasPracyPiekarnika minimalnyCzasPieczenia() {
		return new CzasPracyPiekarnika();
	}

	public CzasPracyPiekarnika maksymalnyCzasPieczenia() {
		return new CzasPracyPiekarnika();
	}
}
