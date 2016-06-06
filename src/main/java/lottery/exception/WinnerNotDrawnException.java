package lottery.exception;

/**
 * WinnerNotDrawnException
 *
 * @author Tong Zhou
 */
public class WinnerNotDrawnException extends BaseException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see BaseException#WinnerNotDrawnException(String)
     */
    public WinnerNotDrawnException(String msg)
    {
        super(msg);
    }

}
