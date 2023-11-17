public class DoorLock {

    //constant.
    public static final int MAX_NUMBER_OF_ATTEMPTS = 3;

    //Instance variables.
    private Combination combination;
    private boolean open;
    private boolean activated;
    private int numberOfAttempts;

    //Constructor.
    public DoorLock (Combination combination){
        this.combination = combination;
        this.open = false;
        this.activated = true;
        this.numberOfAttempts = 0;
    }

    //Access methods.
    public boolean isOpen(){
        if (this.open == true){
            return true;
        }
        return false;
    }

    public boolean isActivated(){
        if (this.activated == true){
            return true;
        }
        return false;   
    }

    // Notice that numberOfAttempts is compared to
    // MAX_NUMBER_OF_ATTEMPTS only when its value has been
    // incremented, Also, numberOfAttempts should be set to zero when
    // activated is false.  Problems related to the combined action of
    // these two variables have caused problems for some students.

    public boolean open(Combination combination){
        if (this.activated == true){
            if ((combination.equals(this.combination)) == true){
                this.open = true;
                this.numberOfAttempts = 0;
            }
            else{
                this.numberOfAttempts++;
            }
            if (numberOfAttempts == MAX_NUMBER_OF_ATTEMPTS){
                this.activated = false;
            }
        }
        return this.isOpen();
    }

    public void activate(Combination c){
        if (this.combination == c){
            this.activated = true;
        }
    }
}