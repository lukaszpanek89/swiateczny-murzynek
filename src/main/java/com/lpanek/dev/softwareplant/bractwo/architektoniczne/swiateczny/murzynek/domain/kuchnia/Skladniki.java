package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Skladniki implements Iterable<Skladnik> {

	private final Map<IdSkladnika, Skladnik> skladniki = new HashMap<IdSkladnika, Skladnik>();

	public static Skladniki pustyZbior() {
		return new Skladniki();
	}

	public void dodaj(Skladnik skladnik) {

	}

	public Skladnik wez(IdSkladnika idSkladnika) {
		return new Skladnik();
	}

	public Iterator<Skladnik> iterator() {
		return skladniki.values().iterator();
	}
}
