package payment.Voucher;

import java.util.ArrayList;

public class VoucherManager {

    ArrayList<Voucher> voucherList;

    public VoucherManager(){
        voucherList = new ArrayList<>();
    }

    public void addValueVoucher(String code, double value, int quantity ){
        Voucher voucher = new ValueVoucher(code, value, quantity);
        voucherList.add(voucher);
    }

    public void addPercentVoucher(String code, double percent, int quantity){
        Voucher voucher = new PercentVoucher(code, percent, quantity);
        voucherList.add(voucher);
    }

    public ArrayList<Voucher> getVoucherList(){
        return voucherList;
    }

}
