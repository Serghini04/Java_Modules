Here’s a cleaner and more structured version of your instructions:  

---

# **Compilation**  

```bash
# Step 1: Compile Java source files and place the output in the "target" directory
javac -d target src/java/fr/42/printer/app/Main.java src/java/fr/42/printer/logic/BMPReader.java src/java/fr/42/printer/logic/ImageConverter.java

# Step 2: Create a JAR file from the compiled files
# c: Create a new JAR file
# f: Specify the output filename
# -C target .: Change directory to "target" and package all compiled files inside
jar cf target/ImagesToChar.jar -C target .
```

---

# **Execution**  

```bash
# Run the compiled program
java -cp target fr._42.printer.app.Main . 0 imgs/it.bmp
```

---

# **Running a JAR File**  

### **1️⃣ Running a Non-Executable JAR**  
If the JAR contains only libraries (no `Main-Class` specified in the manifest):  
```bash
java -cp target/ImagesToChar.jar fr._42.printer.app.Main . 0 imgs/it.bmp
```
Here, `-cp target/ImagesToChar.jar` sets the classpath to include the JAR.

---

### **2️⃣ Creating an Executable JAR**  
To run the JAR using `java -jar ImagesToChar.jar`, we need to define a **manifest file**.

#### **Creating a Manifest File (`manifest.txt`)**
```text
Main-Class: fr._42.printer.app.Main
```
> **Important:** Ensure the last line has an extra newline (`\n`).

#### **Packing the JAR with the Manifest**
```bash
jar cfm ImagesToChar.jar manifest.txt -C target .
```

---

### **3️⃣ Running the Executable JAR**  
Once the JAR is properly packaged, you can execute it as follows:  
```bash
java -jar ImagesToChar.jar . 0 imgs/it.bmp
```

---

This version improves clarity while keeping the steps well-organized. 🚀