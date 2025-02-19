
```sh
AI was used to generate the JavaDoc in this project.

Adapted the following lines in Storage class from https://github.com/nus-cs2103-AY2425S2/ip/pull/469/files#:

File file = new File(syntaxPath);
boolean fileExists = file.exists();
try {
    if (!fileExists) {
        file.getParentFile().mkdirs();
        Files.createFile(Paths.get(syntaxPath)); 
