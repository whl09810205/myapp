/**
 * @author whl
 * 扩展easyui valivatebox
 */
//扩展easyui表单的验证    
$.extend($.fn.validatebox.defaults.rules, {    
    //验证汉字    
    CHS: {    
        validator: function (value) {    
            return /^[\u0391-\uFFE5]+$/.test(value);    
        },    
        message: '请输入汉字.'    
    },    
    //移动手机号码验证    
    Mobile: {//value值为文本框中的值    
        validator: function (value) {    
            var reg = /^1[3|4|5|8|9]\d{9}$/;    
            return reg.test(value);    
        },    
        message: '请输入正确手机号码.'    
    },    
    //国内邮编验证    
    ZipCode: {    
        validator: function (value) {    
            var reg = /^[0-9]\d{5}$/;    
            return reg.test(value);    
        },    
        message: '请输入正确邮编.'    
    },    
  //数字    
    Number: {    
        validator: function (value) {    
            var reg =/^[0-9]*$/;    
            return reg.test(value);    
        },    
        message: '请输入正确数字.'    
    },    
     //非负整数  
    Integer: {  
        validator: function (value) {  
            var reg = /^[1-9]\d*|0$/;  
            return reg.test(value);  
        },  
        message: '请输入非负整数'  
    },  
})    