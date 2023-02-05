package payment;

public class Card {

    private String CardNo = null;
    private String CardCvv = null;
    private String CardName = null;
    private String CardPassword = null;
    private boolean Validity = false;

    private Card(String CardNo, String CardCvv, String CardName, String CardPassword){
        if(setCardNo(CardNo) && setCardCvv(CardCvv) && setCardName(CardName) && setCardPassword(CardPassword)){
            this.CardNo = CardNo;
            this.CardCvv = CardCvv;
            this.CardName = CardName;
            this.CardPassword = CardPassword;
            this.Validity = true;
        }
    }

    public static Card getInstance(String CardNo, String CardCvv, String CardName, String CardPassword){
        Card card = new Card( CardNo, CardCvv, CardName, CardPassword);
        return card;
    }

    public String getCardNo() {
        return CardNo;
    }

    public boolean setCardNo(String cardNo) {
        boolean isValid = false;
        if(cardNo.length() == 16){
            isValid = true;
            CardNo = cardNo;
        }
        return isValid;
    }

    public String getCardCvv() {
        return CardCvv;
    }

    public boolean setCardCvv(String cardCvv) {
        boolean isValid = false;
        if(cardCvv.length() == 3){
            isValid = true;
            CardCvv = cardCvv;
        }
        return isValid;
    }

    public String getCardName() {
        return CardName;
    }

    public boolean setCardName(String cardName) {
        boolean isValid = false;
        if(cardName.length() > 3 && cardName.length() < 20){
            isValid = true;
            CardName = cardName;
        }
        return isValid;
    }

    public String getCardPassword() {
        return CardPassword;
    }

    public boolean setCardPassword(String cardPassword) {
        boolean isValid = false;
        if(cardPassword.length() == 6){
            isValid = true;
            CardPassword = cardPassword;
        }
        return isValid;
    }

    public boolean isValidity() {
        return Validity;
    }

    @Override
    public String toString() {
        String retVal = "";
        retVal += "Payment Successful !!" + '\n';

        return retVal;
    }
}
