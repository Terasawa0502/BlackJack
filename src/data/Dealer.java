package data;

public class Dealer extends Human{

    /**
     * フィールド
     */
    private static Dealer instance;

    public static Dealer getInstance() {
        if (instance == null) {
            instance = new Dealer();
        }
        return instance;
    }

    private Dealer () {
        name = "ディーラー";
    }

    // ディーラーの名前表示(getter)
    public String getName() {
        return name;
    }

}
