package lottery.exception;

/**
 * NoAvailableSpotException
 *
 * @author Tong Zhou
 */
public class NoAvailableSpotException extends BaseException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see BaseException#NoAvailableSpotException(String)
     */
    public NoAvailableSpotException(String msg)
    {
        super(msg);
    }
}
