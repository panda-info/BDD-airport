package com.informain.flights.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static com.informain.flights.models.FlightType.BUSINESS;
import static com.informain.flights.models.FlightType.ECONOMIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightTest {

    private Flight flightService;
    private Passenger vipPassenger;
    private Passenger usualPassenger;
    private PassengersTacker passengersTacker;

    @BeforeEach
    void setUp() {
        vipPassenger = when(mock(Passenger.class).isVip()).thenReturn(true).getMock();
        usualPassenger = when(mock(Passenger.class).isVip()).thenReturn(false).getMock();
        passengersTacker = mock(PassengersTacker.class);
    }

    @Nested
    @DisplayName("Given There is an economic flight")
    class EconomicFlightTest {

        @BeforeEach
        void setUp() {
            flightService = new Flight(ECONOMIC, passengersTacker);
        }

        @Nested
        @DisplayName("When we have usual passenger")
        class UsualPassengerTest {

            @Test
            @DisplayName("Then we should add him")
            void should_add_usual_passenger() {
                boolean isUsualPassengerAdded = flightService.addPassenger(usualPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(isUsualPassengerAdded).isTrue();
                assertThat(passengers)
                        .isNotEmpty()
                        .hasSize(1)
                        .containsExactly(usualPassenger);
            }

            @Test
            @DisplayName("Then we should remove him")
            void should_remove_usual_passenger() {
                flightService.addPassenger(usualPassenger);
                boolean isRemoved = flightService.removePassenger(usualPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(isRemoved).isTrue();
                assertThat(passengers).isEmpty();
            }
        }

        @Nested
        @DisplayName("When we have vip passenger")
        class VipPassengerTest {

            @Test
            @DisplayName("Then we should add him")
            void should_add_vip_passenger() {
                boolean isVipPassengerAdded = flightService.addPassenger(vipPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(isVipPassengerAdded).isTrue();
                assertThat(passengers)
                        .isNotEmpty()
                        .hasSize(1)
                        .containsExactly(vipPassenger);
            }

            @Test
            @DisplayName("Then we should not remove him")
            void should_not_remove_vip_passenger() {
                flightService.addPassenger(vipPassenger);
                boolean isRemoved = flightService.removePassenger(vipPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(isRemoved).isFalse();
                assertThat(passengers)
                        .isNotEmpty()
                        .hasSize(1)
                        .containsExactly(vipPassenger);
            }
        }
    }

    @Nested
    @DisplayName("Given there is a business flight")
    class BusinessFlightTest {

        @BeforeEach
        void setUp() {
            flightService = new Flight(BUSINESS, passengersTacker);
        }

        @Nested
        @DisplayName("When we have usual passenger")
        class UsualPassengerTest {
            @Test
            @DisplayName("Then we should not add him")
            void should_add_vip_passenger() {
                boolean usualAdded = flightService.addPassenger(usualPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(usualAdded).isFalse();
                assertThat(passengers).isEmpty();
            }
        }

        @Nested
        @DisplayName("When we have vip passenger")
        class VipPassengerTest {

            @Test
            @DisplayName("Then we should add him")
            void should_add_vip_passenger() {
                boolean vipAdded = flightService.addPassenger(vipPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(vipAdded).isTrue();
                assertThat(passengers)
                        .isNotEmpty()
                        .hasSize(1)
                        .containsExactly(vipPassenger);
            }

            @Test
            @DisplayName("Then we should not remove him")
            void should_not_remove_vip_passenger() {
                flightService.addPassenger(vipPassenger);
                boolean isRemoved = flightService.removePassenger(vipPassenger);
                Iterable<Passenger> passengers = flightService.getPassengers();

                assertThat(isRemoved).isFalse();
                assertThat(passengers)
                        .isNotEmpty()
                        .hasSize(1)
                        .containsExactly(vipPassenger);
            }

            @Test
            void name() {
                Collection<Passenger> passengers = new HashSet<>();
                boolean add1 = passengers.add(usualPassenger);
                boolean add2 = passengers.add(usualPassenger);
                boolean add3 = passengers.add(usualPassenger);
                assertThat(add1).isTrue();
                assertThat(add2).isFalse();
                assertThat(add3).isFalse();
            }
        }
    }
}
