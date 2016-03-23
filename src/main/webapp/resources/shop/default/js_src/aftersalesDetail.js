$(function(){
    afterSalesDetail.init();
});

var afterSalesDetail = {
    init : function(){
        this.btnoff();
        this.viewExpress();
        this.initExpress();
    },
    initExpress:function(){
        if( $(".expressin").length > 0 ){
            $.ajax({
                url:"../../common/expressView.jhtml",
                type:"post",
                dataType:"json",
                data:{
                    trackingNo:$(".expressin").attr("data")
                },
                success:function(data){
                    var html = "<table class='expressTable'>";
                    var p = "";
                    if( data.data.length < 1 ){
                        html = "<p>暂无物流信息</p>";
                    }else{
                        for( var i=0 ;i<data.data.length ; i++ ){
                            p ="<tr class='state'><td style='width:350px;'>"+data.data[i].time+"</td><td style='width:500px;'>"+
                                data.data[i].context+"</td></tr>";
                            html += p;
                        }
                    }
                    html +="</table>";

                    $("#expressin").append( html );
                }
            });
        }

        if( $(".expressout").length > 0 ){
            $.ajax({
                url:"../../common/expressView.jhtml",
                type:"post",
                dataType:"json",
                data:{
                    trackingNo:$(".expressout").attr("data")
                },
                success:function(data){
                    var html = "<table class='expressTable'>";
                    var p = "";
                    if( data.data.length < 1 ){
                        html = "<p>暂无物流信息</p>";
                    }else{
                        for( var i=0 ;i<data.data.length ; i++ ){
                            p ="<tr class='state'><td style='width:350px;'>"+data.data[i].time+"</td><td style='width:500px;'>"+
                                data.data[i].context+"</td></tr>";
                            html += p;
                        }
                    }
                    html +="</table>";
                    $("#expressout").append( html );
                }
            });
        }


    },
    btnoff:function(){
        //售后商品和进度查询更多或收起

        $("#detailMore").on("click" , function(){
            $(this).hide();
            $("#detailClose").show();
            $(".opts-hide").show();
        });

        $("#detailClose").on("click" , function(){
            $(this).hide();
            $("#detailMore").show();
            $(".opts-hide").hide();
        });

        $("#goodMore").on("click" , function(){
            $(this).hide();
            $("#goodClose").show();
            $(".goods-hide").show();
        });

        $("#goodClose").on("click" , function(){
            $(this).hide();
            $("#goodMore").show();
            $(".goods-hide").hide();
        });

    },
    viewExpress:function() {
        //查看物流
        $(".aftersale_detail_wrap").on("click", ".express", function () {
            //弹出框显示物流信息
            layer.open({
                type: 1,
                title: false, //不显示标题
                content: $("#expressin"),
                cancel: function (index) {
                    layer.close(index);
                }
            });
        });
    }


}