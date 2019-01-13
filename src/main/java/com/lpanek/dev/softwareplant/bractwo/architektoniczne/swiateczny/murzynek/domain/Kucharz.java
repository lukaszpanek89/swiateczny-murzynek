package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.CzasPracyPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Kuchnia;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Skladnik;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.Skladniki;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.SprzetKuchenny;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.SprzetyKuchenne;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TemperaturaPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TrybPracyPiekarnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.TypSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.PrzepisNaMurzynka;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaPrzygotowaniaBlaszki;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaSkladnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis.SpecyfikacjaUstawienPiekarnika;

public class Kucharz {

	private static final IdSprzetuKuchennego ID_GARNKA_DO_CIASTA = new IdSprzetuKuchennego("Garnek do przygotowania ciasta");

	private static final IdSprzetuKuchennego ID_WYKALACZEK_DO_MURZYNKA = new IdSprzetuKuchennego("Wykalaczki do sprawdzenia upieczenia murzynka");

	private static final IdSprzetuKuchennego ID_NOZA_DO_MURZYNKA = new IdSprzetuKuchennego("Noz do pokrojenia murzynka");

	private static final IdSprzetuKuchennego ID_LOPATKI_DO_MURZYNKA = new IdSprzetuKuchennego("Lopatka do wyjecia murzynka");

	private static final IdSprzetuKuchennego ID_PATERY_DO_MURZYNKA = new IdSprzetuKuchennego("Patera do murzynka");

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

		SpecyfikacjaSprzetuKuchennego specyfikacjaGarnka = specyfikacjaGarnkaDoCiasta();
		SprzetKuchenny garnek = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaGarnka);
		sprzetyKuchenne.dodaj(garnek);

		SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczek = specyfikacjaWykalaczekDoMurzynka();
		SprzetKuchenny wykalaczki = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaWykalaczek);
		sprzetyKuchenne.dodaj(wykalaczki);

		SpecyfikacjaSprzetuKuchennego specyfikacjaNoza = specyfikacjaNozaDoMurzynka();
		SprzetKuchenny noz = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaNoza);
		sprzetyKuchenne.dodaj(noz);

		SpecyfikacjaSprzetuKuchennego specyfikacjaLopatki = specyfikacjaLopatkiDoWyjeciaMurzynka();
		SprzetKuchenny lopatka = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaLopatki);
		sprzetyKuchenne.dodaj(lopatka);

		SpecyfikacjaSprzetuKuchennego specyfikacjaPatery = specyfikacjaPateryDoMurzynka();
		SprzetKuchenny patera = znajdzWKuchniSprzetKuchennyLubZglosBrak(specyfikacjaPatery);
		sprzetyKuchenne.dodaj(patera);

		return sprzetyKuchenne;
	}

	private SprzetKuchenny wezGarnek() {
		return sprzetyKuchenne.wez(ID_GARNKA_DO_CIASTA);
	}

	private void dodajSkladnikiDoGarnka(SprzetKuchenny garnek) {
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : przepis.skladnikiPotrzebneDoPrzygotowaniaCiasta()) {
			Skladnik odmierzonySkladnik = odmierzPorcjeSkladnikaDoDodania(specyfikacjaSkladnika);
			dodajSkladnikDoGarnka(odmierzonySkladnik, garnek);
		}
	}

	private void wymieszajSkladnikiWGarnku(SprzetKuchenny garnek) {
		SprzetKuchenny lyzka = wezLyzkeDoMieszaniaCiasta();
		do {
			zamieszajCiastoWGarnku(garnek, lyzka);
		} while (ciastoWymagaDalszegoMieszania(garnek));
		sprzetyKuchenne.zwroc(lyzka);
	}

	private SprzetKuchenny wezBlaszke() {
		SpecyfikacjaSprzetuKuchennego specyfikacjaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki().specyfikacjaBlaszki();
		return sprzetyKuchenne.wez(specyfikacjaBlaszki.id());
	}

	private void przygotujBlaszkePodMurzynka(SprzetKuchenny blaszka) {
		SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki();
		przygotujBlaszkeZgodnieZeSpecyfikacja(blaszka, specyfikacjaPrzygotowaniaBlaszki);
	}

	private void wylejZmieszaneSkladnikiDoBlaszki(SprzetKuchenny garnek, SprzetKuchenny blaszka) {

	}

	private void nastawPiekarnikZMinimalnymCzasemPieczenia() {
		SpecyfikacjaUstawienPiekarnika specyfikacjaPiekarnika = przepis.specyfikacjaUstawienPiekarnika();
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		nastawTemperaturePiekarnika(piekarnik, specyfikacjaPiekarnika.temperatura());
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.GORA_DOL);
		nastawCzasPieczeniaPiekarnika(piekarnik, specyfikacjaPiekarnika.minimalnyCzasPieczenia());
	}

	private void zaczekajNaNagrzaniePiekarnika() {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		while (piekarnikNagrzewaSie(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void wstawBlaszkeDoPiekarnika(SprzetKuchenny blaszka) {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		otworzPiekarnik(piekarnik);
		wstawBlaszkeDoOtwartegoPiekarnika(blaszka, piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void zaczekajNaSkonczeniePieczenia() {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		while (czasPieczeniaNieUplynal(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void dopieczMurzynkaJesliPotrzeba() {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
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
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		otworzPiekarnik(piekarnik);
		SprzetKuchenny blaszka = wyjmijBlaszkeZOtwartegoPiekarnika(piekarnik);
		zamknijPiekarnik(piekarnik);
		return blaszka;
	}

	private void wylaczPiekarnik() {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
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
		SprzetKuchenny noz = sprzetyKuchenne.wez(ID_NOZA_DO_MURZYNKA);
		SprzetKuchenny lopatka = sprzetyKuchenne.wez(ID_LOPATKI_DO_MURZYNKA);
		SprzetKuchenny patera = sprzetyKuchenne.wez(ID_PATERY_DO_MURZYNKA);
		pokrojMurzynkaNaKawalki(blaszka, noz);
		MurzynekNaPaterze murzynekNaPaterze = przelozKawalkiMurzynkaNaPatere(blaszka, patera, lopatka);
		sprzetyKuchenne.zwroc(noz);
		sprzetyKuchenne.zwroc(lopatka);
		sprzetyKuchenne.zwroc(patera);
		return murzynekNaPaterze;
	}

	// --- DOTAD ---

	private Skladnik znajdzWKuchniSkladnikLubZglosBrak(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		return new Skladnik(); // TODO: Znaleźć składnik w kuchni
	}

	private SprzetKuchenny znajdzWKuchniSprzetKuchennyLubZglosBrak(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
		return new SprzetKuchenny(); // TODO: Znaleźć sprzęt w kuchni
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaGarnkaDoCiasta() {
		return new SpecyfikacjaSprzetuKuchennego(ID_GARNKA_DO_CIASTA, TypSprzetuKuchennego.GARNEK);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczekDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_WYKALACZEK_DO_MURZYNKA, TypSprzetuKuchennego.WYKALACZKI);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaNozaDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_NOZA_DO_MURZYNKA, TypSprzetuKuchennego.NOZ_DO_CIASTA);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaLopatkiDoWyjeciaMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_LOPATKI_DO_MURZYNKA, TypSprzetuKuchennego.LOPATKA_DO_CIASTA);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaPateryDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_PATERY_DO_MURZYNKA, TypSprzetuKuchennego.PATERA_DO_CIASTA);
	}

	private Skladnik odmierzPorcjeSkladnikaDoDodania(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		Skladnik skladnik = skladniki.wez(specyfikacjaSkladnika.id());
		Skladnik odmierzonySkladnik = new Skladnik(); // TODO: Odmierzyć składnik zgodnie ze specyfikacją
		skladniki.dodaj(skladnik);
		return odmierzonySkladnik;
	}

	private void dodajSkladnikDoGarnka(Skladnik skladnik, SprzetKuchenny garnek) {

	}

	private SprzetKuchenny wezLyzkeDoMieszaniaCiasta() {
		SpecyfikacjaSprzetuKuchennego specyfikacjaLyzki = przepis.specyfikacjaPrzygotowaniaCiasta().specyfikacjaLyzkiDoMieszania();
		return sprzetyKuchenne.wez(specyfikacjaLyzki.id());
	}

	private void zamieszajCiastoWGarnku(SprzetKuchenny garnek, SprzetKuchenny lyzka) {

	}

	private boolean ciastoWymagaDalszegoMieszania(SprzetKuchenny garnek) {
		return false;
	}

	private void przygotujBlaszkeZgodnieZeSpecyfikacja(SprzetKuchenny blaszka, SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki) {

	}

	private SprzetKuchenny przejdzDoPiekarnika() {
		IdSprzetuKuchennego idPiekarnika = przepis.specyfikacjaUstawienPiekarnika().specyfikacjaPiekarnika().id();
		return sprzetyKuchenne.uzyj(idPiekarnika);
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
