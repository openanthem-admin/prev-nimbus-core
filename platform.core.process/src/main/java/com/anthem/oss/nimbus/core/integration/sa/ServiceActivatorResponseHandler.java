/**
 * 
 */
package com.anthem.oss.nimbus.core.integration.sa;

import java.io.Serializable;

import com.anthem.nimbus.platform.core.process.api.ActivitiContext.ServiceActivatorContext;
import com.anthem.oss.nimbus.core.entity.AbstractEntity;

/**
 * @author AC67870
 *
 */
public interface ServiceActivatorResponseHandler<T> {

	public T handle(ServiceActivatorContext srvcActCtx) throws ServiceActivatorException;

}