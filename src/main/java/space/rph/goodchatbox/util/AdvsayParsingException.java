package space.rph.goodchatbox.util;

public class AdvsayParsingException extends Exception {
    public String reason;
    public AdvsayParsingException(String reason) {
        this.reason = reason;
    }
}
