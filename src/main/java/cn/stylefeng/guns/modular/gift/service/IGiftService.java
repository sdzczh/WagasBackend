package cn.stylefeng.guns.modular.gift.service;

import cn.stylefeng.guns.modular.system.model.Gift;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-08
 */
public interface IGiftService extends IService<Gift> {

    List selectLists(String gift_name);
}
