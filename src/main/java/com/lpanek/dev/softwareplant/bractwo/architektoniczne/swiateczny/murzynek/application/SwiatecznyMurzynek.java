package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.application;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.Kucharz;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.MurzynekNaPaterze;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.PrzepisNaMurzynka;

public class SwiatecznyMurzynek {

	public static void main(String[] args) {
		Kuchnia kuchnia = new Kuchnia();
		PrzepisNaMurzynka przepis = new PrzepisNaMurzynka();

		Kucharz kucharz = new Kucharz(kuchnia);
		MurzynekNaPaterze murzynekNaPaterze = kucharz.upieczMurzynka(przepis);

		System.out.println(String.format("%s", murzynekNaPaterze));
	}
}
