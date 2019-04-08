package cn.stylefeng.guns.modular.activity.service.impl;

import cn.stylefeng.guns.modular.system.model.Activity;
import cn.stylefeng.guns.modular.system.dao.ActivityMapper;
import cn.stylefeng.guns.modular.activity.service.IActivityService;
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
 * @since 2019-04-08
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public List<Map<String, Object>> selectLists(String goods_name) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("goods_name", goods_name);
        return activityMapper.selectMaps(entityWrapper);
    }
}
