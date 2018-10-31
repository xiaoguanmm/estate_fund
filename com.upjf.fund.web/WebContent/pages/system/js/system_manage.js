$(function(){
	// .....................................1.用户管理.............................................................

//用户管理-点击‘新增用户’
    $('#Subject_Provide_Add1').on('click', function(){
        layer.open({
            type: 1,
            title: ['新增用户'],
            area: ['600px', '500px'],
            btnAlign: 'c',
            content: $('#Subject_Provide_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//用户管理-点击‘修改用户’
    $('#Subject_Provide_Modify1').on('click', function(){
        layer.open({
            type: 1,
            title: ['修改用户'],
            area: ['600px', '500px'],
            btnAlign: 'c',
            content: $('#Subject_Provide_Modify'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//用户管理-点击‘用户数据授权’
    $('#Data_License1').on('click', function(){
        layer.open({
            type: 1,
            title: ['用户数据授权'],
            area: ['700px', '400px'],
            btnAlign: 'c',
            content: $('#Data_License'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

// .....................................3.角色管理...........................................................

//角色管理-点击‘新增角色’
    $('#Role_Manage_Add1').on('click', function(){
        layer.open({
            type: 1,
            title: ['新增角色'],
            area: ['600px', '360px'],
            btnAlign: 'c',
            content: $('#Role_Manage_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });

//角色管理-点击‘新增角色’
    $('#Role_Manage_Modify1').on('click', function(){
        layer.open({
            type: 1,
            title: ['修改角色'],
            area: ['600px', '360px'],
            btnAlign: 'c',
            content: $('#Role_Manage_Modify'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
            }
        });
    });
})