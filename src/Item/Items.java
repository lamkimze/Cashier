package Item;


public class Items{

    private String itemBrand;
    private String itemName;
    private Double price;
    private int itemCode;
    private int quantity;
    private boolean validity;

    public Items(String itemBrand, String itemName, Double price, int quantity){
        if (setItemBrand(itemBrand) && setItemName(itemName) && setPrice(price) && setQuantity(quantity)){
            this.itemCode = Utils.nextID();
            this.validity = true;
        }
    }

    public Items(int itemCode, String itemBrand, String itemName, Double price, int quantity){
        if (setItemBrand(itemBrand) && setItemName(itemName) && setPrice(price) && setQuantity(quantity)){
            this.itemCode = itemCode;
            this.validity = true;
        }
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public boolean setItemBrand(String itemBrand) {
        boolean isValid = false;
        if(itemBrand.length() > 0 && itemBrand.length() < 20)
        {
            this.itemBrand = itemBrand;
            isValid = true;
        }
        return isValid;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean setItemName(String itemName) {
        boolean isValid = false;
        if(itemName.length() >1 && itemName.length() < 20) {
            isValid = true;
            this.itemName = itemName;
        }
        return isValid;
    }

    public Double getPrice() {
        return price;
    }

    public boolean setPrice(Double price) {
        boolean isValid = false;
        if(price > 0) {
            isValid = true;
            this.price = price;
        }
        return isValid;
    }

    public int getItemCode() {
        return itemCode;
    }

    public boolean setQuantity(int quantity) {
        boolean isValid = false;
        if(quantity > 0){
            isValid = true;
            this.quantity = quantity;
        }
        return isValid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void buyItem(int buyQuantity){
        if (this.quantity > 1){
            this.quantity -= buyQuantity;
        }

    }

    public boolean getValidity(){
        return this.validity;
    }

    public String description(){
        String retVal = "";
        retVal += "Item: " + getItemName() + " | ";
        retVal += "Item Brand: " + getItemBrand() + " | ";
        retVal += "Cost: " + getPrice() + " AUD | ";
        retVal += "Quantity: " + getQuantity();
        return retVal;
    }

}
