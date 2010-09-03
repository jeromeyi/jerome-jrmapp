<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<script type="text/javascript" src="xloadtree/xtree.js"></script>
	<script type="text/javascript" src="xloadtree/xmlextras.js"></script>
	<script type="text/javascript" src="xloadtree/xloadtree.js"></script>
	<script type="text/javascript" src="xloadtree/json_parse.js"></script>
	<script type="text/javascript" src="xloadtree/json2.js"></script>
  </head>
  
  <body>
  <input type="button" value="确定" onclick="doClose()" ><br>
    <script type="text/javascript">
		var tree = new WebFXTree("中国",null,null,"xloadtree/images/xp/base.gif","xloadtree/images/xp/base.gif");
		
		document.write(tree);
		
		function doClose(){
			var temp=document.getElementsByName("checkbox_id");
			for(i=0;i<temp.length;i++){
				if(temp[i].checked){
					alert(temp[i].value);
				}
			}
		}
		
		function getRootTree(){
		tree.add(new WebFXLoadTreeItem("云南","getData.jsp?id=1","javascript:alert('点击事件')",null,null,null,1,"id-1",true,null));
		tree.add(new WebFXLoadTreeItem("广东","getData.jsp?id=2","javascript:alert('点击事件')",null,null,null,2,"id-2",false,null));
		tree.add(new WebFXLoadTreeItem("其它","getData.jsp?id=0","javascript:alert('点击事件')",null,null,null,0,"id-3",false,null));
		}
			_startLoadXmlTree("getData.jsp?id=0", tree);
		
		//tree.add(new WebFXLoadTreeItem("云南","getData.jsp?id=1","javascript:alert('点击事件')",null,null,null,1,"id-1",true,null));
	</script>
  </body>
</html>
