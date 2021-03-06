package com.github.sansp00.maven.sonarqube.gateway;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.github.sansp00.maven.sonarqube.gateway.model.Analysis;
import com.github.sansp00.maven.sonarqube.gateway.model.Category;
import com.github.sansp00.maven.sonarqube.gateway.model.Component;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentShowResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Event;
import com.github.sansp00.maven.sonarqube.gateway.model.History;
import com.github.sansp00.maven.sonarqube.gateway.model.Issue;
import com.github.sansp00.maven.sonarqube.gateway.model.IssuesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Measure;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureComponentResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureSearchHistoryResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Paging;
import com.github.sansp00.maven.sonarqube.gateway.model.Period;
import com.github.sansp00.maven.sonarqube.gateway.model.Project;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectAnalysesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Visibility;
import com.github.sansp00.maven.sonarqube.gateway.model.converter.DateTimeConverter;

public class GatewayModelBuilder {
	public static final String PROJECT_ID = "id";
	public static final String PROJECT_KEY = "com.github.sonarqube:master";
	public static final String PROJECT_NAME = "SonarQube Maven Client";
	public static final String PROJECT_BRANCH = "master";

	public static final String COMPONENT_ID = "id";
	public static final String COMPONENT_KEY = "com.github.sonarqube:master";
	public static final String COMPONENT_NAME = "SonarQube Maven Client";
	public static final String COMPONENT_BRANCH = "master";

	public static final String KEYWORD = "keyword";

	public static final String PROJECT_ORGANISATION = "Personnal";
	public static final String PROJECT_QUALIFIER = "maven plugin";
	public static final Visibility PROJECT_VISIBILITY = Visibility.PUBLIC;

	public static final String LANGUAGE_NAME = "java";

	public static final String QUALITY_PROFILE_NAME = "myQualityProfile";

	public static final Category EVENT_CATEGORY = Category.QUALITY_PROFILE;
	public static final String EVENT_NAME = "myEventName";
	public static final String EVENT_KEY = "myEventKey";
	public static final String EVENT_DESCRIPTION = "myEventDescription";

	public static final String ANALISYS_KEY = "myAnalisysKey";
	// public static final String ANALISYS_DATE = "2016-12-12T17:12:45+0100";
//	public static final LocalDateTime ANALISYS_DATE = LocalDateTime.parse("2016-12-12T17:12:45+0100",
//			DateTimeConverter.DATE_TIME_FORMATTER);
//	public static final LocalDateTime LEAK_PERIOD_DATE = LocalDateTime.parse("2016-12-10T17:12:45+0100",
//			DateTimeConverter.DATE_TIME_FORMATTER);
//
//	public static final LocalDateTime HISTORY_DATE = LocalDateTime.parse("2016-12-10T17:12:45+0100",
//			DateTimeConverter.DATE_TIME_FORMATTER);
	public static final LocalDateTime ANALISYS_DATE = LocalDateTime.parse("2016-12-12T17:12:45",
			DateTimeConverter.DATE_TIME_FORMATTER);
	public static final LocalDateTime LEAK_PERIOD_DATE = LocalDateTime.parse("2016-12-10T17:12:45",
			DateTimeConverter.DATE_TIME_FORMATTER);

	public static final LocalDateTime HISTORY_DATE = LocalDateTime.parse("2016-12-10T17:12:45",
			DateTimeConverter.DATE_TIME_FORMATTER);

	public static final String HISTORY_VALUE = "10";

	public static final String MEASURE_METRIC = "coverage";
	public static final List<String> MEASURE_METRIC_LIST = Arrays.asList("coverage");
	public static final String MEASURE_VALUE = "10";

	public static final int PROJECT_SEARCH_PAGE_INDEX = 0;
	public static final int PROJECT_SEARCH_PAGE_SIZE = 10;
	public static final int PROJECT_SEARCH_PAGE_TOTAL = 1;

	public static final int PROJECT_SEARCH_ANALYSES_PAGE_INDEX = 0;
	public static final int PROJECT_SEARCH_ANALYSES_PAGE_SIZE = 10;
	public static final int PROJECT_SEARCH_ANALYSES_PAGE_TOTAL = 1;

	public static final int COMPONENT_SEARCH_PAGE_INDEX = 0;
	public static final int COMPONENT_SEARCH_PAGE_SIZE = 10;
	public static final int COMPONENT_SEARCH_PAGE_TOTAL = 1;

	public static final int ISSUE_SEARCH_PAGE_INDEX = 0;
	public static final int ISSUE_SEARCH_PAGE_SIZE = 10;
	public static final int ISSUE_SEARCH_PAGE_TOTAL = 1;

	public static final String ISSUE_KEY = "AV1GDmj6NAVDjyrgWoNi";
	public static final String ISSUE_COMPONENT = "com.github.sonarqube:src/main/java/com/github/sansp00/maven/sonarqube/Assert.java";
	public static final String ISSUE_PROJECT = "com.github.sonarqube";
	public static final String ISSUE_RULE = "java:S2589";
	public static final String ISSUE_STATUS = "CONFIRMED";
	public static final String ISSUE_SEVERITY = "MAJOR";
	public static final String ISSUE_MESSAGE = "Remove this expression which always evaluates to \"true\"";
	public static final int ISSUE_LINE = 133;
	public static final String ISSUE_HASH = "53e44d43e22a4c58d3a711137fefad95";
	public static final String ISSUE_EFFORT = "10min";
	public static final String ISSUE_CREATION_DATE = "2017-07-15T13:39:53";
	public static final String ISSUE_UPDATE_DATE = "2017-07-15T13:39:53";
//	public static final String ISSUE_CREATION_DATE = "2017-07-15T13:39:53+0200";
//	public static final String ISSUE_UPDATE_DATE = "2017-07-15T13:39:53+0200";
	public static final String ISSUE_TYPE = "CODE_SMELL";

	public static final Integer PERIOD_INDEX = 1;
//	public static final LocalDateTime PERIOD_DATE = LocalDateTime.parse("2016-12-10T17:12:45+0100",
//			DateTimeConverter.DATE_TIME_FORMATTER);
	public static final LocalDateTime PERIOD_DATE = LocalDateTime.parse("2016-12-10T17:12:45",
			DateTimeConverter.DATE_TIME_FORMATTER);
	public static final String PERIOD_VALUE = "10";

	public static Project buildProject() {
		Project p = new Project();
		p.setKey(PROJECT_KEY);
		p.setName(PROJECT_NAME);
		p.setOrganization(PROJECT_ORGANISATION);
		p.setQualifier(PROJECT_QUALIFIER);
		p.setVisibility(PROJECT_VISIBILITY);
		return p;
	}

	public static Paging buildIssueSearchPaging() {
		Paging p = new Paging();
		p.setPageIndex(ISSUE_SEARCH_PAGE_INDEX);
		p.setPageSize(ISSUE_SEARCH_PAGE_SIZE);
		p.setTotal(ISSUE_SEARCH_PAGE_TOTAL);
		return p;
	}

	public static Paging buildComponentSearchPaging() {
		Paging p = new Paging();
		p.setPageIndex(COMPONENT_SEARCH_PAGE_INDEX);
		p.setPageSize(COMPONENT_SEARCH_PAGE_SIZE);
		p.setTotal(COMPONENT_SEARCH_PAGE_TOTAL);
		return p;
	}

	public static Paging buildProjectSearchPaging() {
		Paging p = new Paging();
		p.setPageIndex(PROJECT_SEARCH_PAGE_INDEX);
		p.setPageSize(PROJECT_SEARCH_PAGE_SIZE);
		p.setTotal(PROJECT_SEARCH_PAGE_TOTAL);
		return p;

	}

	public static Paging buildProjectAnalysesSearchPaging() {
		Paging p = new Paging();
		p.setPageIndex(PROJECT_SEARCH_ANALYSES_PAGE_INDEX);
		p.setPageSize(PROJECT_SEARCH_ANALYSES_PAGE_SIZE);
		p.setTotal(PROJECT_SEARCH_ANALYSES_PAGE_TOTAL);
		return p;

	}

	public static ProjectSearchResponse buildProjectSearch() {
		ProjectSearchResponse ps = new ProjectSearchResponse();
		ps.getProjects().add(buildProject());
		ps.setPaging(buildProjectSearchPaging());

		return ps;
	}

	public static Event buildEvent() {
		Event e = new Event();
		e.setCategory(EVENT_CATEGORY);
		e.setKey(EVENT_KEY);
		e.setName(EVENT_NAME);
		e.setDescription(EVENT_DESCRIPTION);
		return e;
	}

	public static Analysis buildAnalysis() {
		Analysis a = new Analysis();
		a.getEvents().add(buildEvent());
		a.setKey(ANALISYS_KEY);
		a.setDate(ANALISYS_DATE);
		return a;
	}

	public static IssuesSearchResponse buildIssuesSearch() {
		IssuesSearchResponse is = new IssuesSearchResponse();
		is.getComponents().add(buildComponent());
		is.getIssues().add(buildIssue());
		is.setPaging(buildIssueSearchPaging());
		return is;
	}

	private static Issue buildIssue() {
		Issue i = new Issue();
		i.setKey(ISSUE_KEY);
		i.setComponent(ISSUE_COMPONENT);
		i.setProject(ISSUE_PROJECT);
		i.setRule(ISSUE_RULE);
		i.setStatus(ISSUE_STATUS);
		i.setSeverity(ISSUE_SEVERITY);
		i.setMessage(ISSUE_MESSAGE);
		i.setLine(ISSUE_LINE);
		i.setHash(ISSUE_HASH);
		i.setEffort(ISSUE_EFFORT);
		i.setCreationDate(ISSUE_CREATION_DATE);
		i.setUpdateDate(ISSUE_UPDATE_DATE);
		i.setType(ISSUE_TYPE);
		return i;
	}

	public static ComponentShowResponse buildComponentShow() {
		ComponentShowResponse cs = new ComponentShowResponse();
		cs.setComponent(buildComponent());
		return cs;
	}

	public static ComponentSearchResponse buildComponentSearch() {
		ComponentSearchResponse cs = new ComponentSearchResponse();
		cs.setPaging(buildComponentSearchPaging());
		cs.getComponents().add(buildComponent());
		return cs;
	}

	public static ProjectAnalysesSearchResponse buildProjectAnalysesSearch() {
		ProjectAnalysesSearchResponse pas = new ProjectAnalysesSearchResponse();
		pas.setPaging(buildProjectAnalysesSearchPaging());
		pas.getAnalyses().add(buildAnalysis());
		return pas;
	}

	public static MeasureSearchHistoryResponse buildMeasureSearchHistoryResponse() {
		MeasureSearchHistoryResponse msh = new MeasureSearchHistoryResponse();
		msh.getMeasures().add(buildMeasure());
		msh.setPaging(buildPaging());
		return msh;
	}

	public static MeasureComponentResponse buildMeasureComponentResponse() {
		MeasureComponentResponse mc = new MeasureComponentResponse();
		mc.setComponent(buildComponent());
		mc.getComponent().setMeasures(Arrays.asList(buildMeasure()));
		return mc;
	}

	public static Component buildComponent() {
		Component c = new Component();
		c.setId(COMPONENT_ID);
		c.setKey(COMPONENT_KEY);
		c.setLanguage(LANGUAGE_NAME);
		c.setLastAnalysisDate(ANALISYS_DATE);
		c.setLeakPeriodDate(LEAK_PERIOD_DATE);
		c.setName(COMPONENT_NAME);
		return c;
	}

	public static Measure buildMeasure() {
		Measure m = new Measure();
		m.setMetric(MEASURE_METRIC);
		m.setValue(MEASURE_VALUE);
		return m;
	}

	public static History buildHistory() {
		History h = new History();
		h.setDate(HISTORY_DATE);
		h.setValue(HISTORY_VALUE);
		return h;
	}

	public static Period buildPeriod() {
		Period p = new Period();
		p.setIndex(PERIOD_INDEX);
		p.setDate(PERIOD_DATE);
		p.setValue(PERIOD_VALUE);
		return p;
	}

	public static Paging buildPaging() {
		Paging p = new Paging();
		p.setPageIndex(1);
		p.setPageSize(100);
		p.setTotal(1);
		return p;
	}
}
