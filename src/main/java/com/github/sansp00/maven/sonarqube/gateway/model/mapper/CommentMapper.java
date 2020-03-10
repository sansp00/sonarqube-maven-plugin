package com.github.sansp00.maven.sonarqube.gateway.model.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
	public com.github.sansp00.maven.sonarqube.model.Comment map(final com.github.sansp00.maven.sonarqube.gateway.model.Comment source);
}
