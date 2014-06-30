package com.sefford.kor.responses;

/**
 * Interface that declares basic information for managing a response
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public interface ResponseInterface {
    /**
     * Returns if the Request was successful.
     * <p/>
     * This flag indicates if the request was or not successful independently of the result of the
     * network operation. While typically a successful request completes, there could be cases
     * where the request actually is not successful because of the data.
     *
     * @return TRUE if it was, FALSE otherwise
     */
    boolean isSuccess();

    /**
     * Returns if the request comes from the network or not.
     * <p/>
     * The basic implementation only discerns between network and non-network (cache), but extensions of
     * ResponseInterface may allow for a more fine-grained information about their sources.
     *
     * @return TRUE if the response comes from the network, FALSE othewise
     */
    boolean isFromNetwork();
}