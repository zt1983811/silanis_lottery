package lottery.exception;

/**
 * DrawFinishedException
 *
 * @author Tong Zhou
 */
public class DrawFinishedException extends BaseException
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see BaseException#DrawFinishedException(String)
     */
    public DrawFinishedException(String msg)
    {
        super(msg);
    }
}
