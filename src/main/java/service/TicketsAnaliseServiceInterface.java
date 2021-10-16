package service;

import domain.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface TicketsAnaliseServiceInterface {
    Double getAverageFlightTimeInMinutes();
    Long percentile(int percentageValue);
}
