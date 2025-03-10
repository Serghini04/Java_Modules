# ImagesToChar - Image to ASCII Art Converter

This project provides a Java application that converts BMP images to ASCII art and displays them in the terminal with colored output.

## Description

ImagesToChar is a simple utility that reads a BMP image file and converts it to ASCII art representation. It replaces white and black pixels with colored spaces using JCDP library for colored terminal output. The application uses JCommander to process command-line arguments.

## Project Structure

```
ImagesToChar/
├── lib/                     # External library folder
│   ├── jcommander-2.0.jar   # JCommander library for command-line parsing
│   ├── JCDP-2.0.1.jar       # JCDP library for colored output
│   └── jansi-2.4.0.jar      # Jansi library (dependency for JCDP)
├── src/                     # Source files
│   └── java/
│       └── fr/_42/printer/  # Package structure
│           ├── app/
│           │   └── Main.java
│           └── logic/
│               ├── BMPReader.java
│               └── ImageConverter.java
├── target/                  # Compiled .class files and resources
│   ├── fr/_42/printer/...   # Compiled class files
│   ├── com/beust/...        # JCommander class files
│   ├── com/diogonunes/...   # JCDP class files
│   └── resources/
│       └── it.bmp           # Sample BMP image
└── README.txt               # This file
```

## Dependencies

This project uses the following external libraries:

1. **JCommander** - For processing command-line arguments
   - Version: 2.0
   - Website: https://jcommander.org/

2. **JCDP (Java Colored Debug Printer)** - For colored terminal output
   - Version: 2.0.1
   - GitHub: https://github.com/dialex/JCDP

3. **Jansi** - Dependency for JCDP to work on different platforms
   - Version: 2.4.0
   - Website: https://fusesource.github.io/jansi/

## Compilation and Execution

### Compilation

```bash
javac -cp "lib/jansi-2.4.0.jar:lib/JCDP-2.0.1.jar:lib/jcommander-2.0.jar" \
     -d target \
     src/java/fr/_42/printer/app/Main.java \
     src/java/fr/_42/printer/logic/BMPReader.java \
     src/java/fr/_42/printer/logic/ImageConverter.java
```

### Execution

```bash
java -cp target:lib/jcommander-2.0.jar:lib/jansi-2.4.0.jar:lib/JCDP-2.0.1.jar fr._42.printer.app.Main --white=RED --black=GREEN
```

## Including External Libraries

To include external libraries in the final JAR assembly:

1. **Creating the JAR with dependencies**

   ```bash
   jar -cfm images-to-chars-printer.jar MANIFEST.MF -C target/ . lib/jcommander-2.0.jar lib/JCDP-2.0.1.jar lib/jansi-2.4.0.jar
   ```

2. **MANIFEST.MF should contain**

   ```
   Manifest-Version: 1.0
   Main-Class: fr._42.printer.app.Main
   Class-Path: lib/jcommander-2.0.jar lib/JCDP-2.0.1.jar lib/jansi-2.4.0.jar
   ```

## Usage

The application accepts two command-line arguments:

- `--white=COLOR`: Specifies the color for white pixels in the image
- `--black=COLOR`: Specifies the color for black pixels in the image

Available colors: RED, GREEN, BLUE, YELLOW, MAGENTA, CYAN, WHITE, BLACK

Example:
```bash
java -jar images-to-chars-printer.jar --white=RED --black=GREEN
```

This will display the image with red spaces for white pixels and green spaces for black pixels.

## Implementation Notes

- BMPReader: Reads a BMP file and returns a BufferedImage
- ImageConverter: Converts the BufferedImage to ASCII art with colored output
- Main: Handles command-line arguments and orchestrates the conversion process