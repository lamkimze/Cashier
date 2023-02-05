package payment.Voucher;

public class PercentVoucher extends Voucher{

    public double percent;

    public PercentVoucher(String code, double percent, int  quantity){
        super(code, quantity);
        setPercent(percent);
    }

    public boolean setPercent(double percent){
        boolean flag = false;
        if(percent > 0 && percent < 100){
            flag = true;
            this.percent = percent;
        }
        return flag;
    }

    public double getPercent(){
        return percent;
    }

    public double finalPrice(double oriPrice){
        return ((100-percent)/100)*oriPrice;
    }

    public String toString() {
        return "Voucher Code: " + super.getCode() + " Voucher Value: " + getPercent() + "%" + " Voucher Quantity: " + getQuantity();
    }

}
