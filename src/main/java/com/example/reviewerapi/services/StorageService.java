package com.example.reviewerapi.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class StorageService {
    public static void handleFiles(List<File> filesList){
        filesList.stream().map(
                file -> {
                    System.out.println(file.getName());
                    return file;
                }
        );
    }
}
