public class Account {
    
    double balance;

    public Account(){
        this.balance = 0;
    }

    public void deposit(double money){
        this.balance = this.balance + money;
        System.out.println("New Balance = " + this.balance + "$");
    }

    public void withdraw(double money) throws NotEnoughMoneyException{
        if(money > this.balance){
            throw new NotEnoughMoneyException(money, balance);
        }

        this.balance = this.balance - money;
        System.out.println("New Balance = " + this.balance + "$");
    }

    public double getBalance(){
        return balance;
    }

}