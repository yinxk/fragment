package chip.date20211120.third;

import java.math.BigDecimal;
import java.util.Optional;

public class TransferMain {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        TransferService transferService = new TransferServiceImpl(accountService);

        System.out.println(accountService.listAccounts());
        System.out.println(accountService.listAccountBalanceOpLog());

        test(accountService, transferService, 1L, 2L, new BigDecimal("100"));
        test(accountService, transferService, 1L, 2L, new BigDecimal("800"));
        test(accountService, transferService, 1L, 2L, new BigDecimal("100"));
        test(accountService, transferService, 1L, 2L, new BigDecimal("100"));
        test(accountService, transferService, 2L, 1L, new BigDecimal("1500"));

    }

    private static void test(AccountService accountService, TransferService transferService, long from, long to,
                             BigDecimal amount) {
        TransferOrderResult order = transferService.createOrder(from, to, amount);
        if (Optional.ofNullable(order).map(TransferOrderResult::getId).orElse(null) != null) {
            System.out.println(order.getId());
            TransferResult transfer = transferService.transfer(order.getId());
            System.out.println(transfer.isSuccess());
            transfer = transferService.transfer(order.getId());
            System.out.println(transfer.isSuccess());
            transfer = transferService.transfer(order.getId());
            System.out.println(transfer.isSuccess());
        }
        System.out.println(accountService.listAccounts());
        System.out.println(accountService.listAccountBalanceOpLog());
    }
}
