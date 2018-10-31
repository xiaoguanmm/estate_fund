$(function(){
	window.dialogIndex = 1;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#startDate' //指定元素
		});
		laydate.render({
			elem: '#endDate' //指定元素
		});
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
	var amountTypeList = $("#amountTypeList").html();
	var investNameList = $("#investNameList").html();
	var expectInvestAmountList = $("#expectInvestAmountList").html();
	var expectAllReceiverAccountList = $("#expectAllReceiverAccountList").html();
	var realityInvestAmountList = $("#realityInvestAmountList").html();
	var realityReceivedAmountList = $("#realityReceivedAmountList").html();
	
	amountTypeList = JSON.parse(amountTypeList);
	investNameList = JSON.parse(investNameList);
	expectInvestAmountList = JSON.parse(expectInvestAmountList);
	expectAllReceiverAccountList = JSON.parse(expectAllReceiverAccountList);
	realityInvestAmountList = JSON.parse(realityInvestAmountList);
	realityReceivedAmountList = JSON.parse(realityReceivedAmountList);
	
	var idObj = document.getElementById("containerForm");
	var myChart = echarts.init(idObj);
	option = null;
	option = {
	    title : {
	        subtext: '金额（万元）-显示前10条数据'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:amountTypeList
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : investNameList
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'预计总投资',
	            type:'bar',
	            data:expectInvestAmountList
	        },
	        {
	            name:'实际总投资',
	            type:'bar',
	            data:expectAllReceiverAccountList
	        },
	        {
	            name:'预计总回报',
	            type:'bar',
	            data:realityInvestAmountList
	        },
	        {
	            name:'实际总回报',
	            type:'bar',
	            data:realityReceivedAmountList
	        }
	    ]
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}



function resetForm(){
	$("#startDate").val("");
	$("#endDate").val("");
}
