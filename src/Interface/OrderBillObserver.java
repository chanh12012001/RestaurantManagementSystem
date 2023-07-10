package Interface;
import BUS.OrderBill_BUS;
import DTO.OrderBill_DTO;

public interface OrderBillObserver {
    void onPaymentSuccess(OrderBill_DTO orderBill);
}

