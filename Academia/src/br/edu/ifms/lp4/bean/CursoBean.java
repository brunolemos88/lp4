package br.edu.ifms.lp4.bean;

import java.util.List;

import br.edu.ifms.lp4.jpa.dao.CursoDao;
import br.edu.ifms.lp4.modelo.Curso;

public class CursoBean {
	private List<Curso> equipamentos;
	private CursoDao cursoDao = new CursoDao();

	public List<Curso> getModalidades() {
		equipamentos = cursoDao.recuperaTodos();
		return equipamentos;
	}

	public boolean salvaModalidade(Curso modalidade) {
		return cursoDao.salva(modalidade) != null ? true : false;
	}

	public boolean deletaModalidade(Curso modalidade) {
		return cursoDao.remove(modalidade);
	}

	public Curso recuperaModalidade(Integer id) {
		return cursoDao.recupera(id);
	}
}