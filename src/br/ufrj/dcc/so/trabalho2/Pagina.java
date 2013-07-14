package br.ufrj.dcc.so.trabalho2;

public class Pagina {

	
	private final Processo processo;
	private final int numPagina;
	
	
	public Pagina(Processo processo, int numPagina) {
		super();
		this.processo = processo;
		this.numPagina = numPagina;
	}


	
	@Override
	public String toString() {
		return "Pg" + numPagina;
	}
	
	
	public Processo getProcesso() {
		return processo;
	}

	public int getNumPagina() {
		return numPagina;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numPagina;
		result = prime * result
				+ ((processo == null) ? 0 : processo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagina other = (Pagina) obj;
		if (numPagina != other.numPagina)
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
			return false;
		return true;
	}
	
	
	
	
	
}
