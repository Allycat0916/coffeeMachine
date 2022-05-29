import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

class Status{

    int waterAvailable = 400;
    int milkAvailable = 540;
    int beansAvailable = 120;
    int cupsAvailable = 9;
    int moneyAvailable = 550;
    //final variables
    final int espressoWater = 250;
    final int espressoBean = 16;
    final int espressoPrice = 4;
    final int latteWater = 350;
    final int latteMilk = 75;
    final int latteBean = 20;
    final int lattePrice = 7;
    final int capWater = 200;
    final int capMilk = 100;
    final int capBean = 12;
    final int capPrice = 6;
    enum State {
        BUY, FILL, TAKE, REMAINING, EXIT
    }
    Status() {
        this.waterAvailable = waterAvailable;
        this.milkAvailable = milkAvailable;
        this.beansAvailable = beansAvailable;
        this.cupsAvailable = cupsAvailable;
        this.moneyAvailable = moneyAvailable;
    }
    public void actions(State val){

        switch(val){ //switch statement for which action user chose
            case BUY:
                buy();
                break;
            case FILL:
                fill();
                break;
            case TAKE:
                take();
                break;
            case REMAINING:
                remaining();
                break;
            case EXIT:
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }
    //purchase of espresso, latte, or cappuccino
    public void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, or back: ");
        String purchase = Main.response(); //get user response
        switch (purchase) {
            case "1": //espresso
                //all items need to be above espresso levels to make
                if (this.waterAvailable >= espressoWater && this.beansAvailable >= espressoBean && this.cupsAvailable >= 0){
                    // remove the amounts used and add money to the machine
                    this.waterAvailable -= espressoWater;
                    this.beansAvailable -= espressoBean;
                    this.cupsAvailable--;
                    this.moneyAvailable += espressoPrice;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (this.waterAvailable < espressoWater) { //when there is not enough water
                    System.out.println("Sorry, not enough water!");
                } else if (this.beansAvailable < espressoBean) { //when there are not enough beans
                    System.out.println("Sorry, not enough coffee beans");
                } else { //when there are not enough cups
                    System.out.println("Sorry, not enough cups!");
                }
                break;
            case "2": //latte
                //all items need to be above espresso levels to make
                if (this.waterAvailable >= latteWater && this.milkAvailable >= latteMilk && this.beansAvailable >= latteBean && this.cupsAvailable >= 0){
                    this.waterAvailable -= latteWater;
                    this.milkAvailable -= latteMilk;
                    this.beansAvailable -= latteBean;
                    this.cupsAvailable--;
                    this.moneyAvailable += lattePrice;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (this.waterAvailable < latteWater) { //when there is not enough water
                    System.out.println("Sorry, not enough water!");
                } else if (this.milkAvailable < latteMilk) { //when there is not enough milk
                    System.out.println("Sorry, not enough milk!");
                } else if (this.beansAvailable < latteBean) { //when there are not enough beans
                    System.out.println("Sorry, not enough coffee beans");
                } else { //when there are not enough cups
                    System.out.println("Sorry, not enough cups!");
                }
                break;
            case "3": //cappuccino
                //all items need to be above espresso levels to make
                if (this.waterAvailable >= capWater && this.milkAvailable >= capMilk && this.beansAvailable >= capBean && this.cupsAvailable >= 0){
                    this.waterAvailable -= capWater;
                    this.milkAvailable -= capMilk;
                    this.beansAvailable -= capBean;
                    this.cupsAvailable--;
                    this.moneyAvailable += capPrice;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (this.waterAvailable < capWater) { //when there is not enough water
                    System.out.println("Sorry, not enough water!");
                } else if (this.milkAvailable < capMilk) { //when there is not enough milk
                    System.out.println("Sorry, not enough milk!");
                } else if (this.beansAvailable < capBean) { //when there are not enough beans
                    System.out.println("Sorry, not enough coffee beans");
                } else { //when there are not enough cups
                    System.out.println("Sorry, not enough cups!");
                }
                break;
            case "back": //if user changes their mind
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }
    //fill the machine with water, milk, beans, or cups
    public void fill(){
        System.out.println("Write how many ml of water you want to add: ");
        int waterAdd = Integer.parseInt(Main.response());
        System.out.println("Write how many ml of milk you want to add: ");
        int milkAdd = Integer.parseInt(Main.response());
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int beansAdd = Integer.parseInt(Main.response());
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int cupsAdd = Integer.parseInt(Main.response());
        // add the amounts to machine
        this.waterAvailable += waterAdd;
        this.milkAvailable += milkAdd;
        this.beansAvailable += beansAdd;
        this.cupsAvailable += cupsAdd;
    }
    //takes out all the money in the machine
    public void take(){
        System.out.println("I gave you $" + this.moneyAvailable);
        this.moneyAvailable = 0;
    }
    //shows the levels of each item
    public void remaining(){
        System.out.println("The coffee machine has:");
        System.out.println(this.waterAvailable + " ml of water");
        System.out.println(this.milkAvailable + " ml of milk");
        System.out.println(this.beansAvailable + " g of coffee beans");
        System.out.println(this.cupsAvailable + " disposable cups");
        System.out.println("$" + this.moneyAvailable + " of money");
    }

}

public class Main {
    //method for system input
    public static String response(){
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        return userInput;
    }
    public static void main(String[] args) {
        String whatToDo;
        Scanner scanner = new Scanner(System.in);
        Status status = new Status();
        do { //always asks first
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            whatToDo = response().toUpperCase(); //takes user's response and makes uppercase
            status.actions(Status.State.valueOf(whatToDo)); //accesses actions method in status class. value of whatToDo is compared to enums in Status

        } while (!(whatToDo.equals("EXIT"))); //while whatToDo does not equal EXIT, keep running.
    }
}