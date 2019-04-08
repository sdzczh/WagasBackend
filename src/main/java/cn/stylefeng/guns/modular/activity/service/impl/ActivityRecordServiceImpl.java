package cn.stylefeng.guns.modular.activity.service.impl;

import cn.stylefeng.guns.modular.system.model.ActivityRecord;
import cn.stylefeng.guns.modular.system.dao.ActivityRecordMapper;
import cn.stylefeng.guns.modular.activity.service.IActivityRecordService;
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
public class ActivityRecordServiceImpl extends ServiceImpl<ActivityRecordMapper, ActivityRecord> implements IActivityRecordService {

    @Autowired
    private ActivityRecordMapper activityRecordMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone, String act_name) {
        return activityRecordMapper.selectLists(phone, act_name);
    }
}
