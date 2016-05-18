import io.veronicacrowe.AccountStatus;
import io.veronicacrowe.AccountType;
import io.veronicacrowe.BankAccount;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by veronicacrowe on 5/2/16.
 */
public class BankAccountTest {
    private BankAccount b1;


    @Test
    public void getBalanceTestFrozenAcc(){
        b1 = new BankAccount(AccountType.SAVINGS, 45364, 400.00);
        b1.setStatus(AccountStatus.FREEZE);


        String actualValue = b1.getBalance();
        String expectedValue= "Your account is frozen.";
        Assert.assertEquals("The value should return whether your account is frozen.", actualValue, expectedValue);
    }
}
