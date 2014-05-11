package br.edu.ifms.lp4.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.lp4.bean.ModalidadeBean;
import br.edu.ifms.lp4.modelo.Modalidade;

@WebServlet(name = "modalidade", urlPatterns = { "/modalidade" })
public class ModalidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String mensagem = "";
	private String classeCSS = "";

	private ModalidadeBean bean = new ModalidadeBean();
	private Modalidade modalidade;

	public ModalidadeServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			switch (acao) {
			case "salva":
				String descricao = request.getParameter("descricao");
				response.sendRedirect(salva(descricao));
				break;
			case "remove":
				Integer id = Integer.parseInt(request.getParameter("id"));
				response.sendRedirect(remove(id));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}

	}

	protected String remove(Integer id) {

		modalidade = new Modalidade();

		if (bean.deletaModalidade(id)) {
			mensagem = "Removido com sucesso!";
			classeCSS = "alert alert-success";
		} else {
			mensagem = "Erro ao remover!";
			classeCSS = "alert alert-error";
		}
		return resposta(mensagem, classeCSS);

	}

	protected String salva(String descricao) {

		modalidade = new Modalidade();
		modalidade.setDescricao(descricao);

		if (bean.salvaModalidade(modalidade)) {
			mensagem = "Salvo com sucesso!";
			classeCSS = "alert alert-success";
		} else {
			classeCSS = "alert alert-error";
			mensagem = "Ocorreu um erro ao tentar realizar o cadastro.";
		}

		return resposta(mensagem, classeCSS);
	}

	protected String resposta(String mensagem, String classResposta) {
		return "index.jsp?pagina=modalidades&resposta=" + mensagem
				+ "&classResposta=" + classeCSS;
	}

}
