package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Score;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
public interface ScoreMapper extends BaseMapper<Score> {

    List<Map<String, Object>> selectLists(@Param("phone") String phone);
}
