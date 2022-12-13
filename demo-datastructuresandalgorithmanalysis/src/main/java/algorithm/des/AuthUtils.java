package algorithm.des;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangxp
 * @createTime 2021年11月16日 16:34
 */
public class AuthUtils {

    private static final String SPLIT_STR = "_";

    private static final String KEY_STR = "&#EW(3tv#&*8306$";

    private static final int EXPIRE_TIME = 2 * 60 * 1000 * 1000;

    public static String encodeToken(Long userId, String name, DeviceEnum device, LanguageEnum languageEnum) {
        String bu = userId + SPLIT_STR + name + SPLIT_STR +
                device.getCode() + SPLIT_STR + languageEnum.name() + SPLIT_STR +
                (System.currentTimeMillis() + EXPIRE_TIME);
        return encrypt(bu);
    }

    private static String encrypt(String data) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.USER_KEY, data);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, KEY_STR).compact();
        return token;
    }

    public static AuthInfo decodeToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY_STR).parseClaimsJws(token).getBody();
            String data = (String) claims.get(Constants.USER_KEY);
            String[] infos = data.split(SPLIT_STR);
            AuthInfo info = new AuthInfo();
            info.setUserId(Long.parseLong(infos[0]));
            info.setUserRealName(infos[1]);
            info.setDeviceEnum(DeviceEnum.valueOf(infos[2]));
            info.setLanguageEnum(LanguageEnum.valueOf(infos[3]));
            info.setExpireTime(Long.parseLong(infos[4]));
            return info;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        AuthInfo authInfo = decodeToken("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2tleSI6IjI2NV90ZXh0XzUwMV9TRUFfQ0hJTkVTRV8xNjQwMTA2NzI4MjE0In0.D-trcfMk0FZ7l12_-JEwVpILmu4JE5zlzopSyJRefRbV3yFUOTYT11n0kXvjTM2NI0Exy-49OyS8j-6AoNHJaA");
        System.out.println(authInfo);
    }
}
