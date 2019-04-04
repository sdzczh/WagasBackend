package cn.stylefeng.guns.modular.user_log.service;

import cn.stylefeng.guns.modular.system.model.UserLoginLog;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
public interface IUserLoginLogService extends IService<UserLoginLog> {

    List<Map<String, Object>> selectLists(String phone);
}
