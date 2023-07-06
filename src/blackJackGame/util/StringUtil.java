package blackJackGame.util;

public class StringUtil {
    /**
     * 区切り線作成
     * @param c 区切り使用する文字
     */
    public static String getSeparator(char c){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constant.windowMaxWidth; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 標準区切り線作成
     * @return 区切り文字列
     */
    public static String getSeparator(){
        return getSeparator('-');
    }

    /**
     * 空行作成
     */
    public static String getEmptyRow() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constant.windowMaxWidth; i++) {
            sb.append(Constant.SPACE);
        }
        return sb.toString();
    }

    /**
     * 文字の表示幅を取得する
     * 全角文字は2桁、半角文字は1桁としてカウントする
     * @param str 対象文字列
     * @return 文字幅
     */
    public static int getStringWidth(String str) {
        int width = 0;
        char[] charArray = str.toCharArray(); //String型の文字列をchar型の配列にする
        for (int i = 0; i < charArray.length; i++) {
            if (String.valueOf(charArray[i]).getBytes().length <= 1) {
                width += 1; //半角
            } else {
                width += 2; //全角
            }
        }
        return width;
    }

    /**
     * 文字列の中央表示
     */
    public static String alignCenter(String str) {
        // 文字列の表示幅を取得する
        int strTotalWidth = getStringWidth(str);
        if (strTotalWidth >= Constant.windowMaxWidth) {
            return str;
        }

        int prevSpaceWidth = (Constant.windowMaxWidth - strTotalWidth) / 2;
        int trailSpaceWidth = Constant.windowMaxWidth - (prevSpaceWidth + strTotalWidth);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prevSpaceWidth; i++) {
            sb.append(Constant.SPACE);
        }
        sb.append(str);
        for (int i = 0; i < trailSpaceWidth; i++) {
            sb.append(Constant.SPACE);
        }
        return sb.toString();
    }

}
