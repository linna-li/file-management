package com.linna.assignment.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linna.assignment.file.model.FileData;

public interface FileRepository extends JpaRepository<FileData, Long> {
}
