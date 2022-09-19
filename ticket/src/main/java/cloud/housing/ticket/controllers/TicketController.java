package cloud.housing.ticket.controllers;
import cloud.housing.ticket.data.TicketRepository;
import cloud.housing.ticket.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

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

    @GetMapping("/{field}")
    public String displayAllTicketsWithSorting(Model model, @PathVariable String field){
        List<Ticket> tickets = this.findAllTicketsWithSorting(field);
        model.addAttribute("tickets",tickets);
        return "tickets/index";
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public String findAllTicketsWithPagination(@PathVariable int offset,@PathVariable int pageSize, Model model){
        Page<Ticket> page = ticketRepository.findAll(PageRequest.of(offset,pageSize));
        model.addAttribute("tickets",page);
        return "tickets/index";
    }

    //sorting using field
    public List<Ticket> findAllTicketsWithSorting(String field){
        return ticketRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
}
