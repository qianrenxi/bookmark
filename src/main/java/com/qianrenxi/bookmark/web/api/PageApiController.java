package com.qianrenxi.bookmark.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.domain.Page;
import com.qianrenxi.bookmark.service.FolderService;
import com.qianrenxi.bookmark.service.PageService;

@RestController
@RequestMapping(value = "/folder/{folderId}/page")
public class PageApiController {
	
	@Autowired
	FolderService folderService;
	@Autowired
	PageService pageService;
	
	/*@ModelAttribute("folder")
	public Folder getFolder(@PathVariable(value="folderId") Long folderId){
		return folderService.findOne(folderId);
	}*/
	
	@RequestMapping(value="/list")
	public List<Page> list(@PathVariable(value="folderId") Long folderId){
		Folder folder = folderService.findOne(folderId);
		return pageService.findByFolder(folder);
	}
	
	@RequestMapping(value="/save")
	public Page save(Page page, @PathVariable(value="folderId") Long folderId){
		Folder folder = folderService.findOne(folderId);
		page.setFolder(folder);
		return pageService.save(page);
	}
	
	@RequestMapping(value="/{pageId}/delete")
	public String delete(@PathVariable(value="pageId") Long id){
		pageService.delete(id);
		return "success";
	}
}
