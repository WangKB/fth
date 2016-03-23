function biaoti(){
	alert(1);
}
$(function(){
	reviwOpt.init();

});
/**
 * 
 * 提交反馈 
 * @author:
 * @date: 2015-10-20 下午3:44:51 
 * @modify: 修改记录
 */
function tijiao(){
	var istrue=true;
	var istrues=true;
	//获取反馈的信息
	var $radio= $('input[name="feedback"]:checked ').val();
	var $tit=$("#biaoti").val();
	var $content=$("#content").val();
	if($tit==""){
		$(".biaoti-block").text("请输入标题");
		istrue=false;
	}else if($tit.length<4){
		$(".biaoti-block").text("标题最少4个字");
		istrue=false;
	}else{
		$(".biaoti-block").text("");
		istrue=true;
	}
	
	if($content==""){
		$(".neirong-block").text("请输入内容");
		istrues=false;
	}else{
		$(".neirong-block").text("");
		istrues=true;
	}
	//ajax提交
	if(istrue && istrues){
		$.ajax({
			url: "submit.jhtml",
			type: "POST",
			data:{
				type:$radio,
				content:$content,
				title:$tit
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.type == "success"){
					layer.msg("提交成功",{icon: 1,time:2000},function(){
						window.location.reload();//刷新当前页面.
					});
				} 
			},
		});
	}
	
}



var reviwOpt = {
		init : function(){
			this.addImg();
			this.fileUpload();
			this.delImg();
		},
		//文件上传
		fileUpload:function(){
			
			$("#fileupload").change(function(){
				var options ={
					url:'../../controller/upload/2.jhtml',   
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
		addImg:function(){
			$("#addImg").on("click" , function(){
				
				if(parseInt($("#biaoji").val())>=5){
					layer.msg("上传图片最多5张!",{icon: 2,time:1000},function(){
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