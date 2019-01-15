package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.application;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.Kucharz;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.TypWyjsciowy;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.TypWejsciowy;

public class SwiatecznyMurzynek {

	public static void main(String[] args) {
		Kuchnia kuchnia = new Kuchnia();
		TypWejsciowy przepis = new TypWejsciowy();

		Kucharz kucharz = new Kucharz(kuchnia);
		TypWyjsciowy murzynekNaPaterze = kucharz.nazwaMetody(przepis);

		System.out.println(String.format("%s", murzynekNaPaterze));
	}
}
