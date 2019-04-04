package cn.stylefeng.guns.modular.users_auth.service;

import cn.stylefeng.guns.modular.system.model.UserAuth;
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
public interface IUserAuthService extends IService<UserAuth> {

    List<Map<String, Object>> selectLists(String phone);
}
