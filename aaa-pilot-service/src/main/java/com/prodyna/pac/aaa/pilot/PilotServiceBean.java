/**
 * 
 */
package com.prodyna.pac.aaa.pilot;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.prodyna.pac.aaa.common.annotation.Monitored;

/**
 * 
 * Session Bean implementation class PilotServiceBean for {@link PilotService}.
 * 
 * @author Sergej Herdt, PRODYNA AG
 * 
 */
@Stateless
@Monitored
public class PilotServiceBean implements PilotService {

	/** Entity manger for DB access. */
	@Inject
	private EntityManager entityManager;

	@Override
	public Pilot createPilot(final Pilot Pilot) {
		this.entityManager.persist(Pilot);
		return Pilot;
	}

	@Override
	public Pilot readPilot(final String username) {
		final TypedQuery<Pilot> namedQuery = this.entityManager.createNamedQuery(
				PilotNamedQueries.SELECT_PILOT_BY_USERNAME, Pilot.class);
		namedQuery.setParameter("username", username);

		return namedQuery.getSingleResult();
	}

	@Override
	public List<Pilot> readPilots() {
		return this.entityManager.createNamedQuery(PilotNamedQueries.SELECT_ALL_PILOTS, Pilot.class).getResultList();
	}

	@Override
	public Pilot updatePilot(final Pilot Pilot) {
		return this.entityManager.merge(Pilot);
	}

	@Override
	public void deletePilot(final String username) {
		final Pilot pilot = this.entityManager.find(Pilot.class, username);
		this.entityManager.remove(pilot);
	}

}
