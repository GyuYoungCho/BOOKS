package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Category;
import com.project.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
//	Optional<Category> findCategory_IdByColumn_Id(int column_id);
//	Optional<Category> findColumn_IdByCategory_Id(int category_id);
	Optional<Category> findColumnIdByCategoryId(int category_id);
	Optional<Category> findCategoryIdByColumnId(int column_id);


}
