package lottery.exception;

/**
 * UnknowCommandExcpetion
 *
 * @author Tong Zhou
 */
public class UnknowCommandExcpetion extends BaseException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @see BaseException#UnknowCommandExcpetion(String)
     */
    public UnknowCommandExcpetion(String msg)
    {
        super(msg);
    }

}
