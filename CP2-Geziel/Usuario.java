import java.util.ArrayList;

public class Usuario {

    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    void criarPlaylist(String nomePlaylist) {
        Playlist novaPlaylist = new Playlist();
        novaPlaylist.nome = nomePlaylist;
        // A lista de músicas já é instanciada direto na classe Playlist
        playlists.add(novaPlaylist);
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Você ainda não tem playlists.");
            return;
        }
        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            System.out.println((i + 1) + ". " + p.nome + " (" + p.getQuantidadeMusicas() + " músicas)");
        }
    }
}