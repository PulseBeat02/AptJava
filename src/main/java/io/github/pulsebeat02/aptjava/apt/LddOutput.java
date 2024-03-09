package io.github.pulsebeat02.aptjava.apt;

import io.github.pulsebeat02.aptjava.AptJava;
import io.github.pulsebeat02.aptjava.utils.ProcessUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class LddOutput {

  private static final Pattern PATTERN;

  static {
    PATTERN = Pattern.compile(".+?(?==)");
  }

  private final Path executable;

  public LddOutput(final AptJava apt) {
    this.executable = apt.getExecutable();
  }

  public List<String> getMissingDependencies() throws IOException {
    final List<String> lines = ProcessUtils.getProcessOutput("ldd", this.executable.toString());
    return lines.stream()
        .filter(line -> line.contains("not found"))
        .map(PATTERN::matcher)
        .filter(Matcher::find)
        .map(line -> line.group().trim())
        .collect(Collectors.toList());
  }
}
