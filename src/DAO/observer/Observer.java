
package DAO.observer;

import DTO.OrderBill_DTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class Observer {
    private static List<IOrderBillObserver> observers = new ArrayList<>();

    public static void addObserver(IOrderBillObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(IOrderBillObserver observer) {
        observers.remove(observer);
    }

    public static void notifyObservers(OrderBill_DTO orderBill) {
        for (IOrderBillObserver observer : observers) {
            observer.onPaymentSuccess(orderBill);
        }
    }
}