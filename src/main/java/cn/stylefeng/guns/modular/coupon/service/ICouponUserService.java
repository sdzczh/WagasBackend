package cn.stylefeng.guns.modular.coupon.service;

import cn.stylefeng.guns.modular.system.model.CouponUser;
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
public interface ICouponUserService extends IService<CouponUser> {

    List<Map<String, Object>> selectLists(String phone, String title);
}
