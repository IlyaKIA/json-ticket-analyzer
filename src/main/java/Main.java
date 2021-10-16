import domain.Ticket;
import service.JsonTicketParser;
import service.TicketsAnaliseService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "tickets.json";
        String origin_name = "Владивосток";
        String destination_name = "Тель-Авив";
        String mainNodeName = "tickets";
        int percentageValue = 90;

        List<Ticket> tickets = new JsonTicketParser().getTicketsList(fileName, mainNodeName);
        if(tickets != null) {
            TicketsAnaliseService ticketsAnaliseService = new TicketsAnaliseService(tickets, origin_name, destination_name);
            double averageFlightTime = ticketsAnaliseService.getAverageFlightTimeInMinutes();
            float percentile = ticketsAnaliseService.percentile(percentageValue);
            System.out.println("Статистика билетов из файла " + fileName + ":");
            System.out.println(" - Среднее время полета из города " + origin_name + " в " + destination_name +
                    String.format(" составляет %d час. %d мин.", (int) averageFlightTime / 60, (int) averageFlightTime % 60));
            System.out.println(" - " + percentageValue + "й процентиль времени полета из города " + origin_name + " в " + destination_name +
                    String.format(" составляет %d час. %d мин.", (int) percentile / 60, (int) percentile % 60));
        } else System.err.println("Ошибка чтения файла или отсутствуют билеты, удовлетворяющие критериям поиска");
    }
}
