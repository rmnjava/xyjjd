package com.redstar.jjd.common;

/**
 * 
 * ClassName: ServiceException <br/>
 * Date: 2016年9月18日 下午4:14:58 <br/>
 * Description: 自定义业务异常 <br/>
 * 
 * @author lenovo
 * @version
 * @see
 */
public class ServiceException extends RuntimeException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3296780237518168008L;

    public ServiceException(String message) {
        super(message);
    }

}
