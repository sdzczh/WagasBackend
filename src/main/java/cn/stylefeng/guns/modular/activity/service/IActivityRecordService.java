package cn.stylefeng.guns.modular.activity.service;

import cn.stylefeng.guns.modular.system.model.ActivityRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-08
 */
public interface IActivityRecordService extends IService<ActivityRecord> {

    List<Map<String, Object>> selectLists(String phone, String act_name);
}
