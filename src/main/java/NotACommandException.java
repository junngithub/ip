public class NotACommandException extends Exception {
    public NotACommandException(){
        super();
    }

    @Override
    public String toString() {
        return "Apologies, I am unable to assist with that request.";
    }
}
