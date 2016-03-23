$(function(){
    var $subhead=$("#subhead").val();
    switch($subhead)
    {
    case "订单中心":
       $("#orderCenter").addClass("navigation_checked");
      break;
    case "我的卡券":
    	$("#coupons").addClass("navigation_checked");
      break;
   	case "我的预定":
    	$("#preorder").addClass("navigation_checked");
      break;
    case "我的账户":
    	$("#account").addClass("navigation_checked");
    	break;
    case "我的推荐":
		$("#recommen").addClass("navigation_checked");
      break;
    case "我要反馈":
		$("#feedback").addClass("navigation_checked");
      break;
    case "我的抢购":
		$("#shoppingRush").addClass("navigation_checked");
      break;
    case "售后管理":
        $("#cusservice").addClass("navigation_checked");
        break;
    default:
    
    }
});