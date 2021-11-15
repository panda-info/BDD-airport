package com.informain.flights.models;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.informain.flights.models.FlightType.ECONOMIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PassengersPolicy {

    private Flight flight;
    private Passenger usualPassenger;
    private Passenger vipPassenger;
    private PassengersTacker passengersTacker = mock(PassengersTacker.class);

    @Given("^There is an economic flight$")
    public void there_is_an_economic_flight() {
        flight = new Flight(ECONOMIC, passengersTacker);
    }

    @When("^We have an usual passenger$")
    public void we_have_an_usual_passenger() {
        usualPassenger = when(mock(Passenger.class).isVip()).thenReturn(false).getMock();
    }

    @Then("^We should add him to the economic flight and remove him$")
    public void we_should_add_him_to_the_flight() {
        boolean isUsualPassengerAdded = flight.addPassenger(usualPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();

        assertThat(isUsualPassengerAdded).isTrue();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(usualPassenger);

        boolean isUsualPassengerRemoved = flight.removePassenger(usualPassenger);
        passengers = flight.getPassengers();

        assertThat(isUsualPassengerRemoved).isTrue();
        assertThat(passengers).isEmpty();

        flight.addPassenger(usualPassenger);
    }

    @When("We have a VIP passenger")
    public void we_have_a_VIP_passenger() {
        vipPassenger = when(mock(Passenger.class).isVip()).thenReturn(true).getMock();
    }

    @Then("We should add him to the economic flight but cannot remove him")
    public void we_should_add_him_to_the_flight_but_cannot_remove_him() {
        boolean isVipPassengerAdded = flight.addPassenger(vipPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();

        assertThat(isVipPassengerAdded).isTrue();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(vipPassenger);

        boolean isVipPassengerRemoved = flight.removePassenger(vipPassenger);
        passengers = flight.getPassengers();

        assertThat(isVipPassengerRemoved).isFalse();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(vipPassenger);
    }

    @Given("There is an business flight")
    public void there_is_an_business_flight() {
        flight = new Flight(FlightType.BUSINESS, passengersTacker);
    }

    @Then("We should add him to the business flight but cannot remove him")
    public void we_should_add_him_to_the_business_flight_but_cannot_removeHim() {
        boolean isVipPassengerAdded = flight.addPassenger(vipPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();

        assertThat(isVipPassengerAdded).isTrue();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(vipPassenger);

        boolean isVipPassengerRemoved = flight.removePassenger(vipPassenger);
        assertThat(isVipPassengerRemoved).isFalse();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(vipPassenger);
    }

    @Then("We should not add him to the business")
    public void weShouldNotAddHimToTheBusiness() {
        boolean isUsualPassengerAdded = flight.addPassenger(usualPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();
        assertThat(isUsualPassengerAdded).isFalse();
        assertThat(passengers).isEmpty();
    }

    @Given("There is an premium flight")
    public void thereIsAnPremiumFlight() {
        flight = new Flight(FlightType.PREMIUM, passengersTacker);
    }

    @Then("We should add and remove him to the premium flight")
    public void weShouldAddAndRemoveHimToThePremiumFlight() {
        boolean isVipPassengerAdded = flight.addPassenger(vipPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();

        assertThat(isVipPassengerAdded).isTrue();
        assertThat(passengers)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(vipPassenger);

        boolean isVipPassengerRemoved = flight.removePassenger(vipPassenger);
        assertThat(isVipPassengerRemoved).isTrue();
        assertThat(passengers).isEmpty();

        flight.addPassenger(vipPassenger);
    }

    @Then("We should not add him to the premium flight")
    public void weShouldNotAddHimToThePremiumFlight() {
        boolean isVipPassengerAdded = flight.addPassenger(usualPassenger);
        Iterable<Passenger> passengers = flight.getPassengers();

        assertThat(isVipPassengerAdded).isFalse();
        assertThat(passengers).isEmpty();
    }

    @And("We cannot add an usual passenger more than once to the flight")
    public void weCannotAddAnUsualPassengerMoreThanOneToTheFlight() {
        Iterable<Passenger> passengersBeforeAdding = flight.getPassengers();
        boolean isVipPassengerAdded = flight.addPassenger(usualPassenger);
        Iterable<Passenger> passengersAfterAdding = flight.getPassengers();

        assertThat(isVipPassengerAdded).isFalse();
        assertThat(passengersBeforeAdding).containsExactlyElementsOf(passengersAfterAdding);

    }

    @And("We cannot add a VIP passenger more than once to the flight")
    public void weCannotAddAVIPPassengerMoreThanOneToTheFlight() {
        Iterable<Passenger> passengersBeforeAdding = flight.getPassengers();
        boolean isVipPassengerAdded = flight.addPassenger(vipPassenger);
        Iterable<Passenger> passengersAfterAdding = flight.getPassengers();

        assertThat(isVipPassengerAdded).isFalse();
        assertThat(passengersBeforeAdding).containsExactlyElementsOf(passengersAfterAdding);
    }


}
