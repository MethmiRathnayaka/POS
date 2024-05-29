import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class POS {

    private String branchName;
    private Map<String, GroceryItem> database;

    public POS(String branchName) {
        this.branchName = branchName;
        this.database = new HashMap<>();
        // Initialize database with some sample items (you would fetch this from a real
        // database)
        initializeDatabase();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Map<String, GroceryItem> getDatabase() {
        return database;
    }

    public void setDatabase(Map<String, GroceryItem> database) {
        this.database = database;
    }

    // Method to initialize database with sample items
    private void initializeDatabase() {
        // Sample items (hardcoded for demonstration)
        GroceryItem item1 = new GroceryItem("001", "Apples", 2350, "1kg", "2024/03/05", "2024/03/15", "Local Produce",
                0.0);
        GroceryItem item2 = new GroceryItem("002", "Bread", 275, "450g", "2024/03/01", "2024/03/15", "Prima", 0.0);
        GroceryItem item3 = new GroceryItem("003", "Eggs", 600, "1 carton", "2024/03/02", "2024/03/20", "Farm Fresh",
                0.0);
        GroceryItem item4 = new GroceryItem("004", "Chicken", 1300, "1kg", "2024/03/03", "2024/03/14", "Local Butcher",
                0.0);
        GroceryItem item5 = new GroceryItem("005", "Potatoes", 400, "1kg", "2024/02/29", "2024/03/25", "Farmers Market",
                0.0);
        GroceryItem item6 = new GroceryItem("006", "Orange Juice", 200, "200ml", "2024/03/01", "2024/03/18", "Kist",
                0.0);
        GroceryItem item7 = new GroceryItem("007", "Spinach", 465, "200g", "2024/03/02", "2024/03/12", "Organic Greens",
                0.0);
        GroceryItem item8 = new GroceryItem("008", "Pasta", 660, "400g", "2024/02/29", "2024/03/25", "Italian Imports",
                0.0);
        GroceryItem item9 = new GroceryItem("009", "Milk", 130, "100ml", "2024/03/04", "2024/04/10", "Kothmale", 0.0);
        GroceryItem item10 = new GroceryItem("010", "Yogurt", 80, "75ml", "2024/03/03", "2024/04/05", "Highland", 0.0);
        GroceryItem item11 = new GroceryItem("011", "Cheese", 540, "120g", "2024/03/01", "2024/04/08", "HappyCow", 0.0);
        GroceryItem item12 = new GroceryItem("012", "Salmon", 490, "100g", "2024/03/05", "2024/03/14", "Fresh Seafoods",
                0.0);
        GroceryItem item13 = new GroceryItem("013", "Cereal", 1020, "300g", "2024/03/02", "2024/03/25", "Kelloggs",
                0.0);
        GroceryItem item14 = new GroceryItem("014", "Tomatoes", 410, "1kg", "2024/02/29", "2024/03/16", "Local Produce",
                0.0);
        GroceryItem item15 = new GroceryItem("015", "Bananas", 350, "500g", "2024/03/01", "2024/03/13",
                "Tropical Harvest", 0.0);
        GroceryItem item16 = new GroceryItem("016", "Coffee", 750, "200mg", "2024/03/03", "2024/04/01", "Nescafe", 0.0);
        GroceryItem item17 = new GroceryItem("017", "Ground Beef", 3500, "1kg", "2024/03/04", "2024/03/12",
                "Local Butcher", 0.0);
        GroceryItem item18 = new GroceryItem("018", "Lettuce", 320, "1kg", "2024/03/02", "2024/03/15", "Organic Greens",
                0.0);
        GroceryItem item19 = new GroceryItem("019", "Sprite", 170, "400ml", "2024/03/01", "2024/03/20", "Cocacola Co.",
                0.0);
        GroceryItem item20 = new GroceryItem("020", "Rice", 230, "1kg", "2024/03/05", "2024/04/10", "Araliya", 0.0);
        GroceryItem item21 = new GroceryItem("021", "Almonds", 1750, "200g", "2024/03/01", "2024/03/30", "Nuts 'n More",
                0.0);
        GroceryItem item22 = new GroceryItem("022", "Olive Oil", 2950, "500ml", "2024/02/29", "2024/03/31", "Sunflower",
                0.0);
        GroceryItem item23 = new GroceryItem("023", "Ice Cream", 570, "1l", "2024/03/02", "2024/04/15",
                "Eelephant house", 0.0);
        GroceryItem item24 = new GroceryItem("024", "Onions", 690, "1kg", "2024/03/03", "2024/03/16", "Local Produce",
                0.0);
        GroceryItem item25 = new GroceryItem("025", "Tea", 365, "200g", "2024/03/05", "2024/03/25", "Dilmah", 0.0);
        
        // Add items to the database
        database.put(item1.getItemCode(), item1);
        database.put(item2.getItemCode(), item2);
        database.put(item3.getItemCode(), item3);
        database.put(item4.getItemCode(), item4);
        database.put(item5.getItemCode(), item5);
        database.put(item6.getItemCode(), item6);
        database.put(item7.getItemCode(), item7);
        database.put(item8.getItemCode(), item8);
        database.put(item9.getItemCode(), item9);
        database.put(item10.getItemCode(), item10);
        database.put(item11.getItemCode(), item11);
        database.put(item12.getItemCode(), item12);
        database.put(item13.getItemCode(), item13);
        database.put(item14.getItemCode(), item14);
        database.put(item15.getItemCode(), item15);
        database.put(item16.getItemCode(), item16);
        database.put(item17.getItemCode(), item17);
        database.put(item18.getItemCode(), item18);
        database.put(item19.getItemCode(), item19);
        database.put(item20.getItemCode(), item20);
        database.put(item21.getItemCode(), item21);
        database.put(item22.getItemCode(), item22);
        database.put(item23.getItemCode(), item23);
        database.put(item24.getItemCode(), item24);
        database.put(item25.getItemCode(), item25);
    }

}