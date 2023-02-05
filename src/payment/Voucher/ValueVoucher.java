package payment.Voucher;

public class ValueVoucher extends Voucher{

    public double value;

    public ValueVoucher(String code, double value, int quantity){
        super(code, quantity);
        setValue(value);
    }

    public boolean setValue(double value){
        boolean flag = false;
        if(value > 0){
            flag = true;
            this.value = value;
        }
        return flag;
    }

    public double getValue(){
        return value;
    }

    public double finalPrice(double oriPrice){
        return oriPrice - value;
    }

    @Override
    public String toString() {
        return "Voucher Code: " + super.getCode() + " Voucher Value: " + getValue() + " Voucher Quantity: " + getQuantity();
    }
}
