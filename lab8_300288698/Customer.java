import java.util.Random;

public class Customer{
    int arrivalTime;
    int initialNumberOfItems;
    int numberOfItems;
    static final int MAX_NUM_ITEMS = 25;


    public Customer(int arrivalTime){
        Random generator = new Random();

        this.arrivalTime = arrivalTime;
        this.initialNumberOfItems = generator.nextInt(MAX_NUM_ITEMS - 1 )+1;
        this.numberOfItems = initialNumberOfItems;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public int getNumberOfItems(){
        return numberOfItems;
    }

    public int getNumberOfServedItems(){
        return (initialNumberOfItems - numberOfItems);
    }

    public void serve(){
        numberOfItems--;
    }
}