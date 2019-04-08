/**
 * 初始化活动记录详情对话框
 */
var ActivityRecordInfoDlg = {
    activityRecordInfoData : {}
};

/**
 * 清除数据
 */
ActivityRecordInfoDlg.clearData = function() {
    this.activityRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityRecordInfoDlg.set = function(key, val) {
    this.activityRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActivityRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActivityRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.ActivityRecord.layerIndex);
}

/**
 * 收集数据
 */
ActivityRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('activityId')
    .set('number')
    .set('type')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
ActivityRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/activityRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.ActivityRecord.table.refresh();
        ActivityRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ActivityRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/activityRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.ActivityRecord.table.refresh();
        ActivityRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activityRecordInfoData);
    ajax.start();
}

$(function() {

});
