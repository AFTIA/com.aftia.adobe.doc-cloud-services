package com.aftia.adobe.karaf.doccloud.core.repository;

public class RepoFolderContents {
    Folders folders;
    Files files;

    public RepoFolderContents(Folders folders, Files files) {
        this.folders = folders;
        this.files = files;
    }
}
