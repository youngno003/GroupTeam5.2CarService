package d14cqcp01.group5.carservices;

/**
 * Created by T420 on 5/5/2018.
 */

public class CoachListModel {
    private String companyId;
    private String from;
    private String id;
    private String journey;
    private Long price;
    private float stars;
    private Long timeEnd;
    private Long timeStart;
    private String to;
    private String type;
    private Long vacantSeats;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(Long vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public CoachListModel() {
    }

    public CoachListModel(String companyId, String from, String id, String journey, Long price, float stars, Long timeEnd, Long timeStart, String to, String type, Long vacantSeats) {
        this.companyId = companyId;
        this.from = from;
        this.id = id;
        this.journey = journey;
        this.price = price;
        this.stars = stars;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;
        this.to = to;
        this.type = type;
        this.vacantSeats = vacantSeats;
    }
}