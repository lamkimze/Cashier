package payment;

public class Points {
    private int totalPoint;

    public Points(){
        this.totalPoint = 1000;
    }

    public int getTotalPoint(){
        return totalPoint;
    }

    public void addPoint(int addedPoint){
        this.totalPoint += addedPoint;
    }

    public void reducePoint(int reducedPoint){
        if(this.totalPoint - reducedPoint >= 0){
            this.totalPoint -= reducedPoint;
        }
        else{
            System.out.println("insufficient point !!");
        }
    }
}
