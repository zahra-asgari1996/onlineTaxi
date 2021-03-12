package ir.maktab.model;

import javax.persistence.*;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private  Passenger passenger;
    @Column
    private  long originX;
    @Column
    private  long originY;
    @Column
    private  long destinationX;
    @Column
    private  long destinationY;
    @Enumerated(EnumType.STRING)
    private TypeOfTrip type;

    public Travel() {
    }

    public Travel(Driver driver, Passenger passenger, long originX, long originY, long destinationX, long destinationY) {
        this.driver = driver;
        this.passenger = passenger;
        this.originX=originX;
        this.originY=originY;
        this.destinationX=destinationX;
        this.destinationY=destinationY;

    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public long getOriginX() {
        return originX;
    }

    public void setOriginX(long originX) {
        this.originX = originX;
    }

    public long getOriginY() {
        return originY;
    }

    public void setOriginY(long originY) {
        this.originY = originY;
    }

    public long getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(long destinationX) {
        this.destinationX = destinationX;
    }

    public long getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(long destinationY) {
        this.destinationY = destinationY;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public TypeOfTrip getType() {
        return type;
    }

    public void setType(TypeOfTrip type) {
        this.type = type;
    }

    public int calculatePrice(long originX,long originY,long destinationX,long destinationY){
        double distance=Math.sqrt(((destinationX-originX)*(destinationX-originX))+((destinationY- originY)*(destinationY- originY)));
        return (int)distance*1000;
    }


}
