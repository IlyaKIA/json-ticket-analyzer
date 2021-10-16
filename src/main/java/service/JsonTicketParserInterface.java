package service;

import domain.Ticket;

import java.util.List;

public interface JsonTicketParserInterface {
    List<Ticket> getTicketsList(String fileName, String mainNodeName);
}
