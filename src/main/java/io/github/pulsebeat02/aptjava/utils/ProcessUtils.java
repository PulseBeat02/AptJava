package io.github.pulsebeat02.aptjava.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class ProcessUtils {

  private ProcessUtils() {
    throw new AssertionError("This class cannot be instantiated");
  }

  public static List<String> getProcessOutput(final String... args) throws IOException {
    final List<String> lines = new ArrayList<>();
    final ProcessBuilder builder = new ProcessBuilder();
    builder.command(args);
    final Process process = builder.start();
    try (final BufferedReader reader = createBufferedReader(process)) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
      return lines;
    }
  }

  private static BufferedReader createBufferedReader(final Process process) {
    return new BufferedReader(new InputStreamReader(process.getInputStream()));
  }
}
