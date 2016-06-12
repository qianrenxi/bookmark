package com.qianrenxi.bookmark.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qianrenxi.bookmark.domain.Folder;
import com.qianrenxi.bookmark.domain.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
	
	List<Page> findByFolder(Folder folder);

}
