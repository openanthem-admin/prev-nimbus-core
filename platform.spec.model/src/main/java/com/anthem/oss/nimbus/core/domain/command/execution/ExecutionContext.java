/**
 *  Copyright 2016-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.anthem.oss.nimbus.core.domain.command.execution;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.anthem.oss.nimbus.core.domain.command.Command;
import com.anthem.oss.nimbus.core.domain.command.CommandMessage;
import com.anthem.oss.nimbus.core.domain.model.state.EntityState.ExecutionModel;
import com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param;
import com.anthem.oss.nimbus.core.domain.model.state.QuadModel;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Soham Chakravarti
 *
 */
@Getter @Setter
public class ExecutionContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private final CommandMessage commandMessage;
	
	private QuadModel<?, ?> quadModel;
	
	public ExecutionContext(Command command) {
		this(new CommandMessage(command, null));
	}
	
	public ExecutionContext(CommandMessage commandMessage) {
		this.commandMessage = commandMessage;
	}
		
	public ExecutionContext(CommandMessage commandMessage, QuadModel<?, ?> quadModel) {
		this(commandMessage);
		setQuadModel(quadModel);
	}
	
	public String getId() {
		return getCommandMessage().getCommand().getRootDomainUri();
	}
	
	public boolean equalsId(Command cmd) {
		return StringUtils.equals(getId(), cmd.getRootDomainUri());
	}
	
	public ExecutionModel<?> getRootModel(){
		return quadModel.getRoot();
	}
	
	public <P> Param<P> findParamByPath(String path) {
		return getRootModel().findParamByPath(path);
	}
	
	public <P> P findStateByPath(String path) {
		return getRootModel().findStateByPath(path);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		
		if(!(other instanceof ExecutionContext))
			return false;
		
		ExecutionContext otherCtx = (ExecutionContext)other;
		
		String thisDomainRootUri = getId();
		String otherDomainRootUri = otherCtx.getId();
		
		return thisDomainRootUri.equals(otherDomainRootUri);
	}
	
	@Override
	public String toString() {
		return getId();
	}
}
