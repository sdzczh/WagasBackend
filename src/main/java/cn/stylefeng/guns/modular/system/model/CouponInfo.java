package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-08
 */
@TableName("coupon_info")
public class CouponInfo extends Model<CouponInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 优惠券ID
     */
    @TableField("coupon_id")
    private Integer couponId;
    /**
     * 名称
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 用法规则
     */
    private String rules;
    /**
     * 展示图片
     */
    @TableField("show_img_url")
    private String showImgUrl;
    /**
     * 介绍图片
     */
    @TableField("info_img_url")
    private String infoImgUrl;
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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getShowImgUrl() {
        return showImgUrl;
    }

    public void setShowImgUrl(String showImgUrl) {
        this.showImgUrl = showImgUrl;
    }

    public String getInfoImgUrl() {
        return infoImgUrl;
    }

    public void setInfoImgUrl(String infoImgUrl) {
        this.infoImgUrl = infoImgUrl;
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
        return "CouponInfo{" +
        ", id=" + id +
        ", couponId=" + couponId +
        ", title=" + title +
        ", desc=" + desc +
        ", rules=" + rules +
        ", showImgUrl=" + showImgUrl +
        ", infoImgUrl=" + infoImgUrl +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
