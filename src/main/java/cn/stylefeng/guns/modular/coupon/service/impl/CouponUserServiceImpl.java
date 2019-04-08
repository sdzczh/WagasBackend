package cn.stylefeng.guns.modular.coupon.service.impl;

import cn.stylefeng.guns.modular.system.model.CouponUser;
import cn.stylefeng.guns.modular.system.dao.CouponUserMapper;
import cn.stylefeng.guns.modular.coupon.service.ICouponUserService;
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
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements ICouponUserService {

    @Autowired
    private CouponUserMapper couponUserMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone, String title) {
        return couponUserMapper.selectLists(phone, title);
    }
}
