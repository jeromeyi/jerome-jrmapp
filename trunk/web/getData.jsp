<%@ page language="java" pageEncoding="UTF-8"%>

<%
java.util.List<com.jrmapp.xloadtree.Node> list= new java.util.ArrayList<com.jrmapp.xloadtree.Node>(); 
		String id=request.getParameter("id");
		if(id.equals("0")){
			for(int i=1;i<11;i++){
				com.jrmapp.xloadtree.Node node= new com.jrmapp.xloadtree.Node();
				node.setAction("javascript:alert('点击事件测试')");
				node.setText("测试"+i);
				node.setCheckboxType(i/2);
				node.setIsChecked(false);
				node.setValue(String.valueOf(i));
				node.setSrc("getData.jsp?id="+i);
				list.add(node);
			}
				
		}else{
			for(int i=1;i<6;i++){
				com.jrmapp.xloadtree.Node node= new com.jrmapp.xloadtree.Node();
				if(i%2==0){
				node.setSrc("getData.jsp?id="+id+"-"+i);
				node.setAction("javascript:alert('点击事件测试')");
				}else{
					node.setAction("javascript:alert('点击...')");
				}
				node.setText("测试"+id+"-"+i);
				node.setCheckboxType(i/2);
				node.setIsChecked(false);
				node.setValue(String.valueOf(id+"-"+i));
				list.add(node);
			}
		}
		/**
		if(id.equals("1")){
			node.setAction("javascript:alert('点击事件测试')");
			node.setText("昆明");
			node.setCheckboxType(1);
			node.setIsChecked(false);
			node.setValue("3");
			list.add(node);
		}else
		 if(id.equals("2")){
			node= new com.jrmapp.xloadtree.Node();
			node.setAction(null);
			node.setText("深圳");
			node.setCheckboxType(2);
			node.setIsChecked(true);
			node.setValue("4");
			list.add(node);
		}else{
			node= new com.jrmapp.xloadtree.Node();
			node.setAction(null);
			node.setText("其他");
			node.setCheckboxType(0);
			node.setIsChecked(false);
			node.setValue("5");
			list.add(node);
		}
		**/
		net.sf.json.JSONArray json= net.sf.json.JSONArray.fromObject(list);   
	    out.println(json);  
%>