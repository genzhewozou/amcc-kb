package com.nroad.amcc.kb;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "`knowledge_base`")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KnowledgeBase {

	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Id
	private String id;

	private String parentId;

	private String organizationId;

	private String knowledegName;

	@Transient
	private List<KnowledgeBase> children;

	private String knowledegLevel;

	@Column(columnDefinition="TEXT")
	private String knowledegContent;

	public List<KnowledgeBase> getChildren() {
		return children;
	}

	public void setChildren(List<KnowledgeBase> children) {
		this.children = children;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKnowledegName() {
		return knowledegName;
	}

	public void setKnowledegName(String knowledegName) {
		this.knowledegName = knowledegName;
	}

	public String getKnowledegLevel() {
		return knowledegLevel;
	}

	public void setKnowledegLevel(String knowledegLevel) {
		this.knowledegLevel = knowledegLevel;
	}

	public String getKnowledegContent() {
		return knowledegContent;
	}

	public void setKnowledegContent(String knowledegContent) {
		this.knowledegContent = knowledegContent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "KnowledgeBase{" +
				"id='" + id + '\'' +
				", parentId='" + parentId + '\'' +
				", organizationId='" + organizationId + '\'' +
				", knowledegName='" + knowledegName + '\'' +
				", children=" + children +
				", knowledegLevel='" + knowledegLevel + '\'' +
				", knowledegContent='" + knowledegContent + '\'' +
				'}';
	}
}
