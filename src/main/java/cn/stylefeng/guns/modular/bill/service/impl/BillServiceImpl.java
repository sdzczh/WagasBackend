package cn.stylefeng.guns.modular.bill.service.impl;

import cn.stylefeng.guns.modular.system.model.Bill;
import cn.stylefeng.guns.modular.system.dao.BillMapper;
import cn.stylefeng.guns.modular.bill.service.IBillService;
import com.baomidou.mybatisplus.plugins.Page;
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
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Map<String, Object>> selectLists(Page<Bill> page, String phone, Integer type) {
        return billMapper.selectLists(page, phone, type);
    }
}
