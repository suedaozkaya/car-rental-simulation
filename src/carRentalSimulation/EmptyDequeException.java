package carRentalSimulation;

public class EmptyDequeException extends RuntimeException{
    public EmptyDequeException(){
        this(null);
    }

    public EmptyDequeException(String message){
        super(message);
    }
}
