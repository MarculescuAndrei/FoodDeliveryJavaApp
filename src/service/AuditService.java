package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {
    private static AuditService instance = null;
    private final BufferedWriter bfWr;

    public static AuditService getInstance() throws IOException {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    private AuditService() throws IOException {
        Path path = Path.of("files/audit.csv");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (Exception exc) {
                System.out.println(exc);
            }
        }
        this.bfWr = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
    }

    public void write(String action) throws IOException {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss").format(new Date());
        String auditAction = action + "," + timeStamp;
        bfWr.write(auditAction + "\n");
        bfWr.flush();
    }
}
