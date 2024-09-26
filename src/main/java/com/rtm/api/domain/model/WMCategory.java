package com.rtm.api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WMCategory
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	private Long productId;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String type;
	private String example;
	private String validateExpression;
}
