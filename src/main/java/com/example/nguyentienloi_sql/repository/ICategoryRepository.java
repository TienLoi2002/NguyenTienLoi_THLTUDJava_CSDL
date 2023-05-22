package com.example.nguyentienloi_sql.repository;

import com.example.nguyentienloi_sql.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
