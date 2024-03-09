package io.github.pulsebeat02.aptjava;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(final String[] args) throws IOException, InterruptedException {

    if (args.length == 0) {
      System.out.println("Usage: java -jar aptjava.jar <path to executable> <path to directory>");
      System.exit(1);
    }

    final Path executable = Paths.get(args[0]);
    final Path directory = Paths.get(args.length == 1 ? System.getProperty("user.dir") : args[1]);

    new AptJava(executable, directory);
  }
}
