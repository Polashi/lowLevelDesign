package entity.seat;


public class Seat {
    private final String number;
    private final SeatType type;
    private SeatStatus status;

    public Seat(String number, SeatType type){
        this.number = number;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public void release(){
        this.status = SeatStatus.AVAILABLE;
    }

    public void reserve(){
        this.status = SeatStatus.RESERVED;
    }
}
