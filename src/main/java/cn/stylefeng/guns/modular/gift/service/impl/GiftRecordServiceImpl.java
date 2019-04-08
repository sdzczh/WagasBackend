package cn.stylefeng.guns.modular.gift.service.impl;

import cn.stylefeng.guns.modular.system.model.GiftRecord;
import cn.stylefeng.guns.modular.system.dao.GiftRecordMapper;
import cn.stylefeng.guns.modular.gift.service.IGiftRecordService;
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
public class GiftRecordServiceImpl extends ServiceImpl<GiftRecordMapper, GiftRecord> implements IGiftRecordService {

    @Autowired
    private GiftRecordMapper giftRecordMapper;
    @Override
    public List<Map<String, Object>> selectLists(String pushPhone, String recivePhone, String giftName, Integer state) {
        return giftRecordMapper.selectLists(pushPhone, recivePhone, giftName, state);
    }
}
