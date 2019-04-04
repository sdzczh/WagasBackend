package cn.stylefeng.guns.modular.user_log.service.impl;

import cn.stylefeng.guns.modular.system.model.UserLoginLog;
import cn.stylefeng.guns.modular.system.dao.UserLoginLogMapper;
import cn.stylefeng.guns.modular.user_log.service.IUserLoginLogService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return userLoginLogMapper.selectLists(phone);
    }
}
