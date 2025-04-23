package ppss.matriculacion.dao;

import ppss.matriculacion.to.AsignaturaTO;

import java.util.List;

public interface IAsignaturaDAO {

	public abstract AsignaturaTO getAsignatura(int cod) throws DAOException;
	public abstract List<AsignaturaTO> getAsignaturas() throws DAOException;
	public abstract void delAsignatura(String cod) throws DAOException;
	public abstract void addAsignatura(AsignaturaTO asignatura) throws DAOException;
	public abstract AsignaturaTO buscaAsignatura(String nombre) throws DAOException;

}
