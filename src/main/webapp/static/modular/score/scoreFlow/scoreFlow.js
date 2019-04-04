/**
 * 积分流水管理初始化
 */
var ScoreFlow = {
    id: "ScoreFlowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScoreFlow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '操作类型', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人ID', field: 'oper_id', visible: true, align: 'center', valign: 'middle'},
            {title: '操作积分', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '操作后积分', field: 'result_amount', visible: true, align: 'center', valign: 'middle'},
            {title: '积分过期时间', field: 'end_time', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ScoreFlow.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ScoreFlow.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加积分流水
 */
ScoreFlow.openAddScoreFlow = function () {
    var index = layer.open({
        type: 2,
        title: '添加积分流水',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scoreFlow/scoreFlow_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看积分流水详情
 */
ScoreFlow.openScoreFlowDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '积分流水详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/scoreFlow/scoreFlow_update/' + ScoreFlow.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除积分流水
 */
ScoreFlow.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scoreFlow/delete", function (data) {
            Feng.success("删除成功!");
            ScoreFlow.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scoreFlowId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询积分流水列表
 */
ScoreFlow.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    ScoreFlow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScoreFlow.initColumn();
    var table = new BSTable(ScoreFlow.id, "/scoreFlow/list", defaultColunms);
    table.setPaginationType("client");
    ScoreFlow.table = table.init();
});
