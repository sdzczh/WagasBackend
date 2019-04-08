package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Bill;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
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
public interface BillMapper extends BaseMapper<Bill> {

    List<Map<String, Object>> selectLists(@Param("page") Page<Bill> page, @Param("phone") String phone, @Param("type") Integer type);
}
