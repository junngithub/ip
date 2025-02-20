# **Icarus:** Your faithful companion

Have _you_ ever found yourself completing a task way too close to a deadline and wondered, "Wow, I wished I had something to remind me". Fret not, Icarus is here to help. 


# Why Icarus?
Icarus is:
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

Best of all, he is here whenever you need it.

# How to get started?
Download the latest release from [here](https://github.com/junngithub/ip/releases).


🌞  Don't fly too close to the Sun! Give Icarus a try today! 🚀

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