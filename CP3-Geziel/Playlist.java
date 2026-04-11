import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public Playlist() {
        this("Playlist Sem Nome");
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da playlist não pode ser vazio.");
        }
        this.nome = nome.trim();
    }
    
    public ArrayList<Musica> getMusicas() {
        return new ArrayList<>(this.musicas);
    }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma música nula.");
        }
        this.musicas.add(musica);
        System.out.println("✅ Música adicionada à playlist '" + this.nome + "'!");
    }

    public void removerMusica(int indice) {
        if (indice < 0 || indice >= this.musicas.size()) {
            throw new IllegalArgumentException("Índice de música inválido para remoção.");
        }
        this.musicas.remove(indice);
        System.out.println("✅ Música removida da playlist!");
    }

    public void listarMusicas() {
        if (this.musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
            return;
        }
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            this.musicas.get(i).exibir();
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : this.musicas) {
            total += m.getDuracaoSegundos(); 
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}