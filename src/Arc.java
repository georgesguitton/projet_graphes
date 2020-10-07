
public class Arc {

	private int extremiteinitial;
	private int extremitefinal;
	private int valeurarc;
	
	public Arc(int extremiteinitial, int extremitefinal, int valeurarc) {
		this.extremiteinitial = extremiteinitial;
		this.extremitefinal = extremitefinal;
		this.valeurarc = valeurarc;
	}
	
	public Arc() {
		
	}

	public int getExtremiteinitial() {
		return extremiteinitial;
	}

	public void setExtremiteinitial(int extremiteinitial) {
		this.extremiteinitial = extremiteinitial;
	}

	public int getExtremitefinal() {
		return extremitefinal;
	}

	public void setExtremitefinal(int extremitefinal) {
		this.extremitefinal = extremitefinal;
	}

	public int getValeurarc() {
		return valeurarc;
	}

	public void setValeurarc(int valeurarc) {
		this.valeurarc = valeurarc;
	}
	
	public void affichersommet() {
		System.out.println("extremite initial : "+ this.extremiteinitial);
		System.out.println("extremite final : "+ this.extremitefinal);
		System.out.println("valeur arc : "+ this.valeurarc + "\n");
	}
}
