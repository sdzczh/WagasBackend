package cn.stylefeng.guns.modular.users.service.impl;

import cn.stylefeng.guns.modular.system.model.UserInfo;
import cn.stylefeng.guns.modular.system.dao.UserInfoMapper;
import cn.stylefeng.guns.modular.users.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
