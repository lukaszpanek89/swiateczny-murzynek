package com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.przepis;

import com.google.common.base.Optional;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSkladnika;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IdSprzetuKuchennego;
import com.lpanek.dev.softwareplant.bractwo.architektoniczne.swiateczny.murzynek.domain.kuchnia.IloscSkladnika;

public final class SpecyfikacjaSkladnika {

	private final IdSkladnika idSkladnika;

	private final IloscSkladnika iloscSkladnika;

	private final IdSprzetuKuchennego sprzetBedacyMiaraIlosciSkladnika;

	private SpecyfikacjaSkladnika(IdSkladnika idSkladnika, IloscSkladnika iloscSkladnika, IdSprzetuKuchennego sprzetBedacyMiaraIlosciSkladnika) {
		this.idSkladnika = idSkladnika;
		this.iloscSkladnika = iloscSkladnika;
		this.sprzetBedacyMiaraIlosciSkladnika = sprzetBedacyMiaraIlosciSkladnika;
	}

	public static SpecyfikacjaSkladnika skladnikPoliczalny(IdSkladnika idSkladnika, IloscSkladnika iloscSkladnika) {
		return new SpecyfikacjaSkladnika(idSkladnika, iloscSkladnika, null);
	}

	public static SpecyfikacjaSkladnika skladnikNiepoliczalny(IdSkladnika idSkladnika,
			IloscSkladnika iloscSkladnika,
			IdSprzetuKuchennego sprzetBedacyMiaraIlosciSkladnika) {
		return new SpecyfikacjaSkladnika(idSkladnika, iloscSkladnika, sprzetBedacyMiaraIlosciSkladnika);
	}

	public IdSkladnika id() {
		return idSkladnika;
	}

	public IloscSkladnika getIloscSkladnika() {
		return iloscSkladnika;
	}

	public Optional<IdSprzetuKuchennego> getSprzetBedacyMiaraIlosciSkladnika() {
		return Optional.fromNullable(sprzetBedacyMiaraIlosciSkladnika);
	}
}
