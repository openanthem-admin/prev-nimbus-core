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
package com.anthem.oss.nimbus.core.domain.model.state;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anthem.oss.nimbus.core.domain.model.config.ParamValue;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.model.config.ParamConfig;
import com.antheminc.oss.nimbus.domain.model.state.EntityStateAspectHandlers;
import com.antheminc.oss.nimbus.domain.model.state.ExecutionTxnContext;
import com.antheminc.oss.nimbus.domain.model.state.Notification;
import com.antheminc.oss.nimbus.domain.model.state.StateType;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.support.pojo.LockTemplate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import test.com.anthem.nimbus.platform.utils.PathUtils;

/**
 * A mock Param implementation intended for testing purposes.
 * 
 * @author Tony Lopez (AF42192)
 *
 */
@Getter
@Setter
public class MockParam implements Param<Object> {

	private boolean active = true;
	private EntityStateAspectHandlers aspectHandlers = null;
	private String beanPath = "";
	private ParamConfig<Object> config = new MockParamConfig();
	private List<MappedParam<?, Object>> consumers = new ArrayList<>();
	@Setter(AccessLevel.NONE)
	private List<Notification<Object>> emittedNotifications = new ArrayList<>();;
	private boolean enabled = true;
	private List<MappedParam<?, Object>> eventSubscribers = new ArrayList<>();
	private LockTemplate lockTemplate = null;
	private Message message = null;
	private Map<String, Model<Object>> modelMap = new HashMap<>();
	private Map<String, Param<Object>> paramMap = new HashMap<>();
	private Model<Object> parentModel = null;
	private String path = "";
	private PropertyDescriptor propertyDescriptor = null;
	private Object state = null;
	private boolean stateInitialized = false;
	private StateType type = null;
	private List<ParamValue> values = null;
	private boolean visible = true;

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param#activate()
	 */
	@Override
	public void activate() {
		this.setActive(true);
		this.setEnabled(true);
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param#deactivate()
	 */
	@Override
	public void deactivate() {
		this.setActive(false);
		this.setEnabled(false);
		this.setVisible(false);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.Notification.Producer#deregisterConsumer(com.anthem.oss.nimbus.core.domain.model.state.EntityState.MappedParam)
	 */
	@Override
	public boolean deregisterConsumer(MappedParam<?, Object> consumer) {
		return this.consumers.remove(consumer);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.Notification.Dispatcher#emitNotification(com.anthem.oss.nimbus.core.domain.model.state.Notification)
	 */
	@Override
	public void emitNotification(Notification<Object> event) {
		this.emittedNotifications.add(event);
	}

	public void putParam(Param<Object> param, String path) {
		this.paramMap.put(path, param);
	}
	
	public void putModel(Model<Object> model, String path) {
		this.modelMap.put(path, model);
	}
	
	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#findModelByPath(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S> Model<S> findModelByPath(String path) {
		return (Model<S>) this.modelMap.get(path);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#findModelByPath(java.lang.String[])
	 */
	@Override
	public <S> Model<S> findModelByPath(String[] pathArr) {
		return PathUtils.findFirstByPath(pathArr, k -> this.findModelByPath(k));
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#findParamByPath(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <P> Param<P> findParamByPath(String path) {
		return (Param<P>) this.paramMap.get(path);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#findParamByPath(java.lang.String[])
	 */
	@Override
	public <P> Param<P> findParamByPath(String[] pathArr) {
		return PathUtils.findFirstByPath(pathArr, k -> this.findParamByPath(k));
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#fireRules()
	 */
	@Override
	public void fireRules() {
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param#getLeafState()
	 */
	@Override
	public Object getLeafState() {
		return this.state;
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#getRootDomain()
	 */
	@Override
	public Model<?> getRootDomain() {
		if (null == getParentModel()) {
			return null;
		}
		return getParentModel().getRootDomain();
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#getRootExecution()
	 */
	@Override
	public ExecutionModel<?> getRootExecution() {
		if (null == getParentModel()) {
			return null;
		}
		return getParentModel().getRootExecution();
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#initSetup()
	 */
	@Override
	public void initSetup() {
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState#initState()
	 */
	@Override
	public void initState() {
		this.setStateInitialized(true);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.Notification.Producer#registerConsumer(com.anthem.oss.nimbus.core.domain.model.state.EntityState.MappedParam)
	 */
	@Override
	public void registerConsumer(MappedParam<?, Object> consumer) {
		this.consumers.add(consumer);
	}

	/* (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.State#setState(java.lang.Object)
	 */
	@Override
	public Action setState(Object state) {
		this.state = state;
		this.setStateInitialized(true);
		return Action.DEFAULT;
	}

	/*
	 * (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param#onStateLoadEvent()
	 */
	@Override
	public void onStateLoadEvent() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param#onStateChangeEvent(com.anthem.oss.nimbus.core.domain.model.state.ExecutionTxnContext, com.anthem.oss.nimbus.core.domain.command.Action)
	 */
	@Override
	public void onStateChangeEvent(ExecutionTxnContext txnCtx, Action a) {
		
	}
}