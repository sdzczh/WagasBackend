/**
 * 初始化用户活动管理详情对话框
 */
var ActivityInfoInfoDlg = {
    activityInfoInfoData : {}
};

/**
 * 清除数据
 */
ActivityInfoInfoDlg.clearData = function() {
    this.activityInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityInfoInfoDlg.set = function(key, val) {
    this.activityInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActivityInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ActivityInfo.layerIndex);
}

/**
 * 收集数据
 */
ActivityInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('activityId')
    .set('nowNumber')
    .set('state')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
ActivityInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/activityInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ActivityInfo.table.refresh();
        ActivityInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ActivityInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/activityInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ActivityInfo.table.refresh();
        ActivityInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityInfoInfoData);
    ajax.start();
}

$(function() {

});
