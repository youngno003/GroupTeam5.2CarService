package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 5/5/2018.
 */

public class TicketListModel {
    private String id;
    private String idCoach;
    private Long orderTime;
    private Long seatNumber;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(String idCoach) {
        this.idCoach = idCoach;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber( Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TicketListModel(String id, String idCoach, Long orderTime, Long seatNumber, String status) {
        this.id = id;
        this.idCoach = idCoach;
        this.orderTime = orderTime;
        this.seatNumber = seatNumber;
        this.status = status;
    }
    public TicketListModel(){}


}
