<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<div class="easyui-accordion" style="width:100%;height: auto;" data-options="border:false,fit:true">
        <div title="菜单" data-options="iconCls:'icon-search'"
            style="padding: 10px;">
            <ul id="menu-tree">
            </ul>
        </div></div>
        <script type="text/javascript">
        Array.prototype.remove = function(from, to) {
            var rest = this.slice((to || from) + 1 || this.length);
            this.length = from < 0 ? this.length + from : from;
            return this.push.apply(this, rest);
        };
        
$('#menu-tree').tree({
    url: '<c:url value="/menu.do?method=getUserMenu"/>',
     loadFilter: function(data){
    	 for(var i=0;i<data.length;i++){
    		 var children=data[i]["children"];
    		 for(var j=0;j<children.length;j++){
    			 if(children[j].text=="商场匹配金融机构管理"){
    				 children.remove(j,j); 
    			 }
    			 if(children[j].text=="金融机构利率管理"){
    				 children.remove(j,j); 
    			 }
    		 }
    	 }
    	 return data;

    } 
})


</script>