package com.vivekgupta;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    void analyze_java_file() throws IOException, InvalidFileTypeException, UnsupportedFileTypeException {
        String inputFile = "./tests/test_files/JavaTest.java";
        String expectedOutput, output;

        expectedOutput = "Blank: 6\n" + "Comments: 4\n" + "Code: 8\n" + "Total: 22";

        Analyzer analyzer = new Analyzer(inputFile);

        output = analyzer.analyze();

        assertEquals(expectedOutput, output);
    }

    @Test
    void analyze_html_file() throws IOException, InvalidFileTypeException, UnsupportedFileTypeException {
        String inputFile = "./tests/test_files/htmltest.html";
        String expectedOutput, output;

        expectedOutput = "Blank: 4\n" + "Comments: 3\n" + "Code: 7\n" + "Total: 16";

        Analyzer analyzer = new Analyzer(inputFile);

        output = analyzer.analyze();

        assertEquals(expectedOutput, output);
    }

    @Test
    void analyze_python_file() throws IOException, InvalidFileTypeException, UnsupportedFileTypeException {
        String inputFile = "./tests/test_files/python_test.py";
        String expectedOutput, output;

        expectedOutput = "Blank: 1\n" + "Comments: 2\n" + "Code: 6\n" + "Total: 9";

        Analyzer analyzer = new Analyzer(inputFile);

        output = analyzer.analyze();

        assertEquals(expectedOutput, output);
    }

    @Test
    void  analyze_unsupported_file_type() throws IOException, InvalidFileTypeException, UnsupportedFileTypeException {
        String inputFile = "./tests/test_files/PhpTest.php";

        Analyzer analyzer = new Analyzer(inputFile);

        assertThrows(UnsupportedFileTypeException.class, analyzer::analyze);
    }
}