package io.github.pulsebeat02.aptjava;

import io.github.pulsebeat02.aptjava.apt.AptCompile;

import java.io.IOException;
import java.nio.file.Path;

public final class AptJava {

  private final Path executable;
  private final Path directory;

  public AptJava(final Path executable, final Path directory)
      throws IOException, InterruptedException {
    this.executable = executable;
    this.directory = directory;
    this.compileDependencies();
  }

  public void compileDependencies() throws IOException, InterruptedException {
    new AptCompile(this).compileDependencies();
  }

  public Path getExecutable() {
    return this.executable;
  }

  public Path getDirectory() {
    return this.directory;
  }
}
