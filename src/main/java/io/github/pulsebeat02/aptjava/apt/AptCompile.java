package io.github.pulsebeat02.aptjava.apt;

import io.github.pulsebeat02.aptjava.AptJava;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class AptCompile {

  private final AptJava apt;

  public AptCompile(final AptJava apt) {
    this.apt = apt;
  }

  public void compileDependencies() throws IOException, InterruptedException {
    final List<String> missing = new LddOutput(this.apt).getMissingDependencies();
    for (final String dependency : missing) {
      this.compileMissingDependency(dependency);
    }
  }

  private void compileMissingDependency(final String dependency)
      throws IOException, InterruptedException {
    final Path bin = this.apt.getDirectory();
    this.createBasicProcess("apt-get", "source", dependency);
    this.createBasicProcess("./configure", String.format("--prefix=%s", bin));
    this.createBasicProcess("make");
    this.createBasicProcess("make", "install");
  }

  private void createBasicProcess(final String... args) throws InterruptedException, IOException {
    final ProcessBuilder builder = new ProcessBuilder();
    builder.command(args);
    builder.inheritIO();
    final Process process = builder.start();
    if (process.waitFor() != 0) {
      throw new RuntimeException("Failed to execute command: " + String.join(" ", args));
    }
  }
}
