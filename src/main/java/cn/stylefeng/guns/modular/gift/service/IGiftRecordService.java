package cn.stylefeng.guns.modular.gift.service;

import cn.stylefeng.guns.modular.system.model.GiftRecord;
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
public interface IGiftRecordService extends IService<GiftRecord> {

    List<Map<String, Object>> selectLists(String pushPhone, String recivePhone, String giftName, Integer state);
}
