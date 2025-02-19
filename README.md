

```sh
AI was used to generate the JavaDoc and JUnit tests in this project.

Adapted the following lines in Storage class from https://github.com/nus-cs2103-AY2425S2/ip/pull/469/files#:

File file = new File(syntaxPath);
boolean fileExists = file.exists();
try {
    if (!fileExists) {
        file.getParentFile().mkdirs();
        Files.createFile(Paths.get(syntaxPath)); 

Adapted the following lines in MainWindow class from https://stackoverflow.com/questions/21974415/how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem

new Timer(true).schedule(new TimerTask() {
    public void run () {
        Platform.exit();
    }
}, 1000);