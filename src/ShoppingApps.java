import Item.Items;
import payment.Card;
import payment.Voucher.Voucher;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingApps {

    private ArrayList<Items> items = new ArrayList();

    private HashMap<String, Member> members = new HashMap<>();

    private ArrayList <Items> insufficient = new ArrayList<>();


    public ShoppingApps() {
        addItems();
        addMembers();
        printStatus();
    }

    private int coverMenu() {
        Scanner coverScan = new Scanner(System.in);
        System.out.println("1) Sign Up");
        System.out.println("2) Sign In");
        System.out.println("3) Add Item");
        System.out.println("4) Update Item");
        System.out.println("5) Member List");
        System.out.println("6) Item List");
        System.out.println("7) Exit");
        System.out.println("-----------------");
        System.out.println("Select your option: ");
        return coverScan.nextInt();
    }

    private int selectMenu() {
        Scanner selectScan = new Scanner(System.in);
        System.out.println("1) Shopping");
        System.out.println("2) Check Out");
        System.out.println("3) Exit");
        System.out.println("-----------------");
        System.out.println("Select your option: ");
        return selectScan.nextInt();
    }

    private int payMenu(int points, double price) {
        Scanner payScan = new Scanner(System.in);
        System.out.println("1) Pay by Card");
        if(points - price > 0){
            System.out.println("2) Pay by Point");
        }
        System.out.println("3) Back");
        System.out.println("-----------------");
        System.out.println("Select your option: ");
        return payScan.nextInt();
    }

    public int shoppingList() {
        Scanner shopScan = new Scanner(System.in);
        displayItems();
        System.out.println("Select Item: ");
        return shopScan.nextInt();
    }

    public int voucherList(String userID){
        System.out.println("Please select your voucher: ");
        Scanner voucherScan = new Scanner(System.in);
        for(int i = 0; i < members.get(userID).getVoucherList().getVoucherList().size(); i++){
            System.out.println( i + 1 + ") " + members.get(userID).getVoucherList().getVoucherList().get(i).toString());
        }
        System.out.println((members.get(userID).getVoucherList().getVoucherList().size() + 1) + ") Skip");
        return voucherScan.nextInt();
    }

    public int voucherConfirmation(){
        Scanner confirmScan = new Scanner(System.in);
        System.out.println("1) Confirm");
        System.out.println("2) Back");
        System.out.println("Please Confirm your voucher: ");
        return confirmScan.nextInt();
    }

    public void printStatus() {
        System.out.println("Welcome to Konnie Shopping App");
        int selection1;
        mainLoop:
        do {
            selection1 = coverMenu();
            switch (selection1) {
                case 1:
                    Member customer;
                    do {
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Please Enter your Name: ");
                        String memberName = scanner1.nextLine();
                        System.out.println("Please Enter your Age: ");
                        String memberAge = scanner1.nextLine();
                        System.out.println("Please Enter your Phone Number: ");
                        String phoneNumber = scanner1.nextLine();
                        System.out.println("Please Enter your Full Address: ");
                        String fullAddress = scanner1.nextLine();
                        System.out.println("Please Enter your Email Address: ");
                        String memberEmail = scanner1.nextLine();
                        System.out.println("Please Enter your UserID: ");
                        String memberID = scanner1.nextLine();
                        System.out.println("Please Enter your Password: ");
                        String memberPassword = scanner1.nextLine();
                        System.out.println("Please Confirm your Password: ");
                        String confirmPassword = scanner1.nextLine();
                        while (!memberPassword.equals(confirmPassword) && (memberPassword.length() == 0)) {
                            System.out.println("Password must be the same as confirmed password");
                            System.out.println("Please Enter your Password: ");
                            memberPassword = scanner1.nextLine();
                            System.out.println("Please Confirmed your Password: ");
                            confirmPassword = scanner1.nextLine();
                        }
                        customer = Member.getInstance(memberID, memberPassword, memberName, memberAge, phoneNumber, fullAddress, memberEmail);
                        createMember(memberID, memberPassword, memberName, memberAge, phoneNumber, fullAddress, memberEmail);
                        continue mainLoop;
                    }
                    while (!customer.getValidity());

                case 2:
                    String userID;
                    String password;
                    boolean flag = false;
                    do {
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Please Enter your User ID: ");
                        userID = scanner2.nextLine();
                        System.out.println("Please Enter your Password: ");
                        password = scanner2.nextLine();
                        for (Member member : members.values()) {
                            if (member.getMemberID().equals(userID)) {
                                flag = true;
                                break;
                            }
                        }
                    } while (flag == false || !members.get(userID).getPassword().equals(password));

                    int selection2 = 0;
                    shoppingLoop:
                    do {
                        switch (selectMenu()) {
                            case 1:
                                do {
                                    selection2 = shoppingList();
                                    Scanner scanner21 = new Scanner(System.in);
                                    int quantity = 0;
                                    if (selection2 <= items.size()) {
                                        Items itemSelected = items.get(selection2 - 1);
                                        System.out.println('\n' + "Item selected: ");
                                        System.out.println(itemSelected.description() + '\n');
                                        do {
                                            System.out.println("Please select quantity: ");
                                            quantity = scanner21.nextInt();
                                            members.get(userID).addItem(new Items(itemSelected.getItemCode(), itemSelected.getItemBrand(), itemSelected.getItemName(), itemSelected.getPrice(), quantity));
                                        } while ((itemSelected.getQuantity() - quantity) < 0);
                                    }
                                    displayCart(userID);
                                } while (selection2 <= items.size());
                                break;

                            case 2:
                                if (renewQuantity(userID)) {
                                    System.out.println("Please Choose your payment method");
                                    Scanner scanner3 = new Scanner(System.in);
                                    int memberPoint = members.get(userID).getPoints().getTotalPoint();
                                    double memberPrice = members.get(userID).getShoppingCart().priceOnly();
                                    switch (payMenu(memberPoint, memberPrice)) {
                                        case 1:
                                            int selectionVoucher = 0;
                                            int voucherNo = members.get(userID).getVoucherList().getVoucherList().size();
                                            voucherLoop:
                                            do {
                                                selectionVoucher = voucherList(userID);
                                                Scanner scannerSV = new Scanner(System.in);
                                                if (selectionVoucher <= voucherNo) {
                                                    Voucher voucherSelected = members.get(userID).getVoucherList().getVoucherList().get(selectionVoucher - 1);
                                                    System.out.println('\n' + " Voucher Selected: ");
                                                    System.out.println(voucherSelected.toString());
                                                    switch (voucherConfirmation()){
                                                        case 1:
                                                            double finalMemberPrice = voucherSelected.finalPrice(memberPrice);
                                                            System.out.println("Original Price: " + memberPrice);
                                                            System.out.println("Final Price: " + finalMemberPrice);
                                                            System.out.println("You had saved: " + (memberPrice - finalMemberPrice));

                                                            Card card;
                                                            do {
                                                                System.out.println("Enter card Number: ");
                                                                String cardNo = scanner3.nextLine();
                                                                System.out.println("Enter card CVV Number: ");
                                                                String cardCVV = scanner3.nextLine();
                                                                System.out.println("Enter card Holder Name: ");
                                                                String cardName = scanner3.nextLine();
                                                                System.out.println("Enter Your 6 digits Password: ");
                                                                String cardPassword = scanner3.nextLine();
                                                                card = Card.getInstance(cardNo, cardCVV, cardName, cardPassword);
                                                                System.out.println(members.get(userID).description());
                                                                displayCart(userID);
                                                                System.out.println(card.toString());
                                                                members.get(userID).getShoppingCart().removeItemFromCart();
                                                                members.get(userID).getPoints().addPoint((int)(finalMemberPrice/100));
                                                                if(voucherSelected.getQuantity() > 1){
                                                                    members.get(userID).getVoucherList().getVoucherList().get(selectionVoucher - 1).setQuantity(voucherSelected.getQuantity() - 1);
                                                                }
                                                                else{
                                                                    members.get(userID).getVoucherList().getVoucherList().remove(selectionVoucher - 1);
                                                                }
                                                                continue shoppingLoop;
                                                            }
                                                            while (!card.isValidity());
                                                        case 2:
                                                            continue voucherLoop;
                                                    }
                                                }
                                                else if(selectionVoucher <= voucherNo + 1) {
                                                    Card card;
                                                    paymentLoop:
                                                    do {
                                                        System.out.println("Enter card Number: ");
                                                        String cardNo = scanner3.nextLine();
                                                        System.out.println("Enter card CVV Number: ");
                                                        String cardCVV = scanner3.nextLine();
                                                        System.out.println("Enter card Holder Name: ");
                                                        String cardName = scanner3.nextLine();
                                                        System.out.println("Enter Your 6 digits Password: ");
                                                        String cardPassword = scanner3.nextLine();
                                                        card = Card.getInstance(cardNo, cardCVV, cardName, cardPassword);
                                                        System.out.println(members.get(userID).description());
                                                        displayCart(userID);
                                                        System.out.println(card.toString());
                                                        members.get(userID).getShoppingCart().removeItemFromCart();
                                                        members.get(userID).getPoints().addPoint((int) (memberPrice / 100));
                                                        continue shoppingLoop;
                                                    }while (!card.isValidity());
                                                }
                                            } while (selectionVoucher > members.get(userID).getVoucherList().getVoucherList().size());
                                        case 2:
                                            members.get(userID).getPoints().reducePoint((int)memberPrice);
                                            System.out.println("remaining points: " + members.get(userID).getPoints().getTotalPoint());
                                        case 3:
                                            continue shoppingLoop;
                                    }
                                } else {
                                    Scanner inScan = new Scanner(System.in);
                                    int newQuant;
                                    for (int i = 0; i < insufficient.size(); i++){
                                        do {
                                            System.out.println('\n' + insufficient.get(i).description());
                                            System.out.println("Please change your quantity: ");
                                            newQuant = inScan.nextInt();
                                        }while (insufficient.get(i).getQuantity() < newQuant);
                                        Items item = insufficient.get(i);
                                        members.get(userID).getShoppingCart().addItemToCart(new Items(item.getItemCode(), item.getItemBrand(), item.getItemName(), item.getPrice(), newQuant));
                                    }
                                    continue shoppingLoop;
                                }
                            case 3:
                                continue mainLoop;
                        }
                    }
                    while (selection2 > 3);
                case 3:
                    Items newItem;
                    do {
                        Scanner scanner3 = new Scanner(System.in);
                        System.out.println("Add New Item");
                        System.out.println("Item Name: ");
                        String itemName = scanner3.nextLine();
                        System.out.println("Item Brand: ");
                        String itemBrand = scanner3.nextLine();
                        System.out.println("Item Price: ");
                        Double itemPrice = scanner3.nextDouble();
                        System.out.println("Item Quantity: ");
                        int itemQuantity = scanner3.nextInt();
                        newItem = new Items(itemBrand, itemName, itemPrice, itemQuantity);
                        items.add(newItem);
                    } while (!newItem.getValidity());

                    break;
                case 4:

                    break;
                case 5:
                    displayMember();
                    break;
                case 6:
                    displayItems();
                    break;
            }
        }
        while (selection1 != 7);
        System.out.println("Thank you for shopping at Konnie Shopping App");
    }

    public boolean renewQuantity(String userID) {
        boolean flag = true;
        ArrayList<Items> shopCart = members.get(userID).getShoppingCart().getShoppingCart();
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < shopCart.size(); j++) {
                if (shopCart.get(j).getItemCode() == items.get(i).getItemCode()) {
                    if ((items.get(i).getQuantity() - shopCart.get(j).getQuantity()) < 0) {
                        System.out.println("This Item has insufficient stock: ");
                        System.out.println(items.get(i).description());
                        System.out.println("Something went wrong!!");
                        flag = false;
                        insufficient.add(items.get(i));
                        shopCart.remove(j);
                    } else {
                        items.get(i).setQuantity(items.get(i).getQuantity() - shopCart.get(j).getQuantity());
                    }
                }
            }
        }
        return flag;
    }

    public void displayItems() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ") " + items.get(i).description());
        }
        System.out.println(items.size() + 1 + ") Exit");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    public void displayMember() {
        System.out.println("\nMember List: ");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Member member : members.values()) {
            System.out.println(member.description());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    public void displayCart(String userID) {
        System.out.println("\nYour Shopping Cart: ");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(members.get(userID).getShoppingCart().toString());
        System.out.println(members.get(userID).getShoppingCart().getTotalPrice());
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    public void createItem(String inputName, String inputBrand, Double inputPrice, int inputQuantity) {
        try {
            this.items.add(new Items(inputBrand, inputName, inputPrice, inputQuantity));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createMember(String newID, String newPassword, String newName, String newAge, String newPhone, String newAddress, String newEmail) {
        try {
            this.members.put(newID, Member.getInstance(newID, newPassword, newName, newAge, newPhone, newAddress, newEmail));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItems() {
        createItem("Shoes", "Adidas", 788.8, 14);
        createItem("Shoes", "Nike", 323.6, 18);
        createItem("Shirts", "Puma", 78.8, 32);
        createItem("Track Pants", "Adidas", 130.0, 12);
        createItem("Socks", "Nike", 21.9, 65);
        createItem("Bags", "Puma", 145.8, 32);
    }

    public void addMembers() {
        createMember("Casey", "Casey3179", "Casey Asbulment", "23", "0412357493", "1/23 Evelyn Street Clayton Vic 3168", "Casey0021@coles.com");
        createMember("Caleb", "Caleb2368", "Caleb Tan", "21", "0419486932", "1/26 Morton Street Clayton Vic 3168", "Caleb0021@coles.com");
        createMember("Antony", "Antony3168", "Antony Micheline", "31", "0495837205", "25 Donald Street,Clayton Vic 3168", "Antony0021@coles.com");
        createMember("Cloe", "Cloe7183", "Cloe Aster", "20", "0492857391", "Pullyn Street, Pullyn St, Clayton VIC 3168", "Cloe0021@coles.com");
        createMember("Mikey", "Mikey2179", "Mikey Mouse", "30", "0492847592", "1486 North Rd, Clayton Vic 3168", "Mikey0021@coles.com");
        createMember("Donald", "Donald1693", "Donald Duck", "49", "0492847794", "160 victoria st, Clayton Vic 3168", "Donald0021@coles.com");
    }

}
