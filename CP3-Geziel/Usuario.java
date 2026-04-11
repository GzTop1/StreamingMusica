import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario(String nome) {
        setNome(nome);
        this.playlists = new ArrayList<>();
    }

    public Usuario() {
        this("Visitante");
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public void criarPlaylist(String nomePlaylist) {
        try {
            Playlist novaPlaylist = new Playlist(nomePlaylist); 
            this.playlists.add(novaPlaylist);
            System.out.println("✅ Playlist '" + novaPlaylist.getNome() + "' criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro ao criar playlist: " + e.getMessage());
        }
    }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        return null;
    }

    public void listarPlaylists() {
        if (this.playlists.isEmpty()) {
            System.out.println("Você ainda não tem playlists criadas.");
            return;
        }
        for (int i = 0; i < this.playlists.size(); i++) {
            Playlist p = this.playlists.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " (" + p.getQuantidadeMusicas() + " músicas)");
        }
    }
    
    public int getQuantidadePlaylists() {
        return this.playlists.size();
    }
}