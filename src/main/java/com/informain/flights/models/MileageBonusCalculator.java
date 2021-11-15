package com.informain.flights.models;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

public class MileageBonusCalculator {

    private final int VIP_PASSENGER_FACTOR = 10;
    private final int USUAL_PASSENGER_FACTOR = 20;

    private Map<Passenger, Integer> mileageByPassenger;

    public MileageBonusCalculator() {
        mileageByPassenger = new HashMap<>();
    }

    public void add(Passenger passenger) {
        mileageByPassenger.putIfAbsent(passenger, 0);
    }

    public void addMileages(Passenger passenger, int... miles) {
        mileageByPassenger.put(passenger, stream(miles).sum());
    }

    public int computeBonus(Passenger passenger) {
        int passengerFactor = passenger.isVip() ? VIP_PASSENGER_FACTOR : USUAL_PASSENGER_FACTOR;
        return mileageByPassenger.get(passenger) / passengerFactor;
    }
}
