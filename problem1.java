import java.util.*;

class Item {
    String name;
    double price;
    boolean isImported;
    boolean isExempt;

    Item(String name, double price, boolean isImported, boolean isExempt) {
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }
    
    public double calculateSalesTax() {
        double salesTax = 0;
        if (!isExempt) salesTax += price * 0.10;
        if (isImported) salesTax += price * 0.05;
        return Math.ceil(salesTax * 20.0) / 20.0;  // round to nearest 0.05
    }
}

class Receipt {
    List<Item> items = new ArrayList<>();
    double totalTax = 0;
    double totalPrice = 0;

    public void addItem(Item item) {
        double tax = item.calculateSalesTax();
        totalTax += tax;
        double finalPrice = item.price + tax;
        totalPrice += finalPrice;
        System.out.println(item.name + ": " + String.format("%.2f", finalPrice));
    }

    public void printReceipt() {
        System.out.println("Sales Taxes: " + String.format("%.2f", totalTax));
        System.out.println("Total: " + String.format("%.2f", totalPrice));
    }
}

public class SalesTaxCalculator {
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.addItem(new Item("book", 12.49, false, true));
        receipt.addItem(new Item("music CD", 14.99, false, false));
        receipt.addItem(new Item("chocolate bar", 0.85, false, true));

        receipt.printReceipt();
    }
}
