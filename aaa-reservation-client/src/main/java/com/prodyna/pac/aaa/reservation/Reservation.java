package com.prodyna.pac.aaa.reservation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.prodyna.pac.aaa.aircraft.Aircraft;

/**
 * Entity representing an reservation of an aircraft for a pilot.
 * 
 * @author Sergej Herdt, PRODYNA AG
 * 
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 8060033254574982695L;

	/** The unique id for the reservation. It's recommended to use an generated UUID. */
	@Id
	private String id;

	/** The start date for the aircraft reservation (also containing the time). */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	/** The end date for the aircraft reservation (also containing the time). */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	/** The state of this reservation. */
	@JoinColumn(name = "name")
	@ManyToOne
	private ReservationState reservationState;

	/** The aircraft this reservation is for. */
	@JoinColumn(name = "tailSign")
	@ManyToOne
	private Aircraft aircraft;

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the reservationState
	 */
	public ReservationState getReservationState() {
		return this.reservationState;
	}

	/**
	 * @param reservationState
	 *            the reservationState to set
	 */
	public void setReservationState(final ReservationState reservationState) {
		this.reservationState = reservationState;
	}

	/**
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return this.aircraft;
	}

	/**
	 * @param aircraft
	 *            the aircraft to set
	 */
	public void setAircraft(final Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Reservation other = (Reservation) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Reservation [id=").append(this.id).append(", startDate=").append(this.startDate)
				.append(", endDate=").append(this.endDate).append(", reservationState=").append(this.reservationState)
				.append(", aircraft=").append(this.aircraft).append("]");
		return builder.toString();
	}

}
