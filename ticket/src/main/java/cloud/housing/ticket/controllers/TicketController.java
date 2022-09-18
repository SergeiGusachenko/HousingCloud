package cloud.housing.ticket.controllers;
import cloud.housing.ticket.data.TicketRepository;
import cloud.housing.ticket.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
@RequestMapping("tickets")
public class TicketController {
    @Autowired  // dependency injection
    private TicketRepository ticketRepository;

    @GetMapping
    public String displayAllTickets(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(){
        return "tickets/create";
    }

    @PostMapping("create")
    public String createTicket(@ModelAttribute Ticket newTicket){
        ticketRepository.save(newTicket);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteTicketForm(Model model){
        model.addAttribute("title","Delete Tickets");
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets/delete";
    }

    @PostMapping("delete")
    public String processDeleteTicketForm(@RequestParam(required = false) int[] ticketsIds){
        if(ticketsIds == null)
            return "tickets/create";
        for(int id:ticketsIds){
            ticketRepository.deleteById(id);
        }
        return "redirect:";
    }
}
