import java.util.ArrayList;

public class Playlist {

    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            musicas.remove(indice);
        }
    }

    void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música nesta playlist.");
            return;
        }
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    int getDuracaoTotal() {
        int total = 0;
        for (int i = 0; i < musicas.size(); i++) {
            total += musicas.get(i).duracaoSegundos;
        }
        return total;
    }

    int getQuantidadeMusicas() {
        return musicas.size();
    }
}