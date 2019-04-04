package cn.stylefeng.guns.modular.wallet.service.impl;

import cn.stylefeng.guns.modular.system.model.Record;
import cn.stylefeng.guns.modular.system.dao.RecordMapper;
import cn.stylefeng.guns.modular.wallet.service.IRecordService;
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
 * @since 2019-04-04
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return recordMapper.selectLists(phone);
    }
}
