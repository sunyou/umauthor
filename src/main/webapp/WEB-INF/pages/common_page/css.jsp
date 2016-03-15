<%@ page contentType="text/html;charset=UTF-8" %>
<!-- basic styles -->

<link href="${resourcePath}/ace_v135/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/jquery.gritter.min.css" />

<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/daterangepicker.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="${resourcePath}/ace/assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->
<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/ace-fonts.min.css" />

<!-- ace styles -->

<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/ace.min.css" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/ace-part2.min.css" class="ace-main-stylesheet" />
<![endif]-->

<!--[if lte IE 9]>
  <link rel="stylesheet" href="${resourcePath}/ace_v135/dist/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="${resourcePath}/ace_v135/dist/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${resourcePath}/ace_v135/dist/js/html5shiv.js"></script>
<script src="${resourcePath}/ace_v135/dist/js/respond.min.js"></script>
<![endif]-->


<!--[if !IE]> -->

<script src="${resourcePath}/ace_v135/dist/js/jquery.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${resourcePath}/ace_v135/dist/js/jquery1x.min.js"></script>
<![endif]-->
<style>
.datepicker{z-index:1151 !important;}
.daterangepicker{z-index:1151 !important;}
.checkbox-title{cursor: pointer;}
.table-bottom{padding-top:10px!important;}
.dataTables_wrapper .table_buttons button{margin-right:3px;}
/* scroll fixes */
.modal-open .modal {
  padding-left: 0px !important;
  padding-right: 0px !important;
  overflow-y: scroll;
}
/*垂直居中*/
.valign {
  font-size: 0;
}
.valign > [class*="col"] {
  display: inline-block;
  float: none;
  font-size: 13px;
  vertical-align: middle;
}
</style>
<shiro:authenticated>
<script src="${ctx}/static/business/main/sidebar-menu.js"></script>
<script type="text/javascript" src="${ctx}/static/business/main/menu.js"></script>
</shiro:authenticated>
<script>
window.umauthor = {
	
		pageInit:function(){	var  oTable1;}
};


var GLOBAL = {
    WEBROOT:"${ctx}"
};
</script>