package payment.Voucher;

import java.util.HashMap;

public class Voucher {

    public String code;
    public int quantity;

    public Voucher(String code, int quantity){
        setCode(code);
        setQuantity(quantity);
    }

    public boolean setQuantity(int quantity){
        boolean flag = false;
        if (quantity >0){
            flag = true;
            this.quantity = quantity;
        }
        return flag;
    }

    public boolean setCode(String code){
        boolean flag = false;
        if(code.length() == 6){
            flag = true;
            this.code = code;
        }
        return flag;
    }

    public int getQuantity(){
        return quantity;
    }

    public double finalPrice(double oriPrice){
        return oriPrice;
    }

    public String getCode(){
        return code;
    }


    @Override
    public String toString() {
        return "Voucher{" +
                "code='" + code + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
