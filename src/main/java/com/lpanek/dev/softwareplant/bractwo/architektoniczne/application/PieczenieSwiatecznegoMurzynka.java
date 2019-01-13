package com.lpanek.dev.softwareplant.bractwo.architektoniczne.application;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.domain.Kucharz;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.domain.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.domain.Murzynek;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.domain.PrzepisNaMurzynka;

public class PieczenieSwiatecznegoMurzynka {

	public static void main(String[] args) {
		Kuchnia kuchnia = new Kuchnia();
		PrzepisNaMurzynka przepis = new PrzepisNaMurzynka();

		Kucharz kucharz = new Kucharz(kuchnia);
		Murzynek murzynek = kucharz.upieczMurzynka(przepis);

		System.out.println(String.format("Oto swiateczny murzynek! %s", murzynek));
	}
}
