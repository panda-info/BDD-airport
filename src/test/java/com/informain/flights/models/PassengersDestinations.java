package com.informain.flights.models;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.informain.flights.models.FlightType.ECONOMIC;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PassengersDestinations {
    private Passenger passenger;
    private PassengersTacker passengersTacker;

    @Given("We have passenger")
    public void weHavePassenger() {
        passenger = mock(Passenger.class);
    }

    @And("A Passenger Tacker")
    public void aPassengerTacker() {
        passengersTacker = new PassengersTacker();
    }

    @When("He takes a flights to")
    public void heTakesAFlightsTo(List<String> destinations) {
        destinations.forEach(destination -> passengersTacker.add(passenger, destination));
    }

    @Then("We should retrieve all his destinations without duplication")
    public void weShouldRetrieveAllHisDestinations(List<String> cities) {
        Iterable<String> destinationsForPassenger =  passengersTacker.getDestinationsFor(passenger);
        assertThat(destinationsForPassenger)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrderElementsOf(cities);
    }

    @When("He takes a flights to cities n times")
    public void heTakesAFlightsToCitiesNTimes(DataTable dataTable) {
        dataTable
                .asLists()
                .stream()
                .skip(1)
                .forEach(row -> {
                    String destination = row.get(0);
                    int count = parseInt(row.get(1));
                    IntStream.range(0, count).forEach(unusedInt -> passengersTacker.add(passenger, destination));
                });
    }

    @Then("We should retrieve all his visited cities with count")
    public void weShouldRetrieveAllHisVisitedCitiesWithCount(DataTable dataTable) {
        Function<List<String>, String> keyMapper = list -> list.get(0);
        Function<List<String>, Integer> valueMapper = list -> Integer.parseInt(list.get(1));
        Map<String, Integer> countByDestination = dataTable
                .asLists()
                .stream()
                .skip(1)
                .collect(toMap(keyMapper, valueMapper, Integer::sum));
        assertThat(passengersTacker.getDestinationsAndCountFor(passenger))
                .containsAllEntriesOf(countByDestination);
    }

    @When("He takes a flight to {string}")
    public void heTakesAFlightToParis(String destination) {
        passengersTacker.add(passenger, destination);
    }

    @Then("We should retrieve his destination travel {string}")
    public void weShouldRetrieveHisDestinationTravelParis(String destination) {
        Iterable<String> destinationsForPassenger = passengersTacker.getDestinationsFor(passenger);
        assertThat(destinationsForPassenger)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(destination);
    }

    @When("He takes a flight to {word}")
    public void heTakesAFlightToLondon(String destination) {
        passengersTacker.add(passenger, destination);
    }

    @Then("We should retrieve his destination travel {word}")
    public void weShouldRetrieveHisDestinationTravelLondon(String destination) {
        Iterable<String> destinationsForPassenger = passengersTacker.getDestinationsFor(passenger);
        assertThat(destinationsForPassenger)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(destination);
    }

    @When("He takes a flight {} to {}")
    public void heTakesAFlightToBerlin(int count, String destination) {
        IntStream.range(0, count).forEach(unused -> passengersTacker.add(passenger, destination));
    }

    @Then("We should retrieve his {} travels having destination {}")
    public void weShouldRetrieveHisTravelsHavingDestinationBerlin(int count, String destination) {
        Map<String, Integer> destinationsAndCount = passengersTacker.getDestinationsAndCountFor(passenger);
        assertThat(destinationsAndCount.get(destination))
                .isNotNull()
                .isEqualTo(1);
    }
}
