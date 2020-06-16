package chess.base.exceptions;

public class InvalidPromotionException extends IllegalArgumentException {

    /**
     * Warns the user that pawn you've moved cannot be promoted until your pawn reached enemy base
     */
    public static InvalidPromotionException notPawn() {
        return new InvalidPromotionException("Your cannot promote piece other than Pawn!");
    }

    /**
     * Warns the user that pawn you've moved cannot be promoted until your pawn reached enemy base
     */
    public static InvalidPromotionException notPromotable() {
        return new InvalidPromotionException("Your pawn is far away from promotion!");
    }

    /**
     * Warns the user that pawn you've moved must be promoted immediately after reached enemy base
     */
    public static InvalidPromotionException notPromotedYet() {
        return new InvalidPromotionException("Your pawn must be promoted to higher-tier pieces!");
    }

    /**
     * The notation coordinate which do not follow promotion notation format
     * [A-H][1-8]-[A-H][1-8][RNBQ] (A-H for column, 1-8 for row, RNBQ for rank notation)
     *
     * @param notation notation which invalids promotion notation formatting
     */
    public static InvalidPromotionException notation(String notation) {
        return new InvalidPromotionException(String.format("Invalid promotion: Promotion initial must be R, N, B, or Q (uppercase)! (notation value: %s)", notation));
    }

    /**
     * The subject or detailed message that leads to invalid promotion
     *
     * @param s detail message about invalid promotion
     */
    public InvalidPromotionException(String s) {
        super(s);
    }

    /**
     * The throwable which leads to invalid promotion
     *
     * @param t error/exception caused to throw
     */
    public InvalidPromotionException(Throwable t) {
        super(t);
    }

    /**
     * The exception where caused by invalid promotion character
     */
    public InvalidPromotionException() {
        super("Invalid promotion: Promotion initial must be R, N, B, or Q (must be uppercase!)");
    }
}
