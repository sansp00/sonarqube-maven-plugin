package com.github.sansp00.maven.sonarqube.gateway.model.mapper;

import org.mapstruct.Mapper;

@Mapper(uses = { AttrMapper.class, CommentMapper.class, TextRangeMapper.class })
public interface IssueMapper {

	public com.github.sansp00.maven.sonarqube.model.Issue map(
			final com.github.sansp00.maven.sonarqube.gateway.model.Issue source);
}
