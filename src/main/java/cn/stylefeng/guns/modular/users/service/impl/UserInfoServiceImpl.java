package cn.stylefeng.guns.modular.users.service.impl;

import cn.stylefeng.guns.modular.system.model.UserInfo;
import cn.stylefeng.guns.modular.system.dao.UserInfoMapper;
import cn.stylefeng.guns.modular.users.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("phone", phone);
        return userInfoMapper.selectMaps(entityWrapper);
    }
}
