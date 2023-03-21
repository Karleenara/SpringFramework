package com.kosa.myapp3.common;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	public static String makeTag(HttpServletRequest request, int totalCnt, int pgSize) {
		int pgGroupSize = 5; 
		int cpage;
		String pg = request.getParameter("pg");
		if (pg == null || pg.equals(""))
			pg = "0";
		cpage = Integer.parseInt(pg); 
		int pageTotal = (int) Math.ceil((float) totalCnt / pgSize)-1;
		System.out.println(pageTotal);

		String path="";
		String firstLabel ="<<";
		String prevLabel = "<";
		String nextLabel = ">";
		String lastLabel = ">>";
		int start, end;
		
		start = cpage/pgGroupSize * pgGroupSize;
		end = start + pgGroupSize;
		if(end>pageTotal)
			end = pageTotal +1;
		System.out.println(String.format("start %d end %d cpage %d", start, end, cpage));
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<ul class='pagination justify-content-center'>");
		buffer.append(makeLink(0,firstLabel));
		
		if(cpage>0)
			buffer.append(makeLink(cpage-1, prevLabel));
		
		for(int i=start; i<end; i++)
		{
			if(i==cpage)
				buffer.append(makeActiveLink(i, (i+1)+""));
			else
				buffer.append(makeLink(i,(i+1)+""));
		}
		if(cpage< pageTotal)
			buffer.append(makeLink(cpage+1, nextLabel));
		
		buffer.append(makeLink(pageTotal,lastLabel));
		buffer.append("</ul>");
		return buffer.toString();
	}
	
	static String makeLink(int page, String label)
	{
		String s = "<li class='page-item'>"
				   +"<a style=\"color: black; border-color: pink;\"  class='page-link' href='#none' "
				   + "onclick=goPage("+ page+ ") "
 				   + " >"
				   + label 
				   + "</a></li>";
		return s;
	}
	
	static String makeActiveLink(int page, String label)
	{
		String s = "<li class='page-item active' >"
				+"<a style=\"border-color: pink ; background-color: pink;\" class='page-link' href='#none' "
				+ "onclick=goPage("+ page+ ") "
				+ " >"
				+ label 
				+ "</a></li>";
		return s;
	}
}