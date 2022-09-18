package cloud.housing.ticket.models;

import java.util.Comparator;

public class TicketPriorityComparator implements Comparator<Ticket> {
    private int getPriorityNum(String priority) {
        if (priority.equals("high"))
            return 3;
        if (priority.equals("medium"))
            return 2;
        return 1;
    }

    public int compare(Ticket a, Ticket b) {
        return (getPriorityNum(b.getPriority()) - getPriorityNum(a.getPriority()));
    }
}
