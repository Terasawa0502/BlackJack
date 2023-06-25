public interface BlackJackGame {

    /**
     * 実行用メソッド
     */
    void exec(OnFinishListener listener);

    /**
     * 終了メソッド
     * @param  reason 終了理由
     */
    void finish(Reason reason);


    /**
     * 終了通知用のリスナー
     */
    interface OnFinishListener {
        void onFinish(Reason reason);
    }

    enum Reason {
        USER_CHOOSES, // ユーザが選択
        RUNTIME_ERROR // 実行時エラー
    }


}
