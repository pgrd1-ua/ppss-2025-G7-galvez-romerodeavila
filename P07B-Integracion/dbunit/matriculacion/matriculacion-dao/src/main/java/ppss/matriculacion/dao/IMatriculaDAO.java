package ppss.matriculacion.dao;

import ppss.matriculacion.to.AlumnoTO;
import ppss.matriculacion.to.AsignaturaTO;

import java.util.List;

public interface IMatriculaDAO {

	public boolean existeMatricula(AlumnoTO alumno, AsignaturaTO asignatura) throws DAOException;
	public void altaMatricula(AlumnoTO alumno, AsignaturaTO asignatura) throws DAOException;
	public void delMatricula(AlumnoTO alumno, AsignaturaTO asignatura) throws DAOException;
	public List<AsignaturaTO> getAsignaturas(AlumnoTO alumno) throws DAOException;
	public List<AlumnoTO> getAlumnos(AsignaturaTO alumno) throws DAOException;
	
}
