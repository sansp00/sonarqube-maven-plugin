package com.github.sansp00.maven.sonarqube.gateway.model.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface TextRangeMapper {
	public com.github.sansp00.maven.sonarqube.model.TextRange map(final com.github.sansp00.maven.sonarqube.gateway.model.TextRange source);
}
