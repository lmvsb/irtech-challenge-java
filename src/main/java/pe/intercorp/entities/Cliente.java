package pe.intercorp.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.google.firebase.database.annotations.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Cliente {

	@NotNull
	private String nombre;

	@NotNull
	private String apellido;

	@NotNull
	private Integer edad;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;

}
