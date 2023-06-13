package data;

public class Dealer extends Human{

    /**
     * フィールド
     */
    private static Dealer instance;

    public static Dealer getInstance() {
        if (instance == null) {
            return new Dealer();
        }
        return instance;
    }

    // ディーラーの名前
    private String name;

    private Dealer (String name) {
        this.name = name;
    }

    private Dealer () {
        this("ディーラー");
    }

    // ディーラーの名前表示(getter)
    public String getName() {
        return this.name;
    }

}
