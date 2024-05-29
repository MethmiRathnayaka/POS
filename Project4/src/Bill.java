import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Bill {
    private String cashierName;
    private String customerName;
    private POS pos;

    public Bill(String cashierName, POS pos) {
        this.cashierName = cashierName;
        this.pos=pos;
    }

    // Method to get item details by item code
    public GroceryItem getItemDetails(String itemCode) throws ItemCodeNotFoundException {
        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            GroceryItem item = pos.getDatabase().get(itemCode);
            if (item == null) {
                throw new ItemCodeNotFoundException("Item with code " + itemCode + " not found.");
            }

            return item;
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Method to process a bill
    public void processBill(List<GroceryItem> items) {
        GroceryItem groceryItem;
        boolean paused = false;

        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            String itemCode;

            System.out.println("Enter customer name: ");
            customerName = br.readLine();

            System.out.println("Enter 0 to end adding items to the bill.");
            System.out.println("Enter 1 to hold bill.");

            while (true) {
                System.out.print("Enter item code: ");
                itemCode = br.readLine();
                if (itemCode.equals("0")) {
                    break;
                } else if (itemCode.equals("1")) {
                    storePendingBill(items);
                    paused = true;
                    break;
                } else {
                    groceryItem = getItemDetails(itemCode);
                    if (groceryItem != null) {
                        groceryItem = (GroceryItem) getItemDetails(itemCode).clone(); //
                        System.out.println("Enter the quantity: ");
                        int quantity=Integer.parseInt(br.readLine());
                        groceryItem.setQuantity(quantity);
                        items.add(groceryItem);
                    }
                }
            }
            System.out.println("Bill is processing.");
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("Error: " + e.getMessage());

        }
        if (!paused) {
            double totalDiscount = 0.0;
            double totalPrice = 0.0;

            // Get the current date and time using LocalDateTime
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Define a DateTimeFormatter to format the output
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd                                                                         HH:mm:ss");

            // Format the current date and time using the formatter
            String formattedDateTime = currentDateTime.format(formatter);

            // Print the formatted current date and time
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                         SUPER-SAVING SUPERMARCKET              ");
            System.out.println("                                             " + pos.getBranchName() + "           ");
            
            System.out.println(formattedDateTime);

            System.out.println("Cashier name: " + this.cashierName);
            if (isRegistered(customerName)) {
                System.out.println("Customer name: " + this.customerName);
            }

            System.out.println();

            System.out.println("Item: \t\tUnit Price(Rs): \tQuantity: \t\tDiscount: \t\tNet Price(Rs): ");

            for (GroceryItem item : items) {
                double discount= item.getPrice() *item.getQuantity()* (item.getDiscount() / 100.0);
                double netPrice = item.getPrice() *item.getQuantity()-discount;
                totalDiscount +=discount;
                totalPrice += netPrice;

                // Print or store bill details
                
                System.out.println( item.getItemName() +"\t\t"+ item.getPrice() +"\t\t\t"+item.getQuantity()+"\t\t\t"+ item.getDiscount() + "%" +"\t\t\t"+netPrice);
            }

            // Print or store total discount and total price
            System.out.println();
            System.out.println("Total Discount: " + totalDiscount);
            System.out.println("Total Price: " + totalPrice);
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public static boolean isRegistered(String customerName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("registeredCustomers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(customerName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to store pending bill
    public void storePendingBill(List<GroceryItem> items) {
        try {
            // Make a FileOutputStream
            FileOutputStream fileStream = new FileOutputStream(customerName+".ser");
            // Make a ObjectOutputStream
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            // Write the object
            os.writeObject(items);
            // Close the ObjectOutputStream
            os.close();
            System.out.println("Pending bill stored successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePendingBill(String pendingName) {
        try {
            // Make a FileInputStream
            FileInputStream fileStream = new FileInputStream(pendingName+".ser");
            // Make an ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(fileStream);
            // Read the objects
            List<GroceryItem> items = (List<GroceryItem>) ois.readObject();
            processBill(items);
            ois.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error: Pending bill file '" + pendingName + ".ser' not found.");
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
