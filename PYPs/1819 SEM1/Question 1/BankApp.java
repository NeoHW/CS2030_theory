import java.util.Scanner;

class BankApp {
    static Scanner sc;

    static void transfer(Account source, Account target, double amount) {
        if (amount <= 0) {
            System.out.println("Amount needs to be greater than 0");
        } else if (source ==  null) {
            System.out.println("Needs a valid source account");
            return;
        } else if (target == null) {
            System.out.println("Needs a valid target account");
            return;
        }

        try {
            source.withdraw(amount);
            target.deposit(amount);
        } catch (AccountException a) {
            System.out.println(a);
            System.out.println("Transfer unsuccessful");
            return;
        }
        System.out.println("Transfer sucessful");
    }

    static double getAmount() {
        sc = new Scanner(System.in);
        System.out.print("Enter amount to transfer: ");
        return sc.nextDouble();
    }

    public static void main(String[] args) {
        Account s = getAccount();
        Account t = getAccount();
        double amt = getAmount();
        transfer(s, t, amt);
    }

    // other methods omitted
}

class Account {
    protected double balance;

    Account(double balance) {
        this.balance = balance;
    }

    Account withdraw(double amount) throws AccountException {
        if (amount > balance) {
            throw new AccountException("Withdrawl amount greater than balance!");
        }
        return new Account(this.balance - amount);
    }

    Account deposit(double amount) {
        return new Account(this.balance + amount);
    }

    // other methods omitted
}

class SavingsAccount extends Account {
    private double limit;

    SavingsAccount(double balance) {
        super(balance);
        this.limit = 1000.0;
    }

    @Override 
    Account withdraw(double amount) throws AccountException {
        if (amount > super.balance) {
            throw new AccountException("Withdrawl amount greater than balance!");
        } else if (amount > limit) {
            throw new AccountException("Withdrawl amount greater than withdrawal limit!");
        }
        return new Account(this.balance - amount);
    }

}

class AccountException extends Exception {
    AccountException(String s) {
        super(s);
    }
}
