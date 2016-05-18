package io.veronicacrowe;

/**
 * Created by veronicacrowe on 5/2/16.
 */
public class BankAccount {


    private long accountNumber;
    private AccountType accountType;
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    private AccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    String[] transaction = new String[10];




    public BankAccount(AccountType accountType, long accountNumber, double accountBalance, OverdraftProtection overdraftProtection){
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        accountStatus = AccountStatus.OPEN;
        if (accountType == AccountType.SAVINGS || accountType == AccountType.INVESTMENT){
            interestRate = 4;
        }
        this.overdraftProtection = overdraftProtection;
    }

    //set name for bank account holder
    public void setAccountHolderName(String name){
        if (accountStatus != AccountStatus.CLOSED){
            this.accountHolderName = name;
        }
    }

    //set account status
    public void setStatus(AccountStatus newAccountStatus) {
        if (accountStatus != AccountStatus.CLOSED) {
            accountStatus = newAccountStatus;
        }
        if(newAccountStatus == AccountStatus.CLOSED){
            if(accountBalance == 0){
                accountStatus = newAccountStatus;
            }
        }
    }

    public AccountStatus getStatus(){
        return accountStatus;
    }


    //get account balance
    public double getAccountBalance(){
        if (accountStatus == AccountStatus.FREEZE){
            return 0;
        }
        return accountBalance;
    }

    //deduct debit from account balance
    public void deductDebit(double debit){
        accountBalance = accountBalance - debit;

    }

    //add credit from account balance
    public void addCredit(double credit){
        accountBalance = accountBalance + credit;
    }

    public String debit(double debit){
        if(accountStatus == AccountStatus.OPEN){
            if (overdraftProtection == OverdraftProtection.ENABLED){
                if(accountBalance > debit){
                    deductDebit(debit);
                    return "Debit approved!";
                }
            }
            if (overdraftProtection == OverdraftProtection.DISABLED){
                deductDebit(debit);
                return "Debit approved!";
            }
        }
        return "Debit not approved";
    }

    public String credit(double credit){
        if (accountStatus == AccountStatus.OPEN){
            addCredit(credit);
            return "Credit approved!";
        }
        return "Credit not approved";
    }

    public void makeTransfer(BankAccount transferToAccount, double transferValue){
        deductDebit(transferValue);
        transferToAccount.addCredit(transferValue);
    }

    public String transfer(BankAccount transferToAccount, double transferValue){
        if(accountStatus == AccountStatus.OPEN && transferToAccount.getStatus() == AccountStatus.OPEN){
            if (accountBalance - transferValue >= 0){
                makeTransfer(transferToAccount, transferValue);
                return "Transfer approved";
            }
        }
        return "Transfer not approved";
    }

}
