package cn.szxys.security;

import cn.szxys.bean.XysSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/8.
 */

@Component
public class SessionManager {

    final Logger logger = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    //private Map<String,XysSession> sessionSet = new HashMap<String,XysSession>();

    // 每天早八点到晚八点，间隔2分钟执行任务
    // corn 表达式：秒 分 时 天 月 星期 年;
    @Scheduled(cron="30 * * * * ?")
    public void deleteInvalidSession() {

        logger.warn("===Springboot @Scheduled 验证 30秒 间隔===");

    }

    /**
     *
     * @param wxCode
     * @param wxSessionKey
     * @param wxOpenID
     * @param wxUnionID
     * @return
     */
    public String generateSession(String wxCode, String wxSessionKey, String wxOpenID, String wxUnionID){

        XysSession   s = new XysSession(wxCode,wxSessionKey,wxOpenID,wxUnionID);
        ValueOperations<String, XysSession> operations=redisTemplate.opsForValue();
        operations.set(s.getSessionID(), s);
        // 会话30分钟过期
         redisTemplate.expire(s.getSessionID(),30, TimeUnit.MINUTES);

        //sessionSet.put(s.getSessionID(),s);
        return s.getSessionID();
    }

    /**
     * 判断会话是否有效
     * @param sessionID
     * @return
     */
    public boolean isSessionValid(String sessionID){

        ValueOperations<String, XysSession> operations=redisTemplate.opsForValue();

        if(!redisTemplate.hasKey(sessionID)) return false;

//        if(!isXysSessionValid(operations.get(sessionID))){
//            redisTemplate.delete(sessionID);
//            return false;
//        }
//        if (!sessionSet.containsKey(sessionID)) return false;
//
//        if(sessionSet.get(sessionID).isSessionExpire()){
//            sessionSet.remove(sessionID);
//            return false;
//        }

        return true;

    }

//    public boolean isXysSessionValid(XysSession s)
//    {
//        return s.getExpireTime() - Calendar.getInstance().getTimeInMillis()/1000  > 0;
//    }

    public XysSession getXysSession(String sessionID){

        if(!isSessionValid(sessionID)) return null;

        ValueOperations<String, XysSession> operations=redisTemplate.opsForValue();

        XysSession xysS = operations.get(sessionID);

//        XysSession xysS = sessionSet.get(sessionID);

        return xysS;
    }

}
