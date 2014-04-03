package com.prodyna.pac.aaa.aircraft;

import java.util.List;

/**
 * CRUD service interface for {@link AircraftType}.
 * 
 * @author Sergej Herdt, PRODYNA AG
 * 
 */
public interface AircraftTypeService {

	/**
	 * Creates a new aircraft type.
	 * 
	 * @param aircraftType
	 *            New aircraft type to create.
	 * @return Created aircraft type.
	 */
	AircraftType createAircraftType(AircraftType aircraftType);

	/**
	 * Retrieves a list of all aircraft types.
	 * 
	 * @return List of all available aircraft types.
	 */
	List<AircraftType> readAircraftTypes();

	/**
	 * Updates given aircraft type.
	 * 
	 * @param aircraftType
	 *            Aircraft type to update.
	 * @return Updated aircraft type.
	 */
	AircraftType updateAircraftType(AircraftType aircraftType);

	/**
	 * Deletes aircraft type with given name (which is the ID).
	 * 
	 * @param name
	 *            Name of the aircraft type to delete.
	 */
	void deleteAircraftType(String name);
}
