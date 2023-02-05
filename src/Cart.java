import Item.Items;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Items>shoppingCart;
    private int cartID;
    private String memberID;
    private double totalPrice;

    public Cart(){
        shoppingCart = new ArrayList<Items>();
    }

    public void addItemToCart(Items inputItem){
        boolean flag = false;
        for(int i = 0; i < shoppingCart.size(); i++){
            if(inputItem.getItemCode() == shoppingCart.get(i).getItemCode()){
                flag = true;
                shoppingCart.get(i).setQuantity(shoppingCart.get(i).getQuantity() + inputItem.getQuantity());
            }
        }
        if(flag == false){
            shoppingCart.add(inputItem);
        }
    }

    public double priceOnly(){
        return totalPrice;
    }

    public String getTotalPrice(){
        totalPrice = 0;
        for(int i = 0; i < shoppingCart.size(); i++){
            totalPrice += shoppingCart.get(i).getPrice() * shoppingCart.get(i).getQuantity();
        }
        return "Total Price: " + String.format("%.2f", totalPrice) + " AUD";
    }

    public void removeItemFromCart(){
        for(int i = 0; i < shoppingCart.size(); i++){
            shoppingCart.clear();
            totalPrice = 0;
        }
    }

    public String toString(){
        String retVal = "";
        for(int i = 0; i < shoppingCart.size(); i++){
            retVal += shoppingCart.get(i).description() + '\n';
        }
        return retVal;
    }

    public ArrayList getShoppingCart() {
        return shoppingCart;
    }
}
