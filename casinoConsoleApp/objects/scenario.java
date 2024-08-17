package objects;

public class scenario {
    private int prize1;
    private int prize2;
    private int prize3;

    public scenario(){};

    public scenario(int prize1, int prize2, int prize3){
        this.prize1 = prize1;
        this.prize2 = prize2;
        this.prize3 = prize3;
    }

    public int getPrize1() {
        return prize1;
    }

    public void setPrize1(int prize1) {
        this.prize1 = prize1;
    }

    public int getPrize2() {
        return prize2;
    }

    public void setPrize2(int prize2) {
        this.prize2 = prize2;
    }

    public int getPrize3() {
        return prize3;
    }

    public void setPrize3(int prize3) {
        this.prize3 = prize3;
    }
}
