package dev.ia.travel;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.inject.Inject;

public class BookingMcpTools {

    @Inject
    BookingService service;

    @Tool(description = "Obtém detalhes completos de uma reserva pelo ID. Retorna erro se não encontrada.")
    public String getBookingDetails(
            @ToolArg(description = "O ID numérico único da reserva (ex: 12345)") long id) {
        return service.getBookingDetails(id)
                .map(Booking::toString)
                .orElse("Reserva não encontrada com ID: " + id);
    }

    @Tool(description = "Cancela uma reserva existente. Requer ID e nome do cliente para validação.")
    public String cancelBooking(
            @ToolArg(description = "ID da reserva a cancelar") long id) {
        return service.cancelBooking(id)
                .map(b -> "Sucesso: Reserva " + id + " cancelada. Status: " + b.status())
                .orElse("Falha: Reserva não encontrada ou nome incorreto para o ID " + id);
    }

}
