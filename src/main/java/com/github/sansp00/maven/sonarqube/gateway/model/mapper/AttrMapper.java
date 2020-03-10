package com.github.sansp00.maven.sonarqube.gateway.model.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface AttrMapper {
	public com.github.sansp00.maven.sonarqube.model.Attr map(final com.github.sansp00.maven.sonarqube.gateway.model.Attr source);
}
