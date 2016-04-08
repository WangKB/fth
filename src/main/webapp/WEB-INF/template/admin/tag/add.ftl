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
    <script type="text/javascript" src="${base}/resources/admin/js/webuploader.js"></script>

    <script type="text/javascript">
        $().ready(function() {

            var $inputForm = $("#inputForm");
            var $filePicker = $("#filePicker");
	
			[@flash_message /]
			

            $filePicker.uploader({data:{imageType:'tag'}});

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
                <span class="requiredField">*</span>标签名称:
            </th>
            <td>
                <input type="text" name="name" class="text" maxlength="200" />
            </td>
        </tr>
        
        <tr>
            <th>
                标签介绍:
            </th>
            <td>
                <input type="text" name="introduction" class="text" maxlength="200" />
            </td>
        </tr>

        <tr>
            <th>
                图片:
            </th>
            <td>
                <input type="text" name="images" class="text" maxlength="200" title="${message("admin.goods.imageTitle")}" />
                <a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
            </td>
        </tr>
        <tr class='none'>
            <th>
                关联商品：
            </th>
            <td>
                <select name='productId'>
                [#list products as product]
                	<option value='${product.id}'>${product.name}</option>
                [/#list]
                </select>
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