package cn.stylefeng.guns.modular.score.service.impl;

import cn.stylefeng.guns.modular.system.model.Score;
import cn.stylefeng.guns.modular.system.dao.ScoreMapper;
import cn.stylefeng.guns.modular.score.service.IScoreService;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return scoreMapper.selectLists(phone);
    }
}
