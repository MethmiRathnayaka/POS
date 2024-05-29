import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("-------------------------Welcome!-------------------------");
        System.out.println("-----------------Super-Saving SuperMarket-----------------");
        System.out.println();

        POS pos = new POS("Katubedda Branch");

        System.out.println("Update today's discounts");
        System.out.println();
        
        addDiscounts(pos); 

        start(pos);

    }

    // Method to start the Point Of Sales
    public static void start(POS pos) {
        List<GroceryItem> items = new ArrayList<>();
        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);

            System.out.println("Enter cashier name: ");
            String cashiername = br.readLine();

            Bill bill = new Bill(cashiername, pos);

            System.out.println();
            System.out.println("Choose a relavent number: ");

            System.out.println("1.Process new bill");
            System.out.println("2.Resume the pending bill");
            System.out.println("3.Close");
            System.out.println();

            int input = Integer.parseInt(br.readLine());

            switch (input) {
                case 1:
                    bill.processBill(items);
                    start(pos);
                case 2:
                    System.out.println("Enter the customer name for the pending bill continuation");
                    String name = br.readLine();
                    bill.handlePendingBill(name);
                    start(pos);
                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid input");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: " + nfe.getMessage());
            start(pos);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            start(pos);
        }
    }

    // Method to add discounts to relavent items
    public static void addDiscounts(POS pos) throws ItemCodeNotFoundException {  

        System.out.println("Enter 0 if there are no more discounts");
        System.out.println();

        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            String itemCode;
            double discount;

            while (true) {
                System.out.println("Enter the itemCode: ");
                itemCode = br.readLine();

                if (itemCode.equals("0")) {
                    break;  
                }
                GroceryItem item = pos.getDatabase().get(itemCode);

                if (item == null) {
                    throw new ItemCodeNotFoundException("Item with code " + itemCode + " not found.");
                    
                } else {
                    System.out.println("Enter the discount: ");
                    String sDiscount = br.readLine();
                    discount = Double.parseDouble(sDiscount);
                    item.setDiscount(discount);
                    
                }
               
            }

        } catch(ItemCodeNotFoundException icnf){
            System.out.println("Error: " + icnf.getMessage());
            addDiscounts(pos);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Error: " + nfe.getMessage());
            addDiscounts(pos);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            addDiscounts(pos);
        }

    }
}