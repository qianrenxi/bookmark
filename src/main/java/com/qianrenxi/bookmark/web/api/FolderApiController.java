package com.qianrenxi.bookmark.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.service.FolderService;

@RestController
@RequestMapping(value = "/folder")
public class FolderApiController {
	@Autowired
	FolderService folderService;
	@ModelAttribute
	public Folder getFolder(@RequestParam(name="id", defaultValue="0")Long id){
		Folder folder = folderService.findOne(id);
		return folder ==null ? new Folder() : folder;
	}

	@RequestMapping(value = "/tree")
	public List<Folder> folderTree() {
		return folderService.findRoots();
	}

	@RequestMapping(value = "/save")
	// public Folder save(@RequestBody Folder folder){
	public Folder save(Folder folder) {
		return folderService.save(folder);
	}

	@RequestMapping(value = "/{id}/delete")
	public Map<String, Object> delete(@PathVariable(value = "id") Long id) {
		folderService.delete(id);
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		return result;
	}
}
