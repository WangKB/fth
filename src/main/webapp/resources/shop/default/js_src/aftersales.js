$(function(){
    $(".table-content").on("click",".shipping" , function(){
        $("#trackingNo").val("");
        var returnOrderId = $(this).attr("returnOrderId");
        $("#returnOrderId").val(returnOrderId);
        $("#shipformError").empty();
        var layera = layer.open({
            type:1,
            title :"发货",
            area:["auto" , "auto"],
            btn:["确认","取消"],
            content:$("#returnShip"),
            yes:function(index){
                $.ajax({
                    url:"./orderCenter/shipReturn.jhtml",
                    type:"post",
                    dataType:"json",
                    data:$("#shipform").serialize(),
                    success:function( data ){
                        if( data.success ){
                            //成功
                            layer.close( index );
                            layer.msg( "发货成功" , {icon: 1,time: 2000} , function(){
                                window.location.reload(); //刷新当前页面
                            });
                        }else{
                            //失败
                            $("#shipformError").text( data.message );
                        }
                    }
                })
                return false;
            },
            cancel:function(index){
                layer.close(index);
            }
        });
    });

    $(".table-content").on("click" , ".toaddr" , function(){
        var returnOrderId = $(this).attr("returnOrderId");
        $.ajax({
           url:"./getOrginAddr.jhtml",
            type:"post",
            dataType:"json",
            data:{
                refundsId : returnOrderId
            },
            success:function(data){
                if( data.success ){
                    //成功
                    var html = "<div class='consignee-info'>";
                    html = html+"<p>联系人："+ data.name +"</p>";
                    html = html+"<p>收货地址："+ data.addr +"</p>";
                    html = html+"<p>联系方式："+ data.phone +"</p>";
                    html = html+"</div>";
                    layer.open({
                        title:"收货信息",
                        type: 1,
                        content:html,
                        area:["400px" , "200px"]
                    });
                }else{
                    //失败
                    layer.msg(data.message , {icon:2 , time:2000} );
                }

            }

        });
    });

    /* 售后信息详情 */
    $(".table-content").on("click",".detail" , function(){
        var returnOrderId = $(this).attr("returnOrderId");
        var url="./returnOrderDetail.jhtml?returnId="+returnOrderId;
        window.location.href=url;
    });

    /*去付款*/
    $(".table-content").on("click",".topay" , function(){
        var returnOrderId = $(this).attr("returnOrderId");
        var url="./repairPayment.jhtml?returnId="+returnOrderId;
        window.location.href=url;
    });

});