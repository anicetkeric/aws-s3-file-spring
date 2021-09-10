package com.filestorage.awss3filespring.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {
	
	private String fileName;
    private String fileUrl;
    private String fileType;
    private long size;
}
