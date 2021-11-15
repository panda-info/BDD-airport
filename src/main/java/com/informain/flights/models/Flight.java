package com.informain.flights.models;

import java.util.*;

import static com.informain.flights.models.FlightType.BUSINESS;
import static com.informain.flights.models.FlightType.PREMIUM;
import static java.util.Arrays.asList;

public class Flight {

    private Collection<Passenger> passengers;
    private FlightType flightType;
    private PassengersTacker passengersTacker;

    public Flight(FlightType flightType, PassengersTacker passengersTacker) {
        this.passengersTacker = passengersTacker;
        passengers = new HashSet<>();
        this.flightType = flightType;
    }

    public boolean addPassenger(Passenger passenger) {
        if (!passenger.isVip() && asList(BUSINESS, PREMIUM).contains(flightType)) {
            return false;
        }
        boolean isAdded = passengers.add(passenger);
        Optional
                .of(isAdded)
                .filter(Boolean.TRUE::equals)
                .ifPresent(unused -> passengersTacker.add(passenger, getDestination()));
        return isAdded;
    }

    public Iterable<Passenger> getPassengers() {
        return Collections.unmodifiableCollection(passengers);
    }

    public boolean removePassenger(Passenger passenger) {
        if (passenger.isVip() && flightType!=PREMIUM ) {
            return false;
        }
        return passengers.remove(passenger);
    }

    public String getDestination() {
        return null;
    }
}
