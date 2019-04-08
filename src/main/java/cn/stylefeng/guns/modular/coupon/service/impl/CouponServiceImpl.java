package cn.stylefeng.guns.modular.coupon.service.impl;

import cn.stylefeng.guns.modular.system.model.Coupon;
import cn.stylefeng.guns.modular.system.dao.CouponMapper;
import cn.stylefeng.guns.modular.coupon.service.ICouponService;
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
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

    @Autowired
    private CouponMapper couponMapper;
    @Override
    public List<Map<String, Object>> selectLists(String title) {
        return couponMapper.selectLists(title);
    }
}
