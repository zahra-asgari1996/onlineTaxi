package ir.maktab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver  extends  User{
    @Column
    private  String carName;
    @Column
    private String carColor;
    @Column
    private String carTag;
    @Column
    private long locationX;
    @Column
    private long locationY;
    @Enumerated(EnumType.STRING)
    private TypeOfTrip type;
    @OneToMany(mappedBy = "driver")
    private List<Travel> travel=new ArrayList<>();



    public Driver() {
    }

    public Driver(String firstName, String lastName, int nationalCode, String carName, String carColor, String carTag) {
        super(firstName, lastName, nationalCode);
        this.carName = carName;
        this.carColor = carColor;
        this.carTag = carTag;
        this.locationX=0;
        this.locationY=0;
        this.setType(TypeOfTrip.WaitForTrip);
        setUserRole(UserRole.Driver);




    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
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

    public TypeOfTrip getType() {
        return type;
    }

    public void setType(TypeOfTrip type) {
        this.type = type;
    }

    public List<Travel> getTravel() {
        return travel;
    }

    public void setTravel(List<Travel> travel) {
        this.travel = travel;
    }



}
