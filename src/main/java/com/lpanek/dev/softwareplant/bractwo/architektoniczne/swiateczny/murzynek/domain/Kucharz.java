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

	private PrzepisNaMurzynka przepis;

	private Skladniki skladniki;

	private SprzetyKuchenne sprzetyKuchenne;

	public Kucharz(Kuchnia kuchnia) {
		this.kuchnia = kuchnia;
	}

	public Murzynek upieczMurzynka(PrzepisNaMurzynka przepis) {
		this.przepis = przepis;
		this.skladniki = zbierzZKuchniPotrzebneSkladniki();
		this.sprzetyKuchenne = zbierzZKuchniPotrzebneSprzetyKuchenne();
		dodajSkladnikiDoGarnka();
		wymieszajSkladnikiWGarnku();
		przygotujBlaszkePodMurzynka();
		wylejZmieszaneSkladnikiNaBlaszke();
		nastawPiekarnikZMinimalnymCzasemPieczenia();
		zaczekajNaNagrzaniePiekarnika();
		wstawBlaszkeDoPiekarnika();
		zaczekajNaSkonczeniePieczenia();
		dopieczMurzynkaJesliPotrzeba();
		wyjmijBlaszkeZPiekarnika();
		wylaczPiekarnik();
		zaczekajNaOstygniecieMurzynka();
		Murzynek murzynek = wyjmijMurzynkaZBlaszki();
		return murzynek;
	}

	private Skladniki zbierzZKuchniPotrzebneSkladniki() {
		Skladniki skladniki = Skladniki.pustyZbior();
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : przepis.potrzebneSkladniki()) {
			Skladnik skladnik = znajdzWKuchniSkladnikLubZglosBrak(specyfikacjaSkladnika);
			skladniki.dodaj(skladnik);
		}
		return skladniki;
	}

	private SprzetyKuchenne zbierzZKuchniPotrzebneSprzetyKuchenne() {
		SprzetyKuchenne sprzetyKuchenne = SprzetyKuchenne.pustyZbior();
		for (SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu : przepis.potrzebneSprzetyKuchenne()) {
			SprzetKuchenny sprzet = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaSprzetu);
			sprzetyKuchenne.dodaj(specyfikacjaSprzetu, sprzet);
		}

		// TODO: Zapakowac w metody pomocnicze?
		SpecyfikacjaSprzetuKuchennego specyfikacjaGarnka = specyfikacjaGarnkaDoMieszaniaSkladnikow();
		SprzetKuchenny garnek = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaGarnka);
		sprzetyKuchenne.dodaj(specyfikacjaGarnka, garnek);

		SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek = specyfikacjaWykalaczek();
		SprzetKuchenny wykalaczki = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaWykalaczek);
		sprzetyKuchenne.dodaj(specyfikacjaWykalaczek, wykalaczki);

		SpecyfikacjaSprzetuKuchennego specyfikacjaNoza = specyfikacjaNozaDoPokrojeniaMurzynkaWBlaszce();
		SprzetKuchenny noz = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaNoza);
		sprzetyKuchenne.dodaj(specyfikacjaNoza, noz);

		SpecyfikacjaSprzetuKuchennego specyfikacjaLopatki = specyfikacjaLopatkiDoWyjeciaMurzynkaZBlaszki();
		SprzetKuchenny lopatka = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaLopatki);
		sprzetyKuchenne.dodaj(specyfikacjaLopatki, lopatka);

		return sprzetyKuchenne;
	}

	private void dodajSkladnikiDoGarnka() {
		// TODO: Do poprawy
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		for (Skladnik skladnik : skladniki) {
			dodajSkladnikDoGarnka(skladnik, garnek);
		}
	}

	private void wymieszajSkladnikiWGarnku() {
		SpecyfikacjaSprzetuKuchennego specyfikacjaLyzki = przepis.specyfikacjaMieszaniaSkladnikow().lyzkaDoMieszania();
		SprzetKuchenny lyzka = sprzetyKuchenne.wez(specyfikacjaLyzki);
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		do {
			zamieszajZawartoscGarnka(garnek, lyzka);
		} while (skladnikiWymagajaDalszegoMieszania(garnek));
	}

	private void przygotujBlaszkePodMurzynka() {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki();
		przygotujBlaszkeZgodnieZeSpecyfikacja(blaszka, specyfikacjaPrzygotowaniaBlaszki);
	}

	private void wylejZmieszaneSkladnikiNaBlaszke() {
		SprzetKuchenny garnek = sprzetyKuchenne.garnek();
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		wylejZawartoscGarnkaNaBlaszke(garnek, blaszka);
	}

	private void nastawPiekarnikZMinimalnymCzasemPieczenia() {
		SpecyfikacjaUstawienPiekarnika specyfikacjaPiekarnika = przepis.specyfikacjaUstawienPiekarnika();
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		nastawTemperaturePiekarnika(piekarnik, specyfikacjaPiekarnika.temperatura());
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.GORA_DOL);
		nastawCzasPieczeniaPiekarnika(piekarnik, specyfikacjaPiekarnika.minimalnyCzasPieczenia());
	}

	private void zaczekajNaNagrzaniePiekarnika() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		while (piekarnikNagrzewaSie(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void wstawBlaszkeDoPiekarnika() {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		otworzPiekarnik(piekarnik);
		wstawBlaszkeDoOtwartegoPiekarnika(blaszka, piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void zaczekajNaSkonczeniePieczenia() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		while (czasPieczeniaNieUplynal(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void dopieczMurzynkaJesliPotrzeba() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		if (murzynekJestDopieczony(piekarnik)) {
			return;
		}
		nastawDodatkowyCzasPracyPiekarnika(piekarnik);
		while (murzynekJestNiedopieczony(piekarnik) && czasPieczeniaNieUplynal(piekarnik)) {
			czekajPrzezPiecMinut();
		}
		if (murzynekJestNiedopieczony(piekarnik)) {
			zglosProblemZUpieczeniemMurzynkaWPiekarniku();
		}
	}

	private void wyjmijBlaszkeZPiekarnika() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		otworzPiekarnik(piekarnik);
		wyjmijBlaszkeZOtwartegoPiekarnika(piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void wylaczPiekarnik() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		nastawTemperaturePiekarnika(piekarnik, TemperaturaPiekarnika.ZERO);
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.WYLACZONY);
		nastawCzasPieczeniaPiekarnika(piekarnik, CzasPracyPiekarnika.ZERO);
	}

	private void zaczekajNaOstygniecieMurzynka() {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		while (murzynekWBlaszceJestCieply(blaszka)) {
			czekajPrzezDziesiecMinut();
		}
	}

	private Murzynek wyjmijMurzynkaZBlaszki() {
		SprzetKuchenny blaszka = sprzetyKuchenne.blaszka();
		SprzetKuchenny noz = sprzetyKuchenne.noz();
		SprzetKuchenny lopatka = sprzetyKuchenne.lopatka();

		pokrojPierwszeDwaRzedyMurzynkaWBlaszce(blaszka, noz);
		Murzynek murzynek = wyjmijMurzynkaZBlaszki(blaszka, lopatka);
		return murzynek;
	}

	// --- DOTAD ---

	private Skladnik znajdzWKuchniSkladnikLubZglosBrak(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		return new Skladnik();
	}

	private SprzetKuchenny znajdzWKuchniSprzetKuchennyLubZglosBrak(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
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

	private void dodajSkladnikDoGarnka(Skladnik skladnik, SprzetKuchenny garnek) {

	}

	private SprzetKuchenny wezSprzetDoMieszania(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetuDoMieszania) {
		return new SprzetKuchenny();
	}

	private void zamieszajZawartoscGarnka(SprzetKuchenny garnek, SprzetKuchenny lyzka) {

	}

	private boolean skladnikiWymagajaDalszegoMieszania(SprzetKuchenny garnek) {
		return false;
	}

	private void przygotujBlaszkeZgodnieZeSpecyfikacja(SprzetKuchenny blaszka, SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki) {

	}

	private void wylejZawartoscGarnkaNaBlaszke(SprzetKuchenny garnek, SprzetKuchenny blaszka) {

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

	private void nastawDodatkowyCzasPracyPiekarnika(SprzetKuchenny piekarnik) {

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
