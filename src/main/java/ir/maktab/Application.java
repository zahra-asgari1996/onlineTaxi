package ir.maktab;




import ir.maktab.model.Driver;
import ir.maktab.model.Passenger;
import ir.maktab.model.Travel;
import ir.maktab.model.TypeOfTrip;
import ir.maktab.service.DriverDao;
import ir.maktab.service.PassengerDao;
import ir.maktab.service.TravelDao;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args)  {
        menu();
    }

    public static void menu()  {
        int number;
        DriverDao driverDao=new DriverDao();
        PassengerDao passengerDao=new PassengerDao();
        TravelDao travelDao=new TravelDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                1.Add a group of drivers
                2.Add a group of passengers
                3.Driver signup or login
                4.Passenger signup or login
                5.Show ongoing travels
                6.Show a list of drivers
                7.Show a list of passengers
                8.exit """);

        number = sc.nextInt();
        Pattern pattern = Pattern.compile("\\d{1}");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        if (matcher.matches()) {
            switch (number) {
                case 1:
                    driver();
                    break;
                case 2:
                    passenger();
                    break;
                case 3:
                    registerDriver();
                    break;
                case 4:
                    registerPassenger();
                    break;
                case 5:
                    travelDao.fetchAllTravels().stream().forEach(System.out::println);
                    menu();
                    break;
                case 6:
                    driverDao.fetchAllDriver().stream().forEach(System.out::println);
                    menu();
                    break;
                case 7:
                    passengerDao.fetchAllPassenger().stream().forEach(System.out::println);
                    menu();
                    break;
                case 8:
                    break;
                default:
                    menu();
            }
        } else {
            menu();
        }
    }
    public static void driver()  {
        int number;
        Scanner sc = new Scanner(System.in);
        System.out.println("Plz Enter Number Of Drivers : ");
        number = sc.nextInt();
        Pattern pattern = Pattern.compile("\\d{1}");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        if (matcher.matches()) {
            for (int i = 0; i < number; i++) {
                addNewDriver();
            }
            System.out.println("Your information was successfully registered.");
            menu();
        } else {
            driver();
        }
    }
    public static void addNewDriver() {
        Scanner sc = new Scanner(System.in);
        String name = "", lastName = "", carTag = "", carModel = "", carColor = "";
        int nationalCode =0;
        boolean temp = true;
        while (temp) {
            System.out.println("plz Inter Your Name : ");
            name = sc.next();
            Pattern pattern = Pattern.compile("[a-zA-Z]{3,10}");
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your LastName : ");
            lastName = sc.next();
            Pattern pattern = Pattern.compile("[a-zA-Z]{3,10}");
            Matcher matcher = pattern.matcher(lastName);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your NationalCode : ");
            nationalCode = sc.nextInt();
            Pattern pattern = Pattern.compile("[0-9]{10}");
            Matcher matcher = pattern.matcher(String.valueOf(nationalCode));
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your CarTag : ");
            carTag = sc.next();
            Pattern pattern = Pattern.compile("[1-9]{3}[a-z][1-9]{2}-[1-9][0-9]");
            Matcher matcher = pattern.matcher(carTag);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your CarModel : ");
            carModel = sc.next();
            Pattern pattern = Pattern.compile("[a-zA-Z]{3,10}");
            Matcher matcher = pattern.matcher(carModel);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter  Your CarColor : ");
            carColor = sc.next();
            Pattern pattern = Pattern.compile("((sefid)|(meshki)|(ghermez)|(bezh)|(nokmedadi))");
            Matcher matcher = pattern.matcher(carColor);
            if (matcher.matches()) {
                temp = false;
            }
        }
        DriverDao driverDao=new DriverDao();
        Driver driver=new Driver(name,lastName,nationalCode,carTag,carModel,carColor);
        driverDao.saveNewDriver(driver);
    }

    public static void passenger()  {
        int number;
        Scanner sc = new Scanner(System.in);
        System.out.println("Plz Enter Number Of Passengers : ");
        number = sc.nextInt();
        Pattern pattern = Pattern.compile("\\d{1}");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        if (matcher.matches()) {
            for (int i = 0; i < number; i++) {
                addNewPassenger();
            }
            System.out.println("Your Information Was Successfully Registered.");
            menu();
        } else {
            passenger();
        }
    }
    public static void addNewPassenger() {
        Scanner sc = new Scanner(System.in);
        String name = "", lastName = "";int  nationalCode = 0;
        boolean temp = true;
        while (temp) {
            System.out.println("plz Inter Your Name : ");
            name = sc.next();
            Pattern pattern = Pattern.compile("[a-zA-Z]{3,10}");
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your LastName : ");
            lastName = sc.next();
            Pattern pattern = Pattern.compile("[a-zA-Z]{3,10}");
            Matcher matcher = pattern.matcher(lastName);
            if (matcher.matches()) {
                temp = false;
            }
        }
        temp = true;
        while (temp) {
            System.out.println("plz Inter Your NationalCode : ");
            nationalCode = sc.nextInt();
            Pattern pattern = Pattern.compile("[0-9]{10}");
            Matcher matcher = pattern.matcher(String.valueOf(nationalCode));
            if (matcher.matches()) {
                temp = false;
            }
        }
        PassengerDao passengerDao=new PassengerDao();
        Passenger passenger=new Passenger(name,lastName,nationalCode);
        passengerDao.saveNewPassenger(passenger);

    }

    public static void registerDriver()  {
        int userName;
        int number;
        DriverDao driverDao=new DriverDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("Plz Inter Your Id: ");
        userName = sc.nextInt();
        Driver driver=driverDao.findDriverById(userName);
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(String.valueOf(userName));
        if (matcher.matches()) {
            if (driver!=null) {
                driver.toString();
                if (driver.getType()== TypeOfTrip.WaitForTrip) {
                    System.out.println(" YOU ARE WAITING FOR THE TRIP ");
                    System.out.println("1) EXIT ");
                    boolean temp = true;
                    while (temp) {
                        System.out.println("Plz Inter Number Again :");
                        number = sc.nextInt();
                        pattern = Pattern.compile("[1]");
                        matcher = pattern.matcher(String.valueOf(number));
                        if (matcher.matches()) {
                            temp = false;
                        }
                    }
                    menu();

                } else if (driver.getType()== TypeOfTrip.OnTrip) {
                    menuForRegisteredDriver(driver);
                }
            } else {
                backToMenu();
                boolean temp = true;
                int num = -1;
                while (temp) {
                    System.out.println("Plz Inter Number Again :");
                    num = sc.nextInt();
                    pattern = Pattern.compile("[1,2]");
                    matcher = pattern.matcher(String.valueOf(num));
                    if (matcher.matches()) {
                        temp = false;
                    }
                }
                switch (num) {
                    case 1:
                        addNewDriver();
                        backToMenu();
                        temp = true;
                        while (temp) {
                            System.out.println("Plz Inter Number Again :");
                            number = sc.nextInt();
                            pattern = Pattern.compile("[2]");
                            matcher = pattern.matcher(String.valueOf(num));
                            if (matcher.matches()) {
                                temp = false;
                            }
                        }
                        menu();
                        break;
                    case 2:
                        menu();
                }
            }
        } else {
            registerDriver();
        }

    }

    public static void menuForRegisteredDriver(Driver driver)  {
        System.out.println("1.Confirm cash receipt" + "\n" + "2.Travel finished " + "\n" + "3.Exit");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        TravelDao travelDao=new TravelDao();
        Pattern pattern = Pattern.compile("[1,2,3]");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        if (matcher.matches()) {
            switch (number) {
                case 1:
                    System.out.println(" Pardakht Shod! ");
                    System.out.println("2.Travel finished " + "\n" + "3.Exit");
                    int num = sc.nextInt();
                    if (num == 2) {
                        travelDao.finishTravel(driver);
                        menu();
                    } else if (num == 3) {
                        menu();
                    }
                    break;
                case 2:
                    travelDao.finishTravel(driver);
                    menu();
                    break;
                case 3:
                    menu();
                    break;
                default:
                    menu();
            }
        } else {
            menuForRegisteredDriver(driver);
        }

    }
    public static void backToMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1)Register" + "\n2)Exit");
    }

    public static void registerPassenger()  {
        int userName;
        PassengerDao passengerDao=new PassengerDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("Plz Inter Your Id  : ");
        userName = sc.nextInt();
        Passenger passenger=passengerDao.findPassengerById(userName);
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(String.valueOf(userName));
        if (matcher.matches()) {
            if (passenger!=null) {
                passenger.toString();
                passengerInfo(userName);
            } else {
                notRegisteredPassenger(passenger);
            }
        } else {
            registerPassenger();
        }

    }

    public static void notRegisteredPassenger(Passenger passenger)  {
        Scanner sc = new Scanner(System.in);
        PassengerDao passengerDao=new PassengerDao();
        int number;
        backToMenu();
        number = sc.nextInt();
        Pattern pattern = Pattern.compile("[1,2]");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        if (matcher.matches()) {
            switch (number) {
                case 1:
                    addNewPassenger();
                    passengerInfo(passenger.getId());
                    break;
                case 2:
                    menu();
            }
        } else {
            notRegisteredPassenger(passenger);
        }

    }


    public static void passengerInfo(int  userName) {
        int number;
        Travel travel;
        TravelDao travelDao=new TravelDao();
        PassengerDao passengerDao=new PassengerDao();
        DriverDao driverDao=new DriverDao();
        long originX, originY, destinationX, destinationY;
        Scanner sc = new Scanner(System.in);
        Passenger passenger=passengerDao.findPassengerById(userName);
        if (passenger.getType().equals(TypeOfTrip.OnTrip)) {
            System.out.println(" you are on trip ");
            menu();
        } else {
            boolean temp = true;
            int num=-1;
            while (temp) {
                System.out.println("1.Travel request (pay by cash)\n 2.Travel request (pay by account balance)\n 3.Increase account balance\n 4.Exit ");
                num = sc.nextInt();
                Pattern pattern = Pattern.compile("[1,2,3]");
                Matcher matcher = pattern.matcher(String.valueOf(num));
                if (matcher.matches()) {
                    temp = false;
                }
            }

            switch (num) {
                case 1:
                    System.out.println("Enter the origin and destination of your travel: ");
                    originX = sc.nextLong();
                    originY = sc.nextLong();
                    destinationX = sc.nextLong();
                    destinationY = sc.nextLong();
                    Driver driver=driverDao.returnCandidDriver(originX,originY);
                    travel=new Travel(driver,passenger,originX,originY,destinationX,destinationY);
                    //trip1 = new Trip(snapp.returnPassenger(userName).requestTrip(origin, destination, snapp), snapp.returnPassenger(userName), origin, destination);
                    //snapp.addTrip(trip1);
                    travel.setType(TypeOfTrip.OnTrip);
                    travelDao.saveNewTravel(travel);
                    //trip1.setTypeOfTrip(TypeOfTrip.DoTrip);
                    System.out.println("Your Request Accepted By:" + driver.toString());
                    driverDao.setDriverType(driver);
                    passengerDao.setPassengerType(passenger);
                    //snapp.returnPassenger(userName).setType(TypeOfTripPassenger.DoTrip);
                    //snapp.returnPassenger(userName).requestTrip(origin, destination, snapp).setType(TypeOfTripDriver.DoTrip);
                    menu();
                    break;
                case 2:
                    Travel travel1=new Travel();
                    System.out.println("Enter the origin and destination of your travel: ");
                    originX = sc.nextLong();
                    originY = sc.nextLong();
                    destinationX = sc.nextLong();
                    destinationY = sc.nextLong();
                    driver=driverDao.returnCandidDriver(originX,originY);
                    if (travel1.calculatePrice(originX,originY,destinationX,destinationY) > passenger.getBalance()) {
                        System.out.println(" no balance ");
                    } else {
                        travel = new Travel(driver,passenger,originX,originY,destinationX,destinationY );
                        travelDao.saveNewTravel(travel);
                        System.out.println("Your request accepted by:" + driver.toString());
                        //snapp.returnPassenger(userName).setType(TypeOfTripPassenger.DoTrip);
                        //snapp.returnPassenger(userName).requestTrip(origin, destination, snapp).setType(TypeOfTripDriver.DoTrip);
                        driverDao.setDriverType(driver);
                        passengerDao.setPassengerType(passenger);
                    }
                    menu();
                    break;
                case 3:
                    System.out.println(" plz inter your balance : ");
                    long balance = sc.nextLong();
                    passengerDao.increaseBalance(passenger,balance);
                    menu();
                    break;
                case 4:
                    menu();
                    break;
            }
        }
    }




}
