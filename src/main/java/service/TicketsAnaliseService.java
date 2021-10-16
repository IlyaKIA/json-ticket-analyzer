package service;

import domain.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class TicketsAnaliseService implements TicketsAnaliseServiceInterface {
    private final List<Long> flightsTime = new ArrayList<>();

    public TicketsAnaliseService(List<Ticket> tickets, String origin_name, String destination_name){
        for(Ticket ticket:tickets){
            if(ticket.getOrigin_name().equals(origin_name) && ticket.getDestination_name().equals(destination_name)) {
                LocalDateTime departureTime = LocalDateTime.of(ticket.getDeparture_date(), ticket.getDeparture_time());
                LocalDateTime arrivalTime = LocalDateTime.of(ticket.getArrival_date(), ticket.getArrival_time());
                Long minute = Duration.between(departureTime, arrivalTime).toMinutes();
                flightsTime.add(minute);
            }
        }
    }

    @Override
    public Double getAverageFlightTimeInMinutes() {
        OptionalDouble average = flightsTime
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    @Override
    public Long percentile(int percentageValue) {
        Collections.sort(flightsTime);
        if(percentageValue > 100) percentageValue = 100;
        else if(percentageValue < 0) percentageValue = 0;
        return flightsTime.get((flightsTime.size()-1)*percentageValue/100);
    }
}
