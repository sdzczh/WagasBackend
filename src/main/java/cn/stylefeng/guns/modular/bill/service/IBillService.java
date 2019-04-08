package cn.stylefeng.guns.modular.bill.service;

import cn.stylefeng.guns.modular.system.model.Bill;
import com.baomidou.mybatisplus.plugins.Page;
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
public interface IBillService extends IService<Bill> {

    List<Map<String, Object>> selectLists(Page<Bill> page, String phone, Integer type);
}
