package com.jhtest.library.repository;

import com.jhtest.library.domain.Blog;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Blog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select blog from Blog blog where blog.user.login = ?#{principal.preferredUsername}")
    List<Blog> findByUserIsCurrentUser();
}
