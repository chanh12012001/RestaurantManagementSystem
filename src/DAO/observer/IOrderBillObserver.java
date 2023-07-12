
package DAO.observer;

import DTO.OrderBill_DTO;

/**
 *
 * @author macbookpro
 */
public interface IOrderBillObserver {
    void onPaymentSuccess(OrderBill_DTO orderBill);
}
