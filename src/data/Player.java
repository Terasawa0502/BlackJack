package data;

public class Player {
    /**
     * フィールド
     */

    //プレイヤーの所持金
    private int money;

    /**
     * コンストラクタ
     */
    public Player() {
        this(100);
    }

    public Player(int money) {
        this.money = money;
    }

    /**
     * メソッド
     */

    //所持金表示
    public String getMoney(){
        String msg = "    あなたの現在の手持ちは" + money + "$です    ";
        return msg;
    }
}
