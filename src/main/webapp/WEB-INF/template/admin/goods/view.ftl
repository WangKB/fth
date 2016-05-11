[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.goods.edit")}</title>
<meta name="author"  />
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ueditor/ueditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $inputForm = $("#inputForm");
	var $reject=$("#reject");

	[@flash_message /]
	
	$reject.on('click',function(){
		$.ajax({
			url: "reject.jhtml",
			type: "POST",
			data: {id:${goods.id},auditMemo:$('#auditMemo').val()},
			dataType: "json",
			success: function(data) {
				$.message('success','操作成功');
				setTimeout(function(){
					location.href='examine.jhtml';
				},'1000');
			}
		});
	});
	
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 商品审核
	</div>
	<form id="inputForm" action="check.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${goods.id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.goods.base")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.introduction")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.productImage")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.specification")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					${message("Goods.sn")}:
				</th>
				<td>
					${goods.sn}
				</td>
			</tr>
			<tr>
				<th>
					${message("Goods.name")}:
				</th>
				<td>
					${goods.name}
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					${goods.order}
				</td>
			</tr>
			<tr>
				<th>
					申请状态:
				</th>
				<td>
					${message("Goods.ApplicationState."+goods.applicationState)}
				</td>
			</tr>
			<tr class='none'>
				<th>
					${message("Goods.caption")}:
				</th>
				<td>
					${goods.caption}
				</td>
			</tr>
			<tr>
				<th>
					${message("Product.price")}:
				</th>
				<td>
					${goods.price}
				</td>
			</tr>
			<tr>
				<th>
					申请备注:
				</th>
				<td>
					${goods.applicationMemo}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						${message("Goods.isMarketable")}:[#if  goods.isMarketable==1]是; [#else]否; [/#if]
					</label>
					<label>
						${message("Goods.isList")}:[#if  goods.isList==1]是; [#else]否; [/#if]
					</label>
					<label>
						是否标品:[#if  goods.isStandard==1]是; [#else]否; [/#if]
					</label>
					<label>
						是否虚拟商品:[#if  goods.isVirtual==1]是; [#else]否; [/#if]
					</label>
				</td>
			</tr>
			[#switch goods.applicationState]
				[#case 'ApplyingOne']
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
							<input type="text" name='auditMemo' id='auditMemo' class="text" />
							<input type="submit" class="button" value="通过" />
							<input id='reject' type="button" class="button" value="拒绝" />
							<input type="hidden" name="actType" value="1" />
						</td>
					</tr>
				[#break]
				[#case 'ApplyingTwo']
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
							${goods.auditMemo1}
						</td>
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${goods.auditDate1}
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td>
							<input type="text" name='auditMemo' id='auditMemo' class="text" />
							<input type="submit" class="button" value="通过" />
							<input id='reject' type="button" class="button" value="拒绝" />
							<input type="hidden" name="actType" value="2" />
						</td>
					</tr>
				[#break]
				[#default]
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
							${goods.auditMemo1}
						</td>
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${goods.auditDate1}
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td>
							${goods.auditMemo2}
						</td>
					</tr>
					<tr>
						<th>
							二级审核人:
						</th>
						<td>
							${auditAdmin2.name}
						</td>
					</tr>
					<tr>
						<th>
							二级审核时间:
						</th>
						<td>
							${goods.auditDate2}
						</td>
					</tr>
				[#break]
			[/#switch]
		</table>
		<table class="input tabContent">
			<tr>
				<td>
					${goods.introduction} 
				</td>
			</tr>
		</table>
		<table  class="input tabContent">
			<tr>
				<th>
					商品图片1:
				</th>
				<td>
					<img src='${goods.productImages1}' class='width125'>
				</td>
			</tr>
			<tr>
				<th>
					商品图片2:
				</th>
				<td>
					<img src='${goods.productImages2}' class='width125'>
				</td>
			</tr>
			<tr>
				<th>
					商品图片3:
				</th>
				<td>
					<img src='${goods.productImages3}' class='width125'>
				</td>
			</tr>
			<tr>
				<th>
					商品图片4:
				</th>
				<td>
					<img src='${goods.productImages4}' class='width125'>
				</td>
			</tr>
		</table>
		<table  class="input tabContent">
			<tr>
				<th>
					规格-目标:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Target']
								[#if goods.specTargetId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-花卉:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Flower']
								[#if goods.specFlowertId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-花型:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Sort']
								[#if goods.specSorttId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-设计:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Design']
								[#if goods.specDesigntId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-颜色:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Color']
								[#if goods.specColortId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-系列:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Series']
								[#if goods.specSeriestId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
			<tr>
				<th>
					规格-主题:
				</th>
				<td>
						[#list specs as spec]
							[#if spec.specification=='Theme']
								[#if goods.specThemetId=spec.id]${spec.specificationValue}[/#if]
							[/#if]
						[/#list]
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
[/#escape]