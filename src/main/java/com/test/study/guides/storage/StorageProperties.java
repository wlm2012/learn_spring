package com.test.study.guides.storage;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wlm
 */

@Data
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location="upload-dir";

}
