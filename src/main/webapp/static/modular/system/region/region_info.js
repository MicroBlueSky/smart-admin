/**
 * 初始化区域管理详情对话框
 */
var SysAreaInfoDlg = {
    sysAreaInfoData : {},
    ztreeInstance: null,
    validateFields: {
        areaName: {
            validators: {
                notEmpty: {
                    message: '区域名称不能为空'
                }
            }
        },
        areaType: {
            validators: {
                notEmpty: {
                    message: '区域类型不能为空'
                }
            }
        },
        areaCode: {
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
SysAreaInfoDlg.clearData = function() {
    this.sysAreaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysAreaInfoDlg.set = function(key, val) {
    this.sysAreaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysAreaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SysAreaInfoDlg.close = function() {
    parent.layer.close(window.parent.Region.layerIndex);
}

/**
 * 收集数据
 */
SysAreaInfoDlg.collectData = function() {
    this.set('areaCode').set('parentCode').set('areaName').set('areaType').set('remarks');
}

/**
 * 验证数据是否为空
 */
SysAreaInfoDlg.validate = function () {
    $('#areaInfoForm').data("bootstrapValidator").resetForm();
    $('#areaInfoForm').bootstrapValidator('validate');
    return $("#areaInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
SysAreaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/add", function(data){
        Feng.success("添加成功!");
        window.parent.Region.table.refresh();
        SysAreaInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysAreaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SysAreaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/region/update", function(data){
        Feng.success("修改成功!");
        window.parent.Region.table.refresh();
        SysAreaInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysAreaInfoData);
    ajax.start();
}

$(function() {

});

/**
 * 点击父级编号input框时
 */
SysAreaInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#parentCode").attr("value", treeNode.id);
};


/**
 * 显示父级菜单选择的树
 */
SysAreaInfoDlg.showAreaSelectTree = function () {
    Feng.showInputTree("parentCode", "parentCodeDiv", 15, 34);
};

$(function () {
    Feng.initValidator("areaInfoForm", SysAreaInfoDlg.validateFields);

    var ztree = new $ZTree("parentCodeTree", "/region/selectAreaTreeList");
    ztree.bindOnClick(SysAreaInfoDlg.onClickDept);
    ztree.init();
    SysAreaInfoDlg.ztreeInstance = ztree;

    //初始化是否是菜单
    if($("#typeValue").val() == undefined){
        $("#areaType").val(1);
    }else{
        $("#areaType").val($("#typeValue").val());
    }
});