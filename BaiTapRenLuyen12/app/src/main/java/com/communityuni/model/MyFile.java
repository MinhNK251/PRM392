package com.communityuni.model;

/**
 * Created by cafe on 28/12/2016.
 */

public class MyFile {
    private  String display;
    private String path;
    private String description;
    private FileSupport fileType;
    private  boolean hasChild;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileSupport getFileType() {
        return fileType;
    }

    public void setFileType(FileSupport fileType) {
        this.fileType = fileType;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public MyFile() {
    }

    public MyFile(String display, String path, String description, FileSupport fileType, boolean hasChild) {
        this.display = display;
        this.path = path;
        this.description = description;
        this.fileType = fileType;
        this.hasChild = hasChild;
    }
}
