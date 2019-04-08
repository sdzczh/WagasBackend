package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.GiftRecord;
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
 * @since 2019-04-08
 */
public interface GiftRecordMapper extends BaseMapper<GiftRecord> {

    List<Map<String, Object>> selectLists(@Param("pushPhone") String pushPhone, @Param("recivePhone")String recivePhone, @Param("giftName")String giftName, @Param("state")Integer state);
}
