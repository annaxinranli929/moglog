package utils;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.UUID;

public class S3Uploader {
    private static final String BUCKET_NAME = "moglog-img"; // bucket name
    private static final Region REGION = Region.AP_NORTHEAST_1; // Japan

    private static final S3Client s3 = S3Client.builder()
            .region(REGION)
            .build(); // ← uses credentials from ~/.aws/credentials

    public static String uploadImage(InputStream inputStream, long contentLength, String contentType, String originalFileName) {
        String key = "uploads/" + UUID.randomUUID() + "-" + originalFileName;

        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .contentType(contentType)
                .build();

        s3.putObject(putReq, RequestBody.fromInputStream(inputStream, contentLength));

        return "https://" + BUCKET_NAME + ".s3." + REGION.id() + ".amazonaws.com/" + key;
    }
}
