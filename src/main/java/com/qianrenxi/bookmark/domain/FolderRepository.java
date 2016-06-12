package com.qianrenxi.bookmark.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findByParent(Folder parent);

	List<Folder> findByParentIsNull();
}
