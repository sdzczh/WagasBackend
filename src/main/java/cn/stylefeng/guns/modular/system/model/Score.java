package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
public class Score extends Model<Score> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 用户级别
     */
    private Integer level;
    /**
     * 可用积分
     */
    @TableField("avail_balance")
    private BigDecimal availBalance;
    /**
     * 冻结积分
     */
    @TableField("frozen_balance")
    private BigDecimal frozenBalance;
    /**
     * 累计积分
     */
    @TableField("add_balance")
    private BigDecimal addBalance;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(BigDecimal availBalance) {
        this.availBalance = availBalance;
    }

    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public BigDecimal getAddBalance() {
        return addBalance;
    }

    public void setAddBalance(BigDecimal addBalance) {
        this.addBalance = addBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Score{" +
        ", id=" + id +
        ", userId=" + userId +
        ", level=" + level +
        ", availBalance=" + availBalance +
        ", frozenBalance=" + frozenBalance +
        ", addBalance=" + addBalance +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
