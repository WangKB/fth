[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>${message("admin.attribute.add")} </title>
    <meta name="author"/>
    <meta name="copyright"/>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>

    <script type="text/javascript">
        $().ready(function() {

            var $inputForm = $("#inputForm");
	
			[@flash_message /]
			


            // 表单验证
            $inputForm.validate({
                rules: {
                    name: "required",
                }
            });

        });
    </script>
</head>
<body>
<div class="breadcrumb">
    <a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 添加标签
</div>
<form id="inputForm" action="save.jhtml" method="post">
    <table class="input">
        <tr>
            <th>
                <span class="requiredField">*</span>标签详情名称:
            </th>
            <td>
                <input type="text" name="name" class="text" maxlength="200" />
            </td>
        </tr>
        
        <tr>
            <th>
                关联标签：
            </th>
            <td>
                <select name='tagsId'>
                [#list tags as tag]
                	<option value='${tag.id}'>${tag.name}</option>
                [/#list]
                </select>
            </td>
        </tr>
        <tr>
        	<th>
                目标-规格：
            </th>
            <td>
        		[#list targetSpecs as targetSpec]
					[#if targetSpec?has_content]
						<label>
                            <input type="checkbox" name="specTargetIds"
                                   value="${targetSpec.id}"/>${targetSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                花卉-规格：
            </th>
            <td>
        		[#list flowerSpecs as flowerSpec]
					[#if flowerSpec?has_content]
						<label>
                            <input type="checkbox" name="specFlowertIds"
                                   value="${flowerSpec.id}"/>${flowerSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                花型-规格：
            </th>
            <td>
        		[#list sortSpecs as sortSpec]
					[#if sortSpec?has_content]
						<label>
                            <input type="checkbox" name="specSorttIds"
                                   value="${sortSpec.id}"/>${sortSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                设计-规格：
            </th>
            <td>
        		[#list designSpecs as designSpec]
					[#if designSpec?has_content]
						<label>
                            <input type="checkbox" name="specDesigntIds"
                                   value="${designSpec.id}"/>${designSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                颜色-规格：
            </th>
            <td>
        		[#list colorSpecs as colorSpec]
					[#if colorSpec?has_content]
						<label>
                            <input type="checkbox" name="specColortIds"
                                   value="${colorSpec.id}"/>${colorSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                系列-规格：
            </th>
            <td>
        		[#list seriesSpecs as seriesSpec]
					[#if seriesSpec?has_content]
						<label>
                            <input type="checkbox" name="specSeriestIds"
                                   value="${seriesSpec.id}"/>${seriesSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
		<tr>
        	<th>
                主题-规格：
            </th>
            <td>
        		[#list themeSpecs as themeSpec]
					[#if themeSpec?has_content]
						<label>
                            <input type="checkbox" name="specThemetIds"
                                   value="${themeSpec.id}"/>${themeSpec.specificationValue}
                        </label>
					[#else]
						&nbsp;
					[/#if]
				[/#list]
			</td>
		</tr>
        <tr>
            <th>
                &nbsp;
            </th>
            <td>
                <input type="submit" class="button" value="${message("admin.common.submit")}" />
                <input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
[/#escape]