/**
 * 初始化会员积分管理详情对话框
 */
var ScoreInfoDlg = {
    scoreInfoData : {}
};

/**
 * 清除数据
 */
ScoreInfoDlg.clearData = function() {
    this.scoreInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreInfoDlg.set = function(key, val) {
    this.scoreInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScoreInfoDlg.close = function() {
    parent.layer.close(window.parent.Score.layerIndex);
}

/**
 * 收集数据
 */
ScoreInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('level')
    .set('availBalance')
    .set('frozenBalance')
    .set('addBalance')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
ScoreInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/score/add", function(data){
        Feng.success("添加成功!");
        window.parent.Score.table.refresh();
        ScoreInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScoreInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/score/update", function(data){
        Feng.success("修改成功!");
        window.parent.Score.table.refresh();
        ScoreInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreInfoData);
    ajax.start();
}

$(function() {

});
