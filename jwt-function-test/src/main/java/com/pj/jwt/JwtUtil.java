package com.pj.jwt;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.pj.mockdao.SysUser;

/**
 * jwt 工具类
 *
 * @author click33
 * @since 1.31.0
 */
public class JwtUtil {

    // key：账号id
    public static final String USER_ID = "userId";

    // key：user
    public static final String USER = "user";

    // key：有效截止期 (时间戳)
    public static final String EFF = "eff";

    // key：乱数 （ 混入随机字符串，防止每次生成的 token 都是一样的 ）
    public static final String RN_STR = "rnStr";

    // 秘钥
    public static String keyt = "asdasdasifhueuiwyurfewbfjsdafjk";


    // ------ 创建

    /**
     * 创建 jwt
     *
     * @param userId 账号id
     * @param user User对象
     * @param timeout token有效期 (单位 秒)
     * @return jwt-token
     */
    public static String createToken(long userId, SysUser user, long timeout) {

        // 计算 eff 有效期：13 位时间戳，代表此 token 到期的时间
        long effTime = timeout * 1000 + System.currentTimeMillis();

        // 创建
        JWT jwt = JWT.create()
                .setPayload(USER_ID, userId)
                .setPayload(USER, user)
                .setPayload(EFF, effTime)
                // 塞入一个随机字符串，防止同账号同一毫秒下每次生成的 token 都一样的
                .setPayload(RN_STR, RandomUtil.randomString(32))
                ;

        // 返回
        return jwt.setSigner(createSigner(keyt)).sign();
    }

    /**
     * 返回 jwt 使用的签名算法
     *
     * @param keyt 秘钥
     * @return /
     */
    public static JWTSigner createSigner (String keyt) {
        return JWTSignerUtil.hs256(keyt.getBytes());
    }

    // ------ 解析

    /**
     * jwt 解析
     */
    public static JWT parseToken(String token) {
        return parseToken(token, true);
    }

    /**
     * jwt 解析
     *
     * @param token Jwt-Token值
     * @param isCheckTimeout 是否校验 timeout 字段
     * @return 解析后的jwt 对象
     */
    public static JWT parseToken(String token, boolean isCheckTimeout) {

        // 如果token为null
        if(token == null) {
            throw new RuntimeException("jwt 字符串不可为空");
        }

        // 解析
        JWT jwt;
        try {
            jwt = JWT.of(token);
        } catch (JWTException | JSONException e) {
            throw new RuntimeException("jwt 解析失败：" + token, e);
        }
        JSONObject payloads = jwt.getPayloads();

        // 校验 Token 签名
        boolean verify = jwt.setSigner(createSigner(keyt)).verify();
        if( ! verify) {
            throw new RuntimeException("jwt 签名无效：" + token);
        }

        // 校验 Token 有效期
        if(isCheckTimeout) {
            Long effTime = payloads.getLong(EFF, 0L);
            if(effTime == null || effTime < System.currentTimeMillis()) {
                throw new RuntimeException("jwt 已过期：" + token);
            }
        }

        // 返回
        return jwt;
    }

    /**
     * 获取 jwt 数据载荷 （校验 sign、timeout）
     * @param token token值
     * @return 载荷
     */
    public static JSONObject getPayloads(String token) {
        return parseToken(token, true).getPayloads();
    }

    /**
     * 获取 jwt 剩余有效期
     * @param token JwtToken值
     * @return 值
     */
    public static long getTimeout(String token) {

        // 取出数据
        JWT jwt;
        try {
            jwt = JWT.of(token);
        } catch (JWTException e) {
            // 解析失败
            return -2;
        }
        JSONObject payloads = jwt.getPayloads();

        // 如果签名无效
        boolean verify = jwt.setSigner(createSigner(keyt)).verify();
        if( ! verify) {
            return -2;
        }

        Long effTime = payloads.get(EFF, Long.class);
        // 如果已经超时
        if(effTime == null || effTime < System.currentTimeMillis()) {
            return -2;
        }

        // 计算timeout (转化为以秒为单位的有效时间)
        return (effTime - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取 jwt 代表的账号id
     * @param token Token值
     * @return 值
     */
    public static Object getUser(String token) {
        return getPayloads(token).get(USER);
    }

}
