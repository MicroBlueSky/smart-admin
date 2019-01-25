/**
 * 区域管理初始化
 */
var Region = {
    id: "RegionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Region.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'ID',hide:true, visible: false, align: 'center', valign: 'middle'},
            {title: '区域名称', field: 'NAMECODE', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'CREATE_BY', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'CREATE_TIME', visible: true, align: 'center', valign: 'middle'},
            {title: '描述信息', field: 'DESCRIPTION', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Region.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Region.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加区域管理
 */
Region.openAddRegion = function () {
    var index = layer.open({
        type: 2,
        title: '添加区域',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/region/region_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看区域管理详情
 */
Region.openRegionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '区域详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/region/region_edit/' + Region.seItem.ID
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除区域管理
 */
Region.delete = function () {
    if (this.check()) {

        var operation = function(){
            var id = Region.seItem.ID;
            var ajax = new $ax(Feng.ctxPath + "/region/delete", function () {
                Feng.success("删除成功!");
                Region.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", id);
            ajax.start();
        };

        Feng.confirm("是否删除该区域?",operation);
    }
};


/**
 * 查询区域
 */
Region.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['code'] = $("#code").val();
    Region.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Region.initColumn();
    var table = new BSTreeTable(Region.id, "/region/list", defaultColunms);
    console.log(table);
    table.setExpandColumn(1);
    table.setCodeField("ID");
    table.setParentCodeField("PID");
    table.setExpandAll(true);
    Region.table = table.init();

});
