package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class FitnessCenter{
    List<Client> clients = new ArrayList<Client>();

    void join(String name,String creditCardNumber,int age){
        Client c=new Client(name, creditCardNumber, age);

        c.registrationDate=new Date();
        c.feeDue+=90;

        System.out.println("Enter monthly fee for client " + name +" :");
        Scanner input = new Scanner(System.in);
        c.monthlyFee = input.nextInt();

        clients.add(c);
    }

    void remove(int id){
        for(int i=0;i<clients.size();i++){
            if (clients.get(i).id==id){
                clients.remove(i);
            }
        }
    }

    void getYogaCenterAccess(Client c) {
        for (int i = 0; i < c.facilitiesAccessed.size(); i++) {
            if (c.facilitiesAccessed.get(i) == "YogaCenter") {
                System.out.println("You have already availed this service.");
                return;
            }

        }
        c.facilitiesAccessed.add("YogaCenter");
    }

    void getSwimmingPoolAccess(Client c){
        for (int i = 0; i < c.facilitiesAccessed.size(); i++) {
            if (c.facilitiesAccessed.get(i) == "SwimmingPool") {
                System.out.println("You have already availed this service.");
                return;
            }

        }
        c.facilitiesAccessed.add("SwimmingPool");
    }
    void getTennisCourtAccess(Client c){
        for (int i = 0; i < c.facilitiesAccessed.size(); i++) {
            if (c.facilitiesAccessed.get(i) == "TennisCourt") {
                System.out.println("You have already availed this service.");
                return;
            }

        }
        c.facilitiesAccessed.add("TennisCourt");
    }

    void upgradeClient(Client c){
        Member m = new Member(c.name,c.creditCardNumber,c.age);
        m.feeDue=c.feeDue+300;
        m.registrationDate=c.registrationDate;
        m.dueDate=c.dueDate;
        m.facilitiesAccessed = c.facilitiesAccessed;
        m.monthlyFee=c.monthlyFee;
        m.id=c.id;
        remove(c.id);
        clients.add(m);

    }

    void downgradeClient(Client m){
        Client c = new Client(m.name,m.creditCardNumber,m.age);
        c.feeDue=m.feeDue;
        c.registrationDate=m.registrationDate;
        c.dueDate=m.dueDate;
        c.facilitiesAccessed = m.facilitiesAccessed;
        c.monthlyFee=m.monthlyFee;
        c.id=m.id;
        remove(m.id);
        clients.add(c);
    }

    Client getClient(String creditCardNumber){

        for(int i=0;i<clients.size();i++){
            if (clients.get(i).creditCardNumber.matches(creditCardNumber))
                return clients.get(i);
        }
        System.out.println("Client not found.");
        Client client = null;
        return client;
    }
}

class Gym extends FitnessCenter{
    int openingTime;
    int closingTime;
    Gym(){
        openingTime=1000;
        closingTime=2200;
    }
}

class YogaCenter extends FitnessCenter{
    int openingTime;
    int closingTime;
    YogaCenter(){
        openingTime=1000;
        closingTime=2200;
    }
}
class SwimmingPool extends FitnessCenter{
    int openingTime;
    int closingTime;
    SwimmingPool(){
        openingTime=1000;
        closingTime=1700;
    }
}
class TennisCourt extends FitnessCenter{
    int openingTime;
    int closingTime;
    TennisCourt(){
        openingTime=1000;
        closingTime=1700;
    }
}


class Client{
    static int clientCount=0;
    public String name;
    public String creditCardNumber;
    public int age;

    protected int monthlyFee;
    protected int id;
    protected Date registrationDate;
    protected List<String> facilitiesAccessed;
    protected int feeDue;
    protected Date dueDate;
    Client(String name,String creditCardNumber,int age){
        feeDue=0;
        clientCount++;
        id = clientCount;
        this.name=name;
        this.creditCardNumber=creditCardNumber;
        this.age=age;
        facilitiesAccessed = new ArrayList<String>();
    }

    void generateBill(){
        System.out.println("Generating bill for regular client.");
        feeDue+=monthlyFee;
        for(int i=0;i<facilitiesAccessed.size();i++){
            if(facilitiesAccessed.get(i)=="YogaCenter"){
                feeDue+=50;
            } else

            if(facilitiesAccessed.get(i)=="TennisCourt"){
                feeDue+=45;
            } else

            if(facilitiesAccessed.get(i)=="SwimmingPool"){
                feeDue+=60;
            }

        }
    }

    void printBill(){
        System.out.println("Your bill is " + feeDue);
    }

    void payBill(int value){
        feeDue-=value;
        
        Date now = new Date();
        int month=now.getMonth();
        month+=1;
        dueDate = new Date(now.getYear(),month,now.getDay());
    }

}

class Member extends Client{
    Member(String name, String creditCardNumber, int age) {
        super(name, creditCardNumber, age);
    }

    @Override
    void generateBill(){

        System.out.println("Generating bill for member.");
        feeDue+=monthlyFee;
        for(int i=0;i<facilitiesAccessed.size();i++){
            if(facilitiesAccessed.get(i)=="YogaCenter"){
                feeDue+=50;
            }

            if(facilitiesAccessed.get(i)=="TennisCourt"){
                feeDue+=45;
            }

            if(facilitiesAccessed.get(i)=="SwimmingPool"){
                feeDue+=60;
            }

        }
        Date now = new Date();
        if(registrationDate.getYear()<now.getYear()){
            feeDue+=300;
        }

        int month=now.getMonth();
        month+=1;
        dueDate = new Date(now.getYear(),month,now.getDay());
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        String name;
        String creditCardNumber;
        int age;
        Client c;
        FitnessCenter center = new FitnessCenter();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name:");
        name = input.nextLine();

        System.out.println("Enter your creditCardNumber:");
        creditCardNumber = input.nextLine();

        System.out.println("Enter your age:");
        age = input.nextInt();

        center.join(name,creditCardNumber,age);
        c = center.getClient(creditCardNumber);
        center.getYogaCenterAccess(c);
        center.getTennisCourtAccess(c);
        center.getSwimmingPoolAccess(c);

        //try to get access to already availed facility.
        center.getYogaCenterAccess(c);
        center.getTennisCourtAccess(c);
        center.getSwimmingPoolAccess(c);

        //generate bill for regular client
        c.generateBill();
        c.printBill();
        c.payBill(c.feeDue);
        c.printBill();

        center.upgradeClient(c);
        c = center.getClient(creditCardNumber);
        //generate bill after upgrade to member
        c.generateBill();
        c.printBill();
        c.payBill(c.feeDue);
        c.printBill();

        center.downgradeClient(c);
        c = center.getClient(creditCardNumber);
        //generate bill for regular client
        c.generateBill();
        c.printBill();
        c.payBill(c.feeDue);
        c.printBill();
    }
}
