package lottery.exception;

/**
 * MinParticipantsNotReachException
 *
 * @author Tong Zhou
 */
public class MinParticipantsNotReachException extends BaseException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see BaseException#MinParticipantsNotReachException(String)
     */
    public MinParticipantsNotReachException(String msg)
    {
        super(msg);
    }
}
