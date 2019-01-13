package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.CzasPracyPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Skladnik;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Skladniki;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.SprzetKuchenny;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.SprzetyKuchenne;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TemperaturaPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TrybPracyPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.PrzepisNaMurzynka;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaPrzygotowaniaBlaszki;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaSkladnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaUstawienPiekarnika;

public class Kucharz {

	private final Kuchnia kuchnia;

	public Kucharz(Kuchnia kuchnia) {
		this.kuchnia = kuchnia;
	}

	public Murzynek upieczMurzynka(PrzepisNaMurzynka przepis) {
		Skladniki skladniki = zbierzPotrzebneSkladniki(kuchnia, przepis);
		SprzetyKuchenne sprzetyKuchenne = zbierzPotrzebneSprzetyKuchenne(kuchnia, przepis);
		dodajSkladnikiDoGarnka(sprzetyKuchenne, skladniki, przepis);
		wymieszajSkladnikiWGarnku(sprzetyKuchenne, przepis);
		przygotujBlaszkePodMurzynka(sprzetyKuchenne, skladniki, przepis);
		wylejZmieszaneSkladnikiNaBlaszke(sprzetyKuchenne, przepis);
		nastawPiekarnikZMinimalnymCzasemPieczenia(sprzetyKuchenne, przepis);
		zaczekajNaNagrzaniePiekarnika(sprzetyKuchenne, przepis);
		wstawBlaszkeDoPiekarnika(sprzetyKuchenne, przepis);
		zaczekajNaSkonczeniePieczenia(sprzetyKuchenne, przepis);
		dopieczMurzynkaJesliPotrzeba(sprzetyKuchenne, przepis);
		wyjmijBlaszkeZPiekarnika(sprzetyKuchenne, przepis);
		wylaczPiekarnik(sprzetyKuchenne, przepis);
		zaczekajNaOstygniecieMurzynka(sprzetyKuchenne, przepis);
		Murzynek murzynek = wyjmijMurzynkaZBlaszki(sprzetyKuchenne, przepis);
		return murzynek;
	}

	private Skladniki zbierzPotrzebneSkladniki(Kuchnia kuchnia, PrzepisNaMurzynka przepis) {
		Skladniki skladniki = Skladniki.pustyZbior();
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : przepis.potrzebneSkladniki()) {
			Skladnik skladnik = znajdzSkladnikLubZglosBrak(kuchnia, specyfikacjaSkladnika);
			skladniki.dodaj(skladnik);
		}
		return skladniki;
	}

	private SprzetyKuchenne zbierzPotrzebneSprzetyKuchenne(Kuchnia kuchnia, PrzepisNaMurzynka przepis) {
		SprzetyKuchenne sprzetyKuchenne = SprzetyKuchenne.pustyZbior();
		for (SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu : przepis.potrzebneSprzetyKuchenne()) {
			SprzetKuchenny sprzet = znajdzSprzetKuchennyLubZglosBrak(kuchnia, specyfikacjaSprzetu);
			sprzetyKuchenne.dodaj(specyfikacjaSprzetu, sprzet);
		}

		// TODO: Zapakowac w metody pomocnicze?
		SpecyfikacjaSprzetuKuchennego specyfikacjaGarnka = specyfikacjaGarnkaDoMieszaniaSkladnikow();
		SprzetKuchenny garnek = znajdzSprzetKuchennyLubZglosBrak(kuchnia, specyfikacjaGarnka);
		sprzetyKuchenne.dodaj(specyfikacjaGarnka, garnek);

		SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek = specyfikacjaWykalaczek();
		SprzetKuchenny wykalaczki = znajdzSprzetKuchennyLubZglosBrak(kuchnia, specyfikacjaWykalaczek);
		sprzetyKuchenne.dodaj(specyfikacjaWykalaczek, wykalaczki);

		SpecyfikacjaSprzetuKuchennego specyfikacjaNoza = specyfikacjaNozaDoPokrojeniaMurzynkaWBlaszce();
		SprzetKuchenny noz = znajdzSprzetKuchennyLubZglosBrak(kuchnia, specyfikacjaNoza);
		sprzetyKuchenne.dodaj(specyfikacjaNoza, noz);

		SpecyfikacjaSprzetuKuchennego specyfikacjaLopatki = specyfikacjaLopatkiDoWyjeciaMurzynkaZBlaszki();
		SprzetKuchenny lopatka = znajdzSprzetKuchennyLubZglosBrak(kuchnia, specyfikacjaLopatki);
		sprzetyKuchenne.dodaj(specyfikacjaLopatki, lopatka);

		return sprzetyKuchenne;
	}

	private void dodajSkladnikiDoGarnka(SprzetyKuchenne sprzetyKuchenne, Skladniki skladniki, PrzepisNaMurzynka przepis) {
		// TODO: Do poprawy
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		for (Skladnik skladnik : skladniki) {
			dodajSkladnikDoGarnka(skladnik, garnek, sprzetyKuchenne, przepis);
		}
	}

	private void wymieszajSkladnikiWGarnku(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SpecyfikacjaSprzetuKuchennego specyfikacjaLyzki = przepis.specyfikacjaMieszaniaSkladnikow().lyzkaDoMieszania();
		SprzetKuchenny lyzka = sprzetyKuchenne.wez(specyfikacjaLyzki);
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		do {
			zamieszajZawartoscGarnka(garnek, lyzka, sprzetyKuchenne);
		} while (skladnikiWymagajaDalszegoMieszania(garnek));
	}

	private void przygotujBlaszkePodMurzynka(SprzetyKuchenne sprzetyKuchenne, Skladniki skladniki, PrzepisNaMurzynka przepis) {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki();
		przygotujBlaszkeZgodnieZeSpecyfikacja(blaszka, specyfikacjaPrzygotowaniaBlaszki, sprzetyKuchenne, skladniki);
	}

	private void wylejZmieszaneSkladnikiNaBlaszke(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		wylejZawartoscGarnkaNaBlaszke(garnek, blaszka, sprzetyKuchenne);
	}

	private void nastawPiekarnikZMinimalnymCzasemPieczenia(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SpecyfikacjaUstawienPiekarnika specyfikacjaPiekarnika = przepis.specyfikacjaUstawienPiekarnika();
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		nastawTemperaturePiekarnika(piekarnik, specyfikacjaPiekarnika.temperatura());
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.GORA_DOL);
		nastawCzasPieczeniaPiekarnika(piekarnik, specyfikacjaPiekarnika.minimalnyCzasPieczenia());
	}

	private void zaczekajNaNagrzaniePiekarnika(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		while (piekarnikNagrzewaSie(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void wstawBlaszkeDoPiekarnika(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		otworzPiekarnik(piekarnik);
		wstawBlaszkeDoOtwartegoPiekarnika(blaszka, piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void zaczekajNaSkonczeniePieczenia(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		while (czasPieczeniaNieUplynal(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void dopieczMurzynkaJesliPotrzeba(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		if (murzynekJestDopieczony(piekarnik)) {
			return;
		}
		nastawDodatkowyCzasPracyPiekarnika(piekarnik, przepis);
		while (murzynekJestNiedopieczony(piekarnik) && czasPieczeniaNieUplynal(piekarnik)) {
			czekajPrzezPiecMinut();
		}
		if (murzynekJestNiedopieczony(piekarnik)) {
			zglosProblemZUpieczeniemMurzynkaWPiekarniku();
		}
	}

	private void wyjmijBlaszkeZPiekarnika(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		otworzPiekarnik(piekarnik);
		wyjmijBlaszkeZOtwartegoPiekarnika(piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void wylaczPiekarnik(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		nastawTemperaturePiekarnika(piekarnik, TemperaturaPiekarnika.ZERO);
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.WYLACZONY);
		nastawCzasPieczeniaPiekarnika(piekarnik, CzasPracyPiekarnika.ZERO);
	}

	private void zaczekajNaOstygniecieMurzynka(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		while (murzynekWBlaszceJestCieply(blaszka)) {
			czekajPrzezDziesiecMinut();
		}
	}

	private Murzynek wyjmijMurzynkaZBlaszki(SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SprzetKuchenny noz = sprzetyKuchenne.noz();
		SprzetKuchenny lopatka = sprzetyKuchenne.lopatka();

		pokrojPierwszeDwaRzedyMurzynkaWBlaszce(blaszka, noz);
		Murzynek murzynek = wyjmijMurzynkaZBlaszki(blaszka, lopatka);
		return murzynek;
	}

	// --- DOTAD ---

	private Skladnik znajdzSkladnikLubZglosBrak(Kuchnia kuchnia, SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		return new Skladnik();
	}

	private SprzetKuchenny znajdzSprzetKuchennyLubZglosBrak(Kuchnia kuchnia, SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
		return new SprzetKuchenny();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaGarnkaDoMieszaniaSkladnikow() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek() {
		return null;
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaNozaDoPokrojeniaMurzynkaWBlaszce() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaLopatkiDoWyjeciaMurzynkaZBlaszki() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private void dodajSkladnikDoGarnka(Skladnik skladnik, SprzetKuchenny garnek, SprzetyKuchenne sprzetyKuchenne, PrzepisNaMurzynka przepis) {

	}

	private SprzetKuchenny wezSprzetDoMieszania(SprzetyKuchenne sprzetyKuchenne, SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetuDoMieszania) {
		return new SprzetKuchenny();
	}

	private void zamieszajZawartoscGarnka(SprzetKuchenny garnek, SprzetKuchenny lyzka, SprzetyKuchenne sprzetyKuchenne) {

	}

	private boolean skladnikiWymagajaDalszegoMieszania(SprzetKuchenny garnek) {
		return false;
	}

	private void przygotujBlaszkeZgodnieZeSpecyfikacja(SprzetKuchenny blaszka, SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki,
			SprzetyKuchenne sprzetyKuchenne, Skladniki skladniki) {

	}

	private void wylejZawartoscGarnkaNaBlaszke(SprzetKuchenny garnek, SprzetKuchenny blaszka, SprzetyKuchenne sprzetyKuchenne) {

	}

	private void nastawTemperaturePiekarnika(SprzetKuchenny piekarnik, TemperaturaPiekarnika temperatura) {

	}

	private void nastawTrybPracyPiekarnika(SprzetKuchenny piekarnik, TrybPracyPiekarnika trybPracy) {

	}

	private void nastawCzasPieczeniaPiekarnika(SprzetKuchenny piekarnik, CzasPracyPiekarnika czasPracy) {

	}

	private void otworzPiekarnik(SprzetKuchenny piekarnik) {

	}

	private void zamknijPiekarnik(SprzetKuchenny piekarnik) {

	}

	private void wstawBlaszkeDoOtwartegoPiekarnika(SprzetKuchenny blaszka, SprzetKuchenny piekarnik) {

	}

	private void wyjmijBlaszkeZOtwartegoPiekarnika(SprzetKuchenny piekarnik) {

	}

	private boolean piekarnikNagrzewaSie(SprzetKuchenny piekarnik) {
		return false;
	}

	private boolean czasPieczeniaNieUplynal(SprzetKuchenny piekarnik) {
		return false;
	}

	private void czekajPrzezMinute() {

	}

	private void nastawDodatkowyCzasPracyPiekarnika(SprzetKuchenny piekarnik, PrzepisNaMurzynka przepis) {

	}

	private boolean murzynekJestDopieczony(SprzetKuchenny piekarnik) {
		return true;
	}

	private boolean murzynekJestNiedopieczony(SprzetKuchenny piekarnik) {
		return false;
	}

	private void czekajPrzezPiecMinut() {

	}

	private void zglosProblemZUpieczeniemMurzynkaWPiekarniku() {

	}

	private boolean murzynekWBlaszceJestCieply(SprzetKuchenny blaszka) {
		return false;
	}

	private void czekajPrzezDziesiecMinut() {

	}

	private void pokrojPierwszeDwaRzedyMurzynkaWBlaszce(SprzetKuchenny blaszka, SprzetKuchenny noz) {

	}

	private Murzynek wyjmijMurzynkaZBlaszki(SprzetKuchenny blaszka, SprzetKuchenny lopatka) {
		return new Murzynek();
	}
}
