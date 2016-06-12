package com.qianrenxi.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.domain.FolderRepository;

@Service
public class FolderService extends BaseCrudService<FolderRepository, Folder, Long> {

	@Override
	public <S extends Folder> S save(S entity) {
		if (null != entity.getParent() && null != entity.getParent().getId()) {
			Folder parent = findOne(entity.getParent().getId());
			entity.setParent(parent);
		}else {
			entity.setParent(null);
		}
		return super.save(entity);
	}

	@Override
	public <S extends Folder> Iterable<S> save(Iterable<S> entities) {
		for (S entity : entities) {
			if (null != entity.getParent() && null != entity.getParent().getId()) {
				Folder parent = findOne(entity.getParent().getId());
				entity.setParent(parent);
			}else {
				entity.setParent(null);
			}
		}
		return super.save(entities);
	}

	public List<Folder> findRoots() {
		return repository.findByParentIsNull();
	}

	public List<Folder> findChildren(Folder parent) {
		return repository.findByParent(parent);
	}

	public List<Folder> findChildren(Long id) {
		Folder parent = repository.findOne(id);
		return findChildren(parent);
	}
}
