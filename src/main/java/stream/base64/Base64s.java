package stream.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author:wuhao
 * @description:Base64加密
 * @date:18/12/3
 */
public class Base64s {
    public static void main(String[] args) {
        /**
         * 新的Base64API也支持URL和MINE的编码解码。
         * (Base64.getUrlEncoder() / Base64.getUrlDecoder(), Base64.getMimeEncoder() / Base64.getMimeDecoder())。
         *
         * BASE64不是用来加密的，是BASE64编码后的字符串，全部都是由标准键盘上面的常规字符组成，这样编码后的字符串在网关之间传递不会产生UNICODE字符串不能识别或者丢失的现象
         */
        String text = "Base64 finally in java8!";
        String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("encode:" + encode);

        String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println("decode:" + decode);
    }
}
