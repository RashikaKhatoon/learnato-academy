package com.smart.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Courses;

public interface CoursyRepository extends JpaRepository<Courses,Integer>{
//pagination
	//currentPage-page
	//Contact Per page-5
	@Query("from Courses as c where c.user.id=:userId")
	public Page<Courses> findCoursesByUser(@Param("userId")int userId,Pageable pePageable);
}
