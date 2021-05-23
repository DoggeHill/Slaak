package hyll.sk.uniza.helpers;


/**
 * This exception is thrown when a message extending AbstractmMessage is being created
 * @author patri
 */
public class DemotedMessageException extends Exception{
    public DemotedMessageException(String errorMessage){
        super(errorMessage);
    }
}
