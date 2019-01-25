/**
 * 初始化区域管理详情对话框
 */
var RegionInfoDlg = {
    regionInfoData : {},
    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '区域名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '区域编号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RegionInfoDlg.clearData = function() {
    this.regionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegionInfoDlg.set = function(key, val) {
    this.regionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RegionInfoDlg.close = function() {
    parent.layer.close(window.parent.Region.layerIndex);
}

/**
 * 收集数据
 */
RegionInfoDlg.collectData = function() {
    this.set('id').set('name').set('pid').set('description').set('code');
}

/**
 * 验证数据是否为空
 */
RegionInfoDlg.validate = function () {
    $('#regionInfoForm').data("bootstrapValidator").resetForm();
    $('#regionInfoForm').bootstrapValidator('validate');
    return $("#regionInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
RegionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/add", function(data){
        Feng.success("添加成功!");
        window.parent.Region.table.refresh();
        RegionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.regionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RegionInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/update", function(data) {
        Feng.success("修改成功!");
        window.parent.Region.table.refresh();
        RegionInfoDlg.close();
    }, function(data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.regionInfoData);
    ajax.start();
}


/**
 * 点击父级编号input框时
 */
RegionInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#pName").attr("value", RegionInfoDlg.ztreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};


/**
 * 显示父级菜单选择的树
 */
RegionInfoDlg.showRegionSelectTree = function () {
    Feng.showInputTree("pName", "pIdDiv", 15, 34);
};

$(function () {
    Feng.initValidator("regionInfoForm", RegionInfoDlg.validateFields);
    var ztree = new $ZTree("pIdTree", "/region/selectRegionTreeList");
    ztree.bindOnClick(RegionInfoDlg.onClickDept);
    ztree.init();
    RegionInfoDlg.ztreeInstance = ztree;
});