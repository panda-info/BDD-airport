Feature: Bonus Policy
  Each passenger earns bonus points when traveling based on mileage

  Scenario Outline: Usual Passenger Bonus Policy
    Given We have an usual passenger with a mileage
    When The usual passenger travels <mileage1> and <mileage2> and <mileage3>
    Then The bonus points of the usual passenger should be <points>

    Examples:
      | mileage1 | mileage2 | mileage3 | points |
      | 349      | 319      | 623      | 64     |
      | 312      | 356      | 135      | 40     |
      | 223      | 786      | 503      | 75     |
      | 482      | 98      | 591      | 58     |