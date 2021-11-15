Feature: Passengers Policy
  The company follows a policy of adding and removing passengers, depending on the passenger type and flight type

  Scenario: Economic Flight and Usual Passenger
    Given There is an economic flight
    When We have an usual passenger
    Then We should add him to the economic flight and remove him
    And We cannot add an usual passenger more than once to the flight

  Scenario: Economic Flight and VIP Passenger
    Given There is an economic flight
    When We have a VIP passenger
    Then We should add him to the economic flight but cannot remove him
    And We cannot add a VIP passenger more than once to the flight

  Scenario: Business Flight and VIP Passenger
    Given There is an business flight
    When We have a VIP passenger
    Then We should add him to the business flight but cannot remove him
    And We cannot add a VIP passenger more than once to the flight

  Scenario: Business Flight and Usual Passenger
    Given There is an business flight
    When We have an usual passenger
    Then We should not add him to the business

  Scenario: Premium Flight and Usual Passenger
    Given There is an premium flight
    When We have an usual passenger
    Then We should not add him to the premium flight

  Scenario: Premium Flight and VIP Passenger
    Given There is an premium flight
    When We have a VIP passenger
    Then We should add and remove him to the premium flight
    And We cannot add a VIP passenger more than once to the flight