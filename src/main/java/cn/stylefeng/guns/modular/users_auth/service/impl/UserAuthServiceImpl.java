package cn.stylefeng.guns.modular.users_auth.service.impl;

import cn.stylefeng.guns.modular.system.model.UserAuth;
import cn.stylefeng.guns.modular.system.dao.UserAuthMapper;
import cn.stylefeng.guns.modular.users_auth.service.IUserAuthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return userAuthMapper.selectLists(phone);
    }
}
