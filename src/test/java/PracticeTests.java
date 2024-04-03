package airport;

import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String model = "Boeing";
        Airport airport = Airport.getInstance();
    }

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return airport.getAllAircrafts().stream()
                .map(Aircraft :: getModel)
                .filter(currentModel -> currentModel.contains(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        return airport.getTerminals().stream()
                .collect(Collectors.toMap(
                        Terminal :: getName,
                        terminal -> terminal.getParkedAircrafts().size()));
    }   //currentTerminal ->currentTerminal.getName(), currentTerminal -> currentTerminal.getParkedAircrafts().size();


    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        long second = 60 * 60 * hours;
        return airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(List::stream)
                .filter(currentFlight -> {
                    return currentFlight.getType().equals(Flight.Type.DEPARTURE)
                            && currentFlight.getDate().isAfter(Instant.now())
                            && currentFlight.getDate().isBefore(Instant.now().plusSeconds(second));
                })
                .collect(Collectors.toList());
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.
        return airport.getTerminals().stream()
                .filter(terminal -> terminal.getName().equals(terminalName))
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flights -> flights.getType().equals(Flight.Type.ARRIVAL))
                .min(Comparator.comparing(Flight::getDate));
    }
}
