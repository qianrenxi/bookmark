package com.qianrenxi.bookmark.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.domain.Page;
import com.qianrenxi.bookmark.domain.PageRepository;
import com.qianrenxi.bookmark.util.FaviconParser;

@Service
public class PageService extends BaseCrudService<PageRepository, Page, Long> {
	
	@Override
	public <S extends Page> S save(S entity) {
		parseFavicon(entity);
		return super.save(entity);
	}
	
	@Override
	public <S extends Page> Iterable<S> save(Iterable<S> entities) {
		for (S page : entities) {
			parseFavicon(page);
		}
		return super.save(entities);
	}
	
	public List<Page> findByFolder(Folder folder){
		List<Page> pages = repository.findByFolder(folder);
		checkFavicon(pages);
		return pages;
	}
	
	private void parseFavicon(Page page){
		try {
			String favicon = FaviconParser.getIconUrlString(page.getUrl());
			favicon = null==favicon||"".equals(favicon)?"default-favicon.png":favicon;
			page.setFavicon(favicon);
		} catch (MalformedURLException e) {
			page.setFavicon("default-favicon.png");
			//e.printStackTrace();
		} catch (Exception e) {
			page.setFavicon("default-favicon.png");
		}
	}
	
	private void checkFavicon(Iterable<Page> pages){
		for (Page page : pages) {
			checkFavicon(page);
		}
	}
	
	private void checkFavicon(Page page){
		//TODO: 判断当前的icon链接是否可用
		if(null==page.getFavicon()){
			parseFavicon(page);
			repository.saveAndFlush(page);
		}
	}
}
