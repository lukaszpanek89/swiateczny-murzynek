package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Skladniki implements Iterable<Skladnik> {

	private final Set<Skladnik> skladniki = new HashSet<Skladnik>();

	static Skladniki pustyZbior() {
		return new Skladniki();
	}

	void dodaj(Skladnik skladnik) {

	}

	public Iterator<Skladnik> iterator() {
		return skladniki.iterator();
	}
}
