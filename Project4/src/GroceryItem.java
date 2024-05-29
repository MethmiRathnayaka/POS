import java.io.Serializable;

class GroceryItem implements Serializable , Cloneable{
    private String itemCode;
    private String itemName;
    private double price;
    private String weightOrSize;
    private String manufacturingDate;
    private String expiryDate;
    private String manufacturerName;
    private double discount=0;
    private int quantity;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public GroceryItem(String itemCode, String itemName, double price, String weightOrSize, String manufacturingDate, String expiryDate, String manufacturerName, double discount) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.weightOrSize = weightOrSize;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.manufacturerName = manufacturerName;
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeightOrSize(String weightOrSize) {
        this.weightOrSize = weightOrSize;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getWeightOrSize() {
        return weightOrSize;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public double getDiscount() {
        return discount;
    }
}