package com.qianrenxi.bookmark.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.service.FolderService;

@RestController
@RequestMapping(value="/folder")
public class FolderApiController {
	@Autowired
	FolderService folderService;
	
	@RequestMapping(value="/tree")
	public List<Folder> folderTree(){
		return folderService.findRoots();
	}
	
	@RequestMapping(value="/save")
	public Folder save(Folder folder){
		return folderService.save(folder);
	}
	
	@RequestMapping(value="/{id}/delete")
	public String delete(@PathVariable(value="id") Long id){
		folderService.delete(id);
		return "success";
	}
}
