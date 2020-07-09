package com.vivekgupta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class Analyzer {
    private String fileName;
    private String fileType;
    private Path filePath;

    private HashMap<String, String[]> languageSyntax = new HashMap<String, String[]>();

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String  getFilePath() {
        return filePath.toString();
    }

    public Analyzer(String filePathString) throws IOException, FileNotFoundException, InvalidFileTypeException {
        filePath = Path.of(filePathString);
        fileName = filePath.getFileName().toString();
        fileType = this.getFileExtension();

        populateLanguageSyntax();
    }

    private String getFileExtension() throws InvalidFileTypeException {
        int indexOfDot = this.fileName.lastIndexOf('.');

        if(indexOfDot < 1 || indexOfDot == fileName.length() - 1) {
            throw new InvalidFileTypeException();
        }

        return fileName.substring(indexOfDot + 1);
    }

    public void populateLanguageSyntax() {
        languageSyntax.put("java", new String[] {"//", "/*", "*/"});
        languageSyntax.put("js", new String[] {"//", "/*", "*/"});
        languageSyntax.put("py", new String[] {"#", null, null});
        languageSyntax.put("html", new String[] {null, "<!--", "-->"});
    }

    public String analyze() throws UnsupportedFileTypeException, IOException {
        int totalLines = 0, emptyLines = 0, codeLines = 0, singleLineComments = 0, multiLineComments = 0;
        String singleComment, multilineCommentStart, multilineCommentEnd, line;
        boolean isMultiLineBlock = false;


        if(!languageSyntax.containsKey(fileType)) {
            throw new UnsupportedFileTypeException();
        }

        String[] languageTokens = languageSyntax.get(fileType);
        singleComment = languageTokens[0];
        multilineCommentStart = languageTokens[1];
        multilineCommentEnd = languageTokens[2];

        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            //read each line
            while ((line = br.readLine()) != null) {
                totalLines++;

                // trim the line
                line = line.trim();

                //check empty line
                if (line.isEmpty()) {
                    emptyLines++;
                } else if (singleComment != null && line.startsWith(singleComment) && !isMultiLineBlock) {
                    singleLineComments++;
                } else if (multilineCommentStart != null && multilineCommentEnd != null && line.startsWith(multilineCommentStart) && line.endsWith(multilineCommentEnd)) {
                    multiLineComments++;
                } else if (multilineCommentStart != null && line.startsWith(multilineCommentStart) && !isMultiLineBlock) {
                    multiLineComments++;
                    isMultiLineBlock = true;
                } else if(multilineCommentEnd != null && line.endsWith(multilineCommentEnd) && isMultiLineBlock) {
                    isMultiLineBlock = false;
                } else if (!isMultiLineBlock){
                    codeLines++;
                }
            }
        }

        return "Blank: " + emptyLines +
                "\nComments: " + (singleLineComments + multiLineComments) +
                "\nCode: " + codeLines +
                "\nTotal: " + totalLines;
    }
}
