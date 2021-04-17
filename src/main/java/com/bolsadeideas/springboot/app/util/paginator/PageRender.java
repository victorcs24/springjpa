package com.bolsadeideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPage;
	private int numElemPage;
	
	private int pageActual;
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		this.numElemPage  = 5;
		this.totalPage = this.page.getTotalPages();
		pageActual = page.getNumber() +1;
		int desde, hasta;
		if(totalPage <= numElemPage) {
			desde=1;
			hasta=totalPage;
		}
		else {
			if(pageActual <= numElemPage/2) {
				desde=1;
				hasta=numElemPage;
			} else if (pageActual >= totalPage-numElemPage/2) {
				desde = totalPage -numElemPage +1;
				hasta = numElemPage;
			}
			else {
				desde = pageActual -numElemPage/2;
				hasta = numElemPage;
			}
		}
		
		for(int i=0; i< hasta; i++) {
			paginas.add(new PageItem(desde+i, pageActual==desde +i));
		}
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Page<T> getPage() {
		return page;
	}
	public void setPage(Page<T> page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNumElemPage() {
		return numElemPage;
	}
	public void setNumElemPage(int numElemPage) {
		this.numElemPage = numElemPage;
	}
	public int getPageActual() {
		return pageActual;
	}
	public void setPageActual(int pageActual) {
		this.pageActual = pageActual;
	}
	public List<PageItem> getPaginas() {
		return paginas;
	}
	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	} 
}
