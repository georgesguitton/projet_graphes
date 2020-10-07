import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		Graphe test1 = new Graphe();
		
		System.out.println("Le graphe test1 possède : " + test1.getNombresommets() + " sommets.");
		System.out.println("Le graphe test1 possède : " + test1.getNombrearcs() + " arcs.\n");
		
		int compteurarc = 1;
		
		for(int i=0;i<test1.getArcs().size();i++) {
			System.out.println("Arc numéro " + compteurarc );
			test1.getArcs().get(i).affichersommet();
			compteurarc += 1;
		}
	}
}
