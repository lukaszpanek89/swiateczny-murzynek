package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.SprzetKuchenny;

public class TypWyjsciowy {

	// TODO: Tutaj zakładamy, że murzynek już znajduje się na przekazanej w argumencie paterze.
	// TODO (cd.): Innymi słowy, klasa MurzynekNaPaterze jest tylko prostym opakowaniem na paterę z murzynkiem, utworzonym aby podnieść czytelność kodu
	// TODO (cd.): (dziwnie by to wyglądało, gdyby efektem metody upieczMurzynka() był po prostu obiekt patera ;)).
	// TODO (cd.): Wygląda to trochę na hack, więc może warto by to było jakoś przeprojektować?
	TypWyjsciowy(SprzetKuchenny patera) {

	}

	@Override
	public String toString() {
		return "\n" +
				"\n" +
				"                        ,a@@@@@@@@@@@@@@@@@@@@@@@@@a.\n" +
				"                .,a@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@a,.\n" +
				"              ,a@@@@@@@@@@@@@@@@@@@@@.@@@@@@@@@@@@@@@@@@@@@@@@a,\n" +
				"             a@@@@@@@@@@@@@@@@@@@@@' . `@@@@@@@@@@@@@@@@@@@@@@@@a\n" +
				"             ;`@@@@@@@@@@@@@@@@@@'   .   `@@@@@@@@@@@@@@@@@@@@@';\n" +
				"             ;@@@`@@@@@@@@@@@@@'     .     `@@@@@@@@@@@@@@@@'@@@;\n" +
				"             ;@@@;,.aaaaaaaaaa       .       aaaaa,,aaaaaaa,;@@@;\n" +
				"             ;;@;;;;@@@@@@@@;@      @.@      ;@@@;;;@@@@@@;;;;@@;\n" +
				"             ;;;;;;;@@@@;@@;;@    @@ . @@    ;;@;;;;@@;@@@;;;;;;;\n" +
				"             ;;;;;;;;@@;;;;;;;  @@   .   @@  ;;;;;;;;;;;@@;;;;@;;\n" +
				"             ;;;;;;;;;;;;;;;;;@@     .     @@;;;;;;;;;;;;;;;;@@@;\n" +
				"         ,%%%;;;;;;;;@;;;;;;;;       .       ;;;;;;;;;;;;;;;;@@;;%%%,\n" +
				"      .%%%%%%;;;;;;;@@;;;;;;;;     ,%%%,     ;;;;;;;;;;;;;;;;;;;;%%%%%%,\n" +
				"     .%%%%%%%;;;;;;;@@;;;;;;;;   ,%%%%%%%,   ;;;;;;;;;;;;;;;;;;;;%%%%%%%,\n" +
				"     %%%%%%%%`;;;;;;;;;;;;;;;;  %%%%%%%%%%%  ;;;;;;;;;;;;;;;;;;;'%%%%%%%%\n" +
				"     %%%%%%%%%%%%`;;;;;;;;;;;;,%%%%%%%%%%%%%,;;;;;;;;;;;;;;;'%%%%%%%%%%%%\n" +
				"     `%%%%%%%%%%%%%%%%%,,,,,,,%%%%%%%%%%%%%%%,,,,,,,%%%%%%%%%%%%%%%%%%%%'\n" +
				"       `%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%'\n" +
				"           `%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%'\n" +
				"                 `%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%'\n" +
				"\n";
	}

}
