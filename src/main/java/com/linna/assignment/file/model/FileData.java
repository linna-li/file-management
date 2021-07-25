package com.linna.assignment.file.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "fileName")
    String fileName;
    @Lob
    @Column(name = "fileContent")
    byte[] fileContent;

    public FileData() {
    }

    public FileData(String fileName, byte[] data) {
        this.fileName = fileName;
        this.fileContent = data;
    }

    public long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "File [id=" + id + ", fileName=" + fileName ;
    }
}
