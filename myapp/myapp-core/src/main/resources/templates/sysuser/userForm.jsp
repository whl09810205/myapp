<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var unitTree
	$(function() {
		parent.$.messager.progress('close');
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/userController/save',
							onSubmit : function() {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								var isValid = $(this).form('validate');
								if (!isValid) {
									parent.$.messager.progress('close');

								}
								return isValid;
							},

							success : function(result) {
								parent.$.messager.progress('close');
								try {
									result = $.parseJSON(result);
									if (result.success) {
										parent.$.modalDialog.openner_dataGrid
												.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
										parent.$.modalDialog.handler
												.dialog('close');
									} else {
										parent.$.messager.alert('错误',
												result.msg, 'error');
									}
								} catch (e) {
									alert(e);
									parent.$.messager.alert('错误', result,
											'error');
								}

							}
						});
		var ids = new Array();
		<c:forEach items = "${user.units }" var = "unit">
		ids.push("${unit.id}");
		</c:forEach>
		unitTree = $('#unitIds').combotree({
			url : '${pageContext.request.contextPath}/unitController/tree',
			parentField : 'pid',
			lines : true,
			checkbox : true,
			multiple : true,
			cascadeCheck : false,
			value : ids
		})
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input name="id" type="hidden" class="span2" value="${user.id}"
				>
			
			<table class="table table-hover table-condensed">
				<tr>
					<th>登录名称</th>
					<td><input name="name" type="text" placeholder="请输入登录名称"
						class="easyui-validatebox span2" data-options="required:true"
						value="${user.name }"></td>
					<th>密码</th>

					<td><c:if test="${empty user.pwd}">
							<input name="pwd" type="password" placeholder="请输入密码"
								class="easyui-validatebox span2" data-options="required:true">
						</c:if></td>

				</tr>
				<tr>
					<td>用户状态</td>
					<td colspan="3">
					<input class="easyui-combobox"
						name="status" value = "${user.status }" 
						data-options="
						required:true,
						valueField: 'value',
						textField: 'text',
						data: [{
							text: '启用',
							value: 'true'
						},{
							text: '禁用',
							value: 'false'
						}]" /></td>
				</tr>
				<tr>

					<th>电话号码</th>
					<td><input name="phone" type="text" placeholder="请输入登录名称"
						class="easyui-validatebox span2" data-options="required:true,validType:'Mobile'"
						value="${user.phone }"></td>
					<th>中心</th>
					<td><select id="unitIds" name="unitIds"
						style="width: 140px; height: 29px;"></select><img
						src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png"
						onclick="$('#unitIds').combotree('clear');" /></td>


				</tr>
			</table>
		</form>
	</div>
</div>