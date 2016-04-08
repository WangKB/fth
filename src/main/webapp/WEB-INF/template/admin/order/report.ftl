[#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.order.list")}</title>
<meta name="author" />
<meta name="copyright"/>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/${theme}/layer/layer.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js_src/onlinedeliverylist.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	
	[@flash_message /]
    
    $("#report").on('click',function(){
		location.href="excelReport.jhtml?"+$listForm.serialize();
		return false;
    });
    
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 订单列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="report.jhtml" method="get">
		<div class="bar" style="height:70px">
			<div class="buttonGroup">
                
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div id="pageSizeMenu" class="dropdownMenu">
					<a href="javascript:;" class="button">
						${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<ul>
						<li[#if page.pageSize == 10] class="current"[/#if] val="10">10</li>
						<li[#if page.pageSize == 20] class="current"[/#if] val="20">20</li>
						<li[#if page.pageSize == 50] class="current"[/#if] val="50">50</li>
						<li[#if page.pageSize == 100] class="current"[/#if] val="100">100</li>
					</ul>
				</div>
			</div>
			<div class="dropdownMenu">
				创建时间：
				<input type="text" style="width:100px" id="beginDate" name="beginDate" [#if beginDate??] value="${beginDate?string('yyyy.MM.dd')}"[/#if] class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endDate\')}'});" />
				-
				<input type="text" style="width:100px" id="endDate" name="endDate" [#if endDate??] value="${endDate?string('yyyy.MM.dd')}"[/#if]  class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'beginDate\')}'});" />
				状态：
					<select name='status'>
						<option value="">未选择</option>
						[#list statuses as status]
							<option value='${status}' [#if statusBefore==status]selected=true[/#if]>${message("Order.Status."+status)}</option>
						[/#list]
					</select>
				商家：
					<select name='shopId'>
						<option  value=-1>未选择</option>
						[#list shops as shop]
							<option value='${shop.id}'[#if shopBefore==shop.id]selected=true[/#if]>${shop.name}</option>
						[/#list]
					</select>
				会员手机：<input  type="text" class='text' style="width:100px" name='memberTel' [#if memberTel??]value='${memberTel}'[/#if]>
			</div><br/><br/>
			<div class="dropdownMenu">
				省：<input  type="text" class='text' style="width:100px" name='province' [#if province??]value='${province}'[/#if]>
				市：<input  type="text" class='text' style="width:100px" name='city' [#if city??]value='${city}'[/#if]>
				区：<input  type="text" class='text' style="width:100px" name='distract' [#if distract??]value='${distract}'[/#if]>
				是否结算：	
					<select name='isBlance'>
						<option value='-1'>未选择</option>
						<option value='1' [#if isBlance==1]selected=true[/#if]>是</option>
						<option value='0' [#if isBlance==0]selected=true[/#if]>否</option>						
					</select>
				来源：
					<select name='fromType'>
						<option  value="">未选择</option>
						[#list fromTypes as fromType]
							<option value='${fromType}' [#if fromTypeBefore==fromType]selected=true[/#if]>${message("Order.FromType."+fromType)}</option>
						[/#list]
					</select>&nbsp&nbsp
				<input type="submit" class='button' value='提交查询'>
				<input type="button" class='button' id='report' value='导出报表'>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="sn">${message("Order.sn")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">${message("Order.amount")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="consignee">${message("Order.consignee")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="memberId">会员电话</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">${message("Order.status")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}</a>
				</th>
				<th>
					<span>${message("admin.common.action")}</span>
				</th>
			</tr>
			[#list page.content as order]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${order.id}" />
					</td>
					<td>
						${order.sn}
					</td>
					<td>
						${currency(order.amount, true)}
					</td>
					<td>
						${order.consignee}
					</td>
					<td>
						${member_tel(order.memberId)}
					</td>
					<td>
						${message("Order.Status." + order.status)}
					</td>
					<td>
						<span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
					</td>
					<td>
						<a href="view.jhtml?id=${order.id}">[${message("admin.common.view")}]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>
[/#escape]