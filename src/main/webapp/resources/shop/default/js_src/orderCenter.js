$(function(){
	$("#state"+$("#state").val()+"").addClass("checked");
	$("#orderType"+$("#orderType").val()+"").addClass("checked");

    if($("#orderType1").hasClass("checked")){
        //积分订单去除待付款tab
        $("#paymenting").remove();
    }

});

/**
 * 
 * 预订单取消.
 * @author:王凯斌
 * @date: 2015-12-14 下午5:17:24 
 * @param sn
 */
function precancel(sn,price){
	layer.confirm('是否确定取消订单？该订单为预订单，取消您将损失'+price+"元定金", {
	    btn: ['确定','取消'] //按钮
	}, function(){
	    $.ajax({
			url: "../../../cancel.jhtml",
			type: "POST",
			data:{
				sn:sn
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.type == "success"){
					layer.msg("成功取消订单",{icon: 1,time:1000},function(){
						window.location.reload();//刷新当前页面.
					});
				}
			}
		});
	}, function(){
	});
}
	/**
	 * 
	 * 取消.
	 * @author:王凯斌
	 * @date: 2015-10-14 下午5:17:24 
	 * @modify: 修改记录
	 * @param sn
	 */
	function cancel(sn){
		layer.confirm('是否确定取消订单？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
		    $.ajax({
				url: "../../../cancel.jhtml",
				type: "POST",
				data:{
					sn:sn
				},
				dataType: "json",
				cache: false,
				success: function(data) {
					if(data.type == "success"){
						layer.msg("成功取消订单",{icon: 1,time:1000},function(){
							window.location.reload();//刷新当前页面.
						});
					}
				}
			});
		}, function(){
		    /*layer.msg('奇葩么么哒', {shift: 6});*/
		});
	}
	
	/**
	 * 
	 * 删除. 
	 * @author:王凯斌
	 * @date: 2015-10-14 下午5:17:24 
	 * @modify: 修改记录
	 * @param sn
	 */
	function deleted(sn){
		layer.confirm('是否确定删除订单？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
		    $.ajax({
				url: "../../../delete.jhtml",
				type: "POST",
				data:{
					sn:sn
				},
				dataType: "json",
				cache: false,
				success: function(data) {
					if(data.type == "success"){
						layer.msg("删除订单成功",{icon: 1,time:1000},function(){
							window.location.reload();//刷新当前页面.
						});
					}
				}
			});
		}, function(){

		});
	}
	
	/**
	 * 
	 * 确认.
	 * @author:王凯斌
	 * @date: 2015-10-14 下午5:17:24 
	 * @modify: 修改记录
	 * @param sn
	 */
	function validation(sn){
		layer.confirm('是否确认收货？', {
		    btn: ['确定','取消'] //按钮
		}, function(){
		    $.ajax({
				url: "../../../validation.jhtml",
				type: "POST",
				data:{
					sn:sn
				},
				dataType: "json",
				cache: false,
				success: function(data) {
					if(data.type == "success"){
						layer.msg("确认收货成功",{icon: 1,time:1000},function(){
							window.location.reload();//刷新当前页面.
						});
					}
				}
			});
		}, function(){
		    /*layer.msg('奇葩么么哒', {shift: 6});*/
		});
	}
	
	/**
	 * 
	 * 退换. 
	 * @author:王凯斌
	 * update:王凯斌
	 * @date: 2015-10-14 下午5:17:24 
	 * @modify: 修改记录
	 * @param sn
	 */
	$(".retroexchange").click(function(){

		var orderId = $(this).attr("orderId");
        var url = "../../checkReturn.jhtml?orderId="+orderId;
        window.location.href = url;

	});
	
	
$(".returnPro").click(function(){
    var orderId = $(this).attr("orderId");
    var msg = "该订单尚未发货，您是否确认退货？确认成功后退款将在1-3三个工作日返回您的账户。";
    if(  $(this).hasClass( "daiziti" ) ){
        msg = "该订单尚未自提，您是否确认退货？确认成功后退款将在1-3三个工作日返回您的账户。";
    }
    var index = layer.confirm( msg , {
        btn:[ "确认" ,  "取消"]
    },function(){
        var url = "../../checkReturn.jhtml?orderId="+orderId;
        window.location.href = url;
    },function(){
        layer.close(index);
    });

});
	
	/**
	 * 
	 * 晒论 
	 * @author:王凯斌
	 * @date: 2015-10-14 下午5:17:24 
	 * @modify: 修改记录
	 * @param sn
	 */
	function comment(sn,id){
		var index = $.get("../../../review.jhtml?sn="+sn+"&id="+id+"" ,function( str ){
			layer.open({
			    type: 1 ,
			    title: '评论',
			    area: ['530px', '350px'],
			    content: str,
			    success:function(){
			    	reviwOpt.init( sn,id );
			    }
			});
		} );
	};
	
	
	var reviwOpt = {
			init : function( sn,id ){
				this.reviewCommit( sn,id );
				this.addImg();
				this.fileUpload();
				this.delImg();
			},
			//文件上传
			fileUpload:function(){
				
				$("#fileupload").change(function(){
					var options ={
						url:'../../../../controller/upload/2.jhtml',   
				        success:function(data){
				        	$("#fileupload").val("");
				        	//上传的照片
				        	var $li = $("<li>");
				        	var $bb = $("<bb>");
				        	var $a = $("<a>").attr("href", data.filePath).attr("target" ,"_blank" );
				        	var $img = $("<img>").attr("src" , data.filePath );
				        	var $input = $("<input>").attr("name" , "imgs" ).attr("value" , data.filePath ).attr("type" , "hidden" );
				        	var $a = $a.append( $img ).append( $input );
				        	$li.append( $a );
				        	$li.append($bb);
				        	$(".centerCen").append( $li );
				        }   
					};
					var form =$("form[name=contractFrom]");//form1:表单ID  在表单界面只用这一个表单ID
					form.ajaxSubmit(options); 
				});
				
			},
			//提交评论
			reviewCommit:function( sn,id ){
				$(".reviewSubmit").data("goodsn" , sn  );
				$(".reviewSubmit").data("orderItemId" , id  );
				$(".reviewSubmit").on("click" , function(){
					var textarea = $("#content").val();
					if( typeof(textarea) == "undefined" ||  textarea.trim().length<1 ){
						$("#content_error").empty().text("请输入商品评论");
						return false;
					}else if( textarea.trim().length < 10){
						$("#content_error").empty().text("至少输入10字以上");
						return false;
					}
					
					var imgs = [];
					var $imgs = $("#reviewForm input[name='imgs']");
					var index;
					$imgs.each(function(index){
						imgs[index] = $(this).val();
					});
					
					$.ajax({
						url: "../../../orderCenter/saveReview.jhtml",
						type: "POST",
						data:{
							imgs:imgs,
							content:textarea.trim(),
							goodSn : $(".reviewSubmit").data("goodsn"),
							orderItemId : $(".reviewSubmit").data("orderItemId")
						},
						dataType: "json",
						cache: false,
						success: function(data) {
							if( data.type="success" ){
								layer.msg( data.content ,  { icon:1 ,"time":2000 , success:function(){
									window.location.reload();
								}});
							}else{
								layer.msg(data.content , {"icon":2 , "time":2000});
							}
							
						}
					});
				});
			},
			addImg:function(){
				$("#addImg").on("click" , function(){
					
					if(parseInt($("#biaoji").val())>=10){
						layer.msg("上传图片最多10张!",{icon: 2,time:1000},function(){
						});
					}else{
						$("#biaoji").val(parseInt($("#biaoji").val())+1);
						$("#fileupload").trigger("click");
					}
					
				});
			},
			delImg:function(){
				$(".centerCen").on("click" , "bb" ,  function(){
					$("#biaoji").val(parseInt($("#biaoji").val())-1);
					$(this).closest("li").remove();
					
				});
				
			}
	};
	