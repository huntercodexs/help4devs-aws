package com.huntercodexs.demo.lambda.processor;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Help4DevsAwsS3FileProcessor {

    public static String fileExtension(String filepath) {
        if (!filepath.contains(".")) {
            return filepath;
        }

        String[] fileParts = filepath.split("\\.");
        String fileType = fileParts[fileParts.length-1];

        if (fileType.isEmpty()) {
            return filepath;
        }

        return fileType;
    }

    public void txt(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("TXT");

        logger.log("File processor STARTED");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        logger.log("File processor FINISHED");
    }

    public void csv(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("CSV");

        logger.log("File processor STARTED");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        logger.log("File processor FINISHED");

    }

    public void pdf(LambdaLogger logger, S3ObjectInputStream inputStream, String scope) {
        logger.log("PDF");

        logger.log("File processor STARTED");

        if (scope == null || scope.isEmpty() || scope.equals("content")) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                br.lines().skip(1).forEach(line -> {
                    logger.log("Processing File: " + line + "\n");
                });

            } catch (IOException e) {
                logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }

        } else if (scope.equals("metadata")) {

            try (PDDocument document = PDDocument.load(inputStream)) {

                PDDocumentInformation metadata = document.getDocumentInformation();

                String version = String.valueOf(document.getDocument().getVersion());
                String version2 = String.valueOf(document.getVersion());
                String title = metadata.getTitle();
                String author = metadata.getAuthor();
                String creationDate = String.valueOf(metadata.getCreationDate().getTime());
                String numberOfPages = String.valueOf(document.getNumberOfPages());
                String producer = metadata.getProducer();
                String keywords = metadata.getKeywords();
                String subject = metadata.getSubject();
                String trapped = metadata.getTrapped();
                String modificationDate = String.valueOf(metadata.getModificationDate().getTime());

                logger.log("Version : " + version);
                logger.log("Version2 : " + version2);
                logger.log("Title : " + title);
                logger.log("Author : " + author);
                logger.log("Creation Date : " + creationDate);
                logger.log("Number of Pages : " + numberOfPages);
                logger.log("Producer : " + producer);
                logger.log("Keywords : " + keywords);
                logger.log("Subject : " + subject);
                logger.log("Trapped : " + trapped);
                logger.log("Modification Date : " + modificationDate);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        logger.log("File processor FINISHED");



    }

    public void jpg(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("JPG");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void png(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("PNG");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void gif(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("GIF");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void bmp(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("BMP");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void xls(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("XLS");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void docx(LambdaLogger logger, S3ObjectInputStream inputStream) {
        logger.log("docx");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
