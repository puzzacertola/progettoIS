package pub.entita;

public class Ordinazione {
	private int quantita;
	private float costo;
	private String nome;
	
	public Ordinazione(int quantita, float costo, String nome){
		this.quantita = quantita;
		this.costo = costo;
		this.nome = nome;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
