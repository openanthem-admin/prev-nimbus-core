package com.anthem.oss.nimbus.core.config;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anthem.oss.nimbus.core.bpm.activiti.ActivitiDAO;
import com.anthem.oss.nimbus.core.bpm.activiti.ActivitiGateway;
import com.anthem.oss.nimbus.core.domain.command.execution.CommandMessageConverter;
import com.anthem.oss.nimbus.core.domain.command.execution.CommandTransactionInterceptor;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorGet;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorLookup;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorNav;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorNew;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorProcess;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorSearch;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionExecutorUpdate;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionProcessExecutorDelete;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultActionProcessExecutorReplace;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultBehaviorExecutorConfig;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultBehaviorExecutorNav;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultBehaviorExecutorSave;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultEventExecutorPost;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultEventExecutorPre;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultProcessGateway;
import com.anthem.oss.nimbus.core.domain.command.execution.DefaultRuleBasedTaskRouter;
import com.anthem.oss.nimbus.core.domain.command.execution.HierarchyMatchBasedBeanFinder;
import com.anthem.oss.nimbus.core.domain.command.execution.ParamCodeValueProvider;
import com.anthem.oss.nimbus.core.domain.config.builder.DomainConfigBuilder;
import com.anthem.oss.nimbus.core.domain.model.state.builder.PageNavigationInitializer;
import com.anthem.oss.nimbus.core.domain.model.state.builder.QuadModelBuilder;
import com.anthem.oss.nimbus.core.domain.model.state.repo.db.ModelRepositoryFactory;

/**
 * @author Sandeep Mantha
 *
 */

@Configuration
public class DefaultCoreExecutorConfig {
	
	//domain command execution - defaults
	@Bean
	public CommandMessageConverter commandMessageConverter(){
		return new CommandMessageConverter();
	}
	
	@Bean
	public CommandTransactionInterceptor commandTransactionInterceptor(){
		return new CommandTransactionInterceptor();
	}
	
	@Bean(name="default._get$execute")
	public DefaultActionExecutorGet defaultActionExecutorGet(ModelRepositoryFactory repoFactory, DomainConfigBuilder domainConfigApi){
		return new DefaultActionExecutorGet(repoFactory,domainConfigApi);
	}
	
	@Bean(name="default._lookup$execute")
	public DefaultActionExecutorLookup defaultActionExecutorLookup(){
		return new DefaultActionExecutorLookup();
	}
	
	@Bean(name="default._nav$execute")
	public DefaultActionExecutorNav defaultActionExecutorNav(ActivitiDAO platformProcessDAO, RuntimeService runtimeService,
			TaskService taskService, PageNavigationInitializer navigationStateHelper){
		return new DefaultActionExecutorNav(platformProcessDAO,runtimeService,taskService,navigationStateHelper);
	}
	
	@Bean(name="default._new$execute")
	public DefaultActionExecutorNew defaultActionExecutorNew(@Qualifier("default.quadModelBuilder") QuadModelBuilder quadModelBuilder, DomainConfigBuilder domainConfigApi){
		return new DefaultActionExecutorNew(quadModelBuilder,domainConfigApi);
	}
	
	@Bean(name="default._process$execute")
	public DefaultActionExecutorProcess defaultActionExecutorProcess(ActivitiGateway activitiProcessGateway,
			HierarchyMatchBasedBeanFinder hierarchyMatchBeanLoader){
		return new DefaultActionExecutorProcess(activitiProcessGateway,hierarchyMatchBeanLoader);
	}
	
	@Bean(name="default._search$execute")
	public DefaultActionExecutorSearch defaultActionExecutorSearch(ModelRepositoryFactory repFactory, DomainConfigBuilder domainConfigApi,
			CommandMessageConverter converter){
		return new DefaultActionExecutorSearch(repFactory,domainConfigApi,converter);
	}
	
	@Bean(name="default._update$execute")
	public DefaultActionExecutorUpdate defaultActionExecutorUpdate(){
		return new DefaultActionExecutorUpdate();
	}
	
	@Bean(name="default._delete$execute")
	public DefaultActionProcessExecutorDelete defaultActionProcessExecutorDelete(ModelRepositoryFactory repoFactory, DomainConfigBuilder domainConfigApi){
		return new DefaultActionProcessExecutorDelete(repoFactory,domainConfigApi);
	}
	
	@Bean(name="default._replace$execute")
	public DefaultActionProcessExecutorReplace defaultActionProcessExecutorReplace(QuadModelBuilder qBuilder, ModelRepositoryFactory repoFactory,
			DomainConfigBuilder domainConfigApi){
		return new DefaultActionProcessExecutorReplace(qBuilder,repoFactory,domainConfigApi);
	}
	
	@Bean(name="default.$config")
	public DefaultBehaviorExecutorConfig defaultBehaviorExecutorConfig(){
		return new DefaultBehaviorExecutorConfig();
	}
	
	@Bean(name="default.$nav")
	public DefaultBehaviorExecutorNav defaultBehaviorExecutorNav(ActivitiDAO platformProcessDAO, RuntimeService runtimeService,
			TaskService taskService, PageNavigationInitializer navigationStateHelper) {
		return new DefaultBehaviorExecutorNav(platformProcessDAO,runtimeService,taskService,navigationStateHelper);
	}
	
	@Bean(name="default.$save")
	public DefaultBehaviorExecutorSave defaultBehaviorExecutorSave(){
		return new DefaultBehaviorExecutorSave();
	}
	
//		//TODO - revisit if this bean is needed or next bean default.e_pre is needed.
	@Bean(name="default.e_post")
	public DefaultEventExecutorPost defaultEventExecutorPost(){
		return new DefaultEventExecutorPost();
	}
	
	@Bean(name="default.e_pre")
	public DefaultEventExecutorPre defaultEventExecutorPre(){
		return new DefaultEventExecutorPre();
	}
	
	@Bean
	public HierarchyMatchBasedBeanFinder hierarchyMatchBasedBeanFinder(RepositoryService repositoryService){
		return new HierarchyMatchBasedBeanFinder(repositoryService);
	}
	
	@Bean(name="default.processGateway")
	public DefaultProcessGateway defaultProcessGateway(HierarchyMatchBasedBeanFinder hierarchyMatchBasedBeanFinder){
		return new DefaultProcessGateway(hierarchyMatchBasedBeanFinder);
	}
	
	@Bean(name="ruleBasedTaskRouter")
	public DefaultRuleBasedTaskRouter defaultRuleBasedTaskRouter(){
		return new DefaultRuleBasedTaskRouter();
	}
	
	
	@Bean
	public ParamCodeValueProvider paramCodeValueProvider(DefaultActionExecutorSearch searchExecutor){
		return new ParamCodeValueProvider(searchExecutor);
	}
}