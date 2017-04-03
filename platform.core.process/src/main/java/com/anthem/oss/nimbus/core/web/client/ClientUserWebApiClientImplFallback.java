package com.anthem.oss.nimbus.core.web.client;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anthem.oss.nimbus.core.FrameworkRuntimeException;
import com.anthem.oss.nimbus.core.domain.command.execution.ValidationException;
import com.anthem.oss.nimbus.core.domain.model.state.EntityNotFoundException;
import com.anthem.oss.nimbus.core.entity.client.user.ClientUser;

/**
 * Created by AF13233 on 10/4/16.
 */
//==@Component
//==@Primary
 public   class ClientUserWebApiClientImplFallback implements ClientUserRepoWebAPIClient {
    @Override
    public ClientUser getUser(@PathVariable("loginName") String loginName) throws EntityNotFoundException {
        return null;
    }

    @Override
    public ClientUser getUsersByIDSSourceAndGuid(@RequestParam("source") String source, @RequestParam("idsGuid") String idsGuid) {
        return null;
    }

    @Override
    public void saveUser(@RequestBody ClientUser clientUser) throws EntityNotFoundException {

    }

    @Override
    public Long addClientUser(@PathVariable("clientCode") String clientCode, @RequestBody ClientUser clientUser) throws ValidationException, ValidationException {
        return null;
    }

    @Override
    public Page<ClientUser> getUsersByName(@PathVariable("clientCode") String clientCode, @RequestBody ClientUser clientUser) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteClientUser(@PathVariable("id") Long id) {

    }
    
    @Override
    public void deleteClientUser(String clientCode, ClientUser cu) throws FrameworkRuntimeException {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public Long editClientUser(String clientCode, ClientUser clientUser) throws FrameworkRuntimeException {
    	// TODO Auto-generated method stub
    	return null;
    }
    
    @Override
    public ClientUser getUserById(Long id) throws EntityNotFoundException {
    	// TODO Auto-generated method stub
    	return null;
    }

	@Override
	public Page<ClientUser> getClientUsersByNameOrAll(String code, ClientUser user) throws FrameworkRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
}
