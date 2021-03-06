package com.matthewglover.tictactoe.consoleui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class IOTestHelper {
    public static final String CLEAR_LINE = "\033[H\033[2J";
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);
    private InputStream inputStream = new ByteArrayInputStream("".getBytes());

    static String removeClearLine(String line) {
        return line.replace(CLEAR_LINE, "");
    }

    public ByteArrayOutputStream getOutContent() {
        return outContent;
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String[] getOutContentAsLines() {
        return getOutContentString().split("\\n");
    }

    public String getOutContentString() {
        return getOutContent().toString();
    }

    public void setInputStream(String input) {
        inputStream = new ByteArrayInputStream(input.getBytes());
    }

    public String getLastLineOfOutput() {
        String[] lines = getOutContentAsLines();
        return lines[lines.length - 1];
    }
}
