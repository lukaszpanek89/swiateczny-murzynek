package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.application;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.Kucharz;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.Murzynek;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.PrzepisNaMurzynka;

public class SwiatecznyMurzynek {

	public static void main(String[] args) {
		Kuchnia kuchnia = new Kuchnia();
		PrzepisNaMurzynka przepis = new PrzepisNaMurzynka();

		Kucharz kucharz = new Kucharz(kuchnia);
		Murzynek murzynek = kucharz.upieczMurzynka(przepis);

		System.out.println(String.format("%s", murzynek));
	}
}
