package airport;

import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String model = "Boeing";
        Airport airport = Airport.getInstance();
        System.out.println("Count all aircraft witch found model \"" + model + "\": " +
                findCountAircraftWithModelAirbus(airport, model));

        System.out.println("List flights, which through two hours: " +
                findFlightsLeavingInTheNextHours(airport, 2));
    }

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return airport.getAllAircrafts().stream()
                .map(Aircraft::getModel)
                .filter(currentModel -> currentModel.contains(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        return Collections.emptyMap();
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        long second = hours * 1000 * 60 * 60;
        Instant instantNow = Instant.now();
        return airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(List::stream)
                .filter(flight -> {
                    return flight.getType().equals(Flight.Type.DEPARTURE)
                            && flight.getDate().isAfter(instantNow)
                            && flight.getDate().isBefore(instantNow.plusSeconds(second));
                })
                .collect(Collectors.toList());
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.
        return Optional.empty();
    }
}
