package lottery.exception;

/**
 * BaseException
 *
 * @author Tong Zhou
 */
public class BaseException extends Exception
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see Exception#BaseException(String)
     */
    public BaseException(String msg)
    {
        super(msg);
    }
}
