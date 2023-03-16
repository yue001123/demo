package org.example.redislimater.exception.user;


import org.example.redislimater.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author pangu
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
