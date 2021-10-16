package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTicketParser implements JsonTicketParserInterface {
    @Override
    public List<Ticket> getTicketsList(String fileName, String mainNodeName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(fileName));
            return objectMapper.readValue(jsonNode.path(mainNodeName).toString(), new TypeReference<List<Ticket>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
