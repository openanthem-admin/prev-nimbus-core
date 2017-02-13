/**
 * 
 */
package com.anthem.oss.nimbus.core.domain.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;

import com.anthem.oss.nimbus.core.domain.command.Action;
import com.anthem.oss.nimbus.core.domain.model.config.ActionExecuteConfig;
import com.anthem.oss.nimbus.core.entity.Findable;
import com.anthem.oss.nimbus.core.util.CollectionsTemplate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@ToString @RequiredArgsConstructor
public class DomainConfig implements Findable<String> {
	
	@Getter final private String alias;
	
	@Getter @Setter private List<ActionExecuteConfig<?, ?>> actionExecuteConfigs;
	

	@Transient @JsonIgnore
	private final transient CollectionsTemplate<List<ActionExecuteConfig<?, ?>>, ActionExecuteConfig<?, ?>> template = CollectionsTemplate
			.array(() -> getActionExecuteConfigs(), (abc) -> setActionExecuteConfigs(abc));
	
	
	public CollectionsTemplate<List<ActionExecuteConfig<?, ?>>, ActionExecuteConfig<?, ?>> templateActionConfigs() {
		return template;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<Action> actionsNotConfigured() {
		ArrayList<Action> ncActions = new ArrayList<>(Arrays.asList(Action.values()));
		
		if(templateActionConfigs().isNullOrEmpty()) return ncActions;
		
		getActionExecuteConfigs().forEach((aec) -> ncActions.remove(aec.getAction()));
		return ncActions;
	}
	
	@Override
	public boolean isFound(String alias) {
		boolean eq = StringUtils.equals(this.getAlias(), alias);
		return eq;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof DomainConfig))
			return false;

		DomainConfig other = (DomainConfig) obj;

		boolean eq = isFound(other.getAlias());
		return eq;
	}
	
}