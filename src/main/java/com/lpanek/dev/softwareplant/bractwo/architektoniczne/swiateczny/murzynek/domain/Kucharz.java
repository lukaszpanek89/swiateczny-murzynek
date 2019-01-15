package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.CzasPieczeniaPiekarnika;
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
import java.util.Set;

public class Kucharz {

	// TODO: Wydzielić te stałe (oraz metody z nich korzystające) do odrębnej klasy?
	private static final IdSprzetuKuchennego ID_GARNKA_DO_MURZYNKA = new IdSprzetuKuchennego("Garnek do przygotowania ciasta pod murzynka");

	private static final IdSprzetuKuchennego ID_WYKALACZEK_DO_MURZYNKA = new IdSprzetuKuchennego("Wykalaczki do sprawdzenia murzynka");

	private static final IdSprzetuKuchennego ID_NOZA_DO_MURZYNKA = new IdSprzetuKuchennego("Noz do pokrojenia murzynka");

	private static final IdSprzetuKuchennego ID_LOPATKI_DO_MURZYNKA = new IdSprzetuKuchennego("Lopatka do wyjecia murzynka");

	private static final IdSprzetuKuchennego ID_PATERY_DO_MURZYNKA = new IdSprzetuKuchennego("Patera do podawania murzynka");

	private final Kuchnia kuchnia;

	private PrzepisNaMurzynka przepis;

	private Skladniki skladniki;

	private SprzetyKuchenne sprzetyKuchenne;

	public Kucharz(Kuchnia kuchnia) {
		this.kuchnia = kuchnia;
	}

	private void ____________POZIOM_ABSTRAKCJI_1____________() {
	}

	public MurzynekNaPaterze upieczMurzynka(PrzepisNaMurzynka przepis) {
		this.przepis = przepis;
		zbierzZKuchniPotrzebneSkladnikiISprzetyKuchenne();
		SprzetKuchenny blaszka = przygotujBlaszkeZeZmieszanymiSkladnikamiMurzynka();
		blaszka = upieczMurzynkaWPiekarniku(blaszka);
		return poOstygnieciuPokrojMurzynkaIPrzeniesGoNaPatere(blaszka);
	}

	private void ____________POZIOM_ABSTRAKCJI_2____________() {
	}

	private void zbierzZKuchniPotrzebneSkladnikiISprzetyKuchenne() {
		zbierzZKuchniPotrzebneSkladniki();
		zbierzZKuchniPotrzebneSprzetyKuchenne();
		// TODO: Jeśli nie uda się znaleźć któregoś składnika lub sprzętu, to trzeba odłożyć na miejsce te już zebrane
	}

	private SprzetKuchenny przygotujBlaszkeZeZmieszanymiSkladnikamiMurzynka() {
		SprzetKuchenny garnek = dodajSkladnikiDoGarnka();
		wymieszajSkladnikiWGarnku(garnek);
		SprzetKuchenny blaszka = przygotujBlaszkePrzedWylaniemSkladnikow();
		wylejZmieszaneSkladnikiDoBlaszki(garnek, blaszka);
		return blaszka;
	}

	private SprzetKuchenny upieczMurzynkaWPiekarniku(SprzetKuchenny blaszka) {
		SprzetKuchenny piekarnik = nastawPiekarnikZMinimalnymCzasemPieczenia();
		zaczekajNaNagrzaniePiekarnika(piekarnik);
		wstawBlaszkeDoPiekarnika(blaszka, piekarnik);
		zaczekajNaSkonczeniePieczenia(piekarnik);
		dopieczMurzynkaJesliPotrzeba(piekarnik);
		blaszka = wyjmijBlaszkeZPiekarnika(piekarnik);
		wylaczPiekarnik(piekarnik);
		return blaszka;
	}

	private MurzynekNaPaterze poOstygnieciuPokrojMurzynkaIPrzeniesGoNaPatere(SprzetKuchenny blaszka) {
		zaczekajNaOstygniecieMurzynka(blaszka);
		pokrojMurzynkaWBlaszce(blaszka);
		return przelozMurzynkaZBlaszkiNaPatere(blaszka);
	}

	private void ____________POZIOM_ABSTRAKCJI_3____________() {
	}

	private void zbierzZKuchniPotrzebneSkladniki() {
		Set<SpecyfikacjaSkladnika> specyfikacjeSkladnikowZPrzepisu = przepis.potrzebneSkladniki();
		Skladniki skladniki = Skladniki.pustyZbior();
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : specyfikacjeSkladnikowZPrzepisu) {
			Skladnik skladnik = znajdzWKuchniSkladnikLubZglosBrak(specyfikacjaSkladnika);
			skladniki.dodaj(skladnik);
		}
		this.skladniki = skladniki;
	}

	private void zbierzZKuchniPotrzebneSprzetyKuchenne() {
		Set<SpecyfikacjaSprzetuKuchennego> specyfikacjeSprzetowZPrzepisu = przepis.potrzebneSprzetyKuchenne();
		Set<SpecyfikacjaSprzetuKuchennego> specyfikacjePozostalychSprzetow = specyfikacjePozostalychPotrzebnychSprzetowKuchennych();
		Set<SpecyfikacjaSprzetuKuchennego> specyfikacjeWszystkichSprzetow = suma(specyfikacjeSprzetowZPrzepisu, specyfikacjePozostalychSprzetow);
		SprzetyKuchenne sprzetyKuchenne = SprzetyKuchenne.pustyZbior();
		for (SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu : specyfikacjeWszystkichSprzetow) {
			SprzetKuchenny sprzet = znajdzWKuchniSprzetLubZglosBrak(specyfikacjaSprzetu);
			sprzetyKuchenne.dodaj(sprzet);
		}
		this.sprzetyKuchenne = sprzetyKuchenne;
	}

	private SprzetKuchenny dodajSkladnikiDoGarnka() {
		SprzetKuchenny garnek = wezGarnek();
		for (SpecyfikacjaSkladnika specyfikacjaSkladnika : przepis.skladnikiPotrzebneDoPrzygotowaniaCiasta()) {
			Skladnik odmierzonySkladnik = odmierzPorcjeSkladnikaDoDodania(specyfikacjaSkladnika);
			dodajSkladnikDoGarnka(odmierzonySkladnik, garnek);
		}
		return garnek;
	}

	private void wymieszajSkladnikiWGarnku(SprzetKuchenny garnek) {
		SprzetKuchenny lyzka = wezLyzkeDoMieszaniaCiasta();
		do {
			zamieszajCiastoWGarnku(garnek, lyzka);
		} while (ciastoWymagaDalszegoMieszania(garnek));
		sprzetyKuchenne.odloz(lyzka);
	}

	private SprzetKuchenny przygotujBlaszkePrzedWylaniemSkladnikow() {
		SprzetKuchenny blaszka = wezBlaszke();
		SpecyfikacjaPrzygotowaniaBlaszki specyfikacjaPrzygotowaniaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki();
		if (specyfikacjaPrzygotowaniaBlaszki.czyBlaszkaMaBycWysmarowanaTluszczem()) {
			wysmarujBlaszkeTluszczem(blaszka);
		}
		if (specyfikacjaPrzygotowaniaBlaszki.czyBlaszkaMaBycPosypanaBulkaTarta()) {
			posypBlaszkeBulkaTarta(blaszka);
		}
		return blaszka;
	}

	private void wylejZmieszaneSkladnikiDoBlaszki(SprzetKuchenny garnek, SprzetKuchenny blaszka) {

	}

	private SprzetKuchenny nastawPiekarnikZMinimalnymCzasemPieczenia() {
		SprzetKuchenny piekarnik = przejdzDoPiekarnika();
		SpecyfikacjaUstawienPiekarnika specyfikacjaPiekarnika = przepis.specyfikacjaUstawienPiekarnika();
		nastawTemperaturePiekarnika(piekarnik, specyfikacjaPiekarnika.temperatura());
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.GORA_DOL);
		nastawCzasPieczeniaPiekarnika(piekarnik, specyfikacjaPiekarnika.minimalnyCzasPieczenia());
		return piekarnik;
	}

	private void zaczekajNaNagrzaniePiekarnika(SprzetKuchenny piekarnik) {
		while (piekarnikNagrzewaSie(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void wstawBlaszkeDoPiekarnika(SprzetKuchenny blaszka, SprzetKuchenny piekarnik) {
		otworzPiekarnik(piekarnik);
		wstawBlaszkeDoOtwartegoPiekarnika(blaszka, piekarnik);
		zamknijPiekarnik(piekarnik);
	}

	private void zaczekajNaSkonczeniePieczenia(SprzetKuchenny piekarnik) {
		while (piekarnikPiecze(piekarnik)) {
			czekajPrzezMinute();
		}
	}

	private void dopieczMurzynkaJesliPotrzeba(SprzetKuchenny piekarnik) {
		if (murzynekJestDopieczony(piekarnik)) {
			return;
		}
		nastawDodatkowyCzasPieczeniaPiekarnika(piekarnik);
		while (murzynekJestNiedopieczony(piekarnik) && piekarnikPiecze(piekarnik)) {
			czekajPrzezPiecMinut();
		}
		if (murzynekJestNiedopieczony(piekarnik)) {
			// TODO: Potrzebna obługa tego problemu
			zglosProblemZUpieczeniemMurzynkaWPiekarniku();
		}
	}

	private SprzetKuchenny wyjmijBlaszkeZPiekarnika(SprzetKuchenny piekarnik) {
		otworzPiekarnik(piekarnik);
		SprzetKuchenny blaszka = wyjmijBlaszkeZOtwartegoPiekarnika(piekarnik);
		zamknijPiekarnik(piekarnik);
		return blaszka;
	}

	private void wylaczPiekarnik(SprzetKuchenny piekarnik) {
		nastawTemperaturePiekarnika(piekarnik, TemperaturaPiekarnika.ZERO);
		nastawTrybPracyPiekarnika(piekarnik, TrybPracyPiekarnika.WYLACZONY);
		nastawCzasPieczeniaPiekarnika(piekarnik, CzasPieczeniaPiekarnika.ZERO);
	}

	private void zaczekajNaOstygniecieMurzynka(SprzetKuchenny blaszka) {
		while (murzynekWBlaszceJestCieply(blaszka)) {
			czekajPrzezDziesiecMinut();
		}
	}

	private void pokrojMurzynkaWBlaszce(SprzetKuchenny blaszka) {
		SprzetKuchenny noz = sprzetyKuchenne.wez(ID_NOZA_DO_MURZYNKA);
		while (nieCalyMurzynekWBlaszceJestPokrojonyNaKawalki(blaszka)) {
			pokrojNaKawalkiRzadMurzynka(blaszka, noz);
		}
		sprzetyKuchenne.odloz(noz);
	}

	private MurzynekNaPaterze przelozMurzynkaZBlaszkiNaPatere(SprzetKuchenny blaszka) {
		SprzetKuchenny patera = sprzetyKuchenne.wez(ID_PATERY_DO_MURZYNKA);
		SprzetKuchenny lopatka = sprzetyKuchenne.wez(ID_LOPATKI_DO_MURZYNKA);
		while (nieCalyMurzynekJestPrzeniesionyZBlaszki(blaszka)) {
			przelozRzadKawalkowMurzynkaZBlaszkiNaPatere(blaszka, patera, lopatka);
		}
		sprzetyKuchenne.odloz(lopatka);
		return new MurzynekNaPaterze(patera);
	}

	private void ____________POZIOM_ABSTRAKCJI_4____________() {
	}

	private Set<SpecyfikacjaSprzetuKuchennego> specyfikacjePozostalychPotrzebnychSprzetowKuchennych() {
		return Sets.newHashSet(
				specyfikacjaGarnkaDoMurzynka(),
				specyfikacjaWykalaczekDoMurzynka(),
				specyfikacjaNozaDoMurzynka(),
				specyfikacjaLopatkiDoMurzynka(),
				specyfikacjaPateryDoMurzynka());
	}

	private Skladnik znajdzWKuchniSkladnikLubZglosBrak(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		return new Skladnik(); // TODO: Znaleźć składnik w kuchni
	}

	private SprzetKuchenny znajdzWKuchniSprzetLubZglosBrak(SpecyfikacjaSprzetuKuchennego specyfikacjaSprzetu) {
		return new SprzetKuchenny(); // TODO: Znaleźć sprzęt w kuchni
	}

	private SprzetKuchenny wezGarnek() {
		return sprzetyKuchenne.wez(ID_GARNKA_DO_MURZYNKA);
	}

	private Skladnik odmierzPorcjeSkladnikaDoDodania(SpecyfikacjaSkladnika specyfikacjaSkladnika) {
		Skladnik skladnik = skladniki.wez(specyfikacjaSkladnika.id());
		Skladnik odmierzonySkladnik = new Skladnik(); // TODO: Odmierzyć składnik zgodnie ze specyfikacją
		skladniki.odloz(skladnik); // TODO: Zwrócić pomniejszoną ilość składnika
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

	private SprzetKuchenny wezBlaszke() {
		SpecyfikacjaSprzetuKuchennego specyfikacjaBlaszki = przepis.specyfikacjaPrzygotowaniaBlaszki().specyfikacjaBlaszki();
		return sprzetyKuchenne.wez(specyfikacjaBlaszki.id());
	}

	private void wysmarujBlaszkeTluszczem(SprzetKuchenny blaszka) {

	}

	private void posypBlaszkeBulkaTarta(SprzetKuchenny blaszka) {

	}

	private SprzetKuchenny przejdzDoPiekarnika() {
		IdSprzetuKuchennego idPiekarnika = przepis.specyfikacjaUstawienPiekarnika().specyfikacjaPiekarnika().id();
		return sprzetyKuchenne.uzyj(idPiekarnika);
	}

	private void nastawTemperaturePiekarnika(SprzetKuchenny piekarnik, TemperaturaPiekarnika temperatura) {

	}

	private void nastawTrybPracyPiekarnika(SprzetKuchenny piekarnik, TrybPracyPiekarnika trybPracy) {

	}

	private void nastawCzasPieczeniaPiekarnika(SprzetKuchenny piekarnik, CzasPieczeniaPiekarnika czasPieczenia) {

	}

	private void nastawDodatkowyCzasPieczeniaPiekarnika(SprzetKuchenny piekarnik) {

	}

	private void otworzPiekarnik(SprzetKuchenny piekarnik) {

	}

	private void zamknijPiekarnik(SprzetKuchenny piekarnik) {

	}

	private void wstawBlaszkeDoOtwartegoPiekarnika(SprzetKuchenny blaszka, SprzetKuchenny piekarnik) {

	}

	private SprzetKuchenny wyjmijBlaszkeZOtwartegoPiekarnika(SprzetKuchenny piekarnik) {
		return new SprzetKuchenny(); // TODO: Wyjmować blaszkę z piekarnika
	}

	private boolean piekarnikNagrzewaSie(SprzetKuchenny piekarnik) {
		return false;
	}

	private boolean piekarnikPiecze(SprzetKuchenny piekarnik) {
		return false;
	}

	private boolean murzynekJestDopieczony(SprzetKuchenny piekarnik) {
		return true;
	}

	private boolean murzynekJestNiedopieczony(SprzetKuchenny piekarnik) {
		return false;
	}

	private void czekajPrzezMinute() {

	}

	private void czekajPrzezPiecMinut() {

	}

	private void czekajPrzezDziesiecMinut() {

	}

	private void zglosProblemZUpieczeniemMurzynkaWPiekarniku() {

	}

	private boolean murzynekWBlaszceJestCieply(SprzetKuchenny blaszka) {
		return false;
	}

	private boolean nieCalyMurzynekWBlaszceJestPokrojonyNaKawalki(SprzetKuchenny blaszka) {
		return false;
	}

	private void pokrojNaKawalkiRzadMurzynka(SprzetKuchenny blaszka, SprzetKuchenny noz) {

	}

	private boolean nieCalyMurzynekJestPrzeniesionyZBlaszki(SprzetKuchenny blaszka) {
		return false;
	}

	private void przelozRzadKawalkowMurzynkaZBlaszkiNaPatere(SprzetKuchenny blaszka, SprzetKuchenny patera, SprzetKuchenny lopatka) {

	}

	private void ____________POZIOM_ABSTRAKCJI_5____________() {
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaGarnkaDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_GARNKA_DO_MURZYNKA, TypSprzetuKuchennego.GARNEK);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaWykalaczekDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_WYKALACZEK_DO_MURZYNKA, TypSprzetuKuchennego.WYKALACZKI);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaNozaDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_NOZA_DO_MURZYNKA, TypSprzetuKuchennego.NOZ_DO_CIASTA);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaLopatkiDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_LOPATKI_DO_MURZYNKA, TypSprzetuKuchennego.LOPATKA_DO_CIASTA);
	}

	private SpecyfikacjaSprzetuKuchennego specyfikacjaPateryDoMurzynka() {
		return new SpecyfikacjaSprzetuKuchennego(ID_PATERY_DO_MURZYNKA, TypSprzetuKuchennego.PATERA_DO_CIASTA);
	}

	private void ____________POZIOM_ABSTRAKCJI_6____________() {
	}

	// TODO: Z poziomem abstrakcji tej metody jest coś nie tak - słusznie jest ona bardzo nisko (jest bardzo techniczna), ale z drugiej strony jest
	// TODO (cd.): wywoływana przez metodę z poziomu 2, a zatem następuje tutaj skok z 2. do 5. poziomu...
	// TODO (cd.): Może po prostu brakuje tutaj klasy SpecyfikacjeSprzetuKuchennego, która opakowywałaby Set<SpecyfikacjaSprzetuKuchennego> - wtedy metoda
	// TODO (cd.): suma() byłaby metodą tej klasy opakowującej?
	private Set<SpecyfikacjaSprzetuKuchennego> suma(Set<SpecyfikacjaSprzetuKuchennego> specyfikacje1, Set<SpecyfikacjaSprzetuKuchennego> specyfikacje2) {
		return Sets.newHashSet(Iterables.concat(specyfikacje1, specyfikacje2));
	}
}
