import Item.Items;
import payment.Points;
import payment.Voucher.Voucher;
import payment.Voucher.VoucherManager;

import java.util.ArrayList;

public class Member{

    private String memberName;
    private String age;
    private String phoneNo;
    private String fullAddress;
    private String email;
    private String memberID;
    private String password;
    private boolean validity;
    private Cart shoppingCart;
    private Points points;
    private VoucherManager voucherList;


    private Member(String memberID, String password){
        this.memberID = memberID;
        this.password = password;
        this.shoppingCart = new Cart();
        this.points = new Points();
        this.voucherList = new VoucherManager();
        this.voucherList.addValueVoucher("ABAXZ1", 10, 2);
    }

    public static Member getInstance(String memberID, String password, String memberName, String age, String phoneNo, String fullAddress, String email){
        Member member = new Member(memberID, password);
        member.validity =
        member.setMemberName(memberName) &&
        member.setAge(age) &&
        member.setPhoneNo(phoneNo) &&
        member.setFullAddress(fullAddress) &&
        member.setEmail(email);

        return member;
    }

    public String getMemberName() {
        return memberName;
    }

    public boolean setMemberName(String memberName) {
        boolean isValid = false;
        if(memberName.length() > 0 && memberName.length() < 20){
            isValid = true;
            this.memberName = memberName;
        }
        return  isValid;
    }

    public String getAge() {
        return age;
    }

    public boolean setAge(String age) {
        boolean isValid = false;
        if(age.length() == 2) {
            isValid = true;
            this.age = age;
        }
        return  isValid;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public boolean setPhoneNo(String phoneNo) {
        boolean isValid = false;
        if(phoneNo.length() == 10) {
            isValid = true;
            this.phoneNo = phoneNo;
        }
        return  isValid;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public boolean setFullAddress(String fullAddress){
        boolean isValid = false;
        if(fullAddress.length() > 20){
            isValid = true;
            this.fullAddress = fullAddress;
        }
        return isValid;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        boolean isValid = false;
        if(email.contains("@") && email.contains(".com")){
            isValid = true;
            this.email = email;
        }
        return isValid;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getPassword() {
        return password;
    }

    public Points getPoints(){
        return points;
    }

    public boolean getValidity(){
        return this.validity;
    }

    public void addItem(Items inputItem){
        shoppingCart.addItemToCart(inputItem);
    }

    public Cart getShoppingCart(){
        return shoppingCart;
    }

    public VoucherManager getVoucherList(){
        return  voucherList;

    }
    public String description(){
        return getMemberID() +  " | " + getPassword().substring(0, password.length()-6) + "****" + " | " + getMemberName() + " | " + getAge() + " | " + getPhoneNo() + " | " + getFullAddress() + " | " + getEmail() + " | " + getPoints().getTotalPoint();
    }
}
