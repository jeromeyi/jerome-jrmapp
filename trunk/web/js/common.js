function parsePagin(page, jQuerypagin, queryform) {
	/*alert(page.currentPageNo);
	alert(jQuerypagin.html());
	alert(queryform);
	return;*/
	if (page.totalCount > 0)
		jQuerypagin.append('<div class="pagination" align="right">');
	if (page.currentPageNo > 1)
		jQuerypagin.append('<a href="javascript:ajaxTurnPage (1,\'' + queryform
				+ '\')" style="cursor:hand;" title="第一页"><<</a> ');
	else
		jQuerypagin.append('<a  class="noncepage"><<</a>');

	if (page.currentPageNo > 1)
		jQuerypagin.append('<a href="javascript:ajaxTurnPage ('
				+ (page.currentPageNo - 1) + ',\'' + queryform
				+ '\')" style="cursor:hand;" title="上一页"><</a> ');
	else
		jQuerypagin.append('<a  class="noncepage"><</a> ');

	/*alert(page.totalPageCount);
	return;*/
	var begin = 1;
	if (page.currentPageNo > 5)
		begin = (page.currentPageNo - 5);
	var last = (begin + 10);
	if (last > page.totalPageCount)
		last = page.totalPageCount;
	for ( var index = begin; index <= last;index++ ) {
		if (index == page.currentPageNo)
			jQuerypagin.append('<a class="noncepage" title="当前页">'+index+'</a>');
		else
			jQuerypagin.append('<a href="javascript:ajaxTurnPage (' + index + ',\''
					+ queryform + '\')" style="cursor:hand;" title="第'+index+'页">'
					+ index + '</a> ');
	}

	if (page.currentPageNo < page.totalPageCount)
		jQuerypagin.append('<a href="javascript:ajaxTurnPage ('
				+ (page.currentPageNo + 1) + ',\'' + queryform
				+ '\')" style="cursor:hand;" title="下一页">></a> ');
	else
		jQuerypagin.append('<a  class="noncepage">></a> ');

	if (page.currentPageNo < page.totalPageCount)
		jQuerypagin.append('<a href="javascript:ajaxTurnPage ('
				+ page.totalPageCount + ',\'' + queryform
				+ '\')" style="cursor:hand;" title="最末页"> >></a> ');
	else {
		jQuerypagin.append('<a  class="noncepage">>></a> |每页显示');
	}
		
		jQuerypagin
				.append('<input name="pageSize" type="text" class="inputnumber"  value="'
						+ page.pageSize
						+ '" onkeypress="if(event.keyCode=="13"){ document.getElementById("go").focus(); }"/>');
		jQuerypagin.append('条,共' + page.totalCount + '条记录|');
		jQuerypagin
				.append('第<input name="pageNo" type="text" value="'
						+ page.currentPageNo
						+ '" class="inputnumber" onkeypress="if(event.keyCode=="13"){ document.getElementById("go").focus(); }" /> 页,共'
						+ page.totalPageCount + '页');
		jQuerypagin
				.append('<input name="go" id="go" type="button" value="GO" class="inputgo" style="cursor:hand;" onclick="ajaxTurnPage ("goto",\''
						+ queryform + '\')"/> ');
		jQuerypagin.append('</div>  ');
	
}