/**
 * BV自带48种校验类型--只列出常用，其他如果需要请自行查看API或者源码
 * ----
 * between    检查输入值是否在两个给定数字之间
 * choice check控件选择的数量
 * date 校验日期
 * different 校验与某字段不能相同
 * digits 校验是否为数字
 * emailAddress 校验是否为合法email地址
 * file 校验文件
 * greaterThan 比某个值大
 * identical 校验与某字段值相等(密码确认校验)
 * integer 校验是否为整数
 * lessThan 比某个值小
 * notEmpty 校验不能为空
 * phone 校验电话号码(该校验规则不适用中国，需自定义)
 * remote 远程校验
 * stringCase 校验输入内容是否大写或小写
 * stringLength 校验字符串长度
 * uri 校验是否为有效url地址
 *
 [
 'base64', 'between', 'callback', 'choice', 'creditCard', 'cusip', 'cvv', 'date',
 'different', 'digits', 'ean', 'emailAddress', 'file', 'greaterThan', 'grid', 'hex',
 'hexColor', 'iban', 'id', 'identical', 'imei', 'imo', 'integer', 'ip',
 'isbn', 'isin', 'ismn', 'issn', 'lessThan', 'mac', 'meid', 'notEmpty',
 'numeric', 'phone', 'regexp', 'remote', 'rtn', 'sedol', 'siren', 'siret',
 'step', 'stringCase', 'stringLength', 'uri', 'uuid', 'vat', 'vin', 'zipCode'
 ];
 */
/**自定义BV表单验证规则*/
(function ($) {
    $.fn.bootstrapValidator.i18n.rangeNumberBits = $.extend($.fn.bootstrapValidator.i18n.rangeNumberBits || {}, {
        noticeMsg: '整数位不大于 %s 位且小数位不大于 %s位'
    });
    /**
     * 校验整数位不大于X位,小数位不大于Y位
     * @type {{validate: (function(*, *, *): {valid: boolean, message: (*|string)})}}
     */
    $.fn.bootstrapValidator.validators.rangeNumberBits = {

        validate: function (validator, $field, options) {
            //获取字段中文名称
            var fieldName = $field.parents("div[class~=form-div]").find(">label:first").text().replace(/:|：/,"");
            var checkFlg = true;
            var value = $field.val();
            var _integerBits = options.integerBits;//整数位数
            var _decimalBits = options.decimalBits;//小数位数
            var message = "";
            if (!isNaN(parseFloat(value))) {
                var numberBits = value.split(".");
                var integerBits = (numberBits[0].match(/\d+/g) + "").length;
                var decimalBits = 0;
                if (numberBits.length > 1) {
                    decimalBits = (numberBits[1].match(/\d+/g) + "").length;
                }
                if (integerBits > _integerBits || decimalBits > _decimalBits) {
                    checkFlg = false;
                }
            } else {
                checkFlg = false;
                message = "输入不合法，请输入数值";
            }
            var _message = options.message;
            if (_message) {
                message = _message;
            } else {
                message = fieldName + " " + $.fn.bootstrapValidator.helpers.format($.fn.bootstrapValidator.i18n.rangeNumberBits.noticeMsg, [_integerBits, _decimalBits]);
            }
            return {valid: checkFlg, message: message};
        }
    };
}(window.jQuery));
(function ($) {
    $.fn.bootstrapValidator.i18n.rangeGtNumberBits = $.extend($.fn.bootstrapValidator.i18n.rangeGtNumberBits || {}, {
        noticeMsg: '需大于0且整数位不大于 %s 位，小数位不大于 %s位'
    });
    /**
     * 校验大于0且整数位不大于X位,小数位不大于Y位
     * @type {{validate: (function(*, *, *): {valid: boolean, message: (*|string)})}}
     */
    $.fn.bootstrapValidator.validators.rangeGtNumberBits = {

        validate: function (validator, $field, options) {
            //获取字段中文名称
            var fieldName = $field.parents("div[class~=form-div]").find(">label:first").text().replace(/:|：/,"");
            var checkFlg = true;
            var value = $field.val();
            var _integerBits = options.integerBits;//整数位数
            var _decimalBits = options.decimalBits;//小数位数
            var message = "";
            if (!isNaN(parseFloat(value))) {
                var numberBits = value.split(".");
                if (numberBits[0] > 0) {
                    var integerBits = (numberBits[0].match(/\d+/g) + "").length;
                    var decimalBits = 0;
                    if (numberBits.length > 1) {
                        decimalBits = (numberBits[1].match(/\d+/g) + "").length;
                    }
                    if (integerBits > _integerBits || decimalBits > _decimalBits) {
                        checkFlg = false;
                    }
                } else {
                    checkFlg = false;
                }

            } else {
                checkFlg = false;
                message = "输入不合法，请输入数值";
            }
            var _message = options.message;
            if (_message) {
                message = _message;
            } else {
                message = fieldName + " " + $.fn.bootstrapValidator.helpers.format($.fn.bootstrapValidator.i18n.rangeGtNumberBits.noticeMsg, [_integerBits, _decimalBits]);
            }
            return {valid: checkFlg, message: message};
        }
    };
}(window.jQuery));

(function ($) {
    $.fn.bootstrapValidator.i18n.charLength = $.extend($.fn.bootstrapValidator.i18n.charLength || {}, {
        noticeMsg: '不大于%s且不小于%s个字节的内容'
    });
    /**
     *  验证内容长度(包含中文)
     * @type {{validate: (function(*, *, *): {valid: boolean, message: (*|string)})}}
     */
    $.fn.bootstrapValidator.validators.charLength = {

        validate: function (validator, $field, options) {
            var fieldName = $field.parents("div[class~=form-div]").find(">label:first").text().replace(/:|：/,"");
            var sTmpStr, sTmpChar;
            var sReLenth = 0;
            sTmpStr = new String($field.val());
            var sOriLenth = sTmpStr.length;
            for (var i = 0; i < sOriLenth; i++) {
                sTmpChar = sTmpStr.charAt(i);
                if (escape(sTmpChar).length > 4) {
                    sReLenth += 2;
                } else if (sTmpChar != '\r') {
                    sReLenth++;
                }
            }
            var min = options.min;
            var max = options.max;
            var message = "";
            var _message = options.message;
            if (_message) {
                message = _message;
            } else {
                message = fieldName + " " + $.fn.bootstrapValidator.helpers.format($.fn.bootstrapValidator.i18n.charLength.noticeMsg, [min, max]);
            }
            return {valid: sReLenth >= min && sReLenth <= max, message: message};
        }
    };
}(window.jQuery));

    /**
     * 获取所有校验对象(包含自定义)
     * @type {{}|*}
     */
    var validatorsTypeObj = $.fn.bootstrapValidator.validators;
    /**
     * 处理校验规则，如果添加了自定义校验规则，且附件了额外的选项需要在此添加
     * @param obj 规则对象
     * @param ruleName rule名称
     * @param rule rule条件
     * @param dom form 对象
     */
    function dealRule(obj, ruleName, rule,dom) {
        switch (ruleName) {
            case 'between':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var min = range[0];
                var max = range[1];
                obj.min = min;
                obj.max = max;
                break;
            case 'choice':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var min = range[0];
                var max = range[1];
                obj.min = min;
                obj.max = max;
                break;
            case 'different':
                var fieldName = rule.match(/\[.+\]/g)[0].replace(/\[|\]/g, "");
                obj.field = fieldName;
                var fieldText = dom.find("input[name=" + fieldName + "]").parents("div[class~=form-div]").find(">label:first").text().replace(/:|：/,"");
                obj.message = "不可与 " + fieldText + " 值相同";
                break;
            case 'greaterThan':
                var minNum = rule.match(/\[.+\]/g)[0].replace(/\[|\]/g, "");
                obj.value = minNum;
                break;
            case 'identical':
                var fieldName = rule.match(/\[.+\]/g)[0].replace(/\[|\]/g, "");
                obj.field = fieldName;
                var fieldText = dom.find("input[name=" + fieldName + "]").parents("div[class~=form-div]").find(">label:first").text().replace(/:|：/,"");
                obj.message = "必须与 " + fieldText + " 值相同";
                break;
            case 'lessThan':
                var maxNum = rule.match(/\[.+\]/g)[0].replace(/\[|\]/g, "");
                obj.value = maxNum;
                break;
            case 'stringCase':
                var _case = rule.match(/\[.+\]/g)[0].replace(/\[|\]/g, "");
                obj['case'] = _case;
                break;
            case 'stringLength':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var min = range[0];
                var max = range[1];
                obj.min = min;
                obj.max = max;
                break;
            case 'rangeNumberBits':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var integerBits = range[0];
                var decimalBits = range[1];
                obj.integerBits = integerBits;
                obj.decimalBits = decimalBits;
                break;
            case 'rangeGtNumberBits':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var integerBits = range[0];
                var decimalBits = range[1];
                obj.integerBits = integerBits;
                obj.decimalBits = decimalBits;
                break;
            case 'charLength':
                var range = rule.match(/\[\d+\-\d+\]/g)[0].match(/\d+/g);
                var min = range[0];
                var max = range[1];
                obj.min = min;
                obj.max = max;
                break;
        }

    }


    var validator = {
        _fieldObj: {},
        domObj:{},
        validate: function (dom,callback) {
        	this.domObj = dom;
            var fieldObj = {};
            /**
             * 获取表单所有表单元素
             * diabled、隐藏、submit提交按钮不校验
             */
            this.domObj.find(":input").filter(":not(button)").filter(":not(:submit)").not(":disabled").each(function (i, n) {
                var name = $(n).attr("name"),
                    _rule = $(n).attr("validate-rule"),
                    _regexp = $(n).attr("validate-regexp"),
                    _message = $(n).attr("validate-message");
                if (_rule) {
                    var rules = _rule.split("|");
                    fieldObj[name] = {};
                    fieldObj[name].validators = {}
                    for (var i = 0; i < rules.length; i++) {
                        var curr_rule = rules[i].split(/\[.+\]/g);
                        var ruleName = curr_rule[0];
                        if (validatorsTypeObj[ruleName]) {
                            fieldObj[name].validators[ruleName] = {};
                            dealRule(fieldObj[name].validators[ruleName], ruleName, rules[i],dom);
                            if (_message) {
                                fieldObj[name].validators[ruleName].message = _message;
                            }
                        } else {
                            throw new Error("找不到 " + ruleName + " 的校验规则");
                        }
                    }
                }
                if (_regexp) {
                    if (!fieldObj[name].validators) {
                        fieldObj[name] = {};
                        fieldObj[name].validators = {}
                    }
                    fieldObj[name].validators.regexp = {};
                    fieldObj[name].validators.regexp.regexp = new RegExp(_regexp);
                }
            });
            this._fieldObj = fieldObj;
            this.domObj.bootstrapValidator({
                message: '验证不通过',
                excluded:[":disabled"],
                feedbackIcons: {
                    // valid: 'glyphicon glyphicon-ok',
                    //invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: this._fieldObj
            }).on("success.form.bv", function (e) {
                /*阻止默认提交表单行为*/
                e.preventDefault();
                if(callback){
                	callback($(e.currentTarget));
                }
                /*执行完成之后启用submit按钮*/
                $(e.currentTarget).find("button[type=submit]").attr("disabled", false);
                $(e.currentTarget).find("input[type=submit]").attr("disabled", false);
            });
            
           /* 由于layui-form会将应用的select隐藏故添加事件手动校验
            var resetValiField = function(dom,fieldName){
            	$(dom).data("bootstrapValidator").updateStatus(fieldName, "NOT_VALIDATED", null);
            }
            校验某以字段针对select
            var valiField = function(dom,fieldName){
            	 $(dom).data("bootstrapValidator").validateField(fieldName);
            }
            layui.use(['form','jquery'],function(){
            	var form = layui.form;
            	var $ = jQuery = layui.$;
            	$(dom).find("select").not(":disabled").each(function(i,n){
            		var lay_filter = $(this).attr("lay-filter");
            		var vlidate_rule = $(this).attr("validate-rule");
            		if(vlidate_rule){
            			form.on('select('+lay_filter+')', function(data){
            				var fieldName = $(data.elem).attr("name");
            				if(data.value){
            					resetValiField(dom, fieldName);
            				}else{
            					valiField(dom,fieldName);
            				}
            			});
            		}
            	});
            });*/
        },
        /**
         * 如果是动态添加表单元素，需要调用此方法将其加入validator
         * @param dom
         */
        addField: function(fieldNameDom){
        	
        	 var _name = fieldNameDom.attr("name"),
             _rule = fieldNameDom.attr("validate-rule"),
             _regexp = fieldNameDom.attr("validate-regexp"),
             _message = fieldNameDom.attr("validate-message");
        	 var valiRule={};
        	 if(_name){
        		 if(_rule){
        		 var rules = _rule.split("|");
                 for (var i = 0; i < rules.length; i++) {
                     var curr_rule = rules[i].split(/\[.+\]/g);
                     var ruleName = curr_rule[0];
                     if (validatorsTypeObj[ruleName]) {
                    	 valiRule[ruleName] = {};
                         dealRule(valiRule[ruleName], ruleName, rules[i],this.domObj);
                         if (_message) {
                             fieldObj[name].validators[ruleName].message = _message;
                         }
                     } else {
                         throw new Error("找不到 " + ruleName + " 的校验规则");
                     }
                 }
        		 
        	 }
    		   if (_regexp) {
                   valiRule.regexp = {};
                   valiRule.regexp.regexp = new RegExp(_regexp);
               }
    		   this.domObj.bootstrapValidator('addField',_name,{
    			   validators:valiRule
    		   });
        		 
        	 }
        	 
        },
        /**
         * 重置表单校验
         * @param dom
         */
        resetForm: function (dom) {
            $(dom).data('bootstrapValidator').resetForm(true);
        },
        /**
         * 重置表单某一字段校验
         * @param dom
         * @param fieldName
         */
        resetFormField: function (dom, fieldName) {
            $(dom).data("bootstrapValidator").updateStatus(fieldName, "NOT_VALIDATED", null);
        },
        /**
         * 手动触发表单校验
         * @param dom
         */
        triggerForm: function (dom) {
            $(dom).data("bootstrapValidator").validate();
        },
        /**
         * 手动触发表单中某一字段校验
         * @param dom
         * @param fieldName
         */
        triggerFormField: function (dom, fieldName) {
            $(dom).data("bootstrapValidator").validateField(fieldName);
        },
        /**
         * 当前表单是否验证通过
         * @param dom
         */
        isValid: function (dom) {
            return $(dom).data("bootstrapValidator").isValid();
        },
        /**
         * 移除某一字段的校验
         * @param dom
         * @param fieldName
         */
        removeFiled: function(dom,fieldName){
        	$(dom).bootstrapValidator('removeField',fieldName);
        },
        /**
         * 销毁校验
         * @param dom
         */
        destroy: function(dom){
        	$(dom).data('bootstrapValidator').destroy();
        	$(dom).data('bootstrapValidator',null);
        }
    }


