package com.lpanek.dev.softwareplant.bractwo.architektoniczne.domain;

public class Kucharz {

	private final Kuchnia kuchnia;

	public Kucharz(Kuchnia kuchnia) {
		this.kuchnia = kuchnia;
	}

	public Murzynek upieczMurzynka(PrzepisNaMurzynka przepis) {
		SprzetyKuchenne sprzetyKuchenne = zbierzPotrzebneSprzetyKuchenne(kuchnia, przepis);
		Skladniki skladniki = zbierzPotrzebneSkladniki(kuchnia, przepis);
		dodajSkladnikiDoGarnka(skladniki, sprzetyKuchenne, przepis);
		wymieszajSkladnikiWGarnku(sprzetyKuchenne, przepis);
		przygotujBlaszkePodCiasto(sprzetyKuchenne, skladniki, przepis);
		wylejCiastoNaBlaszke(sprzetyKuchenne, przepis);
		nastawPiekarnik(sprzetyKuchenne, przepis);
		wstawBlaszkeDoPiekarnika(sprzetyKuchenne, przepis);
		odczekajCzasNaUpieczenie(sprzetyKuchenne, przepis);
		wyjmijBlaszkeZPiekarnika(sprzetyKuchenne, przepis);
		wylaczPiekarnik(sprzetyKuchenne, przepis);
		Murzynek murzynek = wyjmijCiastoZBlaszki(sprzetyKuchenne, przepis);
		return murzynek;
	}

	private SprzetyKuchenne zbierzPotrzebneSprzetyKuchenne(Kuchnia kuchnia, PrzepisNaMurzynka przepis) {
		return null;
	}

	private Skladniki zbierzPotrzebneSkladniki(Kuchnia kuchnia, PrzepisNaMurzynka przepis) {
		return null;
	}

	private void dodajSkladnikiDoGarnka(Skladniki skladniki, SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void wymieszajSkladnikiWGarnku(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void przygotujBlaszkePodCiasto(SprzetyKuchenne sprzetyKuchenne, Skladniki skladniki, PrzepisNaMurzynka przepis) {

	}

	private void wylejCiastoNaBlaszke(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void nastawPiekarnik(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void wstawBlaszkeDoPiekarnika(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void odczekajCzasNaUpieczenie(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private void wyjmijBlaszkeZPiekarnika(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
	}

	private void wylaczPiekarnik(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private Murzynek wyjmijCiastoZBlaszki(SprzetyKuchenne sprzetyKuchenne,
			PrzepisNaMurzynka przepis) {
		return null;
	}
}
