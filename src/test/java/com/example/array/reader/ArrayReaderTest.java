package com.example.array.reader;

import com.example.array.exception.CustomArrayException;
import com.example.array.reader.impl.ArrayReaderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayReaderTest {

    private final ArrayReader reader = new ArrayReaderImpl();

    @Test
    void testReadLinesReturnsAllLines(@TempDir Path tempDir) throws IOException, CustomArrayException {
        // given
        Path file = tempDir.resolve("test.txt");
        Files.write(file, List.of("1; 2; 3", "4; 5; 6"));
        // when
        List<String> result = reader.readLines(file.toString());
        // then
        assertEquals(2, result.size());
        assertEquals("1; 2; 3", result.get(0));
    }

    @Test
    void testReadLinesThrowsOnMissingFile() {
        // given
        String nonExistent = "no/such/file.txt";
        // when
        // then
        assertThrows(CustomArrayException.class, () -> reader.readLines(nonExistent));
    }

    @Test
    void testReadLinesHandlesEmptyFile(@TempDir Path tempDir) throws IOException, CustomArrayException {
        // given
        Path file = tempDir.resolve("empty.txt");
        Files.createFile(file);
        // when
        List<String> result = reader.readLines(file.toString());
        // then
        assertTrue(result.isEmpty());
    }
}
