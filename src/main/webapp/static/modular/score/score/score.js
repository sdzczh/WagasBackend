/**
 * 会员积分管理管理初始化
 */
var Score = {
    id: "ScoreTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Score.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '会员级别', field: 'level_name', visible: true, align: 'center', valign: 'middle'},
            {title: '等级折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'},
            {title: '可用积分', field: 'avail_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '冻结积分', field: 'frozen_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '累计积分', field: 'add_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Score.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Score.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员积分管理
 */
Score.openAddScore = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员积分管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/score/score_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员积分管理详情
 */
Score.openScoreDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员积分管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/score/score_update/' + Score.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员积分管理
 */
Score.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/score/delete", function (data) {
            Feng.success("删除成功!");
            Score.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scoreId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员积分管理列表
 */
Score.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    Score.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Score.initColumn();
    var table = new BSTable(Score.id, "/score/list", defaultColunms);
    table.setPaginationType("client");
    Score.table = table.init();
});
