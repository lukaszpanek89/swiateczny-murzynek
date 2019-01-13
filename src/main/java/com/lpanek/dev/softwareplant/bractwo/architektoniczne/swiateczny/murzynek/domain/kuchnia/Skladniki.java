package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Skladniki implements Iterable<Skladnik> {

	private final Set<Skladnik> skladniki = new HashSet<Skladnik>();

	public static Skladniki pustyZbior() {
		return new Skladniki();
	}

	public void dodaj(Skladnik skladnik) {

	}

	public Iterator<Skladnik> iterator() {
		return skladniki.iterator();
	}
}
