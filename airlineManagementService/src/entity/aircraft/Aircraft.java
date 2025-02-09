package entity.aircraft;

public class Aircraft {
    private final String id;
    private final String model;
    private final int totalSeats;

    public Aircraft(String number, String model, int totalSeats) {
        this.id = number;
        this.model = model;
        this.totalSeats = totalSeats;
    }
}
