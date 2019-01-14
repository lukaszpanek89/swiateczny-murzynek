swiateczny-murzynek
====================

Motywacja
---------

_swiateczny-murzynek_ to prosta aplikacja konsolowa, w której prezentuję zastosowanie zasad opisanych przez Uncle Boba w jego książce _Clean Code_, a dotyczących pisania funkcji i metod klas.

Najważniejsze zaprezentowane w aplikacji zasady to:

* _Functions should be small!_
* _Functions should do one thing. They should do it well. They should do it only._
* _One level of abstraction per function_
* _Reading code from top to bottom: The Stepdown Rule_

Do czego służy ta aplikacja
---------------------------

Aplikacja jest implementacją prostego przepisu na murzynka (rodzaj ciasta).

Implementowany przepis to:

> 1. Składniki:
>   * 1 i 1/3 szklanki oleju
>   * 1 i 1/2 szklanki mleka
>   * 1 i 1/3 szklanki cukru
>   * 3 szklanki mąki
>   * 1 i 1/2 łyżeczki sody
>   * 2 jajka
>   * 1 słoik dżemu wiśniowego Łowicz (280 g)
>   * 3 łyżki kakao
> 2. Przygotowanie:
> 
> Wszystkie składniki wymieszać łyżką, wlać do wysmarowanej tłuszczem i posypanej bułką tartą blaszki, a następnie piec w temperaturze 150 st. C przez ok. 45-55 minut.
> 
> Smacznego!

Dla zainteresowanych: Ten przepis jest prawdziwy, tj. da się na jego podstawie upiec prawdziwego murzynka :) Zatytułowałem tę aplikację _swiateczny-murzynek_, ponieważ od kilku lat piekę tego murzynka przed Świętami Bożego Narodzenia (tylko wtedy ;))

O implementacji
---------------

Główna klasa aplikacji to `SwiatecznyMurzynek` (klasa z metodą `main()`).

Logika pieczenia murzynka znajduje się w klasie `Kucharz`, w jej jedynej publicznej metodzie `upieczMurzynka()`.
