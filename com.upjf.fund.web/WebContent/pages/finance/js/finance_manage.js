/**
 * layui 引入 laydate,form,jquery 组件
 */
layui.use(['laydate','form','jquery'],function(){
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = jQuery = layui.$;
/********************项目股东出资审核管理start*******************************/
	$(function(){
		laydate.render({
			elem: '#payDateStart' //指定元素
		});
		laydate.render({
			elem: '#payDateEnd' //指定元素
		});
		laydate.render({
			elem: '#receiverDateStart' //指定元素
		});
		laydate.render({
			elem: '#receiverDateEnd' //指定元素
		});
		laydate.render({
			elem: '#paybackDateStart' //指定元素
		});
		laydate.render({
			elem: '#paybackDateEnd' //指定元素
		});
		
	});
	
/********************项目股东出资审核管理end*******************************/
	
});
$(function(){
	
//************************************************1.项目股东出资审核管理*********************************************

//************************************************4.项目股东回款*********************************************
//回款列表---点击’新增回款‘
    $('#Share_Return_Money_Add1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市汇联资管二十三号投资管理合伙企业  新增回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Share_Return_Money_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//回款列表---点击’修改回款‘
    $('#Share_Return_Money_modify1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市汇联资管二十三号投资管理合伙企业  修改回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Share_Return_Money_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//回款列表---点击’查看回款明细‘
    $('#Share_Return_Money_See1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市汇联资管二十三号投资管理合伙企业  回款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Share_Return_Money_See'),
            btn: [ '返回'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });


//************************************************5.投资主体利润分配*********************************************

//回款列表---点击’新增回款‘
    $('#Subject_Profit_Add1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市融鑫资产管理有限公司  新增回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Subject_Profit_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//回款列表---点击’修改回款‘
    $('#Subject_Profit_modify1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市融鑫资产管理有限公司  修改回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Subject_Profit_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//回款列表---点击’查看修回款明细‘
    $('#Subject_Profit_See1').on('click', function(){
        layer.open({
            type: 1,
            title: ['深圳市融鑫资产管理有限公司  回款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Subject_Profit_See'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });


//************************************************6.投资人利润分配*********************************************
//回款列表---点击’新增回款‘
    $('#Investor_Profit_Add1').on('click', function(){
        layer.open({
            type: 1,
            title: ['XXX有限公司  新增回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Investor_Profit_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });


//回款列表---点击’修改回款‘
    $('#Investor_Profit_modify1').on('click', function(){
        layer.open({
            type: 1,
            title: ['XXX有限公司  修改回款','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Investor_Profit_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });


//回款列表---点击’查看修回款明细‘
    $('#Investor_Profit_See1').on('click', function(){
        layer.open({
            type: 1,
            title: ['XXX有限公司  回款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Investor_Profit_See'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });

//************************************************7.投资主体财务管理*********************************************
//回款列表---点击’查看付款明细‘
    $('#Subject_Finance_Pay1').on('click', function(){
        layer.open({
            type: 1,
            title: ['付款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Subject_Finance_Pay'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });

//回款列表---点击’查看回款明细‘
    $('#Subject_Finance_reture1').on('click', function(){
        layer.open({
            type: 1,
            title: ['回款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Subject_Finance_reture'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });


//************************************************7.投资人财务管理*********************************************
//回款列表---点击’查看付款明细‘
    $('#Investor_Finance_Pay1').on('click', function(){
        layer.open({
            type: 1,
            title: ['付款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Investor_Finance_Pay'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });

//回款列表---点击’查看回款明细‘
    $('#Investor_Finance_reture1').on('click', function(){
        layer.open({
            type: 1,
            title: ['回款明细','font-size:18px;'],
            area: ['1040px', '640px'],
            btnAlign: 'c',
            content: $('#Investor_Finance_reture'),
            btn: ['关闭'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            }
        });
    });

})