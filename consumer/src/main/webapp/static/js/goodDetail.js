layui.use(['jquery','layer','table','laydate','form'],function () {
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        laydate = layui.laydate,
        form = layui.form;

    var tableData;  // 数据表格的渲染的数据

    // 初始化表格数据
    loadPageGoodDetailByParams();

    // 加入日期插件
    laydate.render({
       elem:'#createDate',
       type:'datetime',
       value:new Date(),
       format:'yyyy/MM/dd HH:mm:ss'
    });

    //数据表格的方法级渲染
    function loadPageGoodDetailByParams() {
        var tab01 = table.render({
            elem: '#demo'   // 存放数据的容器id
            ,id:'#demo'
            ,height: 312    // 容器的高度
            ,url: '/GoodDetail/loadPageGoodDetailByParams' //数据接口
            ,limit:5    // 每页显示的条数
            ,limits:[2,5,8,10]  // 进行分页条数的选择
            ,even:true  // 隔行换色
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'id', title: 'ID', width:100, sort: true, align:'center'}
                ,{field: 'name', title: '商品名称', width:150, align:'center'}
                ,{field: 'address', title: '产地', width:100, align:'center'}
                ,{field: 'price', title: '价格', width: 100, align:'center'}
                ,{field: 'createDate', title: '生产日期', width: 180, sort: true, align:'center'}
                ,{field: 'remaining', title: '生产数量', width: 120, sort: true, align:'center'}
                ,{field: 'sortName', title: '类型名称', width: 150, align:'center',templet:'<div>{{d.goodSort.name}}</div>'}
                ,{width:260, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]],
            done:function (res) { // 执行数据表格渲染时候的函数回调
                tableData = res.data;
            }
        });
    }

    // 数据的导出
    $("#outDataBtn").click(function () {
       layer.open({
           type:1,
           title:'选择导出表格类型',
           area:['230px','100px'],
           btn:['导出csv','导出excel'],
           yes:function (index) {
               //将上述表格示例导出为 csv 文件
               table.exportFile(tab01.config.id, tableData); //data 为该实例中的任意数量的数据
               layer.close(index);
           },
           btn2:function () {
               table.exportFile(tab01.config.id, tableData,'xls');
           }
       })
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){ //查看
            //do somehing
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                // 数据库的删除，向服务端发送删除指令
                delGoodDetailById(data.id,obj);
                layer.close(index);
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something

            //同步更新缓存对应的值
            obj.update({
                username: '123'
                ,title: 'xxx'
            });
        }
    });

    // 去添加
    $("#saveGoodDetailBtn").click(function () {
        // 加载所有的商品类型
        loadAllGoodSort();
        layer.open({
            type:1,
            title:'添加商品详情数据',
            area:['360px','500px'],
            content:$("#saveGoodDetailDiv")
        })
    });

    // 添加商品详情数据的表单提交
    form.on('submit(subDemo)', function(data){
        var saveJsonGoodDetail = data.field;//当前容器的全部表单字段，名值对形式：{name: value}
        saveGoodDetail(saveJsonGoodDetail); // 执行服务器端的数据添加
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });




    // 根据id删除商品详情数据
    function delGoodDetailById(id,obj) {
        $.ajax({
            type:'post',
            url:'/GoodDetail/delGoodDetailById',
            data:{"id":id},
            success:function (data) {
                console.log(data);
                if(data == 'success'){
                    loadPageGoodDetailByParams();
                    layer.msg("数据删除成功！",{icon:1,time:2000,anim:4,shade:0.5});
                    obj.del();  //页面的缓存中的数据删除，更新缓存
                }else{
                    layer.msg("数据删除失败！",{icon:2,time:2000,anim:2,shade:0.5});
                }

            },
            error:function () {
                layer.msg("服务器异常",{icon:3,time:2000,anim:6,shade:0.5});
            }
        })

    }
    
    // 加载所有的商品类型
    function loadAllGoodSort(){
        $.ajax({
            type:'post',
            url:'/goodSort/loadAllGoodSort',
            success:function (data) {
                var goodSortStr = '<option value="">——请选择商品类型——</option>';
                $.each(data,function (i, goodSort) {
                    goodSortStr += ' <option value="'+goodSort.id+'">'+goodSort.name+'</option>';
                });
                $("#selGoodSort").html(goodSortStr);
                form.render('select');
            },
            error:function () {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        });
    }

    // 添加商品详情
    function saveGoodDetail(saveJsonGoodDetail) {
        layer.closeAll();
        $.ajax({
            type:'post',
            url:'/GoodDetail/saveGoodDetail',
            data:saveJsonGoodDetail,
            success:function (data) {
                if(data == 'success'){
                    loadPageGoodDetailByParams();
                    layer.msg("添加成功。。。",{icon: 1,time: 2000,anim:2,shade:0.5})
                }else{
                    layer.msg("添加失败！！！",{icon: 2,time: 2000,anim:3,shade:0.5})
                }
            },
            error:function () {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        })
    }



});