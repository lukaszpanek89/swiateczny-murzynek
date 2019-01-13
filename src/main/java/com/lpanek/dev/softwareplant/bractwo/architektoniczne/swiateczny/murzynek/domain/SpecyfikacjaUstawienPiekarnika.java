package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

class SpecyfikacjaUstawienPiekarnika {

	TemperaturaPiekarnika temperatura() {
		return new TemperaturaPiekarnika();
	}

	CzasPracyPiekarnika minimalnyCzasPieczenia() {
		return new CzasPracyPiekarnika();
	}

	CzasPracyPiekarnika maksymalnyCzasPieczenia() {
		return new CzasPracyPiekarnika();
	}
}
