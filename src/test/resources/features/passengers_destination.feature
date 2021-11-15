Feature: Passengers Destinations
  As a flight company we want to track each passenger visited cities

  Background: We have Passenger and Tracker
    Given We have passenger
    And A Passenger Tacker

  Scenario: Save and retrieve passenger destination city (with String)
    When He takes a flight to "Paris"
    Then We should retrieve his destination travel "Paris"

  Scenario: Save and retrieve passenger destination city (with Gerkin Word)
    When He takes a flight to London
    Then We should retrieve his destination travel London

  Scenario: Save and retrieve passenger destination city (with Gerkin Anonymous)
    When He takes a flight 2 to Berlin
    Then We should retrieve his 2 travels having destination Berlin

  Scenario: Save and retrieve passenger visited cities
    When He takes a flights to
      | Paris  |
      | London |
      | Berlin |
      | Paris  |
    Then We should retrieve all his destinations without duplication
      | Paris  |
      | London |
      | Berlin |

  Scenario: Save and retrieve passenger visited cities with count
    When He takes a flights to cities n times
      | City   | Count |
      | Paris  | 2     |
      | London | 1     |
      | Berlin | 3     |
    Then We should retrieve all his visited cities with count
      | City   | Count |
      | Paris  | 2     |
      | London | 1     |
      | Berlin | 3     |