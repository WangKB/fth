/**
 * 选择套餐以及合约信息 JS
 */
$(function(){
	contractModel.init();
});

var contractModel = {
		/**
		 * 
		 * @Description: 初始化加载 
		 * @author:
		 * @date: 2015-10-19 下午12:54:32 
		 * @modify: 修改记录
		 */
	init:function(){
		this.contractInfo();
		this.constractTimeClick();
		this.submitConstract();
		this.selectedPhone();
		this.phoneNumSearch();
		this.searchPhoneNumNext();
		this.commitPhone();
		this.toOrder();
		this.uploadImg();
	},
	contractInfo:function(){//更新套餐信息
		var lastTime = $(".constract-time .selected").attr("data-time");
		var html = "";
        var contractsPhoneold = new Array();
		for(contract in contracts){
			if(lastTime == contracts[contract].lasttime ){
				html = html +"<li class='contract-content-item'><span style='width: 50px;'><input type='radio' value="+contracts[contract].id+" name='contractItem'></span>" +
						"<span style='width: 60px;'><em>"+contracts[contract].monthCost+"</em>元/月</span>" +
						"<span style='width: 70px;'>"+contracts[contract].telFare+"元</span>" +
						"<span class='text-left desc' style='width: 445px;'>"+contracts[contract].desc+"</span></li>";
                contractsPhoneold.push( contracts[contract].desc );
			}
		}
		$(".contract-content").empty().append(html);
        var $lis = $(".contract-content-item .desc");
        for( var i=0 ;i<$lis.length;i++ ){
            $($lis[i]).html( contractsPhoneold[i]);
            var ht = $($lis[i]).text();
            $($lis[i]).empty().append( ht );
        }
		
		//默认选中第一个
		$(".contract-content .contract-content-item input[name='contractItem']:eq(0)").trigger("click");
	},
	constractTimeClick:function(){//点击合约期时间 更新套餐内容
		$(".constract-time").on("click" , "li" , function(){
			var $this = $(this);
			if($this.hasClass("selected")){
				return false;
			}else{
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				contractModel.contractInfo();
			}
		});
	},
	submitConstract:function(){//选择套餐 确认套餐
		$(".commint-contract").on("click",function(){
			var $val = $(".contract-content .contract-content-item input[name='contractItem']:checked");
			
			var isOk = contractModel.queryPhoneNum();
			if(!isOk){
				return false;
			}
			
			$("#contractId").val($val.val());
			
			$("#contractDetailInfo").empty().text(  $val.closest(".contract-content-item").find(".desc").text()  );
			//选择套餐 效果变化
			$(".chooseContract").hide();
			$("#selectedContract").show();
			$(".contract").removeClass("selected");
			
			//选择号码效果变化
			$(".number-wrap").removeClass("nuselect").addClass("selected");
			$(".second").addClass("index");
			
		});
	},
	phoneNumSearch:function(){
		//号码搜索并重绘页面
		$(".search-phone-num").on("click",function(){
			if($("#search-phone-num-value").val().length<1 || $("#search-phone-num-value").val().trim().length<1  ){
				return false;
			}
			$(".search-phone-num-next").data("current" , 1);
			contractModel.queryPhoneNum();
		});
	},
	searchPhoneNumNext:function(){
		//换一批手机号
		$(".search-phone-num-next").on("click",function(){
			contractModel.queryPhoneNum();
		});
	},
	queryPhoneNum:function(){
		//ajax 选择加载号码
		//获取商品 并重绘页面
		var b = false;
		$.ajax({
			url:"./queryPhoneNumber.jhtml",
			type:"post",
			data:{
				productId :$("#productId").val(),
				pageNumber:$(".search-phone-num-next").data("current"),
				phoneNumer:$("#search-phone-num-value").val()
			},
			async:false,
			dataTpe:"json",
			success:function(data){
				if(data.success){
					var phones = data.phones;
					$(".search-phone-num-next").data("current",data.currentPage);
					var html = "";
					for(data in phones){
						html = html + "<li class='constract-item phone-number' data-id ="+phones[data].id+" data-price="+ phones[data].price +" >" +
								"<span class='phone'>"+phones[data].number+"</span><br />" +
										"<span>售价 "+phones[data].price+" 话费"+phones[data].telFare+"</span></li>";
					}
					$("#allPhones").empty().append(html);
					$(".constract-item.phone-number:eq(0)").addClass("selected");
					b =  true;
				}else{
					layer.msg(data.msg , {icon:2 , time:2000});
					b =  false;
				}
			}
		});
		return b;
	},
	selectedPhone:function(){
		$("#allPhones").on("click" , ".phone-number " , function(){
			$(".phone-number.selected").removeClass("selected");
			$(this).addClass("selected");
		});
	},
	commitPhone:function(){
		//确认选择手机号
		$(".commit-phone").on("click",function(){
			$selectPhoneNum = $(".phone-number.selected");
			var phoneId = $selectPhoneNum.attr("data-id");
			var price = $selectPhoneNum.attr("data-price");
			
			$.ajax({
				url:"./selectedPhone.jhtml",
				type:"post",
				dataType:"json",
				data:{
					phoneId : phoneId
				},
				success:function(data){
					if(data.success){
						$(".finishedInfo").empty().append("<span style='color:#333'>已经选择号码：</span><span style='color:#ff0000'>"+data.phone +"</span>");
						
						//更新总金额
						var totalPrice = Number($("#total-price").attr("data-price")) + Number( price );
						
						$("#total-price").text( currency(totalPrice , true) );
						$("#phoneId").val(phoneId);
						$(".number-wrap").removeClass("selected").addClass("finished");
						//触发步骤三的效果
						$(".contract-choose-userinfo").addClass("selected");
						$(".toorder-btn").prop("disabled",false).css("background-color","#f32030");
					}else{
						//失败重新刷出一批号码
						layer.msg(data.msg , {"icon":2,time:2000});
						contractModel.queryPhoneNum();
					}
				}
			});
		});
	},
	toOrder:function(){
		$(".toorder-btn").on("click",function(){
			//
			var $phoneUserName = $("#phoneUserName");
			var $phoneUserCard = $("#phoneUserCard");
			var $hasRead = $("#hasRead");
			var $acceptHookbox = $("#acceptHookbox");
			
			if( $phoneUserName.val().length==0 ||  $phoneUserName.val().trim().length==0 ){
				$("#phoneUserNameError").empty().text("请填写机主姓名");
				return false;
			}
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if( !reg.test($phoneUserCard.val()) ){
				$("#phoneUserCardError").empty().text("您输入的身份证号码不正确，请重新输入");
				return false;
			}
			
			if( $("#phoneUserCardFrontImg").val().length <1 || $("#phoneUserCardBackImg").val().length <1 ){
				layer.msg("您未上传证件照",{"icon":2 , "time":5000});
				return false;
			}
			
			if( !$("#acceptHookbox").prop("checked") ){
				$("#acceptHookboxError").empty().text("请确认您已知晓入网协议");
				return false;
			}
			
			return true;
		});
	},
	uploadImg:function(){
		$("#cardFrontImg").on("click",function(){
			$("#phoneUserCardFrontImg").trigger("click");
		});
		
		$("#cardBackImg").on("click",function(){
			$("#phoneUserCardBackImg").trigger("click");
		});
		
		$("#phoneUserCardFrontImg,#phoneUserCardBackImg" ).on("change",function(){
            var $this = $(this);
            var file = $(this).val();
            if( file.length < 1 ){
                return false;
            }
            var isCheck = CheckType( file );
            if(!isCheck){
                $this.siblings("span").empty().text( "文件格式错误，请上传图片" );
                $this.val();
            }else{
                var fileName = GetFileNameNoExt(file);
                $this.siblings("span").empty().text( fileName );
            }
		});
	}


};

//限制文件类型
function CheckType(obj) {
    if (obj.length > 0) {
        var af = "jpg,jpeg,bmp,gif,png,JPG,JPEG,BMP,GIF,PNG";
        if (eval("with(obj)if(!/" + af.split(",").join("|") + "/ig.test(substring(lastIndexOf('.')+1,length)))1;")) {
            isTrue = false;
        } else {
            isTrue = true;
        }
    } else {
        isTrue = false;
    }
    return isTrue;
}
//取文件后缀名
function GetFileExt(filepath) {
    if (filepath != "") {
        var pos = "." + filepath.replace(/.+\./, "");
        return pos;
    }
}
//取文件名不带后缀
function GetFileNameNoExt(filepath) {
    var pos = strturn(GetFileExt(filepath));
    var file = strturn(filepath);
    var pos1 =strturn( file.replace(pos, ""));
    var pos2 = GetFileName(pos1);
    return pos2;
}
//取文件全名名称
function GetFileName(filepath) {
    if (filepath != "") {
        var names = filepath.split("\\");
        return names[names.length - 1];
    }
}
//字符串逆转
function strturn(str) {
    if (str != "") {
        var str1 = "";
        for (var i = str.length - 1; i >= 0; i--) {
            str1 += str.charAt(i);
        }
        return (str1);
    }
}