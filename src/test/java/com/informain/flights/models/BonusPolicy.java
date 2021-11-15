package com.informain.flights.models;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BonusPolicy {

    private MileageBonusCalculator bonusCalculator;
    private Passenger usualPassenger;

    @Given("We have an usual passenger with a mileage")
    public void weHaveAnUsualPassengerWithAMileage() {
        bonusCalculator = new MileageBonusCalculator();
        usualPassenger = when(mock(Passenger.class).isVip()).thenReturn(false).getMock();
        bonusCalculator.add(usualPassenger);
    }

    @When("The usual passenger travels {int} and {int} and {int}")
    public void theUsualPassengerTravelsMileageAndMileageAndMileage(int arg0, int arg1, int arg2) {
        bonusCalculator.addMileages(usualPassenger, arg0, arg1, arg2);
    }

    @Then("The bonus points of the usual passenger should be {int}")
    public void theBonusPointsOfTheUsualPassengerShouldBePoints(int arg0) {
        int bonus = bonusCalculator.computeBonus(usualPassenger);
        Assertions.assertThat(bonus).isEqualTo(arg0);
    }

}
