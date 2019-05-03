package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate() {
        BigDecimal subTotal = new BigDecimal(0);

        //calculate subTotal
        subTotal = calculateTotalPrice(orderLineItemList).subtract(calculateTotalDiscount(discounts));
        // calculate tax
        BigDecimal tax = subTotal.multiply(this.tax);

        // calculate GrandTotal
        BigDecimal grandTotal = subTotal.add(tax);

        return grandTotal;
    }

    public BigDecimal calculateTotalPrice(List<OrderLineItem> orderLineItemList){

        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderLineItem orderLineItem : orderLineItemList) {
            totalPrice = totalPrice.add(orderLineItem.getPrice());
        }

        return totalPrice;
    }

    public BigDecimal calculateTotalDiscount(List<BigDecimal> discounts){

        BigDecimal totalDiscount = new BigDecimal(0);

        for (BigDecimal discount : discounts) {
            totalDiscount = totalDiscount.add(discount);
        }

        return totalDiscount;
    }

}
