package ir.maktab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger extends User {
    @Column
    private long balance;
    @Enumerated(EnumType.STRING)
    private  TypeOfTrip type;
    @Column
    private long locationX;
    @Column
    private long locationY;
    @OneToMany(mappedBy = "passenger")
    private List<Travel> travel=new ArrayList<>();


    public Passenger() {
    }

    public Passenger(String firstName, String lastName, int  nationalCode) {
        super(firstName, lastName, nationalCode);
        setUserRole(UserRole.Passenger);
        this.balance=0;
        this.type=TypeOfTrip.WaitForTrip;

    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public TypeOfTrip getType() {
        return type;
    }

    public void setType(TypeOfTrip type) {
        this.type = type;
    }

    public long getLocationX() {
        return locationX;
    }

    public void setLocationX(long locationX) {
        this.locationX = locationX;
    }

    public long getLocationY() {
        return locationY;
    }

    public void setLocationY(long locationY) {
        this.locationY = locationY;
    }

    public List<Travel> getTravel() {
        return travel;
    }

    public void setTravel(List<Travel> travel) {
        this.travel = travel;
    }


}
