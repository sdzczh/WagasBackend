package cn.stylefeng.guns.modular.gift.service.impl;

import cn.stylefeng.guns.modular.system.model.Gift;
import cn.stylefeng.guns.modular.system.dao.GiftMapper;
import cn.stylefeng.guns.modular.gift.service.IGiftService;
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
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

    @Autowired
    private GiftMapper giftMapper;
    @Override
    public List<Map<String, Object>> selectLists(String gift_name) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like("gift_name", gift_name);
        return giftMapper.selectMaps(entityWrapper);
    }
}
