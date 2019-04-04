/**
 * 初始化积分流水详情对话框
 */
var ScoreFlowInfoDlg = {
    scoreFlowInfoData : {}
};

/**
 * 清除数据
 */
ScoreFlowInfoDlg.clearData = function() {
    this.scoreFlowInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreFlowInfoDlg.set = function(key, val) {
    this.scoreFlowInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreFlowInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScoreFlowInfoDlg.close = function() {
    parent.layer.close(window.parent.ScoreFlow.layerIndex);
}

/**
 * 收集数据
 */
ScoreFlowInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('operType')
    .set('operId')
    .set('amount')
    .set('resultAmount')
    .set('endTime')
    .set('state')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
ScoreFlowInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scoreFlow/add", function(data){
        Feng.success("添加成功!");
        window.parent.ScoreFlow.table.refresh();
        ScoreFlowInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreFlowInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScoreFlowInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scoreFlow/update", function(data){
        Feng.success("修改成功!");
        window.parent.ScoreFlow.table.refresh();
        ScoreFlowInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreFlowInfoData);
    ajax.start();
}

$(function() {

});
