package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 3/15/2018.
 */
public class TicketOfHistoryLayoutModel{
    public CoachListModel Coach;
    public TicketListModel Ticket;

    public TicketOfHistoryLayoutModel(CoachListModel coach, TicketListModel ticket) {
        Coach = coach;
        Ticket = ticket;
    }
}
