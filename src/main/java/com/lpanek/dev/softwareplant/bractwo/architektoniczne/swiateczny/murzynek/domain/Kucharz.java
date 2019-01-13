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

	public MurzynekNaPaterze upieczMurzynka(PrzepisNaMurzynka przepis) {
		this.przepis = przepis;
		this.skladniki = zbierzZKuchniPotrzebneSkladniki();
		this.sprzetyKuchenne = zbierzZKuchniPotrzebneSprzetyKuchenne();
		SprzetKuchenny garnek = wezGarnek();
		dodajSkladnikiDoGarnka(garnek);
		wymieszajSkladnikiWGarnku(garnek);
		SprzetKuchenny blaszka = wezBlaszke();
		przygotujBlaszkePodMurzynka(blaszka);
		wylejZmieszaneSkladnikiDoBlaszki(garnek, blaszka);
		nastawPiekarnikZMinimalnymCzasemPieczenia();
		zaczekajNaNagrzaniePiekarnika();
		wstawBlaszkeDoPiekarnika(blaszka);
		zaczekajNaSkonczeniePieczenia();
		dopieczMurzynkaJesliPotrzeba();
		blaszka = wyjmijBlaszkeZPiekarnika();
		wylaczPiekarnik();
		zaczekajNaOstygniecieMurzynka(blaszka);
		return przelozMurzynkaZBlaszkiNaPatere(blaszka);
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
			sprzetyKuchenne.dodaj(sprzet);
		}

		// TODO: Zapakowac w metody pomocnicze?
		SpecyfikacjaSprzetuKuchennego specyfikacjaGarnka = specyfikacjaGarnkaDoPrzygotowaniaCiasta();
		SprzetKuchenny garnek = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaGarnka);
		sprzetyKuchenne.dodaj(garnek);

		SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek = specyfikacjaWykalaczek();
		SprzetKuchenny wykalaczki = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaWykalaczek);
		sprzetyKuchenne.dodaj(wykalaczki);

		SpecyfikacjaSprzetuKuchennego specyfikacjaNoza = specyfikacjaNozaDoPokrojeniaMurzynkaWBlaszce();
		SprzetKuchenny noz = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaNoza);
		sprzetyKuchenne.dodaj(noz);

		SpecyfikacjaSprzetuKuchennego specyfikacjaLopatki = specyfikacjaLopatkiDoWyjeciaMurzynkaZBlaszki();
		SprzetKuchenny lopatka = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaLopatki);
		sprzetyKuchenne.dodaj(lopatka);

		SpecyfikacjaSprzetuKuchennego specyfikacjaPatery = specyfikacjaPateryPodMurzynka();
		SprzetKuchenny patera = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaPatery);
		sprzetyKuchenne.dodaj(patera);

		return sprzetyKuchenne;
	}

	private SprzetKuchenny wezGarnek() {
		return null;
	}

	private void dodajSkladnikiDoGarnka(SprzetKuchenny garnek) {
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : przepis.skladnikiPotrzebneDoPrzygotowaniaCiasta()) {

		}
		// TODO: Do poprawy
		for (Skladnik skladnik : skladniki) {
			dodajSkladnikDoGarnka(skladnik, garnek);
		}
	}

	private void wymieszajSkladnikiWGarnku(SprzetKuchenny garnek) {
		SprzetKuchenny lyzka = wezLyzkeDoMieszaniaCiasta();
		do {
			zamieszajCiastoWGarnku(garnek, lyzka);
		} while (ciastoWymagaDalszegoMieszania(garnek));
	}

	private SprzetKuchenny wezBlaszke() {
		return null;
	}

	private void przygotujBlaszkePodMurzynka(SprzetKuchenny blaszka) {
		SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki();
		przygotujBlaszkeZgodnieZeSpecyfikacja(blaszka, specyfikacjaPrzygotowaniaBlaszki);
	}

	private void wylejZmieszaneSkladnikiDoBlaszki(SprzetKuchenny garnek, SprzetKuchenny blaszka) {

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

	private void wstawBlaszkeDoPiekarnika(SprzetKuchenny blaszka) {
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

	private SprzetKuchenny wyjmijBlaszkeZPiekarnika() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		otworzPiekarnik(piekarnik);
		SprzetKuchenny blaszka = wyjmijBlaszkeZOtwartegoPiekarnika(piekarnik);
		zamknijPiekarnik(piekarnik);
		return blaszka;
	}

	private void wylaczPiekarnik() {
		SprzetKuchenny piekarnik = sprzetyKuchenne.piekarnik();
		nastawTemperaturePiekarnika(piekarnik, TemperaturaPiekarnika.ZERO);
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.WYLACZONY);
		nastawCzasPieczeniaPiekarnika(piekarnik, CzasPracyPiekarnika.ZERO);
	}

	private void zaczekajNaOstygniecieMurzynka(SprzetKuchenny blaszka) {
		while (murzynekWBlaszceJestCieply(blaszka)) {
			czekajPrzezDziesiecMinut();
		}
	}

	private MurzynekNaPaterze przelozMurzynkaZBlaszkiNaPatere(SprzetKuchenny blaszka) {
		SprzetKuchenny noz = sprzetyKuchenne.noz();
		SprzetKuchenny lopatka = sprzetyKuchenne.lopatka();
		SprzetKuchenny patera = sprzetyKuchenne.patera();

		pokrojMurzynkaNaKawalki(blaszka, noz);
		MurzynekNaPaterze murzynekNaPaterze = przelozKawalkiMurzynkaNaPatere(blaszka, patera, lopatka);
		return murzynekNaPaterze;
	}

	// --- DOTAD ---

	private Skladnik znajdzWKuchniSkladnikLubZglosBrak(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		return new Skladnik();
	}

	private SprzetKuchenny znajdzWKuchniSprzetKuchennyLubZglosBrak(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
		return new SprzetKuchenny();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaGarnkaDoPrzygotowaniaCiasta() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaNozaDoPokrojeniaMurzynkaWBlaszce() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaLopatkiDoWyjeciaMurzynkaZBlaszki() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaPateryPodMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego();
	}

	private void dodajSkladnikDoGarnka(Skladnik skladnik, SprzetKuchenny garnek) {

	}

	private SprzetKuchenny wezLyzkeDoMieszaniaCiasta() {
		SpecyfikacjaSprzetuKuchennego specyfikacjaLyzki = przepis.specyfikacjaPrzygotowaniaCiasta().lyzkaDoMieszania();
		return sprzetyKuchenne.wez(specyfikacjaLyzki);
	}

	private void zamieszajCiastoWGarnku(SprzetKuchenny garnek, SprzetKuchenny lyzka) {

	}

	private boolean ciastoWymagaDalszegoMieszania(SprzetKuchenny garnek) {
		return false;
	}

	private void przygotujBlaszkeZgodnieZeSpecyfikacja(SprzetKuchenny blaszka, SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki) {

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

	private SprzetKuchenny wyjmijBlaszkeZOtwartegoPiekarnika(SprzetKuchenny piekarnik) {
		return new SprzetKuchenny();
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

	private void pokrojMurzynkaNaKawalki(SprzetKuchenny blaszka, SprzetKuchenny noz) {

	}

	private MurzynekNaPaterze przelozKawalkiMurzynkaNaPatere(SprzetKuchenny blaszka, SprzetKuchenny patera, SprzetKuchenny lopatka) {
		return new MurzynekNaPaterze();
	}
}
