package org.isfce.pid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString//(exclude = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="TPROFESSEUR")
public class Professeur {
	@Id
	@Column(length = 5, nullable = false)
	private String username;
	@Column(length = 40, nullable = false)
	@NotBlank
	private String nom;
	@Column(length = 40, nullable = false)
	@NotBlank
	private String prenom;
	@Setter
	@Column(length = 50, nullable = false)
	@Email
	private String email;
}
