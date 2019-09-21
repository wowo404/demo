package org.liu.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.liu.drools.listener.CustomAgendaEventListener;
import org.liu.drools.listener.CustomProcessEventListener;
import org.liu.drools.listener.CustomRuleRuntimeEventListener;
import org.liu.exception.ServiceException;
import org.liu.pojo.Rule;
import org.liu.pojo.RulePublic;
import org.liu.pojo.User;
import org.liu.pojo.UserRuleRecord;
import org.liu.pojo.vo.ListUserReq;
import org.liu.repository.RulePublicRepository;
import org.liu.repository.RuleRepository;
import org.liu.repository.UserRepository;
import org.liu.repository.UserRuleRecordRepository;
import org.liu.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRuleRecordRepository userRuleRecordRepository;
	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private RulePublicRepository rulePublicRepository;
	@Autowired
	private KieContainer kieContainer;
	@Value("${drools.url}")
	private String droolsUrl;
	@Value("${drools.userName}")
	private String droolsUserName;
	@Value("${drools.password}")
	private String droolsPassword;

	public User getByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	public void save(User user) {
		this.userRepository.save(user);
	}

	public Page<User> listAll(int pageNumber, int pageSize, String direction, String... properties) {
		Sort sort = new Sort(Direction.fromString(direction), properties);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, sort);
		return this.userRepository.findAll(pageRequest);
	}

	public Page<User> listByEmailAndDateRange(ListUserReq req) {
		Specification<User> spec = new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// root = query.from(User.class);//什么傻逼教程，加上这行代码导致自动添加了cross
				// join
				Predicate p = cb.conjunction();
				if (StringUtils.isNotBlank(req.getUserName())) {
					Expression<String> userNamePath = root.get("userName").as(String.class);
					p.getExpressions().add(cb.like(userNamePath, "%" + req.getUserName() + "%"));
				}
				if (StringUtils.isNotBlank(req.getEmail())) {
					Path<String> emailPath = root.get("email");
					p.getExpressions().add(cb.like(emailPath, "%" + req.getEmail() + "%"));
				}
				if (StringUtils.isNotBlank(req.getStartDate())) {
					Path<Date> createTimePath = root.get("createTime");
					p.getExpressions().add(cb.greaterThanOrEqualTo(createTimePath,
							MyDateUtil.parseStringToDateOne(req.getStartDate() + " 00:00:00")));
				}
				if (StringUtils.isNotBlank(req.getEndDate())) {
					Path<Date> createTimePath = root.get("createTime");
					p.getExpressions().add(cb.lessThanOrEqualTo(createTimePath,
							MyDateUtil.parseStringToDateOne(req.getEndDate() + " 23:59:59")));
				}
				return p;
			}
		};

		Sort sort = new Sort(req.getDirection(), req.getProperties());
		PageRequest pageRequest = new PageRequest(req.getPageNumber(), req.getPageSize(), sort);
		return this.userRepository.findAll(spec, pageRequest);
	}

	public Page<User> listByExample(ListUserReq req) {
		User prob = new User();
		prob.setUserName(req.getUserName());
		Example<User> example = Example.of(prob, ExampleMatcher.matchingAny());
		Sort sort = new Sort(req.getDirection(), req.getProperties());
		PageRequest pageRequest = new PageRequest(req.getPageNumber(), req.getPageSize(), sort);
		return this.userRepository.findAll(example, pageRequest);
	}

	public List<User> listByCustomSql(String userName) {
		return this.userRepository.listByCustomSql(userName);
	}

	public List<User> listByHql(String userName) {
		return this.userRepository.listByHql(userName);
	}

	public void checkUser(User user) {
		KieSession kieSession = this.kieContainer.newKieSession("userSession");
		kieSession.addEventListener(new CustomAgendaEventListener());
		kieSession.addEventListener(new CustomProcessEventListener());
		kieSession.addEventListener(new CustomRuleRuntimeEventListener());
		kieSession.insert(user);
		kieSession.fireAllRules();
		List<UserRuleRecord> list = getUserRuleRecord(kieSession);
		this.userRuleRecordRepository.save(list);
		kieSession.dispose();
	}

	private List<UserRuleRecord> getUserRuleRecord(KieSession kieSession) {

		ObjectFilter filter = new ObjectFilter() {
			@Override
			public boolean accept(Object object) {
				if (UserRuleRecord.class.equals(object.getClass()))
					return true;
				if (UserRuleRecord.class.equals(object.getClass().getSuperclass()))
					return true;
				return false;
			}
		};

		List<UserRuleRecord> list = new ArrayList<>();
		for (FactHandle handle : kieSession.getFactHandles(filter)) {
			list.add((UserRuleRecord) kieSession.getObject(handle));
		}
		return list;
	}

	public void checkUserUseRemoteRule(User user) {
		KieServices kieServices = KieServices.Factory.get();
		KieRepository kieRepository = kieServices.getRepository();
		UrlResource urlResource = (UrlResource) kieServices.getResources().newUrlResource(droolsUrl);
		urlResource.setUsername(droolsUserName);
		urlResource.setPassword(droolsPassword);
		urlResource.setBasicAuthentication("enabled");
		try {
			InputStream is = urlResource.getInputStream();
			KieModule kieModule = kieRepository.addKieModule(kieServices.getResources().newInputStreamResource(is));
			KieContainer kc = kieServices.newKieContainer(kieModule.getReleaseId());
			KieSession kieSession = kc.newKieSession();
			kieSession.insert(user);
			int i = kieSession.fireAllRules();
			log.debug("执行了" + i + "条规则");
			List<UserRuleRecord> list = getUserRuleRecord(kieSession);
			this.userRuleRecordRepository.save(list);
			kieSession.dispose();
		} catch (IOException e) {
			log.error("获取远程drools规则时出错", e);
			throw new ServiceException("获取远程drools规则时出错", e);
		}
	}

	public void checkUserUseRuleString(User user) {
		// 从数据库查询出rule和rulePublic，拼接成完整drl文件内容
		List<Rule> ruleList = this.ruleRepository.findAll();
		List<RulePublic> rulePublicList = this.rulePublicRepository.findAll();
		// 把不同的类型的公共部分拼接起来（import,declare,global,function,query）
		StringBuilder publics = analysisRulePublic(rulePublicList);

		// 按不同的package拼接drl内容
		List<StringBuilder> drlList = generateDrlContent(ruleList, publics);

		KieHelper kh = new KieHelper();
		for (StringBuilder drl : drlList) {
			System.out.println(drl.toString());
			Resource resource = ResourceFactory.newByteArrayResource(drl.toString().getBytes());
			kh.addResource(resource, ResourceType.DRL);
		}
		KieSession kieSession = kh.build().newKieSession();

		kieSession.insert(user);
		int i = kieSession.fireAllRules();
		log.info("执行了" + i + "条规则");
		List<UserRuleRecord> list = getUserRuleRecord(kieSession);
		this.userRuleRecordRepository.save(list);
		kieSession.dispose();
	}

	private List<StringBuilder> generateDrlContent(List<Rule> ruleList, StringBuilder publics) {
		Map<String, List<Rule>> ruleMap = new HashMap<>();
		for (Rule rule : ruleList) {
			if (ruleMap.containsKey(rule.getRulePackage())) {
				List<Rule> samePkgRuleList = ruleMap.get(rule.getRulePackage());
				samePkgRuleList.add(rule);
				ruleMap.put(rule.getRulePackage(), samePkgRuleList);
			} else {
				List<Rule> samePkgRuleList = new ArrayList<>();
				samePkgRuleList.add(rule);
				ruleMap.put(rule.getRulePackage(), samePkgRuleList);
			}
		}
		List<StringBuilder> drlList = new ArrayList<>();// 最终的drl文件集合
		Set<Entry<String, List<Rule>>> entrySet = ruleMap.entrySet();
		for (Entry<String, List<Rule>> entry : entrySet) {
			String key = entry.getKey();
			List<Rule> value = entry.getValue();
			// 单个drl的rule集合
			StringBuilder rules = new StringBuilder();
			rules.append("package ").append(key).append(";").append(publics);
			for (Rule rule : value) {
				rules.append(rule.getContent().replaceAll("\r", " ")).append(";");
			}
			drlList.add(rules);
		}
		return drlList;
	}

	private StringBuilder analysisRulePublic(List<RulePublic> rulePublicList) {
		StringBuilder imports = new StringBuilder(), declares = new StringBuilder(), globals = new StringBuilder(),
				functions = new StringBuilder(), querys = new StringBuilder();
		for (RulePublic rulePublic : rulePublicList) {
			switch (rulePublic.getContentType()) {
			case "import":
				imports.append(rulePublic.getContent());
				break;
			case "declare":
				declares.append(rulePublic.getContent());
				break;
			case "global":
				globals.append(rulePublic.getContent());
				break;
			case "function":
				functions.append(rulePublic.getContent());
				break;
			case "query":
				querys.append(rulePublic.getContent());
				break;
			default:
				break;
			}
		}
		return imports.append(declares).append(globals).append(functions).append(querys);
	}

}
