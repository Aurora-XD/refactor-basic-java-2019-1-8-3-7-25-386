package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        //四舍五入
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {

        BigDecimal subTotal = calculateSubtotal(products, items);
        BigDecimal subItemCount = SubtractOrderItemCount(products,items);

        subTotal = subTotal.subtract(subItemCount);

        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return BigDecimal2double(grandTotal);
    }

    public double BigDecimal2double(BigDecimal bigDecimal){

        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    public BigDecimal SubtractOrderItemCount(List<Product> products, List<OrderItem> items){

        BigDecimal subItemCount = new BigDecimal(0);
        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);

            BigDecimal subOrderCount = calculateOrderCount(product.getPrice(),curItem.getCount());
            BigDecimal reducedPrice = product.getDiscountRate().multiply(subOrderCount);

            subItemCount = subItemCount.add(reducedPrice);
        }

        return subItemCount;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = calculateOrderCount(product.getPrice(),item.getCount());
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }

    public BigDecimal calculateOrderCount(BigDecimal bigDecimal,Integer count){
        return bigDecimal.multiply(new BigDecimal(count));
    }
}
