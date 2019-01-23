/**
 * 区域管理初始化
 */
var SysArea = {
    id: "SysAreaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysArea.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            /*{title: 'id', field: 'AREA_CODE', visible: false, align: 'center', valign: 'middle'},*/
            {title: '区域代码', field: 'AR', visible: true, align: 'center', valign: 'middle'},
            {title: '区域名称', field: 'NAMECODE', visible: true, align: 'center', valign: 'middle'},
            {title: '区域类型', field: 'areaType', visible: true, align: 'center', valign: 'middle'},
            {title: '排序号', field: 'AREA_CODE', visible: true, align: 'center', valign: 'middle'},
            {title: '父级编号', field: 'PARENT_CODE', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '备注信息', field: 'REMARKS', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'UPDATE_DATE', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SysArea.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SysArea.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加区域管理
 */
SysArea.openAddSysArea = function () {
    var index = layer.open({
        type: 2,
        title: '添加区域',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/area/Area_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看区域管理详情
 */
SysArea.openSysAreaDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '区域详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/area/Area_update/' + SysArea.seItem.ID
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除区域管理
 */
SysArea.delete = function () {
    if (this.check()) {

        var operation = function(){
            var areacode = SysArea.seItem.ID;
            var ajax = new $ax(Feng.ctxPath + "/area/delete", function () {
                Feng.success("删除成功!");
                MgrUser.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("areacode", areacode);
            ajax.start();
        };

        Feng.confirm("是否删除用户" + SysArea.seItem.ACOUNT + "?",operation);
    }
};

/**
 * 查询区域管理列表
 */
SysArea.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SysArea.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SysArea.initColumn();
    var table = new BSTreeTable(SysArea.id, "/area/list", defaultColunms);
    console.log(table);
    table.setExpandColumn(1);
    table.setCodeField("AREA_CODE");
    table.setParentCodeField("PARENT_CODE");
    table.setExpandAll(true);
    SysArea.table = table.init();
});
