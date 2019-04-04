/**
 * 初始化会员等级管理详情对话框
 */
var UserLevelInfoDlg = {
    userLevelInfoData : {}
};

/**
 * 清除数据
 */
UserLevelInfoDlg.clearData = function() {
    this.userLevelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserLevelInfoDlg.set = function(key, val) {
    this.userLevelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserLevelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserLevelInfoDlg.close = function() {
    parent.layer.close(window.parent.UserLevel.layerIndex);
}

/**
 * 收集数据
 */
UserLevelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('discount');
}

/**
 * 提交添加
 */
UserLevelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userLevel/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserLevel.table.refresh();
        UserLevelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userLevelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserLevelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userLevel/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserLevel.table.refresh();
        UserLevelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userLevelInfoData);
    ajax.start();
}

$(function() {

});
