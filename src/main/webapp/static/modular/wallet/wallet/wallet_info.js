/**
 * 初始化会员钱包管理详情对话框
 */
var WalletInfoDlg = {
    walletInfoData : {}
};

/**
 * 清除数据
 */
WalletInfoDlg.clearData = function() {
    this.walletInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WalletInfoDlg.set = function(key, val) {
    this.walletInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WalletInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WalletInfoDlg.close = function() {
    parent.layer.close(window.parent.Wallet.layerIndex);
}

/**
 * 收集数据
 */
WalletInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('walletNumber')
    .set('availBalance')
    .set('frozenBalance')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
WalletInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wallet/add", function(data){
        Feng.success("添加成功!");
        window.parent.Wallet.table.refresh();
        WalletInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.walletInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WalletInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wallet/update", function(data){
        Feng.success("修改成功!");
        window.parent.Wallet.table.refresh();
        WalletInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.walletInfoData);
    ajax.start();
}

$(function() {

});
