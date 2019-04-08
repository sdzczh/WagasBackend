package cn.stylefeng.guns.modular.activity.service.impl;

import cn.stylefeng.guns.modular.system.model.ActivityInfo;
import cn.stylefeng.guns.modular.system.dao.ActivityInfoMapper;
import cn.stylefeng.guns.modular.activity.service.IActivityInfoService;
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
 * @since 2019-04-08
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements IActivityInfoService {

    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone, String act_name) {
        return activityInfoMapper.selectLists(phone, act_name);
    }
}
