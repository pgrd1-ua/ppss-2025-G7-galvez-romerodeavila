package ppss.matriculacion.bo;

import java.util.List;
import ppss.matriculacion.dao.FactoriaDAO;
import ppss.matriculacion.proxy.ProxyDatosEconomicos;
import ppss.matriculacion.to.*;

public class MatriculaBO {

	protected FactoriaDAO getFactoriaDAO() {
		return new FactoriaDAO();
	}

	protected ProxyDatosEconomicos getProxyDatosEconomicos() {
		return new ProxyDatosEconomicos();
	}

	protected AlumnoBR getAlumnoBR() {
		return new AlumnoBR();
	}

	protected MatriculaBR getMatriculaBR() {
		return new MatriculaBR();
	}

	public MatriculaTO matriculaAlumno(AlumnoTO alumno,
			List<AsignaturaTO> asignaturas) throws BOException {

		return null;
	}

	public ReciboMatriculaTO generaReciboMatricula(AlumnoTO alumno)
			throws BOException {

		return null;
	}
}
