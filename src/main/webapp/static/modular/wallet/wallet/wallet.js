/**
 * 会员钱包管理管理初始化
 */
var Wallet = {
    id: "WalletTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Wallet.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '会员卡编号', field: 'wallet_number', visible: true, align: 'center', valign: 'middle'},
            {title: '可用金额', field: 'avail_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '冻结金额', field: 'frozen_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Wallet.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Wallet.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员钱包管理
 */
Wallet.openAddWallet = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员钱包管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wallet/wallet_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员钱包管理详情
 */
Wallet.openWalletDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员钱包管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wallet/wallet_update/' + Wallet.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员钱包管理
 */
Wallet.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wallet/delete", function (data) {
            Feng.success("删除成功!");
            Wallet.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("walletId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员钱包管理列表
 */
Wallet.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    Wallet.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Wallet.initColumn();
    var table = new BSTable(Wallet.id, "/wallet/list", defaultColunms);
    table.setPaginationType("client");
    Wallet.table = table.init();
});
