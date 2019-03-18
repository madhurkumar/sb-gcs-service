package com.practice.gcs.service;

import com.google.cloud.storage.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentUploadService {

    private static Storage storage = null;

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }


    public String uploadFile(MultipartFile multipartFile) throws IOException {
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        Bucket bucket =
                storage.create(BucketInfo.newBuilder("mk_" + String.valueOf(DateTime.now().getMillis())).build());
        Blob blob = storage.create(
                BlobInfo.newBuilder(bucket, multipartFile.getOriginalFilename()).setAcl(acls).build(),
                multipartFile.getBytes());
        return blob.getMediaLink();
    }

}
