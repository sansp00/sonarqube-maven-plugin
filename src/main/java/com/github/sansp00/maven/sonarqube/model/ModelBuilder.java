package com.github.sansp00.maven.sonarqube.model;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.github.sansp00.maven.sonarqube.gateway.model.Measure;
import com.github.sansp00.maven.sonarqube.gateway.model.Period;

public class ModelBuilder {

	public static List<Component> buildComponents(
			final List<com.github.sansp00.maven.sonarqube.gateway.model.Component> gatewayComponents,
			final Map<String, List<Measure>> gatewayMeasuresMap, final Map<Integer, Period> periodMap,
			final String baseUrl, final LocalDate from, final LocalDate to, final LocalDateTime extraction) {
		final List<Component> components = new ArrayList<>();
		final Integer periodsIndex = 1;

		for (com.github.sansp00.maven.sonarqube.gateway.model.Component gatewayComponent : gatewayComponents) {
			final Component component = buildComponent(baseUrl, extraction, gatewayComponent);
			component.setHistoryDateRange(buildDateRange(from, to));
			component.setNutshellMeasures(buildNutshellMeasures(gatewayComponent.getMeasures(), periodsIndex));
			component.setHistoryMeasures(buildHistoryMeasures(gatewayMeasuresMap.get(gatewayComponent.getKey())));
			components.add(component);
		}
		return components;
	}

	public static DateRange buildDateRange(final LocalDate from, final LocalDate to) {
		final DateRange dateRange = new DateRange();
		dateRange.setFrom(from);
		dateRange.setTo(to);
		return dateRange;
	}

	public static Component buildComponent(final String baseUrl, final LocalDateTime extraction,
			final com.github.sansp00.maven.sonarqube.gateway.model.Component gatewayComponent) {
		final Component component = new Component();
		component.setKey(gatewayComponent.getKey());
		component.setName(gatewayComponent.getName());
		component.setExtractionDate(extraction);
		component.setUri(buildProjectUrl(baseUrl, gatewayComponent.getKey()));
		return component;
	}

	public static String buildProjectUrl(final String baseUrl, final String projectKey) {
		try {
			return baseUrl + "dashboard?id=" + URLEncoder.encode(projectKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return baseUrl + "dashboard?id=" + projectKey + " [NOT ENCODED !!]";
		}
	}

	public static NutshellMeasures buildNutshellMeasures(
			final List<com.github.sansp00.maven.sonarqube.gateway.model.Measure> measures, Integer periodsIndex) {
		final NutshellMeasures nutshellMeasures = new NutshellMeasures();
		initNutshellMeasure(nutshellMeasures);
		final Map<String, String> measuresMap = mapMeasures(measures, periodsIndex);

		if (measuresMap.containsKey("reliability_rating")) {
			nutshellMeasures.setReliabilityRating(
					Rating.valueOfDouble(NumberUtils.toDouble(measuresMap.get("reliability_rating"))));
		}

		if (measuresMap.containsKey("bugs")) {
			nutshellMeasures.setBugs(new BigInteger(measuresMap.get("bugs")));
		}

		if (measuresMap.containsKey("security_rating")) {
			nutshellMeasures
					.setSecurityRating(Rating.valueOfDouble(NumberUtils.toDouble(measuresMap.get("security_rating"))));
		}

		if (measuresMap.containsKey("vulnerabilities")) {
			nutshellMeasures.setVulnerabilities(new BigInteger(measuresMap.get("vulnerabilities")));
		}
		if (measuresMap.containsKey("sqale_rating")) {
			nutshellMeasures
					.setSqaleRating(Rating.valueOfDouble(NumberUtils.toDouble(measuresMap.get("sqale_rating"))));
		}

		if (measuresMap.containsKey("code_smells")) {
			nutshellMeasures.setCodeSmells(new BigInteger(measuresMap.get("code_smells")));
		}

		if (measuresMap.containsKey("coverage")) {
			nutshellMeasures.setCoverage(new BigDecimal(measuresMap.get("coverage")));
		}

		if (measuresMap.containsKey("duplicated_lines_density")) {
			nutshellMeasures.setDuplicatedLinesDensity(new BigDecimal(measuresMap.get("duplicated_lines_density")));
		}

		if (measuresMap.containsKey("ncloc")) {
			nutshellMeasures.setLinesOfCode(new BigInteger(measuresMap.get("ncloc")));
		}
		return nutshellMeasures;
	}

	// public static List<LeakMeasure> buildLeakMeasures(final
	// List<com.github.sansp00.maven.sonarqube.gateway.model.Measure> measures)
	// {
	// final Map<LocalDateTime, LeakMeasure> leakMeasureDetailByDateTime = new
	// TreeMap<>();
	//
	// for (com.github.sansp00.maven.sonarqube.gateway.model.Measure measure :
	// measures) {
	// for (com.github.sansp00.maven.sonarqube.gateway.model.History history :
	// measure.getHistory()) {
	// if (history.isSetValue()) {
	// if (!leakMeasureDetailByDateTime.containsKey(history.getDate())) {
	// final LeakMeasure leakMeasure = new
	// LeakMeasure().withAnalysisDate(history.getDate());
	// initLeakMeasure(leakMeasure);
	// leakMeasureDetailByDateTime.put(history.getDate(), leakMeasure);
	// }
	//
	// final LeakMeasure leakMeasure =
	// leakMeasureDetailByDateTime.get(history.getDate());
	//
	// switch (measure.getMetric()) {
	// case "new_reliability_rating":
	// leakMeasure.setReliabilityRating(Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
	// break;
	// case "new_bugs":
	// leakMeasure.setBugs(new BigInteger(history.getValue()));
	// break;
	// case "new_security_rating":
	// leakMeasure.setSecurityRating(Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
	// break;
	// case "new_vulnerabilities":
	// leakMeasure.setVulnerabilities(new BigInteger(history.getValue()));
	// break;
	// case "new_maintainability_rating":
	// leakMeasure.setMaintainabilityRating(Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
	// break;
	// case "new_code_smells":
	// leakMeasure.setCodeSmells(new BigInteger(history.getValue()));
	// break;
	// case "new_coverage":
	// leakMeasure.setCoverage(new BigDecimal(history.getValue()));
	// break;
	// case "new_duplicated_lines_density":
	// leakMeasure.setDuplicatedLinesDensity(new
	// BigDecimal(history.getValue()));
	// break;
	// case "ncloc":
	// leakMeasure.setLinesOfCode(new BigInteger(history.getValue()));
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// }
	// return new ArrayList<LeakMeasure>(leakMeasureDetailByDateTime.values());
	// }

	public static List<HistoryMeasures> buildHistoryMeasures(
			final List<com.github.sansp00.maven.sonarqube.gateway.model.Measure> measures) {
		final Map<LocalDateTime, HistoryMeasures> historyMeasureDetailByDateTime = new TreeMap<>();

		for (com.github.sansp00.maven.sonarqube.gateway.model.Measure measure : measures) {
			for (com.github.sansp00.maven.sonarqube.gateway.model.History history : measure.getHistory()) {
				if (history.isSetValue()) {
					if (!historyMeasureDetailByDateTime.containsKey(history.getDate())) {
						final HistoryMeasures leakMeasure = new HistoryMeasures().withAnalysisDate(history.getDate());
						initHistoryMeasure(leakMeasure);
						historyMeasureDetailByDateTime.put(history.getDate(), leakMeasure);
					}

					final HistoryMeasures leakMeasure = historyMeasureDetailByDateTime.get(history.getDate());

					switch (measure.getMetric()) {
					case "reliability_rating":
						leakMeasure
								.setReliabilityRating(Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
						break;
					case "bugs":
						leakMeasure.setBugs(new BigInteger(history.getValue()));
						break;
					case "security_rating":
						leakMeasure.setSecurityRating(Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
						break;
					case "vulnerabilities":
						leakMeasure.setVulnerabilities(new BigInteger(history.getValue()));
						break;
					case "sqale_rating":
						leakMeasure.setMaintainabilityRating(
								Rating.valueOfDouble(NumberUtils.toDouble(history.getValue())));
						break;
					case "code_smells":
						leakMeasure.setCodeSmells(new BigInteger(history.getValue()));
						break;
					case "coverage":
						leakMeasure.setCoverage(new BigDecimal(history.getValue()));
						break;
					case "duplicated_lines_density":
						leakMeasure.setDuplicatedLinesDensity(new BigDecimal(history.getValue()));
						break;
					case "ncloc":
						leakMeasure.setLinesOfCode(new BigInteger(history.getValue()));
						break;
					default:
						break;
					}
				}
			}
		}
		return new ArrayList<HistoryMeasures>(historyMeasureDetailByDateTime.values());
	}

	static void initHistoryMeasure(final HistoryMeasures historyMeasure) {
		historyMeasure.setBugs(BigInteger.ZERO);
		historyMeasure.setCodeSmells(BigInteger.ZERO);
		historyMeasure.setCoverage(BigDecimal.valueOf(100l));
		historyMeasure.setDuplicatedLinesDensity(BigDecimal.ZERO);
		historyMeasure.setLinesOfCode(BigInteger.ZERO);
		historyMeasure.setMaintainabilityRating(Rating.UNDEFINED);
		historyMeasure.setReliabilityRating(Rating.UNDEFINED);
		historyMeasure.setSecurityRating(Rating.UNDEFINED);
		historyMeasure.setVulnerabilities(BigInteger.ZERO);
	}

	static void initNutshellMeasure(final NutshellMeasures nutshellMeasure) {
		nutshellMeasure.setBugs(BigInteger.ZERO);
		nutshellMeasure.setCodeSmells(BigInteger.ZERO);
		nutshellMeasure.setCoverage(BigDecimal.valueOf(100l));
		nutshellMeasure.setDuplicatedLinesDensity(BigDecimal.ZERO);
		nutshellMeasure.setLinesOfCode(BigInteger.ZERO);
		nutshellMeasure.setSqaleRating(Rating.UNDEFINED);
		nutshellMeasure.setReliabilityRating(Rating.UNDEFINED);
		nutshellMeasure.setSecurityRating(Rating.UNDEFINED);
		nutshellMeasure.setVulnerabilities(BigInteger.ZERO);
	}

	static Map<String, String> mapMeasures(
			final List<com.github.sansp00.maven.sonarqube.gateway.model.Measure> measures, int periodsIndex) {
		return measures.stream().collect(
				Collectors.toMap(com.github.sansp00.maven.sonarqube.gateway.model.Measure::getMetric, measure -> {
					String value = "";
					if (measure.isSetValue()) {
						return measure.getValue();
					} else if (measure.isSetPeriods()) {
						Map<Integer, String> periods = mapPeriods(measure.getPeriods());
						return periods.getOrDefault(periodsIndex, "");
					}
					return value;
				}));
	}

	static Map<Integer, String> mapPeriods(
			final List<com.github.sansp00.maven.sonarqube.gateway.model.Period> periods) {
		return periods.stream()
				.collect(Collectors.toMap(com.github.sansp00.maven.sonarqube.gateway.model.Period::getIndex,
						com.github.sansp00.maven.sonarqube.gateway.model.Period::getValue));
	}

}
