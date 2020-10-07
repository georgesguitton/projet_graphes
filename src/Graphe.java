import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graphe {

	private int nombresommets;
	private int nombrearcs;
	final private ArrayList<Arc> arcs;
	
	public Graphe() throws IOException {
		int compteurligne = 1;																// compteurligne permet de connaître le numéro de la ligne courante.
		int compteurarc = 1;																// compteurarc permet de connaître le numéro de l'arc courant.
		
		BufferedReader in = new BufferedReader(new FileReader("src/graphe.txt"));			// On place le lecteur sur le fichier texte du graphe.
		String line;																		// line correspond au contenu de la ligne lue.
		
		arcs = new ArrayList<Arc>();
		
		while ((line = in.readLine()) != null)												// La boucle while permet de lire les lignes une par une.
		{
			if (compteurligne==1) {															// On lit le nombre de sommets.
				this.nombresommets = Integer.parseInt(line);
			}
			else if(compteurligne==2) {														// On lit le nombre d'arcs.
				this.nombrearcs = Integer.parseInt(line);
			}
			else {
				Arc arc = new Arc();
				int i = 0;
				String split[] = line.split(" "); 
				for(String s:split) {
					if(i==0) {																// On traite l'extrémité initial de l'arc.
						arc.setExtremiteinitial(Integer.parseInt(s));
					}
					else if(i==1) {															// On traite l'extrémité final de l'arc.
						arc.setExtremitefinal(Integer.parseInt(s));
					}
					else if(i==2) {															// On traite la valeur de l'arc.
						arc.setValeurarc(Integer.parseInt(s));
					}
					i++;
				}
				System.out.println("Ajout de l'arc " + compteurarc + "\n");
				arcs.add(arc);
				compteurarc += 1;
			}
			compteurligne += 1;																// On incrémente compteurligne lorsque la lecture de la ligne est finie.
		}
		in.close();																			// On ferme le lecteur.
	}

	public int getNombresommets() {															// getNombresommets() permet de récupérer le nombre de sommets du graphe.
		return nombresommets;
	}

	public int getNombrearcs() {															// getNombresommets() permet de récupérer le nombre d'arcs du graphe.
		return nombrearcs;
	}

	public ArrayList<Arc> getArcs() {														// getSommets permet de récupérer la liste des arcs du graphe!;
		return arcs;
	}	
	
}
