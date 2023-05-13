// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        //BlackJackゲーム実行
        new BlackJackGameImpl().exec(reason -> {
            switch (reason) {
                case USER_CHOOSES:
                    // TODO: 未実装
                    break;
                case RUNTIME_ERROR:
                    // TODO: 未実装
                    break;
            }
            // TODO: 終了処理
        });
    }
}