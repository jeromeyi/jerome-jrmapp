/*----------------------------------------------------------------------------\
|                               XLoadTree 1.11                                |
|-----------------------------------------------------------------------------|
|                         Created by Erik Arvidsson                           |
|                  (http://webfx.eae.net/contact.html#erik)                   |
|                      For WebFX (http://webfx.eae.net/)                      |
|-----------------------------------------------------------------------------|
| An extension to xTree that allows sub trees to be loaded at runtime by      |
| reading XML files from the server. Works with IE5+ and Mozilla 1.0+         |
|-----------------------------------------------------------------------------|
|             Copyright (c) 2001, 2002, 2003, 2006 Erik Arvidsson             |
|-----------------------------------------------------------------------------|
| Licensed under the Apache License, Version 2.0 (the "License"); you may not |
| use this file except in compliance with the License.  You may obtain a copy |
| of the License at http://www.apache.org/licenses/LICENSE-2.0                |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| Unless  required  by  applicable law or  agreed  to  in  writing,  software |
| distributed under the License is distributed on an  "AS IS" BASIS,  WITHOUT |
| WARRANTIES OR  CONDITIONS OF ANY KIND,  either express or implied.  See the |
| License  for the  specific language  governing permissions  and limitations |
| under the License.                                                          |
|-----------------------------------------------------------------------------|
| Dependencies: xtree.js     - original xtree library                         |
|               xtree.css    - simple css styling of xtree                    |
|               xmlextras.js - provides xml http objects and xml document     |
|                              objects                                        |
|-----------------------------------------------------------------------------|
| 2001-09-27 | Original Version Posted.                                       |
| 2002-01-19 | Added some simple error handling and string templates for      |
|            | reporting the errors.                                          |
| 2002-01-28 | Fixed loading issues in IE50 and IE55 that made the tree load  |
|            | twice.                                                         |
| 2002-10-10 | (1.1) Added reload method that reloads the XML file from the   |
|            | server.                                                        |
| 2003-05-06 | Added support for target attribute                             |
| 2006-05-28 | Changed license to Apache Software License 2.0.                |
|-----------------------------------------------------------------------------|
| Created 2001-09-27 | All changes are in the log above. | Updated 2006-05-28 |
\----------------------------------------------------------------------------*/


webFXTreeConfig.loadingText = "正在加载...";
webFXTreeConfig.loadErrorTextTemplate = "Error loading \"%1%\"";
webFXTreeConfig.emptyErrorTextTemplate = "Error \"%1%\" does not contain any tree items";

/*
 * WebFXLoadTree class
 */

function WebFXLoadTree(sText, sXmlSrc, sAction, sBehavior, sIcon, sOpenIcon,oncontextmenu) {
	// call super
	this.WebFXTree = WebFXTree;
	this.WebFXTree(sText, sAction, sBehavior, sIcon, sOpenIcon,oncontextmenu);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = false;
	this.loaded = false;
	this.errorText = "";
	// check start state and load if open
	if (this.open)
		_startLoadXmlTree(this.src, this);
	else {
		// and create loading item if not
		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);
	}
}

WebFXLoadTree.prototype = new WebFXTree;

// override the expand method to load the xml file
WebFXLoadTree.prototype._webfxtree_expand = WebFXTree.prototype.expand;
WebFXLoadTree.prototype.expand = function() {
	if (!this.loaded && !this.loading) {
		// load
		_startLoadXmlTree(this.src, this);
	}
	this._webfxtree_expand();
};

/*
 * WebFXLoadTreeItem class
 */

function WebFXLoadTreeItem(sText, sXmlSrc, sAction, eParent, sIcon, sOpenIcon,checkboxType,value,isChecked,oncontextmenu) {
	// call super
	this.WebFXTreeItem = WebFXTreeItem;
	this.WebFXTreeItem(sText, sAction, eParent, sIcon, sOpenIcon,checkboxType,value,isChecked,oncontextmenu);

	// setup default property values
	if(sXmlSrc != null && !sXmlSrc == "" ){
		this.src = sXmlSrc;
		this.loading = false;
		this.loaded = false;
		this.errorText = "";
		// check start state and load if open
		if (this.open)
			_startLoadXmlTree(this.src, this);
		else {
			// and create loading item if not
			this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
			this.add(this._loadingItem);
		}
	}
}

WebFXLoadTreeItem.prototype = new WebFXTreeItem;

// override the expand method to load the xml file
WebFXLoadTreeItem.prototype._webfxtreeitem_expand = WebFXTreeItem.prototype.expand;
WebFXLoadTreeItem.prototype.expand = function() {
	//alert("WebFXLoadTreeItem expand");
	if (!this.loaded && !this.loading) {
		// load
		//alert(this.src);
		_startLoadXmlTree(this.src, this);
	}
	this._webfxtreeitem_expand();
};

// reloads the src file if already loaded
WebFXLoadTree.prototype.reload =
WebFXLoadTreeItem.prototype.reload = function () {
	// if loading do nothing
	if (this.loaded) {
		var open = this.open;
		// remove
		while (this.childNodes.length > 0)
			this.childNodes[this.childNodes.length - 1].remove();

		this.loaded = false;

		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);

		if (open)
			this.expand();
	}
	else if (this.open && !this.loading)
		_startLoadXmlTree(this.src, this);
};

/*
 * Helper functions
 */

// creates the xmlhttp object and starts the load of the xml document
function _startLoadXmlTree(sSrc, jsNode) {
	if (jsNode.loading || jsNode.loaded)
		return;
	jsNode.loading = true;
	//alert("_startLoadXmlTree sSrc= " +sSrc);
	if(sSrc == null || sSrc == "" ){
		return;
	}
	var xmlHttp = XmlHttp.create();
	xmlHttp.open("GET", sSrc, true);	// async
	xmlHttp.onreadystatechange = function () {
	//alert(xmlHttp.responseText);
		if (xmlHttp.readyState == 4) {
			if(sSrc.lastIndexOf(".xml") == (sSrc.length -4)){
				_xmlFileLoaded(xmlHttp.responseXML, jsNode);//xloadTree的源码
			}else{
				//alert("xmlHttp.responseText= " +xmlHttp.responseText);
				_dbDataLoaded(xmlHttp.responseText, jsNode);//
			}
		}
	};
	// call in new thread to allow ui to update
	window.setTimeout(function () {
		xmlHttp.send(null);
	}, 10);
}


// Converts an xml tree to a js tree. See article about xml tree format
function _xmlTreeToJsTree(oNode) {
	// retreive attributes
	var text = oNode.getAttribute("text");
	var action = oNode.getAttribute("action");
	var parent = null;
	var icon = oNode.getAttribute("icon");
	var openIcon = oNode.getAttribute("openIcon");
	var src = oNode.getAttribute("src");
	var target = oNode.getAttribute("target");
	// create jsNode
	var jsNode;
	if (src != null && src != "")
		jsNode = new WebFXLoadTreeItem(text, src, action, parent, icon, openIcon);
	else
		jsNode = new WebFXTreeItem(text, action, parent, icon, openIcon);

	if (target != "")
		jsNode.target = target;

	// go through childNOdes
	var cs = oNode.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		if (cs[i].tagName == "tree")
			jsNode.add( _xmlTreeToJsTree(cs[i]), true );
	}
	return jsNode;
}

// Inserts an xml document as a subtree to the provided node
function _xmlFileLoaded(oXmlDoc, jsParentNode) {
	if (jsParentNode.loaded)
		return;
	var bIndent = false;
	var bAnyChildren = false;
	jsParentNode.loaded = true;
	jsParentNode.loading = false;

	// check that the load of the xml file went well
	if( oXmlDoc == null || oXmlDoc.documentElement == null) {
		alert(oXmlDoc.xml);
		jsParentNode.errorText = parseTemplateString(webFXTreeConfig.loadErrorTextTemplate,
							jsParentNode.src);
	}
	else {
		// there is one extra level of tree elements
		var root = oXmlDoc.documentElement;

		// loop through all tree children
		var cs = root.childNodes;
		var l = cs.length;
		for (var i = 0; i < l; i++) {
			if (cs[i].tagName == "tree") {
				bAnyChildren = true;
				bIndent = true;
				jsParentNode.add( _xmlTreeToJsTree(cs[i]), true);
			}
		}

		// if no children we got an error
		if (!bAnyChildren)
			jsParentNode.errorText = parseTemplateString(webFXTreeConfig.emptyErrorTextTemplate,
										jsParentNode.src);
	}

	// remove dummy
	if (jsParentNode._loadingItem != null) {
		jsParentNode._loadingItem.remove();
		bIndent = true;
	}

	if (bIndent) {
		// indent now that all items are added
		jsParentNode.indent();
	}

	// show error in status bar
	if (jsParentNode.errorText != "")
		window.status = jsParentNode.errorText;
}

// parses a string and replaces %n% with argument nr n
function parseTemplateString(sTemplate) {
	var args = arguments;
	var s = sTemplate;

	s = s.replace(/\%\%/g, "%");

	for (var i = 1; i < args.length; i++)
		s = s.replace( new RegExp("\%" + i + "\%", "g"), args[i] )

	return s;
}

/**
  * author : 
  * time : 2007-10-25 18:5:15
  * function: 把从数据库返回的数据整成xtree数据
  */
  
// Converts an xml tree to a js tree. See article about xml tree format
function _treeNodeToJsTree(oNode) {
	// retreive attributes
	var text = oNode.text;
	var action = oNode.action;
	var parent = null;
	var icon = oNode.icon;
	var openIcon = oNode.openIcon;
	var src = oNode.src;
	var target = oNode.target;
	
	var checkboxType = oNode.checkboxType;
	var value = oNode.value;
	var isChecked = oNode.isChecked;
	var oncontextmenu = oNode.oncontextmenu;
	// create jsNode
	var jsNode;
	if (src != null && src != "")
		jsNode = new WebFXLoadTreeItem(text, src, action, parent, icon, openIcon, checkboxType, value, isChecked,oncontextmenu);
	else
		jsNode = new WebFXTreeItem(text, action, parent, icon, openIcon, checkboxType, value, isChecked,oncontextmenu);

	if (target != "")
		jsNode.target = target;

	// go through childNOdes
	/* 一层一层的展开，所以不在展开子结点
	var cs = oNode.childNodeList;
	if(cs != null){
		var l = cs.length;
		for (var i = 0; i < l; i++) {
			//alert(cs[i].tagName);
			jsNode.add( _treeNodeToJsTree(cs[i]), true );
		}
	}
	*/
	return jsNode;
}

function _dbDataLoaded(dbData, jsParentNode){
	if (jsParentNode.loaded)
		return;
	var bIndent = false;
	var bAnyChildren = false;
	jsParentNode.loaded = true;
	jsParentNode.loading = false;

	// check that the load of the xml file went well
	if( dbData == null || dbData == null) {
		alert(dbData);
		jsParentNode.errorText = parseTemplateString(webFXTreeConfig.loadErrorTextTemplate,
							jsParentNode.src);
	}
	else {
		// there is one extra level of tree elements
		var nodeList;
		//alert(dbData);
		try{
			nodeList = JSON.parse(dbData);
		}catch(ex){
			alert("调用JSON解析错误,请检查返回值："+ex.message);
		}

		// loop through all tree childrend
		var l = nodeList.length;
		for (var i = 0; i < l; i++) {
			//if (cs[i].tagName == "tree") {
				bAnyChildren = true;
				bIndent = true;
				jsParentNode.add( _treeNodeToJsTree(nodeList[i]), true);
			//}
		}

		// if no children we got an error
		if (!bAnyChildren)
			jsParentNode.errorText = parseTemplateString(webFXTreeConfig.emptyErrorTextTemplate,
										jsParentNode.src);
	}

	// remove dummy
	if (jsParentNode._loadingItem != null) {
		jsParentNode._loadingItem.remove();
		bIndent = true;
	}

	if (bIndent) {
		// indent now that all items are added
		jsParentNode.indent();
	}

	// show error in status bar
	if (jsParentNode.errorText != "")
		window.status = jsParentNode.errorText;
}