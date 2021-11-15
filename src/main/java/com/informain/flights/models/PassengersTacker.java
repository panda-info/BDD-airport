package com.informain.flights.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static java.util.stream.Stream.concat;

public class PassengersTacker {

    private Map<Passenger, Map<String, Integer>> destinationsByPassenger;

    public PassengersTacker() {
        destinationsByPassenger = new HashMap<>();
    }

    public Iterable<String> getDestinationsFor(Passenger passenger) {
        return getDestinationsAndCountFor(passenger).keySet();
    }

    public void add(Passenger passenger, String destination) {
        destinationsByPassenger.merge(passenger, new HashMap<String, Integer>() {{
            put(destination, 1);
        }}, (oldMap, newMap) -> {
            oldMap.merge(destination, 1, Integer::sum);
            return new HashMap<>(oldMap);
        });
    }

    public Map<String, Integer> getDestinationsAndCountFor(Passenger passenger) {
        return destinationsByPassenger.get(passenger);
    }
}
