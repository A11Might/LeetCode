import java.util.HashMap;

/**
 * @author qhhu
 * @date 2020/2/11 - 9:09
 *
 * [535] TinyURL的加密与解密
 *
 * 题目: TinyURL是一种URL简化服务, 比如: 当你输入一个URL https://leetcode.com/problems/design-tinyurl 时,
 *      它将返回一个简化的URL http://tinyurl.com/4e9iAk.请你设计一个TinyURL的加密encode和解密decode方法.
 *
 * 难度: medium
 *
 * 思路: 使用String类重写的hashCode()方法来为每一个URL产生加密结果. 并将映射结果保存在HashMap中以供解码
 */
public class Codec {
    private HashMap<Integer, String> data = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        data.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        Integer key = Integer.valueOf(shortUrl.substring(19));
        return data.get(key);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
