package Car_rentalSystem.src.model;

public class Car {
    private int id;
    private String make;
    private String model;
    private String licensePlate;
    private double dailyRate;
    private String status;

    public Car(String make, String model, String licensePlate, double dailyRate) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.dailyRate = dailyRate;
        this.status = "AVAILABLE";
    }

    public void setId(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    public String getModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModel'");
    }

    public void setStatus(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

}
